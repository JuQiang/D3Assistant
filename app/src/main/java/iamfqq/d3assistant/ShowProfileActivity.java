package iamfqq.d3assistant;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.showprofiletoolbar);
        toolbar.setTitle(this.nickname
                +"<"+
                this.myProfile.getGuildName()
                +">"
        );

        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        D3API.setContext(this);

        GridView gridView = (GridView) findViewById(R.id.gvProfile);
        gridView.setAdapter(new CareerImageAdapter(this, this.myProfile));

        intentShowHero = new Intent(this, ShowHeroActivity.class);

        final String pid = this.myProfile.getBattleTag();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view == null) return;

                final String heroid = ((ProfileView)view).getHeroID();
                HeroTask ht = new HeroTask(new TaskCompleted() {
                    @Override
                    public void OnTaskCompleted(Object result) {
                        intentShowHero.putExtra("myHero", (Hero) result);
                        startActivity(intentShowHero);
                    }
                });
                //36760898
                ht.execute(pid, heroid);//33575370
            }
        });
    }
}
