package hztywl.com.chat.bean;

import java.io.File;
import java.io.Serializable;

/**
 * Created by ${梅鹏} on 2018/2/7.
 * 消息信息图片
 *
 * @author meipeng
 */

public class MessageImage implements Serializable {


    /**
     * 网络图片地址信息
     */
    private String imageNetUrl;

    /**
     * 原始的本地图片信息
     */
    private File imageSdFile;

    /**
     * 压缩后图片文件
     * 放大的图片就是这个
     */
    private File imageFile;

    /**
     * 显示原图1/2文件的文件
     * 一开始显示的就是这个
     */
    private File imageSmallFile;


    public String getImageNetUrl() {
        return imageNetUrl;
    }

    public void setImageNetUrl(String imageNetUrl) {
        this.imageNetUrl = imageNetUrl;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public File getImageSdFile() {
        return imageSdFile;
    }

    public void setImageSdFile(File imageSdFile) {
        this.imageSdFile = imageSdFile;
    }

    public File getImageSmallFile() {
        return imageSmallFile;
    }

    public void setImageSmallFile(File imageSmallFile) {
        this.imageSmallFile = imageSmallFile;
    }
}
