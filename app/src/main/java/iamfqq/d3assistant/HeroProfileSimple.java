package iamfqq.d3assistant;

import java.io.Serializable;

/**
 * Created by JuQiang on 9/6/2016.
 */
public class HeroProfileSimple  implements Serializable {
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
}
