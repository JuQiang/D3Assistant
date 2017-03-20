package iamfqq.d3assistant;

import android.os.AsyncTask;

import java.net.HttpURLConnection;

/**
 * Created by JuQiang on 3/20/2017.
 */

public class ProfileTask2 extends AsyncTask<String, Integer, String> {
    private TaskCompleted listner;

    public ProfileTask2() {
    }

    public ProfileTask2(TaskCompleted listner) {
        this.listner = listner;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;

        String battleTags = params[0].toLowerCase();
        String[] pidList = battleTags.split(",");

        boolean cached = false;
        if (params[1] == "true") cached = true;

        for(int i=0;i<pidList.length;i++) {
            String urlString = "https://api.battlenet.com.cn/d3/profile/" + pidList[i] + "/?locale=zh_CN&apikey=" + D3API.Key;
            D3API.DownloadString(urlString, cached, pidList[i]);
        }

        return "";
    }

    @Override
    protected void onPostExecute(String stringNothing) {
        if (null != listner) {
            listner.OnTaskCompleted(stringNothing);
        }
    }
}
