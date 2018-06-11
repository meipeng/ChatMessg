package hztywl.com.chat.activity.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import hztywl.com.chat.R;
import hztywl.com.chat.activity.ChatActivity;
import hztywl.com.chat.adapter.MainMessageAdapter;
import hztywl.com.chat.bean.UserInfo;


/**
 * @author meipeng
 */
@SuppressLint("ValidFragment")
public class MainMessageFragment extends Fragment {

    private MainMessageAdapter messageAdapter;
    RecyclerView recycler;

    public static MainMessageFragment getInstance(String title) {
        MainMessageFragment sf = new MainMessageFragment();
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_message, null);
        initViews(v);
        initData();
        return v;
    }

    private void initViews(View v) {
        recycler = (RecyclerView) v.findViewById(R.id.recycler);
        messageAdapter = new MainMessageAdapter(R.layout.item_mainmessage);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(messageAdapter);
        messageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        BmobQuery<UserInfo> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(getActivity(), "CWUhDDDN", new GetListener<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                messageAdapter.addData(userInfo);
            }

            @Override
            public void onFailure(int i, String s) {
            }
        });
    }
}