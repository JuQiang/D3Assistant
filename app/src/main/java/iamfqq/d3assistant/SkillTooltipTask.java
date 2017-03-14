package iamfqq.d3assistant;

import android.os.AsyncTask;

import java.net.HttpURLConnection;

/**
 * Created by JuQiang on 3/12/2017.
 */

public class SkillTooltipTask  extends AsyncTask<String, Integer, String> {
    private TaskCompleted listner;

    public SkillTooltipTask(TaskCompleted listner) {
        this.listner = listner;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;

        String index = params[0];
        boolean isPassive = (params[1]=="true");
        String uriPrefix = params[2];

        //http://d3.blizzard.cn/action/profile/career/%E6%96%B9%E6%9E%AA%E6%9E%AA-5690/36760898/skill/0

        String url = "";
        String html = "";
        if(isPassive){
            url = uriPrefix+"passive/"+index;
            html = D3API.DownloadString(url, true, url);
        }
        else{
            url = uriPrefix+"skill/"+index;
            html = D3API.DownloadString(url, true, url);
        }
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
