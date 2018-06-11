package hztywl.com.chat.activity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hztywl.com.chat.R;


/**
 * @author meipeng
 *         联系人列表
 */
@SuppressLint("ValidFragment")
public class ContactsFragment extends Fragment {
    private String mTitle;

    public static ContactsFragment getInstance(String title) {
        ContactsFragment sf = new ContactsFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacts, null);

        TextView textView = (TextView) v.findViewById(R.id.tv);

        return v;
    }
}