package iamfqq.d3assistant;

import android.content.Context;
import android.content.res.TypedArray;
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

public class PassiveSkillView extends View {
    public Skill Skill;
    public Bitmap Icon;
    private Bitmap border;

    public PassiveSkillView(Context context) {
        super(context);
    }

    public PassiveSkillView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SkillView);

        String borderstr = ta.getString(R.styleable.SkillView_Border);
        int borderid = getResources().getIdentifier(borderstr.replace("res/drawable", "@drawable").replace(".png", ""), "string", "iamfqq.d3assistant");

        border = BitmapFactory.decodeResource(getResources(),borderid);
    }

    public PassiveSkillView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(180, 180);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(border, 32, 32, null);
        //Bitmap bmp = skills.get(iconName);

        //canvas.drawBitmap(bmp,8, 8, null);
    }
}
