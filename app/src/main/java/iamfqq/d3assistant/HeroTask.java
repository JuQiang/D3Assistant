package iamfqq.d3assistant;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
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
        String pid = params[0];
        String hid = params[1];
        boolean cached = false;
        if (params[2] == "true") cached = true;

        String urlString = "https://api.battlenet.com.cn/d3/profile/" + pid + "/hero/" + hid + "?locale=zh_CN&apikey="+D3API.Key;
        String json = D3API.DownloadString(urlString, cached, hid);

        hero.BattleTag = pid;
        hero.ID = Integer.decode(hid);

        try {
            JSONObject jsonStats = new JSONObject(json).getJSONObject("stats");
            Stat stat = new Stat();

            stat.ArcaneResist = (jsonStats.getInt("arcaneResist"));
            stat.Armor = (jsonStats.getInt("armor"));
            stat.AttackSpeed = (jsonStats.getDouble("attackSpeed"));
            stat.BlockAmountMax = ((int) (jsonStats.getDouble("blockAmountMax") * 100.0));
            stat.BlockAmountMin = ((int) (jsonStats.getDouble("blockAmountMin") * 100.0));
            stat.BlockChance = ((int) (jsonStats.getDouble("blockChance") * 100.0));
            stat.ColdResist = (jsonStats.getInt("coldResist"));
            stat.CritChance = ((int) (jsonStats.getDouble("critChance") * 100.0));
            stat.CritDamage = ((int) (jsonStats.getDouble("critDamage") * 100.0));
            stat.Damage = (jsonStats.getInt("damage"));
            stat.DamageIncrease = ((int) (jsonStats.getDouble("damageIncrease") * 100.0));
            stat.DamageReduction = ((int) (jsonStats.getDouble("damageReduction") * 100.0));
            stat.Dexterity = (jsonStats.getInt("dexterity"));
            stat.FireResist = (jsonStats.getInt("fireResist"));
            stat.GoldFind = ((int) (jsonStats.getDouble("goldFind") * 100.0));
            stat.Healing = (jsonStats.getInt("healing"));
            stat.Intelligence = (jsonStats.getInt("intelligence"));
            stat.Life = (jsonStats.getInt("life"));
            stat.LifeOnHit = (jsonStats.getInt("lifeOnHit"));
            stat.LifePerKill = (jsonStats.getInt("lifePerKill"));
            stat.LightningResist = (jsonStats.getInt("lightningResist"));
            stat.LifeSteal = (jsonStats.getInt("lifeSteal"));
            stat.MagicFind = ((int) (jsonStats.getDouble("magicFind") * 100.0));
            stat.PhysicalResist = (jsonStats.getInt("physicalResist"));
            stat.PoisonResist = (jsonStats.getInt("poisonResist"));
            stat.PrimaryResource = (jsonStats.getInt("primaryResource"));
            stat.SecondaryResource = (jsonStats.getInt("secondaryResource"));
            stat.Strength = (jsonStats.getInt("strength"));
            stat.Toughness = ((int) (jsonStats.getDouble("toughness")));
            stat.Thorns = (jsonStats.getInt("thorns"));
            stat.Vitality = (jsonStats.getInt("vitality"));

            JSONArray legendaries = new JSONObject(json).getJSONArray("legendaryPowers");
            if (String.valueOf(legendaries.get(0)) != "null") {
                stat.LegendaryPowerWeapon = ((new JSONObject(String.valueOf(legendaries.get(0)))).getString("name"));
            }
            if (String.valueOf(legendaries.get(1)) != "null") {
                stat.LegendaryPowerArmor = ((new JSONObject(String.valueOf(legendaries.get(1)))).getString("name"));
            }
            if (String.valueOf(legendaries.get(2)) != "null") {
                stat.LegendaryPowerJewelry = ((new JSONObject(String.valueOf(legendaries.get(2)))).getString("name"));
            }

            hero.Stat = stat;
            JSONObject jsonItems = new JSONObject(json).getJSONObject("items");

            int count = jsonItems.length();

            Map<String, Item> items = new HashMap<String, Item>();
            for (int i = 0; i < count; i++) {
                publishProgress((i + 1) * 100 / count);
                String itemName = jsonItems.names().getString(i);
                JSONObject jsonItem = jsonItems.getJSONObject(itemName);

                Item item = new Item();
                item.ID = jsonItem.getString("id");
                item.Name = jsonItem.getString("name");
                item.Icon = jsonItem.getString("icon");
                item.DisplayColor = jsonItem.getString("displayColor");
                item.TooltipParams = jsonItem.getString("tooltipParams");
                Item tmpItem = D3API.getGemsInformation(item.TooltipParams);
                item.GemList = tmpItem.GemList;
                item.SocketCount = tmpItem.SocketCount;

                items.put(itemName, item);
                D3API.DownloadBitmap(item.getIconUrl(), item.Icon);
                for (int j = 0; j < item.GemList.size(); j++) {
                    String icon = item.GemList.get(j);
                    D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + icon + ".png", icon);
                }
            }

            hero.ItemList = items;
            hero.Name = (new JSONObject(json)).getString("name");

            JSONArray jsonActiveSkills = new JSONObject(json).getJSONObject("skills").getJSONArray("active");

            hero.ActiveSkillList = new ArrayList<Skill>();
            for (int i = 0; i < jsonActiveSkills.length(); i++) {
                JSONObject skill = jsonActiveSkills.getJSONObject(i);
                Skill activeSkill = new Skill();

                if(skill.length()>0) {
                    activeSkill.Icon = skill.getJSONObject("skill").getString("icon");
                    activeSkill.Name = skill.getJSONObject("skill").getString("name");
                    activeSkill.Tooltip = skill.getJSONObject("skill").getString("tooltipUrl");
                    if (skill.has("rune")) {
                        activeSkill.RuneName = skill.getJSONObject("rune").getString("name");
                        activeSkill.RuneTooltip = skill.getJSONObject("rune").getString("tooltipParams");
                    }
                }

                hero.ActiveSkillList.add(activeSkill);
            }

            JSONArray jsonPassiveSkills = new JSONObject(json).getJSONObject("skills").getJSONArray("passive");
            hero.PassiveSkillList = new ArrayList<Skill>();
            for (int i = 0; i < jsonPassiveSkills.length(); i++) {
                JSONObject skill = jsonPassiveSkills.getJSONObject(i);
                Skill passiveSkill = new Skill();

                if(skill.length()>0) {
                    passiveSkill.Icon = skill.getJSONObject("skill").getString("icon");
                    passiveSkill.Name = skill.getJSONObject("skill").getString("name");
                    passiveSkill.Tooltip = skill.getJSONObject("skill").getString("tooltipUrl");
                }

                hero.PassiveSkillList.add(passiveSkill);
            }

        } catch (JSONException e) {
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
