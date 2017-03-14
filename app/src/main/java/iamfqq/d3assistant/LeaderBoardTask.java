package iamfqq.d3assistant;

import android.os.AsyncTask;

import java.net.HttpURLConnection;

/**
 * Created by JuQiang on 3/13/2017.
 */

public class LeaderBoardTask  extends AsyncTask<String, Integer, String> {
    private TaskCompleted listner;

    public LeaderBoardTask(TaskCompleted listner) {
        this.listner = listner;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        String stringItemTooltip = params[0];

        String url = "https://api.battlenet.com.cn/data/d3/season/9/leaderboard/rift-barbarian?namespace=2-1-CN&access_token=c7gx9rtd3rjnbjuzqg8ew7js";
        String html = D3API.DownloadString(url, true, url);

        return html;
    }

    @Override
    protected void onPostExecute(String someString) {
        if (null != listner) {
            listner.OnTaskCompleted(someString);
        }
    }
}
