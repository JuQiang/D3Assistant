package iamfqq.d3assistant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ShowProfileActivity extends AppCompatActivity {
    private CareerProfile myProfile;
    private String nickname;
    private Intent intentShowHero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        Intent intent = this.getIntent();
        this.myProfile = (CareerProfile) intent.getSerializableExtra("myFriend");
        this.nickname = intent.getStringExtra("myFriendNickname");

        final String pid = this.myProfile.battleTag;
        final Context context = this;
        D3API.setContext(context);

        Toolbar toolbar = (Toolbar) findViewById(R.id.showprofiletoolbar);
        toolbar.setTitle(this.nickname
                +"<"+
                this.myProfile.guildName
                +">"
        );
        toolbar.setSubtitle("赛季巅峰"+String.valueOf(this.myProfile.paragonLevelSeason)
        +"，巅峰"+String.valueOf(this.myProfile.paragonLevel));
        setSupportActionBar(toolbar);
        //注意：setNavigationIcon(),setOnMenuItemClickListener()
        // 需要放在 setSupportActionBar之后才会生效
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_refresh:
                        Log.i("abc", "!!!!!!!!!!!!!!!refreshed.!!!!!!!!!!!!!!!");
                        final ProgressDialog proDialog = android.app.ProgressDialog.show(ShowProfileActivity.this, "奈非天！", "正在和英雄沟通，请稍候……");
                        ProfileTask pt = new ProfileTask(new TaskCompleted() {
                            @Override
                            public void OnTaskCompleted(Object result) {
                                GridView gridView = (GridView) findViewById(R.id.gvProfile);
                                gridView.setAdapter(new CareerImageAdapter(context, (CareerProfile)result));
                                proDialog.dismiss();//万万不可少这句，否则会程序会卡死。
                            }
                        });
                        pt.execute(pid,"false");
                        break;
                    default:
                        Log.i("abc", "!!!!!!!!!!!!!!!default!!!!!!!!!!!!!!!");
                }
                return true;
            }
        });


        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        GridView gridView = (GridView) findViewById(R.id.gvProfile);
        gridView.setAdapter(new CareerImageAdapter(this, this.myProfile));

        intentShowHero = new Intent(this, ShowHeroActivity.class);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view == null) return;
                final ProgressDialog proDialog = android.app.ProgressDialog.show(ShowProfileActivity.this, "奈非天！", "正在和英雄沟通，请稍候……");

                final String heroid = ((ProfileView)view).getHeroID();
                HeroTask ht = new HeroTask(new TaskCompleted() {
                    @Override
                    public void OnTaskCompleted(Object result) {
                        intentShowHero.putExtra("myHero", (Hero) result);
                        startActivity(intentShowHero);
                        proDialog.dismiss();//万万不可少这句，否则会程序会卡死。
                    }
                });
                //36760898
                ht.execute(pid, heroid,"true");//33575370
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }
}
