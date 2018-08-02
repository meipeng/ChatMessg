package hztywl.com.chat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.Serializable;
import java.util.List;

import hztywl.com.chat.R;
import hztywl.com.chat.activity.ImageCarouselActivity;
import hztywl.com.chat.application.SampleApplication;
import hztywl.com.chat.bean.MessageInfo;

/**
 * Created by ${梅鹏} on 2018/1/27.
 * 聊天适配器
 *
 * @author meipeng
 */

public class ChatMessageAdapter extends BaseMultiItemQuickAdapter<MessageInfo, BaseViewHolder> {

    private Context mContext;
    private final int IMAGE_ROUND_VALUE = 8;


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ChatMessageAdapter(Context context, List<MessageInfo> data) {
        super(data);
        mContext = context;

        addItemType(MessageInfo.OUT_TEXT, R.layout.item_outchat_text);
        addItemType(MessageInfo.OUT_IMG, R.layout.item_outchat_image);
        addItemType(MessageInfo.IN_TEXT, R.layout.item_incomingchat_text);
        addItemType(MessageInfo.IN_IMG, R.layout.item_incomingchat_image);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MessageInfo item) {

        switch (helper.getItemViewType()) {
            case MessageInfo.OUT_TEXT:
                helper.setText(R.id.tv_out_text, item.getText());

                break;
            case MessageInfo.OUT_IMG:

                SampleApplication.getGlideLoader().displayImageRound(item.getMessageImage().
                                getImageSmallFile(),
                        (ImageView) helper.getView(R.id.iv_out), IMAGE_ROUND_VALUE);
                helper.getView(R.id.iv_out).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(mContext, ImageCarouselActivity.class);
                        intent.putExtra("imglist", (Serializable) getData());
                        intent.putExtra("item", item);
                        mContext.startActivity(intent);
                    }
                });
                break;
            case MessageInfo.IN_TEXT:
                helper.setText(R.id.tv_in_text, item.getText());

                break;
            case MessageInfo.IN_IMG:
                SampleApplication.getGlideLoader().displayImageRound(item.getMessageImage()
                        .getImageSmallFile(), (ImageView) helper.getView(R.id.iv_in), 10);
                break;
            default:
                break;
        }
    }

    /**
     * 判断消息状态
     */
    private void messageState(){

    }

}