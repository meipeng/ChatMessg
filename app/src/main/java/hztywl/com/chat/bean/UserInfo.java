package hztywl.com.chat.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by ${梅鹏} on 2018/6/11.
 *
 * @author meipeng
 *         用户信息
 */

public class UserInfo extends BmobObject {

    private String name;
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
