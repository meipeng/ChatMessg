package hztywl.com.chat.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import hztywl.com.chat.R;
import hztywl.com.chat.bean.UserInfo;

/**
 * Created by ${梅鹏} on 2018/6/11
 * 消息首页
 *
 * @author meipeng
 */

public class MainMessageAdapter extends BaseQuickAdapter<UserInfo, BaseViewHolder> {

    public MainMessageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserInfo item) {
        helper.setText(R.id.name, item.getName());
    }
}
