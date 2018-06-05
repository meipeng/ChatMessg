package hztywl.com.chat;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by ${梅鹏} on 2018/2/8.
 * @author meipeng
 */

public class SampleApplication extends TinkerApplication {

    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "hztywl.com.chat.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
