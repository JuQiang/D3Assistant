package iamfqq.d3assistant;

import android.os.AsyncTask;

import java.net.HttpURLConnection;

/**
 * Created by JUQIANG-PC on 2017/3/10.
 */

public class ItemTask extends AsyncTask<String, Integer, String> {
    private TaskCompleted listner;

    public ItemTask(TaskCompleted listner) {
        this.listner = listner;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        String stringItemTooltip = params[0];

        String url = "http://d3.blizzard.cn/action/profile/item?param=" + stringItemTooltip;
        String html = D3API.DownloadString(url, true, stringItemTooltip);

        return html;
    }

    @Override
    protected void onPostExecute(String someString) {
        if (null != listner) {
            listner.OnTaskCompleted(someString);
        }
    }
}
