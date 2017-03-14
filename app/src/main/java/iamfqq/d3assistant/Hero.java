package iamfqq.d3assistant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by JuQiang on 9/6/2016.
 */
public class Hero implements Serializable {
    public String BattleTag;
    public int ID;
    public String Name;
    public String Class;
    public int Gender;
    public int Level;
    public int KillElites;
    public int ParagonLevel;
    public boolean Hardcore;
    public boolean Seasonal;
    public boolean Dead;
    public long LastUpdated;

    public Stat Stat;

    public Map<String,Item> ItemList;
    public ArrayList<Skill> ActiveSkillList;
    public ArrayList<Skill> PassiveSkillList;
}
