package iamfqq.d3assistant;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddFriendFromLeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend_from_leaderboard);

        Spinner spinnerModel = (Spinner) findViewById(R.id.spinnerModel);

        //数据
        ArrayList modelList = new ArrayList<String>();
        modelList.add("普通模式");
        modelList.add("专家级模式");
        modelList.add("赛季");
        modelList.add("专家级赛季");

        //适配器
        ArrayAdapter modelAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modelList);
        //设置样式
        modelAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice );
        //加载适配器
        spinnerModel.setAdapter(modelAdapter);

        Spinner spinnerClass = (Spinner) findViewById(R.id.spinnerClass);
        ArrayList classList = new ArrayList<String>();
        classList.add("野蛮人大秘境");
        classList.add("圣教军大秘境");
        classList.add("猎魔人大秘境");
        classList.add("武僧大秘境");
        classList.add("巫医大秘境");
        classList.add("魔法师大秘境");
        classList.add("二人模式大秘境");
        classList.add("三人模式大秘境");
        classList.add("四人模式大秘境");

        //适配器
        ArrayAdapter classAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classList);
        //设置样式
        classAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice );
        //加载适配器
        spinnerClass.setAdapter(classAdapter);

        final Spinner spinnerSeason = (Spinner) findViewById(R.id.spinnerSeason);
        final ArrayList seasonList = new ArrayList<String>();

        final ProgressDialog proDialog = android.app.ProgressDialog.show(this, "奈非天！", "天梯榜数据太多，请耐心等候……");
        /*LeaderBoardTask it = new LeaderBoardTask(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {
                String html = String.valueOf(result);

                proDialog.dismiss();
            }
        });*/
        final Context context = this;
        SeasonTask st = new SeasonTask(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {
                Season season = (Season)result;

                for(int i=1;i<=season.CurrentSeason;i++){
                    seasonList.add("第"+String.valueOf(i)+"季");
                }
                //适配器
                ArrayAdapter seasonAdapter= new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, seasonList);
                //设置样式
                seasonAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice );
                //加载适配器
                spinnerSeason.setAdapter(seasonAdapter);
                proDialog.dismiss();
            }
        });
        st.execute("");
    }
    //https://api.battlenet.com.cn/data/d3/season/9/leaderboard/rift-barbarian?namespace=2-1-CN&access_token=c7gx9rtd3rjnbjuzqg8ew7js

    //https://api.battlenet.com.cn/data/d3/season/9/leaderboard/rift-dh?namespace=2-1-CN&access_token=c7gx9rtd3rjnbjuzqg8ew7js

    //https://api.battlenet.com.cn/data/d3/season/9/leaderboard/rift-wd?namespace=2-1-CN&access_token=c7gx9rtd3rjnbjuzqg8ew7js

    //https://api.battlenet.com.cn/data/d3/season/9/leaderboard/rift-wizard?namespace=2-1-CN&access_token=c7gx9rtd3rjnbjuzqg8ew7js

    //https://api.battlenet.com.cn/data/d3/season/9/leaderboard/rift-monk?namespace=2-1-CN&access_token=c7gx9rtd3rjnbjuzqg8ew7js

    //https://api.battlenet.com.cn/data/d3/season/9/leaderboard/rift-crusader?namespace=2-1-CN&access_token=c7gx9rtd3rjnbjuzqg8ew7js
}
