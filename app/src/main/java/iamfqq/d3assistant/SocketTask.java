package iamfqq.d3assistant;

import android.os.AsyncTask;

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
import java.util.ArrayList;

/**
 * Created by CharlesJu on 2/27/2017.
 */

public class SocketTask extends AsyncTask<String, Integer, ArrayList<String>>

{
    private TaskCompleted listner;

    public SocketTask(TaskCompleted listner) {
        this.listner = listner;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        ArrayList<String> ret = new ArrayList<String>();
        HttpURLConnection urlConnection = null;

        //https://api.battlenet.com.cn/d3/data/item/Co0BCPm0re0MEgcIBBXqNOh1HWYjBlAd6tWSPx3mFdsNHZinjsAdFkn0hx0HXp3TMItaONgBQABQElgEYLEDajAKDAgAEOvxnrCBgICAGBIgCI7P0eAGEgcIBBVsF6PBMItSOABAAFASWASQAQnYAWCAAUalAZinjsCtAZinjsC1ATZVXUS4AYaFtpcHwAERGNTTwd4OUAJYAKABkqK93g6gAdTTwd4O?locale=zh_CN&apikey=heef46sr5ue44xfdgwr4wrycckgawhu5
        try {
            URL url = new URL("https://api.battlenet.com.cn/d3/data/"+params[0]);
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

    @Override
    protected void onPostExecute(ArrayList<String> sockets) {
        if (null != listner) {
            listner.OnTaskCompleted(sockets);
        }
    }
}

