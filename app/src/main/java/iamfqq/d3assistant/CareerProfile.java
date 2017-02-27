package iamfqq.d3assistant;

import java.util.ArrayList;

/**
 * Created by JuQiang on 2/22/2017.
 */

public class CareerProfile {
    private String battleTag;
    private int paragonLevel;
    private int paragonLevelSeason;
    private String guildName;
    private ArrayList<HeroProfileSimple> heroes=new ArrayList<HeroProfileSimple>();
    private long lastHeroPlayed;
    private long lastUpdated;
    private int killsMonsters;
    private int killsElites;

    public String getBattleTag() {
        return battleTag;
    }

    public void setBattleTag(String battleTag) {
        this.battleTag = battleTag;
    }

    public int getParagonLevel() {
        return paragonLevel;
    }

    public void setParagonLevel(int paragonLevel) {
        this.paragonLevel = paragonLevel;
    }

    public int getParagonLevelSeason() {
        return paragonLevelSeason;
    }

    public void setParagonLevelSeason(int paragonLevelSeason) {
        this.paragonLevelSeason = paragonLevelSeason;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public ArrayList<HeroProfileSimple> getHeroes() {
        return heroes;
    }

    public long getLastHeroPlayed() {
        return lastHeroPlayed;
    }

    public void setLastHeroPlayed(long lastHeroPlayed) {
        this.lastHeroPlayed = lastHeroPlayed;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getKillsMonsters() {
        return killsMonsters;
    }

    public void setKillsMonsters(int killsMonsters) {
        this.killsMonsters = killsMonsters;
    }

    public int getKillsElites() {
        return killsElites;
    }

    public void setKillsElites(int killsElites) {
        this.killsElites = killsElites;
    }



}
