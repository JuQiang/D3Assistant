package iamfqq.d3assistant;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
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


public class MainActivity extends AppCompatActivity {

    private Context context;
    private Intent intentAddFriend;
    private Intent intentTest;
    private Intent intentShowProfile;
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
        intentTest = new Intent(this,ShowItemTooltipActivity.class);
        intentShowProfile = new Intent(this, ShowProfileActivity.class);
        intentShowHero = new Intent(this, ShowHeroActivity.class);

        D3API.setContext(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        D3API.displayWidth = displayMetrics.widthPixels;
        D3API.displayHeight = displayMetrics.heightPixels;

        ArrayList<Friend> friends = D3API.getMyFriends();
        GridView gridView = (GridView) findViewById(R.id.gvFriends);
        gridView.setAdapter(new FriendImageAdapter(context, friends));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view == null) return;

                final String battleTag = ((FriendView) view).getBattleTag();
                final String nickname =  ((FriendView) view).getNickName();
                ProfileTask pt = new ProfileTask(new TaskCompleted() {
                    @Override
                    public void OnTaskCompleted(Object result) {
                        intentShowProfile.putExtra("myFriend",(CareerProfile) result);
                        intentShowProfile.putExtra("myFriendNickname",nickname);
                        startActivity(intentShowProfile);
                    }
                });
                pt.execute(battleTag,"true");

            }
        });
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add:
                    intentAddFriend.putExtra("", "");
                    startActivity(intentAddFriend);
                    break;
                case R.id.action_test:
                    intentTest.putExtra("", "");
                    startActivity(intentTest);
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



}
