package iamfqq.d3assistant;

import android.provider.ContactsContract;
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
import java.util.ArrayList;

/**
 * Created by JuQiang on 2/25/2017.
 */

public class D3API {
    public static CareerProfile getProfile(String profileId) {
        final CareerProfile[] ret = new CareerProfile[1];

        ProfileTask pt = new ProfileTask(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {
                ret[0] = (CareerProfile) result;
            }
        });
        pt.execute(profileId);
        return ret[0];
    }

    public static ArrayList<String> getItemJson(String tooltipParams) {
        ArrayList<String> ret = new ArrayList<String>();
        HttpURLConnection urlConnection = null;

        //https://api.battlenet.com.cn/d3/data/item/Co0BCPm0re0MEgcIBBXqNOh1HWYjBlAd6tWSPx3mFdsNHZinjsAdFkn0hx0HXp3TMItaONgBQABQElgEYLEDajAKDAgAEOvxnrCBgICAGBIgCI7P0eAGEgcIBBVsF6PBMItSOABAAFASWASQAQnYAWCAAUalAZinjsCtAZinjsC1ATZVXUS4AYaFtpcHwAERGNTTwd4OUAJYAKABkqK93g6gAdTTwd4O?locale=zh_CN&apikey=heef46sr5ue44xfdgwr4wrycckgawhu5
        try {
            URL url = new URL("https://api.battlenet.com.cn/d3/data/" + tooltipParams+"?locale=zh_CN&apikey=heef46sr5ue44xfdgwr4wrycckgawhu5");
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

            JSONArray jsonArray = (new JSONObject(buffer.toString())).getJSONArray("gems");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject itemJson = (JSONObject) jsonArray.opt(i);

                ret.add((new JSONObject(itemJson.getString("item"))).getString("icon"));
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

        return ret;
    }

}
