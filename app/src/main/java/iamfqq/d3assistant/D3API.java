package iamfqq.d3assistant;

import android.provider.ContactsContract;
import android.widget.GridView;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by JuQiang on 2/25/2017.
 */

public class D3API {
    public static CareerProfile getProfile(String profileId) {
        final CareerProfile[] ret = new CareerProfile[1];

        ProfileTask pt = new ProfileTask(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {
                ret[0] = (CareerProfile) result;
            }
        });
        pt.execute(profileId);
        return ret[0];
    }

    public static void getItemJson(String tooltipParams){
        String tooltipUrl = "https://api.battlenet.com.cn/d3/data/"+tooltipParams;
        //https://api.battlenet.com.cn/d3/data/item/CogBCJbg5fAGEgcIBBVV4wJkHayvKGId0u_p7R3OgsHbHYaQ_NodZiMGUB1yjh0hMItSOIEBQABQElgEYKICaisKDAgAEK60-dyBgIDgNhIbCM_spKYOEgcIBBVCLTepMI9SOABAAVgEkAEJgAFGpQFyjh0hrQGMWTBHtQGqRZ8TuAGj0_CDCcABARiqjZfADFACWAA?locale=zh_CN&apikey=heef46sr5ue44xfdgwr4wrycckgawhu5
        SocketTask pt = new SocketTask(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {

            }
        });
        pt.execute(tooltipParams);
    }
}
