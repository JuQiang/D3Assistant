package iamfqq.d3assistant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

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

        lvBoard= (ListView)findViewById(R.id.lvBoard);
        Context context = this;
        boardAdapter = new BoardAdapter(context,list);
        lvBoard.setAdapter(boardAdapter);
    }
}
