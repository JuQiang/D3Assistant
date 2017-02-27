package iamfqq.d3assistant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JuQiang on 2/22/2017.
 */

public class ProfileView extends View {
    private Bitmap headBackgroundBitmap;
    private Map<String,Bitmap> heads = new HashMap<String,Bitmap>();
    private HeroProfileSimple heroProfileSimple;
    private Paint penName;
    private Paint penKills;
    private Paint penLevel;

    public ProfileView(Context context,HeroProfileSimple heroProfileSimple) {
        super(context);
        this.heroProfileSimple = heroProfileSimple;

        headBackgroundBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.headback);
        heads.put("barbarian_0", BitmapFactory.decodeResource(getResources(),R.drawable.barbarian_0));
        heads.put("barbarian_1", BitmapFactory.decodeResource(getResources(),R.drawable.barbarian_1));
        heads.put("crusader_0", BitmapFactory.decodeResource(getResources(),R.drawable.crusader_0));
        heads.put("crusader_1", BitmapFactory.decodeResource(getResources(),R.drawable.crusader_1));
        heads.put("demon_hunter_0", BitmapFactory.decodeResource(getResources(),R.drawable.demon_hunter_0));
        heads.put("demon_hunter_1", BitmapFactory.decodeResource(getResources(),R.drawable.demon_hunter_1));
        heads.put("monk_0", BitmapFactory.decodeResource(getResources(),R.drawable.monk_0));
        heads.put("monk_1", BitmapFactory.decodeResource(getResources(),R.drawable.monk_1));
        heads.put("witch_doctor_0", BitmapFactory.decodeResource(getResources(),R.drawable.witch_doctor_0));
        heads.put("witch_doctor_1", BitmapFactory.decodeResource(getResources(),R.drawable.witch_doctor_1));
        heads.put("wizard_0", BitmapFactory.decodeResource(getResources(),R.drawable.wizard_0));
        heads.put("wizard_1", BitmapFactory.decodeResource(getResources(),R.drawable.wizard_1));

        penName = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        penName.setColor(Color.YELLOW);
        penName.setStrokeJoin(Paint.Join.ROUND);
        penName.setStrokeCap(Paint.Cap.ROUND);
        penName.setStrokeWidth(3);
        penName.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 22, getResources().getDisplayMetrics()));

        penKills = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        penKills.setColor(Color.LTGRAY);
        penKills.setStrokeJoin(Paint.Join.ROUND);
        penKills.setStrokeCap(Paint.Cap.ROUND);
        penKills.setStrokeWidth(1);
        penKills.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        penLevel = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        penLevel.setColor(Color.WHITE);
        penLevel.setStrokeJoin(Paint.Join.ROUND);
        penLevel.setStrokeCap(Paint.Cap.ROUND);
        penLevel.setStrokeWidth(1);
        penLevel.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, getResources().getDisplayMetrics()));

    }

    public ProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension(headBackgroundBitmap.getWidth(),headBackgroundBitmap.getHeight());

    }
    @Override
    protected void onDraw(Canvas canvas) {
        // canvas 即为白纸


        canvas.drawBitmap(headBackgroundBitmap,0,0,null);
        Bitmap bmp = heads.get(
                (this.heroProfileSimple.get_class() +"_"+ this.heroProfileSimple.getGender()).replace("-", "_")
        );
        canvas.drawBitmap(
                bmp,
                54, 38, null);

        Rect nameRect = new Rect(0,760,600,200);

        Paint.FontMetricsInt fontMetrics = penName.getFontMetricsInt();
        // 转载请注明出处：http://blog.csdn.net/hursing
        int baseline = (nameRect.bottom + nameRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
        penName.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(this.heroProfileSimple.getName(), nameRect.centerX(), baseline, penName);

        canvas.drawText("消灭精英："+this.heroProfileSimple.getKillElites(),80,580,penKills);
        canvas.drawText(Integer.toString(this.heroProfileSimple.getLevel()),500,580,penLevel);

        Bitmap seasonPng = BitmapFactory.decodeResource(getResources(),R.drawable.season);
        if(Boolean.toString(this.heroProfileSimple.isSeasonal())=="true") {
            canvas.drawBitmap(seasonPng, 500, 450, null);
        }
        //super.onDraw(canvas);
    }
}
