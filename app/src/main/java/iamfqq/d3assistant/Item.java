package iamfqq.d3assistant;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JuQiang on 9/6/2016.
 */
public class Item implements Serializable {
    private String id;

    public ArrayList<String> getGems() {
        return this.gems;
    }

    public void setGems(ArrayList<String> socket) {
        this.gems = socket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }

    public String getTooltipParams() {
        return tooltipParams;
    }

    public void setTooltipParams(String tooltipParams) {
        this.tooltipParams = tooltipParams;
    }

    public String getIconUrl() {
        //图标：http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/x1_amethyst_10_demonhunter_male.png
        return "http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + this.icon + ".png";
    }

    public int getSocketCount(){
        return this.socketCount;
    }

    public void setSocketCount(int count){
        this.socketCount = count;
    }

    private String name;
    private String icon;
    private String displayColor;
    private String tooltipParams;
    private ArrayList<String> gems = new ArrayList<String>();
    private int socketCount=0;

    //图标：http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/x1_amethyst_10_demonhunter_male.png
    //json说明：      https://api.battlenet.com.cn/d3/data/item/Co0BCPm0re0MEgcIBBXqNOh1HWYjBlAd6tWSPx3mFdsNHZinjsAdFkn0hx0HXp3TMItaONgBQABQElgEYLEDajAKDAgAEOvxnrCBgICAGBIgCI7P0eAGEgcIBBVsF6PBMItSOABAAFASWASQAQnYAWCAAUalAZinjsCtAZinjsC1ATZVXUS4AYaFtpcHwAERGNTTwd4OUAJYAKABkqK93g6gAdTTwd4O?locale=zh_CN&apikey=heef46sr5ue44xfdgwr4wrycckgawhu5
    //装备的HTML说明：http://d3.blizzard.cn/action/profile/item?param=item/CogBCJbg5fAGEgcIBBVV4wJkHayvKGId0u_p7R3OgsHbHYaQ_NodZiMGUB1yjh0hMItSOIEBQABQElgEYKICaisKDAgAEK60-dyBgIDgNhIbCM_spKYOEgcIBBVCLTepMI9SOABAAVgEkAEJgAFGpQFyjh0hrQGMWTBHtQGqRZ8TuAGj0_CDCcABARiqjZfADFACWAA
}
