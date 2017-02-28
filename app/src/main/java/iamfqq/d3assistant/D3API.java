package iamfqq.d3assistant;

import android.content.Context;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by JuQiang on 2/25/2017.
 */

public class D3API {
    final private static String packageName = "iamfqq.d3assistant";

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

    public static String DownloadString(String urlString, boolean needCache, String cacheKey) {
        HttpURLConnection urlConnection = null;
        String ret = "";
        String cachedFilename = "item_" + GetHash(cacheKey) + ".json";

        if (needCache) {
            if (fileExists(cachedFilename)) {
                byte[] buf = readInternalStorage(cachedFilename);
                ret = new String(buf);
            }
        } else {
            try {
                URL url = new URL(urlString);
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

                ret = buffer.toString();
                writeInternalStorage(cachedFilename, ret.getBytes());//write to cache
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
        }

        return ret;
    }

    private static byte[] DownloadBinary(String url, boolean needCache) {
        return null;
    }

    private static void WriteLog(String tag, String message) {
        Log.i(tag, message + "-----" + String.valueOf(System.currentTimeMillis()));
    }

    private static String GetHash(String str) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }

    public static ArrayList<String> getItemJson(String tooltipParams) {
        WriteLog("getItemJson begin", tooltipParams);
        ArrayList<String> ret = new ArrayList<String>();

        //https://api.battlenet.com.cn/d3/data/item/Co0BCPm0re0MEgcIBBXqNOh1HWYjBlAd6tWSPx3mFdsNHZinjsAdFkn0hx0HXp3TMItaONgBQABQElgEYLEDajAKDAgAEOvxnrCBgICAGBIgCI7P0eAGEgcIBBVsF6PBMItSOABAAFASWASQAQnYAWCAAUalAZinjsCtAZinjsC1ATZVXUS4AYaFtpcHwAERGNTTwd4OUAJYAKABkqK93g6gAdTTwd4O?locale=zh_CN&apikey=heef46sr5ue44xfdgwr4wrycckgawhu5
        try {
            String urlString = "https://api.battlenet.com.cn/d3/data/" + tooltipParams + "?locale=zh_CN&apikey=heef46sr5ue44xfdgwr4wrycckgawhu5";
            String jsonString = DownloadString(urlString, true, tooltipParams.replace("item/", ""));

            JSONArray jsonArray = (new JSONObject(jsonString)).getJSONArray("gems");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject itemJson = (JSONObject) jsonArray.opt(i);
                ret.add((new JSONObject(itemJson.getString("item"))).getString("icon"));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        WriteLog("getItemJson end", tooltipParams);
        return ret;
    }

    /**
     * Helper Method to Test if external Storage is Available
     */
    private static boolean isExternalStorageAvailable() {
        boolean state = false;
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            state = true;
        }
        return state;
    }

    /**
     * Helper Method to Test if external Storage is read only
     */
    private static boolean isExternalStorageReadOnly() {
        boolean state = false;
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            state = true;
        }
        return state;
    }

    /**
     * Write to external public directory
     *
     * @param filename - the filename to write to
     * @param content  - the content to write
     */
    public static void writeToExternalStoragePublic(String filename, byte[] content) {

        // API Level 7 or lower, use getExternalStorageDirectory()
        //  to open a File that represents the root of the external
        // storage, but writing to root is not recommended, and instead
        // application should write to application-specific directory, as shown below.

        String path = "/Android/data/" + packageName + "/files/";

        if (isExternalStorageAvailable() &&
                !isExternalStorageReadOnly()) {
            try {
                File file = new File(path, filename);
                file.mkdirs();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(content);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads a file from internal storage
     *
     * @param filename - the filename to read from
     * @return the file contents
     */
    public static byte[] readExternallStoragePublic(String filename) {
        int len = 1024;
        byte[] buffer = new byte[len];

        String path = "/Android/data/" + packageName + "/files/";

        if (!isExternalStorageReadOnly()) {
            try {
                File file = new File(path, filename);
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int nrb = fis.read(buffer, 0, len); //read up to len bytes
                while (nrb != -1) {
                    baos.write(buffer, 0, nrb);
                    nrb = fis.read(buffer, 0, len);
                }
                buffer = baos.toByteArray();
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }

    public static void deleteExternalStoragePublicFile(String filename) {
        String path = "/Android/data/" + packageName + "/files/" + filename;
        File file = new File(path, filename);
        if (file != null) {
            file.delete();
        }
    }

    /**
     * Writes content to internal storage making the content private to
     * the application. The method can be easily changed to take the MODE
     * as argument and let the caller dictate the visibility:
     * MODE_PRIVATE, MODE_WORLD_WRITEABLE, MODE_WORLD_READABLE, etc.
     *
     * @param filename - the name of the file to create
     * @param content  - the content to write
     */
    public static void writeInternalStorage(
            String filename, byte[] content) {
        try {
            //MODE_PRIVATE creates/replaces a file and makes
            //  it private to your application. Other modes:
            //    MODE_WORLD_WRITEABLE
            //    MODE_WORLD_READABLE
            //    MODE_APPEND
            FileOutputStream fos = new FileOutputStream(filename);

            //        openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(content);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static boolean fileExists(String filename) {
        File f = new File(filename);
        return f.exists();
    }

    /**
     * Reads a file from internal storage
     *
     * @param filename the file to read from
     * @return the file content
     */
    public static byte[] readInternalStorage(String filename) {
        if (fileExists(filename) == false) return new byte[1];
        int len = 1024;
        byte[] buffer = new byte[len];
        try {
            FileInputStream fis = new FileInputStream(filename);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int nrb = fis.read(buffer, 0, len); // read up to len bytes
            while (nrb != -1) {
                baos.write(buffer, 0, nrb);
                nrb = fis.read(buffer, 0, len);
            }
            buffer = baos.toByteArray();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * Delete internal private file
     *
     * @param filename - the filename to delete
     */
    public static void deleteInternalStoragePrivate(String filename) {

        File file = new File(filename);
        if (file != null) {
            file.delete();
        }
    }

}
