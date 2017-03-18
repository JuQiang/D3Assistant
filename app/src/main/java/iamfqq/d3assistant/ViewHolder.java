package iamfqq.d3assistant;

import android.graphics.Bitmap;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by JUQIANG-PC on 2017/3/16.
 */

public class ViewHolder implements Serializable{
    LinearLayout layout;

    CheckBox checkBox;
    TextView tvName;
    ImageView bmpClass;
    TextView tvRiftLevel;
    TextView tvRiftTime;
    TextView tvCompletedTime;
}
