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

public class SkillPassiveView extends View {
    private String iconName;
    private String skillName;
    private Bitmap passiveBorder;

    public SkillPassiveView(Context context) {
        super(context);
    }

    public SkillPassiveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        passiveBorder = BitmapFactory.decodeResource(getResources(), R.drawable.skillpassiveborder);

        this.iconName = attrs.getAttributeValue(null, "IconName");
        this.skillName= attrs.getAttributeValue(null,"SkillName");
//        this.runeName= attrs.getAttributeValue(null,"RuneName");

    }

    public SkillPassiveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(180, 180);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(passiveBorder, 32, 32, null);
        //Bitmap bmp = skills.get(iconName);

        //canvas.drawBitmap(bmp,8, 8, null);
    }
}
