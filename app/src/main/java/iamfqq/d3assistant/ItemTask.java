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
        String start = "<div class=\"d3-tooltip-item-border";
        String end = "<div class=\"tooltip-";

        int pos = html.indexOf(start);
        int pos2 = html.indexOf(end,pos+start.length());
        if(pos>-1 && pos2>pos){
String left = html.substring(0,pos-1);
            String right = html.substring(pos2,html.length()-1);
            html = left + right;
        }
        return html;
    }

    @Override
    protected void onPostExecute(String someString) {
        if (null != listner) {
            listner.OnTaskCompleted(someString);
        }
    }
}
