package hztywl.com.chat.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by ${梅鹏} on 2018/1/27.
 * 消息内容
 *
 * @author meipeng
 */

public class MessageInfo implements MultiItemEntity, Serializable {

    /**
     * 消息类型 文本、图片、语音
     */

    private int itemType;
    /**
     * 自己发送的消息
     */
    public static final int OUT_TEXT = 1;
    public static final int OUT_IMG = 2;
    public static final int OUT_VOICE = 3;

    /**
     * 接受的消息
     */
    public static final int IN_TEXT = -1;
    public static final int IN_IMG = -2;
    public static final int IN_VOICE = -3;


    /**
     * 文本内容
     */
    private String text;

    /**
     * 图片
     */
    private MessageImage messageImage;

    /**
     * 录音地址
     */
    private String voiceUrl;

    /**
     * 录音长度；
     */
    private float voiceLength;

    /**
     * 消息状态  0 成功 1发送中 2，发送失败 3错误
     * 默认一开始发送；
     */
    private String messageState = "1";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public float getVoiceLength() {
        return voiceLength;
    }

    public void setVoiceLength(float voiceLength) {
        this.voiceLength = voiceLength;
    }

    public String getMessageState() {
        return messageState;
    }

    public void setMessageState(String messageState) {
        this.messageState = messageState;
    }

    public MessageImage getMessageImage() {
        return messageImage;
    }

    public void setMessageImage(MessageImage messageImage) {
        this.messageImage = messageImage;
    }

    @Override
    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }


}
