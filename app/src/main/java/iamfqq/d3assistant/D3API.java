package iamfqq.d3assistant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuQiang on 2/25/2017.
 */

public class D3API {
    final private static String packageName = "iamfqq.d3assistant";
    private static Context context;

    public static int displayWidth;
    public static int displayHeight;
    final public static String Key = "p6zh6ysfdu862mep48mj75pvwuwt5vr7";
    final public static String AccessToken = "c7gx9rtd3rjnbjuzqg8ew7js";

    public static void setContext(Context con) {
        context = con;
    }

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
        WriteLog("!!!DownloadString!!!",urlString);
        HttpURLConnection urlConnection = null;
        String ret = "";
        String cachedFilename = GetHash(cacheKey) + ".string";

        if (needCache) {
            boolean exist = fileExists(cachedFilename);
            if (exist) {
                byte[] buf = readExternallStoragePublic(cachedFilename);
                ret = new String(buf);

                return ret;
            }
        }

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
            writeToExternalStoragePublic(cachedFilename, ret.getBytes());//write to cache
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

    public static void ShowToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static Bitmap DownloadBitmap(String urlString, String cacheKey) {
        boolean needCache = true;

        HttpURLConnection urlConnection = null;
        Bitmap ret = null;
        String cachedFilename = GetHash(cacheKey) + ".bitmap";

        if (needCache) {
            boolean exist = fileExists(cachedFilename);
            if (exist) {
                byte[] buf = readExternallStoragePublic(cachedFilename);
                ret = BitmapFactory.decodeByteArray(buf, 0, buf.length);

                return ret;
            }
        }

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream input = new BufferedInputStream(urlConnection.getInputStream());
            String path = getDiskCacheDir(context, packageName);
            int fileLength = urlConnection.getContentLength();

            if (isExternalStorageAvailable() &&
                    !isExternalStorageReadOnly()) {
                try {
                    File file = new File(path);
                    file.mkdirs();
                    FileOutputStream fos = new FileOutputStream(path + cachedFilename);

                    byte data[] = new byte[4096];
                    long total = 0;
                    int count;
                    while ((count = input.read(data)) != -1) {
                        // allow canceling with back button
//                if ( isCancelled()) {
//                    input.close();
//                    return null;
//                }
//                        total += count;
                        fos.write(data, 0, count);
                    }
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            input.close();
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

    public static void WriteLog(String tag, String message) {
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

    public static Item getGemsInformation(String tooltipParams) {
        WriteLog("getItemJson begin", tooltipParams);
        Item item = new Item();

        //https://api.battlenet.com.cn/d3/data/item/Co0BCPm0re0MEgcIBBXqNOh1HWYjBlAd6tWSPx3mFdsNHZinjsAdFkn0hx0HXp3TMItaONgBQABQElgEYLEDajAKDAgAEOvxnrCBgICAGBIgCI7P0eAGEgcIBBVsF6PBMItSOABAAFASWASQAQnYAWCAAUalAZinjsCtAZinjsC1ATZVXUS4AYaFtpcHwAERGNTTwd4OUAJYAKABkqK93g6gAdTTwd4O?locale=zh_CN&apikey=YourKey
        try {
            String urlString = "https://api.battlenet.com.cn/d3/data/" + tooltipParams + "?locale=zh_CN&apikey="+Key;
            String jsonString = DownloadString(urlString, true, tooltipParams.replace("item/", ""));

            if (jsonString.length() < 1) return item;
            JSONArray jsonArray = (new JSONObject(jsonString)).getJSONArray("gems");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject itemJson = (JSONObject) jsonArray.opt(i);
                item.GemList.add((new JSONObject(itemJson.getString("item"))).getString("icon"));
            }

            JSONObject sockets = (new JSONObject(jsonString)).getJSONObject("attributesRaw");
            if(sockets.has("Sockets")) {
                item.SocketCount=(Integer.parseInt(sockets.getJSONObject("Sockets").get("min").toString().replace(".0", "")));
            }
            else{
                item.SocketCount=(0);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        WriteLog("getItemJson end", tooltipParams);
        return item;
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

    public static ArrayList<Friend> getMyFriends() {
        String filename = "myfriends.txt";
        FileInputStream fis = null;
        BufferedReader br = null;
        ArrayList<Friend> friendList = new ArrayList<Friend>();

        String path = getDiskCacheDir(context, packageName);
        if (fileExists(filename)) {
            try {
                fis = new FileInputStream(path + filename);
                br = new BufferedReader(new InputStreamReader(fis));

                String line = "";
                boolean exist = false;
                while ((line = br.readLine()) != null) {
                    String[] tmp = line.split(",");
                    friendList.add(new Friend(tmp[0].toLowerCase(), tmp[1]));
                }
                fis.close();
                br.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                //if(fis!=null)fis.close();
                //if(br!=null)br.close();
            }
        } else {
            friendList.add(new Friend("方枪枪-5690", "方枪枪"));
        }

        return friendList;
    }

    public static void addOrModifyFriend(String profileID, String nickname) {
        String filename = "myfriends.txt";
        FileInputStream fis = null;
        BufferedReader br = null;

        String path = getDiskCacheDir(context, packageName);
        if (fileExists(filename)) {
            try {
                fis = new FileInputStream(path + filename);
                br = new BufferedReader(new InputStreamReader(fis));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                boolean exist = false;
                profileID=profileID.toLowerCase();

                while ((line = br.readLine()) != null) {
                    if (line.startsWith(profileID + ",")) {
                        exist = true;
                        line = profileID + "," + nickname;
                    }
                    buffer.append(line);
                    buffer.append("\r\n");
                }
                if (exist == false) {
                    buffer.append(profileID + "," + nickname);
                    buffer.append("\r\n");
                }
                fis.close();
                br.close();

                FileOutputStream fos = new FileOutputStream(path + filename);
                fos.write(buffer.toString().getBytes());
                fos.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                //if(fis!=null)fis.close();
                //if(br!=null)br.close();
            }
        } else {
            try {

                FileOutputStream fos = new FileOutputStream(path + filename);
                fos.write("方枪枪-5690,方枪枪\r\n".getBytes());

                fos.write((profileID + "," + nickname + "\r\n").getBytes());
                fos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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

        String path = getDiskCacheDir(context, packageName);

        if (isExternalStorageAvailable() &&
                !isExternalStorageReadOnly()) {
            try {
                File file = new File(path);
                file.mkdirs();
                FileOutputStream fos = new FileOutputStream(path + filename);
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

        WriteLog("====boolean2.1===", filename);
        String path = getDiskCacheDir(context, packageName);
        WriteLog("====boolean2.2===", path);
        if (!isExternalStorageReadOnly()) {
            try {
                FileInputStream fis = new FileInputStream(path + filename);
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
        WriteLog("====boolean2.5===", "");
        return buffer;
    }

    public static void deleteExternalStoragePublicFile(String filename) {
        String path = getDiskCacheDir(context, packageName);
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


    public static boolean fileExists(String filename) {
        String path = getDiskCacheDir(context, packageName);

        boolean ret = false;
        try {
            File file = new File(path, filename);
            ret = file.canRead();
            boolean ret2 = file.canExecute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ret;
        /*
        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir + filename);
        return file.canRead();
        */
        //http://stackoverflow.com/questions/21579468/android-file-exists-returns-false-for-existing-file-for-anything-different
    }

    /**
     * Reads a file from internal storage
     *
     * @param filename the file to read from
     * @return the file content
     */
    public static byte[] readInternalStorage(String filename) {
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

    public static String getDiskCacheDir(Context context, String packageName) {

        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            try {
                cachePath = context.getExternalCacheDir().getPath();
            } catch (Exception e) {
                cachePath = context.getCacheDir().getPath();
            }
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath + File.separator;
    }
}
