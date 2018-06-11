package hztywl.com.chat.loader;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

import hztywl.com.chat.R;
import hztywl.com.chat.application.SampleApplicationLike;

/**
 * Created by ${梅鹏} on 2018/2/1.
 *
 * @author meipeng
 */

public class GlideLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

        Glide.with(activity)
                .load(Uri.fromFile(new File(path)))
                .error(R.drawable.imageselector_photo)
                .placeholder(R.drawable.imageselector_photo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)
                //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .load(Uri.fromFile(new File(path)))
                .error(R.drawable.imageselector_photo)
                .placeholder(R.drawable.imageselector_photo)
                //缓存全尺寸
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


    public void displayImageCircle(File fileImage, ImageView imageView) {
        Glide.with(SampleApplicationLike.getContext())
                //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .load(fileImage)
                //缓存全尺寸
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.imageselector_photo)
                .placeholder(R.drawable.imageselector_photo)
                .transform(new GlideCircleTransform(SampleApplicationLike.getContext()))
                .into(imageView);
    }

    public void displayImageRound(File fileImage, ImageView imageView, int round) {
        Glide.with(SampleApplicationLike.getContext())
                //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .load(fileImage)
                //缓存全尺寸
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.imageselector_photo)
                .placeholder(R.drawable.imageselector_photo)
                .transform(new GlideRoundTransform(SampleApplicationLike.getContext(), round))
                .into(imageView);
    }

    public void displayImage(File fileImage, ImageView imageView) {
        Glide.with(SampleApplicationLike.getContext())
                //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .load(fileImage)
                //缓存全尺寸
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.imageselector_photo)
                .placeholder(R.drawable.imageselector_photo)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
        Glide.get(SampleApplicationLike.getContext()).clearMemory();
    }
}
