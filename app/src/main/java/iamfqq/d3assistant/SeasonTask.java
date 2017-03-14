package iamfqq.d3assistant;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by JuQiang on 3/13/2017.
 */

public class SeasonTask extends AsyncTask<String, Integer, Season> {
    private TaskCompleted listner;

    public SeasonTask(TaskCompleted listner) {
        this.listner = listner;
    }

    @Override
    protected Season doInBackground(String... params) {
        String url = "https://api.battlenet.com.cn/data/d3/season/?access_token="+D3API.AccessToken;
        String json = D3API.DownloadString(url, true, url);

        Season ret = new Season();
        try {
            JSONObject jsonseason = new JSONObject(json);
            ret.CurrentSeason =Integer.decode(String.valueOf(jsonseason.get("current_season")));
            ret.ServiceCurrentSeason=Integer.decode(String.valueOf(jsonseason.get("service_current_season")));
            ret.ServiceSeasonState =String.valueOf(jsonseason.get("service_season_state"));
            ret.LastUpdateTime=String.valueOf(jsonseason.get("last_update_time"));
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ret;
    }

    @Override
    protected void onPostExecute(Season season) {
        if (null != listner) {
            listner.OnTaskCompleted(season);
        }
    }
}
