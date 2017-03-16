package iamfqq.d3assistant;

import android.os.AsyncTask;

import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by JuQiang on 3/13/2017.
 */

public class LeaderBoardTask  extends AsyncTask<String, Integer, ArrayList<LeaderBoard>> {
    private TaskCompleted listner;

    public LeaderBoardTask(TaskCompleted listner) {
        this.listner = listner;
    }

    @Override
    protected ArrayList<LeaderBoard> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        ArrayList<LeaderBoard> list = new ArrayList<LeaderBoard>();

        String url = params[0];
        boolean isCached = params[1].toLowerCase()=="true";

        String json = D3API.DownloadString(url, isCached, url);

        for(int i=0;i<30;i++) {
            LeaderBoard lb = new LeaderBoard();
            lb.BattleTag = "iamfqq-" + String.valueOf(i);
            list.add(lb);
        }

        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<LeaderBoard> list) {
        if (null != listner) {
            listner.OnTaskCompleted(list);
        }
    }
}
