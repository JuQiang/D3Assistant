package iamfqq.d3assistant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JuQiang on 2/24/2017.
 */

public class SkillView extends View {
    private String iconName;
    private String skillName;
    private String runeName;
    private Map<String,Bitmap> skills = new HashMap<String,Bitmap>();

    public SkillView(Context context) {
        super(context);
    }
    public SkillView(Context context, AttributeSet attrs) {
        super(context, attrs);

        skills.put("skilll", BitmapFactory.decodeResource(getResources(),R.drawable.skilll));
        skills.put("skillr", BitmapFactory.decodeResource(getResources(),R.drawable.skillr));
        skills.put("skill1", BitmapFactory.decodeResource(getResources(),R.drawable.skill1));
        skills.put("skill2", BitmapFactory.decodeResource(getResources(),R.drawable.skill2));
        skills.put("skill3", BitmapFactory.decodeResource(getResources(),R.drawable.skill3));
        skills.put("skill4", BitmapFactory.decodeResource(getResources(),R.drawable.skill4));
        skills.put("skillborder", BitmapFactory.decodeResource(getResources(),R.drawable.skillborder));

        this.iconName= attrs.getAttributeValue(null,"IconName");
//        this.skillName= attrs.getAttributeValue(null,"SkillName");
//        this.runeName= attrs.getAttributeValue(null,"RuneName");

    }

    public SkillView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension(180,180);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(skills.get("skillborder"),32,32,null);
        Bitmap bmp = skills.get(iconName);

        canvas.drawBitmap(
                bmp,
                8, 8, null);
    }
}
