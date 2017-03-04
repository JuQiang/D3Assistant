package iamfqq.d3assistant;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        Toolbar toolbar = (Toolbar) findViewById(R.id.addfriendtoolbar);

        toolbar.setTitle("添加基友");
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void findAndSaveFriend(View view) {
        String left = ((EditText) findViewById(R.id.profileLeft)).getText().toString();
        String right = ((EditText) findViewById(R.id.profileRight)).getText().toString();
        final String pid = left + "-" + right;
        final String nick = ((EditText) findViewById(R.id.nickName)).getText().toString();
        final Intent intentMain = new Intent(this, MainActivity.class);

        if (pid == null || pid.trim().length() < 1 || nick == null || nick.trim().length() < 1) {
            D3API.ShowToast("battleTag或者昵称不能为空，请检查。");
            return;
        }
        ProfileTask pt = new ProfileTask(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {
                CareerProfile cp = (CareerProfile) result;
                if (cp.getBattleTag() != null) {
                    D3API.addOrModifyFriend(pid, nick);
                    D3API.ShowToast("已成功添加基友：" + nick);
                    startActivity(intentMain);

                } else {
                    D3API.ShowToast("添加：" + pid + "不成功，请检查是否\r\n1.是国服账号。\r\n2.battleTag都是正确的。"
                    );
                }
            }
        });
        pt.execute(left + "-" + right);
    }


}
