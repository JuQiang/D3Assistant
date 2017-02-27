package iamfqq.d3assistant;

import android.os.AsyncTask;

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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JuQiang on 2/25/2017.
 */

public class HeroTask extends AsyncTask<String, Integer, Hero> {
    private TaskCompleted listner;

    public HeroTask(TaskCompleted listner) {
        this.listner = listner;
    }

    @Override
    protected Hero doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        Hero hero = new Hero();

        try {
            URL url = new URL("https://api.battlenet.com.cn/d3/profile/方枪枪-5690/hero/34691101?locale=zh_CN&apikey=8prs9cf3txhyg92844p7ny8kejesrcz4");
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
            String json = buffer.toString();

            JSONObject jsonStats = new JSONObject(json).getJSONObject("stats");
            Stat stat = new Stat();

            stat.setArcaneResist(jsonStats.getInt("arcaneResist"));
            stat.setArmor(jsonStats.getInt("armor"));
            stat.setAttackSpeed(jsonStats.getDouble("attackSpeed"));
            stat.setBlockAmountMax((int) (jsonStats.getDouble("blockAmountMax") * 100.0));
            stat.setBlockAmountMin((int) (jsonStats.getDouble("blockAmountMin") * 100.0));
            stat.setBlockChance((int) (jsonStats.getDouble("blockChance") * 100.0));
            stat.setColdResist(jsonStats.getInt("coldResist"));
            stat.setCritChance((int) (jsonStats.getDouble("critChance") * 100.0));
            stat.setCritDamage((int) (jsonStats.getDouble("critDamage") * 100.0));
            stat.setDamage(jsonStats.getInt("damage"));
            stat.setDamageIncrease((int) (jsonStats.getDouble("damageIncrease") * 100.0));
            stat.setDamageReduction((int) (jsonStats.getDouble("damageReduction") * 100.0));
            stat.setDexterity(jsonStats.getInt("dexterity"));
            stat.setFireResist(jsonStats.getInt("fireResist"));
            stat.setGoldFind((int) (jsonStats.getDouble("goldFind") * 100.0));
            stat.setHealing(jsonStats.getInt("healing"));
            stat.setIntelligence(jsonStats.getInt("intelligence"));
            stat.setLife(jsonStats.getInt("life"));
            stat.setLifeOnHit(jsonStats.getInt("lifeOnHit"));
            stat.setLifePerKill(jsonStats.getInt("lifePerKill"));
            stat.setLifePerKill(jsonStats.getInt("lifePerKill"));
            stat.setLightningResist(jsonStats.getInt("lightningResist"));
            stat.setLifeSteal(jsonStats.getInt("lifeSteal"));
            stat.setMagicFind((int) (jsonStats.getDouble("magicFind") * 100.0));
            stat.setPhysicalResist(jsonStats.getInt("physicalResist"));
            stat.setPoisonResist(jsonStats.getInt("poisonResist"));
            stat.setPrimaryResource(jsonStats.getInt("primaryResource"));
            stat.setSecondaryResource(jsonStats.getInt("secondaryResource"));
            stat.setStrength(jsonStats.getInt("strength"));
            stat.setToughness((int)(jsonStats.getDouble("toughness")));
            stat.setThorns(jsonStats.getInt("thorns"));
            stat.setVitality(jsonStats.getInt("vitality"));

            hero.setStat(stat);
            JSONObject jsonItems = new JSONObject(json).getJSONObject("items");

            int count = jsonItems.length();

            Map<String, Item> items = new HashMap<String, Item>();
            for (int i = 0; i < count; i++) {
                publishProgress((i + 1) * 100 / count);
                String itemName = jsonItems.names().getString(i);
                JSONObject jsonItem = jsonItems.getJSONObject(itemName);

                Item item = new Item();
                item.setId(jsonItem.getString("id"));
                item.setName(jsonItem.getString("name"));
                item.setIcon(jsonItem.getString("icon"));
                item.setDisplayColor(jsonItem.getString("displayColor"));
                item.setTooltipParams(jsonItem.getString("tooltipParams"));

                ArrayList<Item> dyeList = new ArrayList<Item>();
                if (null != jsonItem.opt("dyeColor")) {
                    JSONObject jsonDyeItem2 = new JSONObject(jsonItem.getString("dyeColor"));
                    JSONObject jsonDyeItem = new JSONObject(jsonDyeItem2.getString("item"));

                    Item dyeItem = new Item();
                    dyeItem.setId(jsonDyeItem.getString("id"));
                    dyeItem.setName(jsonDyeItem.getString("name"));
                    dyeItem.setIcon(jsonDyeItem.getString("icon"));
                    dyeItem.setDisplayColor(jsonDyeItem.getString("displayColor"));
                    dyeItem.setTooltipParams(jsonDyeItem.getString("tooltipParams"));

                    dyeList.add(dyeItem);
                }
                item.setDyeColor(dyeList);

                items.put(itemName, item);
            }

            hero.setItems(items);

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

        return hero;
    }

    protected void onPostExecute(Hero hero) {
        if (null != listner) {
            listner.OnTaskCompleted(hero);
        }
    }
}
