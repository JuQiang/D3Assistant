package iamfqq.d3assistant;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JuQiang on 2/24/2017.
 */

public class SkillView extends View {

    private Bitmap icon;
    private Bitmap border;
    private Bitmap indicatorIcon;
    private Paint paintName;
    private Paint paintRuneName;
    private boolean isPassive = false;
    private Intent intent;
    private Integer index;

    public SkillView(Context context) {
        super(context);
    }

    public SkillView(Context context, AttributeSet attrs) {
        super(context, attrs);

        intent = new Intent(context, ShowSkillTooltipActivity.class);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SkillView);

        isPassive = ta.getBoolean(R.styleable.SkillView_IsPassive, false);
        index = ta.getInt(R.styleable.SkillView_Index, 0);

        if (isPassive == false) {
            String indstr = ta.getString(R.styleable.SkillView_IndicatorIcon);
            int indid = getResources().getIdentifier(indstr.replace("res/drawable", "@drawable").replace(".png", ""), "string", "iamfqq.d3assistant");
            indicatorIcon = BitmapFactory.decodeResource(getResources(), indid);
        }
        String borderstr = ta.getString(R.styleable.SkillView_Border);
        int borderid = getResources().getIdentifier(borderstr.replace("res/drawable", "@drawable").replace(".png", ""), "string", "iamfqq.d3assistant");

        border = BitmapFactory.decodeResource(getResources(), borderid);

        paintName = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paintName.setColor(Color.WHITE);
        paintName.setStrokeJoin(Paint.Join.ROUND);
        paintName.setStrokeCap(Paint.Cap.ROUND);
        paintName.setStrokeWidth(3);
        paintName.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, getResources().getDisplayMetrics()));

        paintRuneName = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paintRuneName.setColor(Color.argb(0xff, 121, 109, 85));
        paintRuneName.setStrokeJoin(Paint.Join.ROUND);
        paintRuneName.setStrokeCap(Paint.Cap.ROUND);
        paintRuneName.setStrokeWidth(3);
        paintRuneName.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        this.setOnTouchListener(touchListener);
    }

    public SkillView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(512, 256);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(border,
                new Rect(0, 0, border.getWidth(), border.getHeight()),
                new Rect(32, 32, (int) (border.getWidth() * 1.5) + 48, (int) (border.getHeight() * 1.5) + 48),
                null);

        if (isPassive) {
            if (icon != null) {
                canvas.drawBitmap(icon,
                        new Rect(0, 0, icon.getWidth(), icon.getHeight()),
                        new Rect(56, 56, icon.getWidth() * 3 + 32, icon.getHeight() * 3 + 32),
                        null);
                canvas.drawText(this.skill.Name, icon.getWidth() * 3 + 64, 144, paintName);
            }
        } else {
            if (icon != null) {
                canvas.drawBitmap(icon,
                        new Rect(0, 0, icon.getWidth(), icon.getHeight()),
                        new Rect(36, 36, icon.getWidth() * 3 + 36, icon.getHeight() * 3 + 36),
                        null);
                canvas.drawText(this.skill.Name, icon.getWidth() * 3 + 64, 100, paintName);
                canvas.drawText(this.skill.RuneName, icon.getWidth() * 3 + 64, 160, paintRuneName);
            }
        }

        if (isPassive == false) {
            canvas.drawBitmap(indicatorIcon, 8, 8, null);
        }
    }

    private Skill skill;
    private String uriPrefix;

    public void SetSkill(String uriPrefix, Skill skill) {
        this.skill = skill;
        this.uriPrefix = uriPrefix;
        //final ProgressDialog proDialog = android.app.ProgressDialog.show(this, "奈非天！", "正在和英雄沟通，请稍候……");

        SkillTask st = new SkillTask(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {
                icon = (Bitmap) result;
                //proDialog.dismiss();
            }
        });
        st.execute("http://content.battlenet.com.cn/d3/icons-zh-cn/skills/64/" + skill.Icon + ".png",
                skill.Icon);
    }

    private int touchX, touchY;
    private OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            touchX = (int) event.getX();
            touchY = (int) event.getY();

            intent.putExtra("index", index);
            intent.putExtra("isPassive", isPassive);
            intent.putExtra("uriPrefix",uriPrefix);

            ((Activity) v.getContext()).startActivity(intent);
            //startActivity(intent);


            SkillView.super.postInvalidate();
            return false;
        }
    };
}
