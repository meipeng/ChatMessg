package hztywl.com.chat.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.nanchen.compresshelper.CompressHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.dreamtobe.kpswitch.util.KPSwitchConflictUtil;
import cn.dreamtobe.kpswitch.util.KeyboardUtil;
import cn.dreamtobe.kpswitch.widget.KPSwitchPanelLinearLayout;
import hztywl.com.chat.R;
import hztywl.com.chat.SampleApplicationLike;
import hztywl.com.chat.adapter.ChatMessageAdapter;
import hztywl.com.chat.bean.MessageImage;
import hztywl.com.chat.bean.MessageInfo;
import hztywl.com.chat.util.BitmapUtil;

/**
 * @author meipeng
 */
public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, View.OnTouchListener {
    /**
     * 设置相册选中最大的数量
     */
    private final int IMAGE_SELECTLIMIT = 9;
    /**
     * 相册
     */
    public static final int IMAGE_PICKER = 200;
    /**
     * 拍照
     */
    public static final int REQUEST_CODE_SELECT = 100;
    /**
     * 相册工具
     */
    private ImagePicker mImagePicker;
    /**
     * 消息显示列表
     */
    private RecyclerView recyclerViewChat;

    private ImageView mPlusIv;
    private KPSwitchPanelLinearLayout mPanelRoot;
    private TextView mSendImgTv, mPanelmgTv, mPlusTv;
    private EditText mSendEdt;
    /**
     * 聊天的适配器
     */
    private ChatMessageAdapter messageAdapter;
    private final int ChatMessageAdapterCount = 0;
    /**
     * 设置原图大小的缩小比例
     */
    private final int IMAGE_SAMPLESIZE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initOnClick();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initOnClick() {
        recyclerViewChat.setOnTouchListener(this);
        KPSwitchConflictUtil.attach(mPanelRoot, mPlusIv, mSendEdt,
                new KPSwitchConflictUtil.SwitchClickListener() {
                    @Override
                    public void onClickSwitch(boolean switchToPanel) {
                        if (switchToPanel) {
                            mSendEdt.clearFocus();
                        } else {
                            mSendEdt.requestFocus();
                        }
                    }
                });


        KeyboardUtil.attach(this, mPanelRoot,
                new KeyboardUtil.OnKeyboardShowingListener() {
                    @Override
                    public void onKeyboardShowing(boolean isShowing) {
                    }
                });


        recyclerViewChat.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {

                if (bottom < oldBottom) {
                    recyclerViewChat.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerViewChat.scrollToPosition(messageAdapter.getItemCount() - 1);
                        }
                    }, 0);
                }
            }
        });


        mSendEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > Activity.RESULT_CANCELED) {
                    mPlusTv.setVisibility(View.VISIBLE);
                    mPlusIv.setVisibility(View.GONE);
                } else {
                    mPlusTv.setVisibility(View.GONE);
                    mPlusIv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {


        recyclerViewChat = (RecyclerView) findViewById(R.id.rl_chat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewChat.setLayoutManager(layoutManager);
        recyclerViewChat.setItemAnimator(new DefaultItemAnimator());

        mPanelRoot = (KPSwitchPanelLinearLayout) findViewById(R.id.panel_root);
        mPlusIv = (ImageView) findViewById(R.id.plus_iv);
        mSendEdt = (EditText) findViewById(R.id.send_edt);
        mSendImgTv = (TextView) findViewById(R.id.send_img_tv);
        mPanelmgTv = (TextView) findViewById(R.id.panel_img_tv);

        mSendEdt.setOnTouchListener(this);
        mSendImgTv.setOnClickListener(this);
        mPanelmgTv.setOnClickListener(this);

        mPlusTv = (TextView) findViewById(R.id.plus_tv);

        mPlusTv.setOnClickListener(this);
        //加载数据
        messageAdapter = new ChatMessageAdapter(this, getData());
        recyclerViewChat.setAdapter(messageAdapter);
        //配置相册
        configureImagePicker();
    }

    /**
     * 配置相册
     */
    private void configureImagePicker() {
        mImagePicker = ImagePicker.getInstance();
        //设置图片加载器
        mImagePicker.setImageLoader(SampleApplicationLike.getGlideLoader());
        //选中数量限制
        mImagePicker.setSelectLimit(IMAGE_SELECTLIMIT);
        // 设置是否裁剪图片
        mImagePicker.setCrop(false);
        //选择相册是否开启拍照
        mImagePicker.setShowCamera(false);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP &&
                event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mPanelRoot.getVisibility() == View.VISIBLE) {
                KPSwitchConflictUtil.hidePanelAndKeyboard(mPanelRoot);
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * @return 添加数据源
     */
    public List<MessageInfo> getData() {
        List<MessageInfo> messageInfoList = new ArrayList<>();
        for (int i = 0; i < ChatMessageAdapterCount; i++) {
            if (i == 0) {
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setItemType(MessageInfo.IN_TEXT);
                messageInfo.setText("我来啦 接收一个消息吧" + i);
                messageInfoList.add(messageInfo);
            } else if (i == 1) {
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setItemType(MessageInfo.OUT_TEXT);
                messageInfo.setText("你来啦" + i);
                messageInfoList.add(messageInfo);
            }
        }

        return messageInfoList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus_tv:
                //发送文字
                addMessageText();
                break;
            case R.id.send_img_tv:
                //选择照片
                selectionAlbums();
                break;
            case R.id.panel_img_tv:
                //拍照
                startPhotograph();
                break;
            default:
                break;
        }
    }

    /**
     * 发送文字
     */
    private void addMessageText() {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setItemType(MessageInfo.OUT_TEXT);
        messageInfo.setText(mSendEdt.getText().toString());
        messageAdapter.addData(messageInfo);


        MessageInfo messageInfo1 = new MessageInfo();
        messageInfo1.setItemType(MessageInfo.IN_TEXT);
        messageInfo1.setText(mSendEdt.getText().toString());
        messageAdapter.addData(messageInfo1);

        recyclerViewChat.scrollToPosition(messageAdapter.getItemCount() - 1);
        mSendEdt.setText("");
    }

    /**
     * 选择相册
     */
    private void selectionAlbums() {
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, IMAGE_PICKER);
    }

    /**
     * 拍照
     */
    private void startPhotograph() {
        Intent intent = new Intent(this, ImageGridActivity.class);
        // 是否是直接打开相机
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true);
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.rl_chat:
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    KPSwitchConflictUtil.hidePanelAndKeyboard(mPanelRoot);
                }
                break;
            case R.id.send_edt:
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    recyclerViewChat.scrollToPosition(messageAdapter.getItemCount() - 1);
                }
                break;
            default:
                break;
        }
        return false;
    }


    /**
     * 图片的回调单选或多选
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS && data != null) {
            if (requestCode == IMAGE_PICKER || requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>)
                        data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                for (ImageItem item : images) {
                    //返回图片路径
                    addMessageImage(item.path);
                }
                recyclerViewChat.scrollToPosition(messageAdapter.getItemCount() - 1);
            }
        }
    }


    /**
     * 通过路径添加图片
     *
     * @param path
     */
    private void addMessageImage(String path) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setItemType(MessageInfo.OUT_IMG);

        MessageImage messageImage = new MessageImage();
        messageImage.setImageSdFile(new File(path));
        messageImage.setImageFile(configureCompress(path));
        messageImage.setImageSmallFile(configImageBitmap
                (messageImage.getImageFile()));

        messageInfo.setMessageImage(messageImage);
        messageAdapter.addData(messageInfo);
    }

    /**
     * @return 返回压缩后的文件
     */
    private File configureCompress(String imagePath) {
        File compressFile;
        compressFile = CompressHelper.getDefault(SampleApplicationLike.getContext()).
                compressToFile(new File(imagePath));
        return compressFile;
    }


    /**
     * @param file 传入原图文件
     * @return 将图片的长和宽缩小味原来的1/2
     */
    private File configImageBitmap(File file) {

        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),
                BitmapUtil.getBitmapOption(IMAGE_SAMPLESIZE));

        return new File(BitmapUtil.saveBitmap(this, bitmap));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
