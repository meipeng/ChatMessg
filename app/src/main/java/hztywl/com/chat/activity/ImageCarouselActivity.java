package hztywl.com.chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.bm.library.PhotoView;

import java.util.ArrayList;
import java.util.List;

import hztywl.com.chat.R;
import hztywl.com.chat.application.SampleApplicationLike;
import hztywl.com.chat.bean.MessageImage;
import hztywl.com.chat.bean.MessageInfo;

/**
 * @author meipeng
 *         图片滑动查看
 */
public class ImageCarouselActivity extends Activity {


    private ViewPager mPager;
    private List<MessageImage> mImageList = new ArrayList<>();
    private final int PAGER_LIMIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_carousel);
        mPager = (ViewPager) findViewById(R.id.parer);

        List<MessageInfo> messageInfos = (List<MessageInfo>)
                getIntent().getSerializableExtra("imglist");

        getImageData(messageInfos);

        if (mImageList.size() > PAGER_LIMIT) {
            mPager.setOffscreenPageLimit(PAGER_LIMIT);
        }

        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        mPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mImageList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView photoView = new PhotoView(ImageCarouselActivity.this);
                photoView.enable();
                photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                SampleApplicationLike.getGlideLoader().displayImage(mImageList.get(position).getImageSdFile(), photoView);
                container.addView(photoView);
                return photoView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }


    /**
     * 返回Image集合
     *
     * @param messageInfos
     */
    private void getImageData(List<MessageInfo> messageInfos) {
        for (MessageInfo messageInfo : messageInfos) {
            if (messageInfo.getItemType() == MessageInfo.OUT_IMG ||
                    messageInfo.getItemType() == MessageInfo.IN_IMG) {
                mImageList.add(messageInfo.getMessageImage());
            }
        }
    }
}
