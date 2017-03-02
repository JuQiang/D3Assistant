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

import java.util.Map;

/**
 * Created by JuQiang on 2/25/2017.
 */

public class ItemView extends View {
    private Bitmap bmpBackground;

    private Bitmap bmpMainHand;
    private Bitmap bmpOffHand;
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


    private Item itemMainHand;
    private Item itemOffHand;
    private Item itemHead;
    private Item itemTorso;
    private Item itemFeet;
    private Item itemHands;
    private Item itemShoulders;
    private Item itemLegs;
    private Item itemBracers;
    private Item itemWaist;
    private Item itemRightFinger;
    private Item itemLeftFinger;
    private Item itemNeck;

    private Bitmap bmpEmptyIcon;

    private Bitmap bmpBackBlue;
    private Bitmap bmpBackYellow;
    private Bitmap bmpBackGreen;
    private Bitmap bmpBackOrange;

    private Paint paintNope;
    private Paint paintGreen;
    private Paint paintOrange;
    private Paint paintBlue;
    private Paint paintYellow;


    private Hero hero;
    private Map<String, Item> items;

    public void setHero(Hero hero) {
        this.hero = hero;
        items = this.hero.getItems();

        itemBracers = hero.getItems().get("bracers");
        if (itemBracers != null) {
            bmpBracers = D3API.DownloadBitmap(itemBracers.getIconUrl(), itemBracers.getIcon());
        }
        itemFeet = hero.getItems().get("feet");
        if (itemFeet != null) {
            bmpFeet = D3API.DownloadBitmap(itemFeet.getIconUrl(), itemFeet.getIcon());
        }
        itemHands = hero.getItems().get("hands");
        if (itemHands != null) {
            bmpHands = D3API.DownloadBitmap(itemHands.getIconUrl(), itemHands.getIcon());
        }
        itemHead = hero.getItems().get("head");
        if (itemHead != null) {
            bmpHead = D3API.DownloadBitmap(itemHead.getIconUrl(), itemHead.getIcon());
        }
        itemLeftFinger = hero.getItems().get("leftFinger");
        if (itemLeftFinger != null) {
            bmpLeftFinger = D3API.DownloadBitmap(itemLeftFinger.getIconUrl(), itemLeftFinger.getIcon());
        }
        itemLegs = hero.getItems().get("legs");
        if (itemLegs != null) {
            bmpLegs = D3API.DownloadBitmap(itemLegs.getIconUrl(), itemLegs.getIcon());
        }
        itemMainHand = hero.getItems().get("mainHand");
        if (itemMainHand != null) {
            bmpMainHand = D3API.DownloadBitmap(itemMainHand.getIconUrl(), itemMainHand.getIcon());
        }
        itemNeck = hero.getItems().get("neck");
        if (itemNeck != null) {
            bmpNeck = D3API.DownloadBitmap(itemNeck.getIconUrl(), itemNeck.getIcon());
        }
        itemOffHand = hero.getItems().get("offHand");
        if (itemOffHand != null) {
            bmpOffHand = D3API.DownloadBitmap(itemOffHand.getIconUrl(), itemOffHand.getIcon());
        }

        itemRightFinger = hero.getItems().get("rightFinger");
        if (itemRightFinger != null) {
            bmpRightFinger = D3API.DownloadBitmap(itemRightFinger.getIconUrl(), itemRightFinger.getIcon());
        }
        itemShoulders = hero.getItems().get("shoulders");
        if (itemShoulders != null) {
            bmpShoulders = D3API.DownloadBitmap(itemShoulders.getIconUrl(), itemShoulders.getIcon());
        }
        itemTorso = hero.getItems().get("torso");
        if (itemTorso != null) {
            bmpTorso = D3API.DownloadBitmap(itemTorso.getIconUrl(), itemTorso.getIcon());
        }
        itemWaist = hero.getItems().get("waist");
        if (itemWaist != null) {
            bmpWaist = D3API.DownloadBitmap(itemWaist.getIconUrl(), itemWaist.getIcon());
        }
    }

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bmpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.barbarian_1_back);
        bmpEmptyIcon = BitmapFactory.decodeResource(getResources(), R.drawable.emptyicon);

        bmpBackBlue = BitmapFactory.decodeResource(getResources(), R.drawable.blue);
        bmpBackYellow = BitmapFactory.decodeResource(getResources(), R.drawable.yellow);
        bmpBackGreen = BitmapFactory.decodeResource(getResources(), R.drawable.green);
        bmpBackOrange = BitmapFactory.decodeResource(getResources(), R.drawable.orange);

        double scale = 0.8;
        /* 产生reSize后的Bitmap对象
        Matrix matrix = new Matrix();
        matrix.postScale(0.625f, 0.625f);

        jew1 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew1), 0, 0, 96, 96, matrix, true);
        jew2 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew2), 0, 0, 96, 96, matrix, true);
        jew3 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew3), 0, 0, 96, 96, matrix, true);
        jew4 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew4), 0, 0, 96, 96, matrix, true);
        jew5 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew5), 0, 0, 96, 96, matrix, true);
        jew6 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jew6), 0, 0, 96, 96, matrix, true);
*/
        paintYellow = new Paint();
        paintOrange = new Paint();
        paintGreen = new Paint();
        paintBlue = new Paint();
        paintNope = new Paint();

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

        paintYellow.setColor(Color.argb(0xff, 177, 167, 60));
        paintYellow.setStrokeJoin(Paint.Join.ROUND);
        paintYellow.setStrokeCap(Paint.Cap.ROUND);
        paintYellow.setStrokeWidth(2);
        paintYellow.setStyle(Paint.Style.STROKE);

        paintBlue.setColor(Color.argb(0xff, 144, 200, 211));
        paintBlue.setStrokeJoin(Paint.Join.ROUND);
        paintBlue.setStrokeCap(Paint.Cap.ROUND);
        paintBlue.setStrokeWidth(2);
        paintBlue.setStyle(Paint.Style.STROKE);

        paintNope.setColor(Color.TRANSPARENT);
        paintNope.setStrokeJoin(Paint.Join.ROUND);
        paintNope.setStrokeCap(Paint.Cap.ROUND);
        paintNope.setStrokeWidth(2);
        paintNope.setStyle(Paint.Style.STROKE);

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

    private Bitmap GetDisplayColorBitmap(String displayColor) {
        switch (displayColor) {
            case "blue":
                return bmpBackBlue;
            case "green":
                return bmpBackGreen;
            case "orange":
                return bmpBackOrange;
            case "yellow":
                return bmpBackYellow;
            default:
                return null;
        }
    }

    private Paint GetPaint(String displayColor) {
        switch (displayColor) {
            case "yellow":
                return paintYellow;
            case "orange":
                return paintOrange;
            case "green":
                return paintGreen;
            case "blue":
                return paintBlue;
            default:
                return paintNope;
        }
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

        if (itemMainHand != null) {
            canvas.drawRect(left, top, left + 68 * 3, top + 132 * 3, GetPaint(itemMainHand.getDisplayColor()));

            Bitmap backMainHand = GetDisplayColorBitmap(itemMainHand.getDisplayColor());
            if (backMainHand != null) {
                canvas.drawBitmap(backMainHand,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 192, top + 2 * 3 + 384), null);
            }
        }
        if (bmpMainHand != null) {
            canvas.drawBitmap(bmpMainHand,
                    new Rect(0, 0, bmpMainHand.getWidth(), bmpMainHand.getHeight()),
                    new Rect(left + 2 * 3, top + 2 * 3, left + 2 * 3 + bmpMainHand.getWidth() * 3, top + 2 * 3 + bmpMainHand.getHeight() * 3), null);
        }

        if (itemMainHand != null) {
            for (int i = 0; i < itemMainHand.getSocketCount(); i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 160, null);
            }
            for (int i = 0; i < itemMainHand.getGems().size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemMainHand.getGems().get(i) + ".png", itemMainHand.getGems().get(i));
                canvas.drawBitmap(bmp, left + 78, top + 177, null);
            }
        }

        left = 786;
        top = 960;

        if (itemOffHand != null) {
            canvas.drawRect(left, top, left + 68 * 3, top + 132 * 3, GetPaint(itemOffHand.getDisplayColor()));

            Bitmap backOffHand = GetDisplayColorBitmap(itemOffHand.getDisplayColor());
            if (backOffHand != null) {
                canvas.drawBitmap(backOffHand,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 192, top + 2 * 3 + 384), null);
            }
        }
        if (bmpOffHand != null) {
            canvas.drawBitmap(bmpOffHand,
                    new Rect(0, 0, bmpOffHand.getWidth(), bmpOffHand.getHeight()),
                    new Rect(left + 2 * 3, top + 2 * 3, left + 2 * 3 + bmpOffHand.getWidth() * 3, top + 2 * 3 + bmpOffHand.getHeight() * 3), null);
        }

        if (itemOffHand != null) {
            for (int i = 0; i < itemOffHand.getSocketCount(); i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 160, null);
            }
            for (int i = 0; i < itemOffHand.getGems().size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemOffHand.getGems().get(i) + ".png", itemOffHand.getGems().get(i));
                canvas.drawBitmap(bmp, left + 78, top + 177, null);
            }
        }

        left = 464;
        top = 123;

        if (itemHead != null) {
            canvas.drawRect(left, top, left + 68 * 3, top + 68 * 3, GetPaint(itemHead.getDisplayColor()));

            Bitmap backHead = GetDisplayColorBitmap(itemHead.getDisplayColor());
            if (backHead != null) {
                canvas.drawBitmap(GetDisplayColorBitmap(items.get("head").getDisplayColor()),
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 192, top + 2 * 3 + 192), null);
            }
        }
        if (bmpHead != null) {
            canvas.drawBitmap(bmpHead,
                    new Rect(0, 0, bmpHead.getWidth(), bmpHead.getHeight()),
                    new Rect(left + 2 * 3, top - 24 * 3, left + 2 * 3 + bmpHead.getWidth() * 3, top - 24 * 3 + bmpHead.getHeight() * 3), null);
        }
        if (itemHead != null) {
            for (int i = 0; i < itemHead.getSocketCount(); i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 60, null);
            }
            for (int i = 0; i < itemHead.getGems().size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemHead.getGems().get(i) + ".png", itemHead.getGems().get(i));
                canvas.drawBitmap(bmp, left + 78, top + 78, null);
            }
        }

        left = 438;
        top = 336;
        if (itemTorso != null) {
            canvas.drawRect(left, top, left + 250, top + 346, GetPaint(itemTorso.getDisplayColor()));

            Bitmap backTorso = GetDisplayColorBitmap(itemTorso.getDisplayColor());
            if (backTorso != null) {
                canvas.drawBitmap(backTorso,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 240, top + 2 * 3 + 340), null);
            }
        }
        if (bmpTorso != null) {
            canvas.drawBitmap(bmpTorso,
                    new Rect(0, 0, bmpTorso.getWidth(), bmpTorso.getHeight()),
                    new Rect(left + 2 * 3, top - 24 * 3, left + 2 * 3 + bmpTorso.getWidth() * 3, top - 24 * 3 + bmpTorso.getHeight() * 3), null);
        }
        if (itemTorso != null) {
            for (int i = 0; i < itemTorso.getSocketCount(); i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 85, top + 20 + 100 * i, null);
            }
            for (int i = 0; i < itemTorso.getGems().size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemTorso.getGems().get(i) + ".png", itemTorso.getGems().get(i));
                canvas.drawBitmap(bmp, left + 103, top + 38 + 100 * i, null);
            }
        }

        left = 464;
        top = 1086;
        if (itemFeet != null) {
            canvas.drawRect(left, top, left + 201, top + 270, GetPaint(itemFeet.getDisplayColor()));

            Bitmap backFeet = GetDisplayColorBitmap(itemFeet.getDisplayColor());
            if (backFeet != null) {
                canvas.drawBitmap(backFeet,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 192, top + 2 * 3 + 261), null);
            }
        }
        if (bmpFeet != null) {
            canvas.drawBitmap(bmpFeet,
                    new Rect(0, 0, bmpFeet.getWidth(), bmpFeet.getHeight()),
                    new Rect(left + 2 * 3, top - 18 * 3, left + 2 * 3 + bmpFeet.getWidth() * 3, top - 18 * 3 + bmpFeet.getHeight() * 3), null);
        }

        left = 138;
        top = 495;
        if (itemHands != null) {
            canvas.drawRect(left, top, left + 201, top + 264, GetPaint(itemHands.getDisplayColor()));
            Bitmap backHands = GetDisplayColorBitmap(itemHands.getDisplayColor());
            if (backHands != null) {
                canvas.drawBitmap(backHands,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 192, top + 2 * 3 + 255), null);
            }
        }
        if (bmpHands != null) {
            canvas.drawBitmap(bmpHands,
                    new Rect(0, 0, bmpHands.getWidth(), bmpHands.getHeight()),
                    new Rect(left + 2 * 3, top - 18 * 3, left + 2 * 3 + bmpHands.getWidth() * 3, top - 18 * 3 + bmpHands.getHeight() * 3), null);
        }


        left = 216;
        top = 201;
        if (itemShoulders != null) {
            canvas.drawRect(left, top, left + 201, top + 261, GetPaint(itemShoulders.getDisplayColor()));

            Bitmap backShoulders = GetDisplayColorBitmap(itemShoulders.getDisplayColor());
            if (backShoulders != null) {
                canvas.drawBitmap(backShoulders,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 192, top + 2 * 3 + 255), null);
            }
        }
        if (bmpShoulders != null) {
            canvas.drawBitmap(bmpShoulders,
                    new Rect(0, 0, bmpShoulders.getWidth(), bmpShoulders.getHeight()),
                    new Rect(left + 2 * 3, top - 20 * 3, left + 2 * 3 + bmpShoulders.getWidth() * 3, top - 20 * 3 + bmpShoulders.getHeight() * 3), null);
        }

        left = 464;
        top = 810;
        if (itemLegs != null) {
            canvas.drawRect(left, top, left + 201, top + 261, GetPaint(itemLegs.getDisplayColor()));

            Bitmap backLegs = GetDisplayColorBitmap(itemLegs.getDisplayColor());
            if (backLegs != null) {
                canvas.drawBitmap(backLegs,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 192, top + 2 * 3 + 255), null);
            }
        }
        if (bmpLegs != null) {
            canvas.drawBitmap(bmpLegs,
                    new Rect(0, 0, bmpLegs.getWidth(), bmpLegs.getHeight()),
                    new Rect(left + 2 * 3, top - 20 * 3, left + 2 * 3 + bmpLegs.getWidth() * 3, top - 20 * 3 + bmpLegs.getHeight() * 3), null);
        }
        if (itemLegs != null) {
            for (int i = 0; i < itemLegs.getSocketCount(); i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 20 + 100 * i, null);
            }
            for (int i = 0; i < itemLegs.getGems().size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemLegs.getGems().get(i) + ".png", itemLegs.getGems().get(i));
                canvas.drawBitmap(bmp, left + 78, top + 38 + 100 * i, null);
            }
        }

        left = 786;
        top = 498;
        if (itemBracers != null) {
            canvas.drawRect(left, top, left + 201, top + 261, GetPaint(itemBracers.getDisplayColor()));

            Bitmap backBracers = GetDisplayColorBitmap(itemBracers.getDisplayColor());
            if (backBracers != null) {
                canvas.drawBitmap(backBracers,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 192, top + 2 * 3 + 255), null);
            }
        }
        if (bmpBracers != null) {
            canvas.drawBitmap(bmpBracers,
                    new Rect(0, 0, bmpBracers.getWidth(), bmpBracers.getHeight()),
                    new Rect(left + 2 * 3, top - 20 * 3, left + 2 * 3 + bmpBracers.getWidth() * 3, top - 20 * 3 + bmpBracers.getHeight() * 3), null);
        }

        left = 438;
        top = 693;
        if (itemWaist != null) {
            canvas.drawRect(left, top, left + 246, top + 100, GetPaint(itemWaist.getDisplayColor()));

            Bitmap backWaist = GetDisplayColorBitmap(itemWaist.getDisplayColor());
            if (backWaist != null) {
                canvas.drawBitmap(backWaist,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 240, top + 2 * 3 + 90), null);
            }
        }
        if (bmpWaist != null) {
            canvas.drawBitmap(bmpWaist,
                    new Rect(0, 0, bmpWaist.getWidth(), bmpWaist.getHeight()),
                    new Rect(left + 2 * 3 + 24, top - 20 * 3 + 15, left + 2 * 3 + 24 + bmpWaist.getWidth() * 3, top - 20 * 3 + bmpWaist.getHeight() * 3 + 15), null);
        }

        left = 825;
        top = 798;
        if (itemRightFinger != null) {
            canvas.drawRect(left, top, left + 120, top + 120, GetPaint(itemRightFinger.getDisplayColor()));

            Bitmap backRightFinger = GetDisplayColorBitmap(itemRightFinger.getDisplayColor());
            if (backRightFinger != null) {
                canvas.drawBitmap(backRightFinger,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 111, top + 2 * 3 + 110), null);
            }
        }
        if (bmpRightFinger != null) {
            canvas.drawBitmap(bmpRightFinger,
                    new Rect(0, 0, bmpRightFinger.getWidth(), bmpRightFinger.getHeight()),
                    new Rect(left + 2 * 3 - 39, top - 12 * 3, left + 2 * 3 - 39 + bmpRightFinger.getWidth() * 3, top - 12 * 3 + bmpRightFinger.getHeight() * 3), null);
        }
        if (itemRightFinger != null) {
            for (int i = 0; i < itemRightFinger.getSocketCount(); i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 15, top + 15, null);
            }
            for (int i = 0; i < itemRightFinger.getGems().size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemRightFinger.getGems().get(i) + ".png", itemRightFinger.getGems().get(i));
                canvas.drawBitmap(bmp, left + 33, top + 33, null);
            }
        }

        left = 177;
        top = 798;
        if (itemLeftFinger != null) {
            canvas.drawRect(left, top, left + 120, top + 120, GetPaint(itemLeftFinger.getDisplayColor()));

            Bitmap backLeftFinger = GetDisplayColorBitmap(itemLeftFinger.getDisplayColor());
            if (backLeftFinger != null) {
                canvas.drawBitmap(backLeftFinger,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 111, top + 2 * 3 + 110), null);
            }
        }
        if (bmpLeftFinger != null) {
            canvas.drawBitmap(bmpLeftFinger,
                    new Rect(0, 0, bmpLeftFinger.getWidth(), bmpLeftFinger.getHeight()),
                    new Rect(left + 2 * 3 - 39, top - 12 * 3, left + 2 * 3 - 39 + bmpLeftFinger.getWidth() * 3, top - 12 * 3 + bmpLeftFinger.getHeight() * 3), null);
        }
        if (itemLeftFinger != null) {
            for (int i = 0; i < itemLeftFinger.getSocketCount(); i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 15, top + 15, null);
            }
            for (int i = 0; i < itemLeftFinger.getGems().size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemLeftFinger.getGems().get(i) + ".png", itemLeftFinger.getGems().get(i));
                canvas.drawBitmap(bmp, left + 33, top + 33, null);
            }
        }

        left = 717;
        top = 260;
        if (itemNeck != null) {
            canvas.drawRect(left, top, left + 165, top + 165, GetPaint(itemNeck.getDisplayColor()));

            Bitmap backNeck = GetDisplayColorBitmap(itemNeck.getDisplayColor());
            if (backNeck != null) {
                canvas.drawBitmap(backNeck,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 156, top + 2 * 3 + 156), null);
            }
        }
        if (bmpNeck != null) {
            canvas.drawBitmap(bmpNeck,
                    new Rect(0, 0, bmpNeck.getWidth(), bmpNeck.getHeight()),
                    new Rect(left + 2 * 3 - 21, top - 6 * 3, left + 2 * 3 - 21 + bmpNeck.getWidth() * 3, top - 6 * 3 + bmpNeck.getHeight() * 3), null);
        }
        if (itemNeck != null) {
            for (int i = 0; i < itemNeck.getSocketCount(); i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 36, top + 36, null);
            }
            for (int i = 0; i < itemNeck.getGems().size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemNeck.getGems().get(i) + ".png", itemNeck.getGems().get(i));
                canvas.drawBitmap(bmp, left + 54, top + 54, null);
            }
        }
    }
}
