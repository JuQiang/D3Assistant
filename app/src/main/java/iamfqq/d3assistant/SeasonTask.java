package iamfqq.d3assistant;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

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
        String url = "";
        boolean isSeason = params[0].toLowerCase() == "season";
        boolean isCached = params[1].toLowerCase()=="true";

        if (isSeason) {
            url = "https://api.battlenet.com.cn/data/d3/season/?access_token=" + D3API.AccessToken;
        } else {
            url = "https://api.battlenet.com.cn/data/d3/era/?access_token=" + D3API.AccessToken;
        }
        String json = D3API.DownloadString(url, isCached, url);

        Season ret = new Season();
        try {
            JSONObject jsonseason = new JSONObject(json);
            if(isSeason) {
                ret.Current = Integer.decode(String.valueOf(jsonseason.get("current_season")));
            }
            else{
                ret.Current = Integer.decode(String.valueOf(jsonseason.get("current_era")));
            }
            ret.LastUpdateTime = String.valueOf(jsonseason.get("last_update_time"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
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
