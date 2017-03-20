package iamfqq.d3assistant;

import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by JuQiang on 3/13/2017.
 */

public class LeaderBoardTask extends AsyncTask<String, Integer, ArrayList<LeaderBoard>> {
    private TaskCompleted listner;

    public LeaderBoardTask(TaskCompleted listner) {
        this.listner = listner;
    }

    private SomeStupidJavaMultipleReturnValues getStringByBeginEnd(String input, String start, String end, int index) {
        SomeStupidJavaMultipleReturnValues ret = new SomeStupidJavaMultipleReturnValues();

        int pos = -1;
        int pos2 = -1;

        pos = input.indexOf(start, index);
        pos2 = input.indexOf(end, pos + start.length());
        if (pos < 0 || pos2 < 0) return ret;

        ret.StringValue = input.substring(pos + start.length(), pos2).trim();
        ret.NextIndex = pos2 + end.length();

        return ret;
    }

    @Override
    protected ArrayList<LeaderBoard> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        ArrayList<LeaderBoard> list = new ArrayList<LeaderBoard>();

        String url = params[0];
        boolean isCached = params[1].toLowerCase() == "true";

        String json = D3API.DownloadString(url, isCached, url);

        int order = 1;
        int index = -1;
        while (true) {
            SomeStupidJavaMultipleReturnValues fuck = getStringByBeginEnd(json, "src=\"", "\"", index);
            if(fuck.NextIndex==0)break;
            //if(order>14)break;

            SomeStupidJavaMultipleReturnValues fuck2 = getStringByBeginEnd(json, "battleName\">", "</span>", fuck.NextIndex);
            SomeStupidJavaMultipleReturnValues fuck3 = getStringByBeginEnd(json, "cell-RiftLevel\">", "</td>", fuck2.NextIndex);
            SomeStupidJavaMultipleReturnValues fuck4 = getStringByBeginEnd(json, "cell-RiftTime\">", "</td>", fuck3.NextIndex);
            SomeStupidJavaMultipleReturnValues fuck5 = getStringByBeginEnd(json, "CompletedTime\">", "</td>", fuck4.NextIndex);
            index = fuck5.NextIndex;

            LeaderBoard lb = new LeaderBoard();
            lb.Order = order++;
            lb.BattleTag = fuck2.StringValue;
            int pos = lb.BattleTag.indexOf("#");
            lb.Name =lb.BattleTag.substring(0,pos);
            lb.RiftLevel = Integer.parseInt(fuck3.StringValue);
            String time = fuck4.StringValue;
            pos = time.indexOf(".");
            if(pos>-1){
                time = time.substring(0,pos)+"秒";
            }
            lb.RiftTime = time;
            lb.CompletedTime = fuck5.StringValue;
            lb.ClassPictureUrl = fuck.StringValue;

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
