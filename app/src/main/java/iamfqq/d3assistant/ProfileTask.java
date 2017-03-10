package iamfqq.d3assistant;

import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * Created by JuQiang on 2/25/2017.
 */

public class ProfileTask extends AsyncTask<String, Integer, CareerProfile> {
    private TaskCompleted listner;

    public ProfileTask() {
    }

    public ProfileTask(TaskCompleted listner) {
        this.listner = listner;
    }

    @Override
    protected CareerProfile doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        CareerProfile careerProfile = new CareerProfile();
        String pid = params[0].toLowerCase();
        boolean cached = false;
        if (params[1] == "true") cached = true;

        String urlString = "https://api.battlenet.com.cn/d3/profile/" + pid + "/?locale=zh_CN&apikey=8prs9cf3txhyg92844p7ny8kejesrcz4";
        String ret = D3API.DownloadString(urlString, cached, pid);

        try {
            JSONObject jsonProfile = new JSONObject(ret);
            careerProfile.battleTag=(jsonProfile.getString("battleTag").replace("#", "-").toLowerCase());
            careerProfile.paragonLevel=(jsonProfile.getInt("paragonLevel"));
            careerProfile.paragonLevelSeason=(jsonProfile.getInt("paragonLevelSeason"));
            careerProfile.guildName=(jsonProfile.getString("guildName"));
            careerProfile.lastHeroPlayed=(jsonProfile.getLong("lastHeroPlayed"));
            careerProfile.lastUpdated=(jsonProfile.getLong("lastUpdated"));
            careerProfile.killsMonsters=(jsonProfile.getJSONObject("kills").getInt("monsters"));
            careerProfile.killsElites=(jsonProfile.getJSONObject("kills").getInt("elites"));


            JSONArray jsonArray = jsonProfile.getJSONArray("heroes");

            int count = jsonArray.length();

            for (int i = 0; i < count; i++) {

                publishProgress((i + 1) * 100 / count);

                JSONObject jsonHero = (JSONObject) jsonArray.opt(i);

                HeroProfileSimple heroSimple = new HeroProfileSimple();
                heroSimple.ID=(jsonHero.getInt("id"));
                heroSimple.Name=(jsonHero.getString("name"));
                heroSimple.Class=(jsonHero.getString("class"));
                heroSimple.Gender=(jsonHero.getInt("gender"));
                heroSimple.Level=(jsonHero.getInt("level"));
                heroSimple.KillElites=(jsonHero.getJSONObject("kills").getInt("elites"));
                heroSimple.ParagonLevel=(jsonHero.getInt("paragonLevel"));
                heroSimple.Hardcore=(jsonHero.getBoolean("hardcore"));
                heroSimple.Seasonal=(jsonHero.getBoolean("seasonal"));
                heroSimple.Dead=(jsonHero.getBoolean("dead"));
                heroSimple.LastUpdated=(jsonHero.getInt("last-updated"));

                careerProfile.heroes.add(heroSimple);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return careerProfile;
    }

    @Override
    protected void onPostExecute(CareerProfile careerProfile) {
        if (null != listner) {
            listner.OnTaskCompleted(careerProfile);
        }
    }
}

