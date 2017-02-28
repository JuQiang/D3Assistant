package iamfqq.d3assistant;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity
{

    private Context context;
    private Intent intentAddFriend;
    private Intent intentShowHero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.d3toolbar);
        //toolbar.setLogo(R.drawable.pig);
        toolbar.setTitle("暗黑狗熊榜");
        //toolbar.setSubtitle("Sub title");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
// Navigation Icon 要設定在 setSupoortActionBar 才有作用
// 否則會出現 back button
        //toolbar.setNavigationIcon(R.drawable.ab_android);

        this.context = this;
        intentAddFriend = new Intent(this, AddFriendActivity.class);
        intentShowHero = new Intent(this, ShowHeroActivity.class);

        D3API.setContext(this);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add:
                    intentAddFriend.putExtra("", "");
                    startActivity(intentAddFriend);

                    break;
                case R.id.action_showhero:

                    HeroTask ht = new HeroTask(new TaskCompleted() {
                        @Override
                        public void OnTaskCompleted(Object result) {
                            intentShowHero.putExtra("myHero", (Hero)result);
                            startActivity(intentShowHero);
                        }
                    });
                    ht.execute("方枪枪-5690","36760898");

                    break;
            }
            return true;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void myTest(View view) {

        D3API.getItemJson("item/Co0BCPm0re0MEgcIBBXqNOh1HWYjBlAd6tWSPx3mFdsNHZinjsAdFkn0hx0HXp3TMItaONgBQABQElgEYLEDajAKDAgAEOvxnrCBgICAGBIgCI7P0eAGEgcIBBVsF6PBMItSOABAAFASWASQAQnYAWCAAUalAZinjsCtAZinjsC1ATZVXUS4AYaFtpcHwAERGNTTwd4OUAJYAKABkqK93g6gAdTTwd4O?locale=zh_CN&apikey=heef46sr5ue44xfdgwr4wrycckgawhu5");
        ProfileTask pt = new ProfileTask(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {
                CareerProfile careerProfile = (CareerProfile)result;
                if(careerProfile==null)return;

                DecimalFormat df = new DecimalFormat();
                df.applyPattern("#,000");
                ((TextView) findViewById(R.id.guildName)).setText(careerProfile.getGuildName());
                ((TextView) findViewById(R.id.paragonLevel)).setText(df.format(careerProfile.getParagonLevel()));
                ((TextView) findViewById(R.id.paragonLevelSeason)).setText(df.format(careerProfile.getParagonLevelSeason()));
                ((TextView) findViewById(R.id.ElitesKills)).setText(df.format(careerProfile.getKillsElites()));
                ((TextView) findViewById(R.id.MonstersKills)).setText(df.format(careerProfile.getKillsMonsters()));

                GridView gridView = (GridView) findViewById(R.id.gridview);
                gridView.setAdapter(new CareerImageAdapter(context, careerProfile));
            }
        });
        pt.execute();
    }

    /**
     * 得到(图片/新闻)缓存存储地址 默认:sd卡指定位置: /sdcard/Android/data/<application
     * package>/cache 如果不存在或不可使用 则是内存卡
     * */

    public void myTest2(View view) {
String path =D3API.getDiskCacheDir(this.context,"iamfqq.d3assistant");
        String name = this.getPackageName();
        HeroTask ht = new HeroTask(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {
                Hero hero = (Hero)result;
                if(hero==null)return;

                TextView tv = (TextView) findViewById(R.id.testid);
                int life = hero.getStat().getLife();
                tv.setText(String.valueOf(life));
            }
        });
        ht.execute();
    }

}
