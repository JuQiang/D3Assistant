package iamfqq.d3assistant;

import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
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

    public ProfileTask(TaskCompleted listner) {
        this.listner = listner;
    }

    @Override
    protected CareerProfile doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        CareerProfile careerProfile = new CareerProfile();

        try {
            URL url = new URL("https://api.battlenet.com.cn/d3/profile/春日夏树-5476/?locale=zh_CN&apikey=8prs9cf3txhyg92844p7ny8kejesrcz4");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            reader.close();
            in.close();

            JSONObject jsonProfile = new JSONObject(buffer.toString());
            careerProfile.setBattleTag(jsonProfile.getString("battleTag"));
            careerProfile.setParagonLevel(jsonProfile.getInt("paragonLevel"));
            careerProfile.setParagonLevelSeason(jsonProfile.getInt("paragonLevelSeason"));
            careerProfile.setGuildName(jsonProfile.getString("guildName"));
            careerProfile.setLastHeroPlayed(jsonProfile.getLong("lastHeroPlayed"));
            careerProfile.setLastUpdated(jsonProfile.getLong("lastUpdated"));
            careerProfile.setKillsMonsters(jsonProfile.getJSONObject("kills").getInt("monsters"));
            careerProfile.setKillsElites(jsonProfile.getJSONObject("kills").getInt("elites"));


            JSONArray jsonArray = jsonProfile.getJSONArray("heroes");

            int count = jsonArray.length();

            for (int i = 0; i < count; i++) {

                publishProgress((i + 1) * 100 / count);

                JSONObject jsonHero = (JSONObject) jsonArray.opt(i);

                HeroProfileSimple heroSimple = new HeroProfileSimple();
                heroSimple.setId(jsonHero.getInt("id"));
                heroSimple.setName(jsonHero.getString("name"));
                heroSimple.set_class(jsonHero.getString("class"));
                heroSimple.setGender(jsonHero.getInt("gender"));
                heroSimple.setLevel(jsonHero.getInt("level"));
                heroSimple.setKillElites(jsonHero.getJSONObject("kills").getInt("elites"));
                heroSimple.setParagonLevel(jsonHero.getInt("paragonLevel"));
                heroSimple.setHardcore(jsonHero.getBoolean("hardcore"));
                heroSimple.setSeasonal(jsonHero.getBoolean("seasonal"));
                heroSimple.setDead(jsonHero.getBoolean("dead"));
                heroSimple.setLastUpdated(jsonHero.getInt("last-updated"));

                careerProfile.getHeroes().add(heroSimple);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
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

