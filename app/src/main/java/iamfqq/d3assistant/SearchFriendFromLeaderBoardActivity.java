package iamfqq.d3assistant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchFriendFromLeaderBoardActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend_from_leaderboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.searchfriendfromleaderboardtoolbar);
        toolbar.setTitle("添加天梯榜上的神基友");
        setSupportActionBar(toolbar);


        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        InitLayout(true);
        intent = new Intent(this, AddFriendFromLeaderBoardActivity.class);

        //InitListview();
    }


    private String GetUrl(String model, String _class, String board) {
        String s1 = "", s2 = "", s3 = "";

        String url = "https://api.battlenet.com.cn/data/d3/";
        if (model == "普通模式" || model == "专家级模式") s1 = "era";
        if (model == "赛季" || model == "专家级赛季") s1 = "season";

        if (model.contains("专家")) s2 = "hardcore";
        else s2 = "normal";//s2 = "-hardcore";

        if (_class.contains("野蛮人")) s3 = "barbarian";
        if (_class.contains("圣教军大秘境")) s3 = "crusader";
        if (_class.contains("猎魔人大秘境")) s3 = "dh";
        if (_class.contains("武僧大秘境")) s3 = "monk";
        if (_class.contains("巫医大秘境")) s3 = "wd";
        if (_class.contains("魔法师大秘境")) s3 = "wizard";
        if (_class.contains("二人模式大秘境")) s3 = "team-2";
        if (_class.contains("三人模式大秘境")) s3 = "team-3";
        if (_class.contains("四人模式大秘境")) s3 = "team-4";

        //oauth搞不定
        //先从官网上抓
        /*url = "https://api.battlenet.com.cn/data/d3/"
                + s1
                + "/"
                + board.replace("第", "").replace("季", "")
                + "/leaderboard/rift"
                + s2
                + "-"
                + s3
                + "?namespace=2-1-CN&access_token="
                + D3API.AccessToken;*/
        http://d3.blizzard.cn/action/ranking/leaderboard?season=season&phase=9&classes=barbarian&type=normal&p=1
        url = "http://d3.blizzard.cn/action/ranking/leaderboard?season="
                + s1
                + "&phase="
                + board.replace("第", "").replace("季", "")
                + "&classes="
                + s3
                + "&type="
                + s2
                + "&p=1";

        return url;
    }

    private void InitLayout(final boolean cached) {
        //Season
        final Spinner spinnerBoard = (Spinner) findViewById(R.id.spinnerBoard);

        final ProgressDialog proDialog = android.app.ProgressDialog.show(this, "奈非天！", "天梯榜数据太多，请耐心等候……");
        final Context context = this;

//Class
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
        CustomizedSpinnerAdapter classAdapter = new CustomizedSpinnerAdapter(this, classList);
        spinnerClass.setAdapter(classAdapter);
//Model
        Spinner spinnerModel = (Spinner) findViewById(R.id.spinnerModel);
        ArrayList modelList = new ArrayList<String>();
        modelList.add("普通模式");
        modelList.add("专家级模式");
        modelList.add("赛季");
        modelList.add("专家级赛季");

        CustomizedSpinnerAdapter modelAdapter = new CustomizedSpinnerAdapter(this, modelList);
        spinnerModel.setAdapter(modelAdapter);
        spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = "";
                if (position < 2) type = "era";
                else type = "season";

                SeasonTask st = new SeasonTask(new TaskCompleted() {
                    @Override
                    public void OnTaskCompleted(Object result) {

                        Season season = (Season) result;
                        ArrayList boardList = new ArrayList<String>();

                        for (int i = season.Current; i >= 1; i--) {
                            boardList.add("第" + String.valueOf(i) + "季");
                        }
                        CustomizedSpinnerAdapter seasonAdapter = new CustomizedSpinnerAdapter(context, boardList);
                        spinnerBoard.setAdapter(seasonAdapter);
                        proDialog.dismiss();
                    }
                });
                st.execute(type, String.valueOf(cached));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerModel.setSelection(2);
    }

    public void findAndSaveLeaderBoardFriend(View view) {

        final Spinner spinnerModel = (Spinner) findViewById(R.id.spinnerModel);
        final Spinner spinnerClass = (Spinner) findViewById(R.id.spinnerClass);
        final Spinner spinnerSeason = (Spinner) findViewById(R.id.spinnerBoard);

        final String model = ((TextView) (spinnerModel.getSelectedView().findViewById(R.id.textView2))).getText().toString();
        final String _class = ((TextView) (spinnerClass.getSelectedView().findViewById(R.id.textView2))).getText().toString();
        final String season = ((TextView) (spinnerSeason.getSelectedView().findViewById(R.id.textView2))).getText().toString();

        EditText test = (EditText) findViewById(R.id.etTest);
        String url = GetUrl(model, _class, season);
        test.setText(url);
        final ProgressDialog proDialog = android.app.ProgressDialog.show(this, "奈非天！", "正在和天梯榜沟通，请稍候……");

        LeaderBoardTask lbt = new LeaderBoardTask(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {
                ArrayList<LeaderBoard> list = (ArrayList<LeaderBoard>) result;
                ArrayList<A> list2 = new ArrayList<A>();
                for (int i = 0; i < list.size(); i++) {
                    list2.add(new A(list.get(i), A.TYPE_NOCHECKED));
                }
                proDialog.dismiss();

                intent.putExtra("LeaderBoard", list2);
                intent.putExtra("model",model);
                intent.putExtra("class",_class);
                intent.putExtra("season",season);
                startActivity(intent);
            }
        });
        lbt.execute(url, "true");
    }
}
