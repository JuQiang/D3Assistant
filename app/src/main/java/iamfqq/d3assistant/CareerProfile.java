package iamfqq.d3assistant;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JuQiang on 2/22/2017.
 */

public class CareerProfile implements Serializable {
    public String battleTag;
    public int paragonLevel;
    public int paragonLevelSeason;
    public String guildName;
    public ArrayList<HeroProfileSimple> heroes=new ArrayList<HeroProfileSimple>();
    public long lastHeroPlayed;
    public long lastUpdated;
    public int killsMonsters;
    public int killsElites;

}
