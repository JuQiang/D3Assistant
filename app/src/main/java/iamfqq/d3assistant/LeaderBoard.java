package iamfqq.d3assistant;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by JuQiang on 3/13/2017.
 */

public class LeaderBoard  implements Serializable {
    public int Order;
    public String Name;
    public String BattleTag;
    public Bitmap ClassPicture;
    public String ClassPictureUrl;
    public String HeroClass;
    public String Gender;
    public int RiftLevel;
    public String RiftTime;
    public String CompletedTime;
}
