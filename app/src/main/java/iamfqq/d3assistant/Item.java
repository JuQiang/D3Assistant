package iamfqq.d3assistant;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JuQiang on 9/6/2016.
 */
public class Item implements Serializable {
    private String id;

    public ArrayList<Item> getDyeColor() {
        return dyeColor;
    }

    public void setDyeColor(ArrayList<Item> dyeColor) {
        this.dyeColor = dyeColor;
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

    private String name;
    private String icon;
    private String displayColor;
    private String tooltipParams;
    private ArrayList<Item> dyeColor;
}
