package iamfqq.d3assistant;

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
import android.widget.CheckBox;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddFriendFromLeaderBoardActivity extends AppCompatActivity {
    ArrayList<A> list;
    ListView lvBoard;
    BoardAdapter boardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend_from_leader_board);
        Intent intent = this.getIntent();
        list = (ArrayList<A>) intent.getSerializableExtra("LeaderBoard");
        String model = intent.getStringExtra("model");
        String _class = intent.getStringExtra("class");
        String season = intent.getStringExtra("season");

        lvBoard = (ListView) findViewById(R.id.lvBoard);
        lvBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.get(i).type = (list.get(i).type == 0) ? 1 : 0;
                CheckBox cb = (CheckBox) view.findViewById(R.id.lbOrder);
                cb.setChecked(!cb.isChecked());
                int a = i;
            }
        });
        Context context = this;
        boardAdapter = new BoardAdapter(context, list);
        lvBoard.setAdapter(boardAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.addfriendfromleaderboardtoolbar);
        toolbar.setTitle("神一样的奈非天");
        toolbar.setSubtitle(model + "-" + _class + "-" + season);
        setSupportActionBar(toolbar);
        //注意：setNavigationIcon(),setOnMenuItemClickListener()
        // 需要放在 setSupportActionBar之后才会生效
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_refresh:
                        //InitLayout(false);
                        break;
                    default:
                        Log.i("abc", "!!!!!!!!!!!!!!!default!!!!!!!!!!!!!!!");
                }
                return true;
            }
        });


        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    public void saveLeaderBoardFriend(View view) {
        ListView lv = (ListView) findViewById(R.id.lvBoard);
        List<A> list = ((BoardAdapter) lv.getAdapter()).getBoardList();
        final StringBuilder sb = new StringBuilder();

        final Intent intentMain = new Intent(this, MainActivity.class);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).type == A.TYPE_CHECKED) {
                sb.append(list.get(i).board.BattleTag).append(",");
            }
        }

        ProfileTask2 pt2 = new ProfileTask2(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {
                String[] pidList = sb.toString().split(",");
                for (int i = 0; i < pidList.length; i++) {
                    int pos = pidList[i].indexOf("#");
                    D3API.addOrModifyFriend(pidList[i].replace("#","-"), pidList[i].substring(0, pos));
                }
                D3API.ShowToast("已成功邀请【"+String.valueOf(pidList.length)+"】位大神加入你的狗熊榜！");
                startActivity(intentMain);
            }
        }
        );
        pt2.execute(sb.toString(), "true");




    }
}
