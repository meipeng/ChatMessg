package hztywl.com.chat.activity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hztywl.com.chat.R;


/**
 * @author meipeng
 */
@SuppressLint("ValidFragment")
public class MyFragment extends Fragment {
    private String mTitle;

    public static MyFragment getInstance(String title) {
        MyFragment sf = new MyFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my, null);

        return v;
    }
}