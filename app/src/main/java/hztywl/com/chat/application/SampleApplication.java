package hztywl.com.chat.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import hztywl.com.chat.loader.GlideLoader;

/**
 * Created by ${梅鹏} on 2018/2/8.
 *
 * @author meipeng
 */

public class SampleApplication extends Application {

    public static final String TAG = "Tinker.SampleApplicationLike";
    private static GlideLoader glideLoader;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        mContext = getApplicationContext();
        glideLoader = new GlideLoader();
    }

    public static GlideLoader getGlideLoader() {
        return glideLoader;
    }

    public static Context getContext() {
        return mContext;
    }
}
