package hztywl.com.chat.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import hztywl.com.chat.R;
import hztywl.com.chat.bean.UserInfo;

/**
 * Created by ${梅鹏} on 2018/6/11.
 * 登录
 *
 * @author meipeng
 */

@SuppressLint("Registered")
public class LoginActivity extends AppCompatActivity {

    EditText edName, edPwd;
    Button btLogin;
    private final String BmobId = "c0b84afb158f3a8d34c5aeaeedbbf50c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //第一：默认初始化
        Bmob.initialize(this, BmobId);
        initView();
        initClick();
    }

    private void initClick() {
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void initView() {
        edName = (EditText) findViewById(R.id.edName);
        edPwd = (EditText) findViewById(R.id.edPwd);
        btLogin = (Button) findViewById(R.id.btLogin);
    }


    private void login() {
        //模糊查询居然要钱 没有办法只能写死
        BmobQuery<UserInfo> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(this, "Getm222C", new GetListener<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfos) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(int i, String s) {
            }
        });
    }
}
