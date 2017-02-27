package iamfqq.d3assistant;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        // my_child_toolbar is defined in the layout file
        Toolbar toolbar =
                (Toolbar) findViewById(R.id.addfriendtoolbar);

        toolbar.setTitle("添加基友");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        //Toolbar toolbar2 =                (Toolbar) findViewById(R.id.addfriendtoolbar2);
//toolbar2.inflateMenu(R.menu.menu_addfriend);

    }

}
