package iamfqq.d3assistant;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JuQiang on 9/6/2016.
 */
public class Item implements Serializable {
    public String ID;
    public String getIconUrl() {
        //图标：http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/x1_amethyst_10_demonhunter_male.png
        return "http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + this.Icon + ".png";
    }

    public String Name;
    public String Icon;
    public String DisplayColor;
    public String TooltipParams;
    public ArrayList<String> GemList = new ArrayList<String>();
    public int SocketCount=0;

    //图标：http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/x1_amethyst_10_demonhunter_male.png
    //json说明：      https://api.battlenet.com.cn/d3/data/item/Co0BCPm0re0MEgcIBBXqNOh1HWYjBlAd6tWSPx3mFdsNHZinjsAdFkn0hx0HXp3TMItaONgBQABQElgEYLEDajAKDAgAEOvxnrCBgICAGBIgCI7P0eAGEgcIBBVsF6PBMItSOABAAFASWASQAQnYAWCAAUalAZinjsCtAZinjsC1ATZVXUS4AYaFtpcHwAERGNTTwd4OUAJYAKABkqK93g6gAdTTwd4O?locale=zh_CN&apikey=YourKey
    //装备的HTML说明：http://d3.blizzard.cn/action/profile/item?param=item/CogBCJbg5fAGEgcIBBVV4wJkHayvKGId0u_p7R3OgsHbHYaQ_NodZiMGUB1yjh0hMItSOIEBQABQElgEYKICaisKDAgAEK60-dyBgIDgNhIbCM_spKYOEgcIBBVCLTepMI9SOABAAVgEkAEJgAFGpQFyjh0hrQGMWTBHtQGqRZ8TuAGj0_CDCcABARiqjZfADFACWAA
}
