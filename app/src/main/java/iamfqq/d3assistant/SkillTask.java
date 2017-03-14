package iamfqq.d3assistant;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.net.HttpURLConnection;

/**
 * Created by JuQiang on 3/12/2017.
 */

public class SkillTask  extends AsyncTask<String, Integer, Bitmap> {
    private TaskCompleted listner;

    public SkillTask(TaskCompleted listner) {
        this.listner = listner;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return D3API.DownloadBitmap(params[0],params[1]);
    }

    @Override
    protected void onPostExecute(Bitmap bmp) {
        if (null != listner) {
            listner.OnTaskCompleted(bmp);
        }
    }
}
