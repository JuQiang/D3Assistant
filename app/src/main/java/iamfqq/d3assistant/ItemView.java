package iamfqq.d3assistant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JuQiang on 2/25/2017.
 */

public class ItemView extends View {
    private Bitmap bmpBackground;
    private Bitmap bmpMainHand;
    private Bitmap bmpHead;
    private Bitmap bmpTorso;
    private Bitmap bmpFeet;
    private Bitmap bmpHands;
    private Bitmap bmpShoulders;
    private Bitmap bmpLegs;
    private Bitmap bmpBracers;
    private Bitmap bmpWaist;
    private Bitmap bmpRightFinger;
    private Bitmap bmpLeftFinger;
    private Bitmap bmpNeck;
    private Bitmap bmpEmptyIcon;
    private Bitmap jew1;
    private Bitmap jew2;
    private Bitmap jew3;
    private Bitmap jew4;
    private Bitmap jew5;
    private Bitmap jew6;

    private Bitmap bmpBackBlue;
    private Bitmap bmpBackYellow;
    private Bitmap bmpBackGreen;
    private Bitmap bmpBackOrange;
    private Paint paintBlue;
    private Paint paintYellow;
    private Paint paintGreen;
    private Paint paintOrange;


    private Hero hero;

    public void setHero(Hero hero) {
        this.hero = hero;

        bmpBracers = D3API.DownloadBitmap(hero.getItems().get("bracers"));
    }

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bmpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.barbarian_1_back);

        bmpMainHand = BitmapFactory.decodeResource(getResources(), R.drawable.unique_dagger_007_x1_demonhunter_male);
        bmpHead = BitmapFactory.decodeResource(getResources(), R.drawable.head);
        bmpTorso = BitmapFactory.decodeResource(getResources(), R.drawable.torso);
        bmpFeet = BitmapFactory.decodeResource(getResources(), R.drawable.feet);
        bmpHands = BitmapFactory.decodeResource(getResources(), R.drawable.hands);
        bmpShoulders = BitmapFactory.decodeResource(getResources(), R.drawable.shoulders);
        bmpLegs = BitmapFactory.decodeResource(getResources(), R.drawable.legs);
        bmpBracers = BitmapFactory.decodeResource(getResources(), R.drawable.bracers);
        bmpWaist = BitmapFactory.decodeResource(getResources(), R.drawable.waist);
        bmpRightFinger = BitmapFactory.decodeResource(getResources(), R.drawable.rightfinger);
        bmpLeftFinger = BitmapFactory.decodeResource(getResources(), R.drawable.leftfinger);
        bmpNeck = BitmapFactory.decodeResource(getResources(), R.drawable.neck);

        bmpEmptyIcon = BitmapFactory.decodeResource(getResources(), R.drawable.emptyicon);

        bmpBackBlue = BitmapFactory.decodeResource(getResources(), R.drawable.blue);
        bmpBackYellow = BitmapFactory.decodeResource(getResources(), R.drawable.yellow);
        bmpBackGreen = BitmapFactory.decodeResource(getResources(), R.drawable.green);
        bmpBackOrange = BitmapFactory.decodeResource(getResources(), R.drawable.orange);

        jew1 = BitmapFactory.decodeResource(getResources(), R.drawable.jew1);
        jew2 = BitmapFactory.decodeResource(getResources(), R.drawable.jew2);
        jew3 = BitmapFactory.decodeResource(getResources(), R.drawable.jew3);
        jew4 = BitmapFactory.decodeResource(getResources(), R.drawable.jew4);
        jew5 = BitmapFactory.decodeResource(getResources(), R.drawable.jew5);
        //jew6 = BitmapFactory.decodeResource(getResources(), R.drawable.jew6);

        double scale = 0.8;
        /* 产生reSize后的Bitmap对象 */
        Matrix matrix = new Matrix();
        matrix.postScale(0.625f, 0.625f);

        jew1 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew1), 0, 0, 96, 96, matrix, true);
        jew2 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew2), 0, 0, 96, 96, matrix, true);
        jew3 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew3), 0, 0, 96, 96, matrix, true);
        jew4 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew4), 0, 0, 96, 96, matrix, true);
        jew5 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew5), 0, 0, 96, 96, matrix, true);
        jew6 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew6), 0, 0, 96, 96, matrix, true);

        paintOrange = new Paint();
        paintGreen = new Paint();

        paintOrange.setColor(Color.argb(0xff, 194, 147, 55));
        paintOrange.setStrokeJoin(Paint.Join.ROUND);
        paintOrange.setStrokeCap(Paint.Cap.ROUND);
        paintOrange.setStrokeWidth(2);
        paintOrange.setStyle(Paint.Style.STROKE);

        paintGreen.setColor(Color.argb(0xff, 135, 167, 61));
        paintGreen.setStrokeJoin(Paint.Join.ROUND);
        paintGreen.setStrokeCap(Paint.Cap.ROUND);
        paintGreen.setStrokeWidth(2);
        paintGreen.setStyle(Paint.Style.STROKE);

        /*
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
*/
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
            setMeasuredDimension(this.getWidth(),this.getHeight());
        }
    */
    //http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/unique_dagger_007_x1_demonhunter_male.png
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bmpBackground, -1320, -350, null);

        int left = 138, top = 960;
        canvas.drawBitmap(bmpBackOrange, left, top, null);
        canvas.drawBitmap(bmpMainHand, left + 2 * 3, top + 2 * 3, null);
        canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 160, null);
        canvas.drawBitmap(jew6, left + 78, top + 177, null);
        canvas.drawRect(left, top, left + 68 * 3, top + 132 * 3, paintOrange);

        left = 464;
        top = 123;

        canvas.drawBitmap(bmpBackGreen,
                new Rect(0, 0, 201, 201),
                new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 192, top + 2 * 3 + 192), null);
        canvas.drawBitmap(bmpHead, left + 2 * 3, top - 24 * 3, null);
        canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 60, null);
        canvas.drawBitmap(jew2, left + 78, top + 78, null);
        canvas.drawRect(left, top, left + 68 * 3, top + 68 * 3, paintGreen);

        left = 438;
        top = 336;
        canvas.drawBitmap(bmpBackGreen,
                new Rect(0, 0, 201, 201),
                new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 240, top + 2 * 3 + 340), null);
        canvas.drawBitmap(bmpTorso, left + 2 * 3, top - 24 * 3, null);
        canvas.drawBitmap(bmpEmptyIcon, left + 85, top + 20, null);
        canvas.drawBitmap(jew3, left + 103, top + 38, null);
        canvas.drawBitmap(bmpEmptyIcon, left + 85, top + 120, null);
        canvas.drawBitmap(jew3, left + 103, top + 138, null);
        canvas.drawBitmap(bmpEmptyIcon, left + 85, top + 220, null);
        canvas.drawBitmap(jew3, left + 103, top + 238, null);
        canvas.drawRect(left, top, left + 250, top + 346, paintGreen);

        left = 464;
        top = 1086;
        canvas.drawBitmap(bmpBackGreen,
                new Rect(0, 0, 201, 201),
                new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 198, top + 2 * 3 + 267), null);
        canvas.drawBitmap(bmpFeet, left + 2 * 3, top - 18 * 3, null);
        canvas.drawRect(left, top, left + 201, top + 270, paintGreen);

        left = 138;
        top = 495;
        canvas.drawBitmap(bmpBackGreen,
                new Rect(0, 0, 201, 201),
                new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 198, top + 2 * 3 + 255), null);
        canvas.drawBitmap(bmpHands, left + 2 * 3, top - 18 * 3, null);
        canvas.drawRect(left, top, left + 201, top + 264, paintGreen);

        left = 216;
        top = 201;
        canvas.drawBitmap(bmpBackGreen,
                new Rect(0, 0, 201, 201),
                new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 198, top + 2 * 3 + 255), null);
        canvas.drawBitmap(bmpShoulders, left + 2 * 3, top - 20 * 3, null);
        canvas.drawRect(left, top, left + 201, top + 261, paintGreen);

        left = 464;
        top = 810;
        canvas.drawBitmap(bmpBackGreen,
                new Rect(0, 0, 201, 201),
                new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 198, top + 2 * 3 + 255), null);
        canvas.drawBitmap(bmpLegs, left + 2 * 3, top - 20 * 3, null);
        canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 20, null);
        canvas.drawBitmap(jew3, left + 78, top + 38, null);
        canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 120, null);
        canvas.drawBitmap(jew3, left + 78, top + 138, null);
        canvas.drawRect(left, top, left + 201, top + 261, paintGreen);

        left = 786;
        top = 498;
        canvas.drawBitmap(bmpBackOrange,
                new Rect(0, 0, 201, 201),
                new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 198, top + 2 * 3 + 255), null);
        canvas.drawBitmap(bmpBracers, left + 2 * 3, top - 20 * 3, null);
        canvas.drawRect(left, top, left + 201, top + 261, paintOrange);

        left = 438;
        top = 693;
        canvas.drawBitmap(bmpBackOrange,
                new Rect(0, 0, 201, 201),
                new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 240, top + 2 * 3 + 95), null);
        canvas.drawBitmap(bmpWaist, left + 2 * 3 + 24, top - 20 * 3, null);
        canvas.drawRect(left, top, left + 246, top + 100, paintOrange);

        left = 825;
        top = 798;
        canvas.drawBitmap(bmpBackGreen,
                new Rect(0, 0, 201, 201),
                new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 117, top + 2 * 3 + 116), null);
        canvas.drawBitmap(bmpRightFinger, left + 2 * 3 - 39, top - 12 * 3, null);
        canvas.drawBitmap(bmpEmptyIcon, left + 15, top + 15, null);
        canvas.drawBitmap(jew4, left + 33, top + 33, null);
        canvas.drawRect(left, top, left + 120, top + 120, paintGreen);

        left = 177;
        top = 798;
        canvas.drawBitmap(bmpBackGreen,
                new Rect(0, 0, 201, 201),
                new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 117, top + 2 * 3 + 116), null);
        canvas.drawBitmap(bmpLeftFinger, left + 2 * 3 - 39, top - 12 * 3, null);
        canvas.drawBitmap(bmpEmptyIcon, left + 15, top + 15, null);
        canvas.drawBitmap(jew5, left + 33, top + 33, null);
        canvas.drawRect(left, top, left + 120, top + 120, paintGreen);

        left = 717;
        top = 260;
        canvas.drawBitmap(bmpBackOrange,
                new Rect(0, 0, 201, 201),
                new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 162, top + 2 * 3 + 162), null);
        canvas.drawBitmap(bmpNeck, left + 2 * 3 - 21, top - 6 * 3, null);
        canvas.drawBitmap(bmpEmptyIcon, left + 36, top + 36, null);
        canvas.drawBitmap(jew1, left + 54, top + 54, null);
        canvas.drawRect(left, top, left + 165, top + 165, paintOrange);

    }
}
