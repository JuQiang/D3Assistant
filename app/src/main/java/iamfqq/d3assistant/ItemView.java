package iamfqq.d3assistant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
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
    private Paint paintName;

    private Hero hero;
    private Map<String, Item> items;
    private Map<String, Rect> itemsRect;

    private Intent intent;
    public void setHero(Hero hero) {

        this.hero = hero;
        items = this.hero.ItemList;
        itemsRect = new HashMap<String, Rect>();

        itemBracers = hero.ItemList.get("bracers");
        if (itemBracers != null) {
            bmpBracers = D3API.DownloadBitmap(itemBracers.getIconUrl(), itemBracers.Icon);
        }
        itemsRect.put("bracers", new Rect(786, 498, 786 + 201, 498 + 261));

        itemFeet = hero.ItemList.get("feet");
        if (itemFeet != null) {
            bmpFeet = D3API.DownloadBitmap(itemFeet.getIconUrl(), itemFeet.Icon);
        }
        itemsRect.put("feet", new Rect(464, 1086, 464 + 201, 1086 + 270));

        itemHands = hero.ItemList.get("hands");
        if (itemHands != null) {
            bmpHands = D3API.DownloadBitmap(itemHands.getIconUrl(), itemHands.Icon);
        }
        itemsRect.put("hands", new Rect(138, 495, 138 + 201, 495 + 264));

        itemHead = hero.ItemList.get("head");
        if (itemHead != null) {
            bmpHead = D3API.DownloadBitmap(itemHead.getIconUrl(), itemHead.Icon);
        }
        itemsRect.put("head", new Rect(464, 123, 464 + 204, 123 + 204));

        itemLeftFinger = hero.ItemList.get("leftFinger");
        if (itemLeftFinger != null) {
            bmpLeftFinger = D3API.DownloadBitmap(itemLeftFinger.getIconUrl(), itemLeftFinger.Icon);
        }
        itemsRect.put("leftFinger", new Rect(177, 798, 177 + 120, 798 + 120));

        itemLegs = hero.ItemList.get("legs");
        if (itemLegs != null) {
            bmpLegs = D3API.DownloadBitmap(itemLegs.getIconUrl(), itemLegs.Icon);
        }
        itemsRect.put("legs", new Rect(464, 810, 464 + 201, 810 + 261));

        itemMainHand = hero.ItemList.get("mainHand");
        if (itemMainHand != null) {
            bmpMainHand = D3API.DownloadBitmap(itemMainHand.getIconUrl(), itemMainHand.Icon);
        }
        itemsRect.put("mainHand", new Rect(138, 960, 138 + 204, 960 + 396));

        itemNeck = hero.ItemList.get("neck");
        if (itemNeck != null) {
            bmpNeck = D3API.DownloadBitmap(itemNeck.getIconUrl(), itemNeck.Icon);
        }
        itemsRect.put("neck", new Rect(717, 260, 717 + 165, 260 + 165));

        itemOffHand = hero.ItemList.get("offHand");
        if (itemOffHand != null) {
            bmpOffHand = D3API.DownloadBitmap(itemOffHand.getIconUrl(), itemOffHand.Icon);
        }
        itemsRect.put("offHand", new Rect(786, 960, 786 + 204, 960 + 396));

        itemRightFinger = hero.ItemList.get("rightFinger");
        if (itemRightFinger != null) {
            bmpRightFinger = D3API.DownloadBitmap(itemRightFinger.getIconUrl(), itemRightFinger.Icon);
        }
        itemsRect.put("rightFinger", new Rect(825, 798, 825 + 120, 798 + 120));

        itemShoulders = hero.ItemList.get("shoulders");
        if (itemShoulders != null) {
            bmpShoulders = D3API.DownloadBitmap(itemShoulders.getIconUrl(), itemShoulders.Icon);
        }
        itemsRect.put("shoulders", new Rect(216, 201, 216 + 201, 201 + 261));

        itemTorso = hero.ItemList.get("torso");
        if (itemTorso != null) {
            bmpTorso = D3API.DownloadBitmap(itemTorso.getIconUrl(), itemTorso.Icon);
        }
        itemsRect.put("torso", new Rect(438, 336, 438 + 250, 336 + 346));

        itemWaist = hero.ItemList.get("waist");
        if (itemWaist != null) {
            bmpWaist = D3API.DownloadBitmap(itemWaist.getIconUrl(), itemWaist.Icon);
        }
        itemsRect.put("waist", new Rect(438, 693, 438 + 246, 693 + 100));
    }

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        intent = new Intent(context,ShowItemTooltipActivity.class);
        bmpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.barbarian_1_back);
        bmpEmptyIcon = BitmapFactory.decodeResource(getResources(), R.drawable.emptyicon);

        bmpBackBlue = BitmapFactory.decodeResource(getResources(), R.drawable.blue);
        bmpBackYellow = BitmapFactory.decodeResource(getResources(), R.drawable.yellow);
        bmpBackGreen = BitmapFactory.decodeResource(getResources(), R.drawable.green);
        bmpBackOrange = BitmapFactory.decodeResource(getResources(), R.drawable.orange);

        double scale = 0.8;

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

        paintName = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paintName.setColor(Color.YELLOW);
        paintName.setStrokeJoin(Paint.Join.ROUND);
        paintName.setStrokeCap(Paint.Cap.ROUND);
        paintName.setStrokeWidth(3);
        paintName.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        this.setOnTouchListener(touchListener);
    }

    private int touchX, touchY;
    private OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            touchX = (int) event.getX();
            touchY = (int) event.getY();

            String tooltip = getItemTooltip((int) event.getX(), (int) event.getY());
            if(tooltip.isEmpty()==false){
                intent.putExtra("stringItemTooltip", tooltip);
                ((Activity)v.getContext()).startActivity(intent);
                //startActivity(intent);
            }

            ItemView.super.postInvalidate();
            return false;
        }
    };

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
    //http://d3.blizzard.cn/action/profile/item?param=item/CogBCJb1yIoLEgcIBBVV4wJkHYxZMEcd5-3vCx2eT4PcHc6CwdsdeTI9qh0NDRyTMIteOJICQABQElgEYJICaisKDAgAEK60-dyBgIDgNhIbCM_spKYOEgcIBBVCLTepMI9SOABAAVgEkAEJgAFGpQGeT4PcrQGQEa7atQGqRZ8TuAHs1NCZBsABAhiqjZfADFACWAA
    //http://d3.blizzard.cn/action/profile/career/%E6%96%B9%E6%9E%AA%E6%9E%AA-5690/36760898/skill/4

    private String getItemTooltip(int x, int y) {
        if(isPointInRect(x,y,itemsRect.get("bracers")) && itemBracers!=null)return itemBracers.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("feet")) && itemFeet!=null)return itemFeet.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("hands")) && itemHands!=null)return itemHands.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("head")) && itemHead!=null)return itemHead.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("leftFinger")) && itemLeftFinger!=null)return itemLeftFinger.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("legs")) && itemLegs!=null)return itemLegs.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("mainHand")) && itemMainHand!=null)return itemMainHand.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("neck")) && itemNeck!=null)return itemNeck.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("offHand")) && itemOffHand!=null)return itemOffHand.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("rightFinger")) && itemRightFinger!=null)return itemRightFinger.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("shoulders")) && itemShoulders!=null)return itemShoulders.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("torso")) && itemTorso!=null)return itemTorso.TooltipParams;
        if(isPointInRect(x,y,itemsRect.get("waist")) && itemWaist!=null)return itemWaist.TooltipParams;

        return "";
    }

    private boolean isPointInRect(int x, int y, Rect r){
        return (x>=r.left && x<=r.right && y>=r.top && y<=r.bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(bmpBackground, -1320, -350, null);
        canvas.drawText(String.valueOf(touchX) + "," + String.valueOf(touchY),
                120, 120, paintName);

        int left = 138, top = 960;

        if (itemMainHand != null) {
            canvas.drawRect(itemsRect.get("mainHand"), GetPaint(itemMainHand.DisplayColor));

            Bitmap backMainHand = GetDisplayColorBitmap(itemMainHand.DisplayColor);
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
            for (int i = 0; i < itemMainHand.SocketCount; i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 160, null);
            }
            for (int i = 0; i < itemMainHand.GemList.size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemMainHand.GemList.get(i) + ".png", itemMainHand.GemList.get(i));
                canvas.drawBitmap(bmp, left + 78, top + 177, null);
            }
        }

        left = 786;
        top = 960;

        if (itemOffHand != null) {
            canvas.drawRect(itemsRect.get("offHand"), GetPaint(itemOffHand.DisplayColor));

            Bitmap backOffHand = GetDisplayColorBitmap(itemOffHand.DisplayColor);
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
            for (int i = 0; i < itemOffHand.SocketCount; i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 160, null);
            }
            for (int i = 0; i < itemOffHand.GemList.size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemOffHand.GemList.get(i) + ".png", itemOffHand.GemList.get(i));
                canvas.drawBitmap(bmp, left + 78, top + 177, null);
            }
        }

        left = 464;
        top = 123;

        if (itemHead != null) {
            canvas.drawRect(itemsRect.get("head"), GetPaint(itemHead.DisplayColor));

            Bitmap backHead = GetDisplayColorBitmap(itemHead.DisplayColor);
            if (backHead != null) {
                canvas.drawBitmap(GetDisplayColorBitmap(items.get("head").DisplayColor),
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
            for (int i = 0; i < itemHead.SocketCount; i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 60, null);
            }
            for (int i = 0; i < itemHead.GemList.size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemHead.GemList.get(i) + ".png", itemHead.GemList.get(i));
                canvas.drawBitmap(bmp, left + 78, top + 78, null);
            }
        }

        left = 438;
        top = 336;
        if (itemTorso != null) {
            canvas.drawRect(itemsRect.get("torso"), GetPaint(itemTorso.DisplayColor));

            Bitmap backTorso = GetDisplayColorBitmap(itemTorso.DisplayColor);
            if (backTorso != null) {
                canvas.drawBitmap(backTorso,
                        new Rect(0, 0, 201, 201),
                        new Rect(left + 1 * 3, top + 1 * 3, left + 2 * 3 + 240, top + 2 * 3 + 340), null);
            }
        }
        if (bmpTorso != null) {
            if ((bmpTorso.getWidth() == 82) && (bmpTorso.getHeight() == 164)) {
                canvas.drawBitmap(bmpTorso,
                        new Rect(0, 0, bmpTorso.getWidth(), bmpTorso.getHeight()),
                        new Rect(left + 2 * 3, top - 24 * 3, left + 2 * 3 + bmpTorso.getWidth() * 3, top - 24 * 3 + bmpTorso.getHeight() * 3), null);
            } else {
                canvas.drawBitmap(bmpTorso,
                        new Rect(0, 0, bmpTorso.getWidth(), bmpTorso.getHeight()),
                        new Rect(left + 2 * 3+32,top - 24 * 3+32, left + 2 * 3 + bmpTorso.getWidth() * 3+32, top - 24 * 3 + bmpTorso.getHeight() * 3+32), null);
            }
        }
        if (itemTorso != null) {
            for (int i = 0; i < itemTorso.SocketCount; i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 85, top + 20 + 100 * i, null);
            }
            for (int i = 0; i < itemTorso.GemList.size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemTorso.GemList.get(i) + ".png", itemTorso.GemList.get(i));
                canvas.drawBitmap(bmp, left + 103, top + 38 + 100 * i, null);
            }
        }

        left = 464;
        top = 1086;
        if (itemFeet != null) {
            canvas.drawRect(itemsRect.get("feet"), GetPaint(itemFeet.DisplayColor));

            Bitmap backFeet = GetDisplayColorBitmap(itemFeet.DisplayColor);
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
            canvas.drawRect(itemsRect.get("hands"), GetPaint(itemHands.DisplayColor));
            Bitmap backHands = GetDisplayColorBitmap(itemHands.DisplayColor);
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
            canvas.drawRect(itemsRect.get("shoulders"), GetPaint(itemShoulders.DisplayColor));

            Bitmap backShoulders = GetDisplayColorBitmap(itemShoulders.DisplayColor);
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
            canvas.drawRect(itemsRect.get("legs"), GetPaint(itemLegs.DisplayColor));

            Bitmap backLegs = GetDisplayColorBitmap(itemLegs.DisplayColor);
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
            for (int i = 0; i < itemLegs.SocketCount; i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 60, top + 20 + 100 * i, null);
            }
            for (int i = 0; i < itemLegs.GemList.size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemLegs.GemList.get(i) + ".png", itemLegs.GemList.get(i));
                canvas.drawBitmap(bmp, left + 78, top + 38 + 100 * i, null);
            }
        }

        left = 786;
        top = 498;
        if (itemBracers != null) {
            canvas.drawRect(itemsRect.get("bracers"), GetPaint(itemBracers.DisplayColor));

            Bitmap backBracers = GetDisplayColorBitmap(itemBracers.DisplayColor);
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
            canvas.drawRect(itemsRect.get("waist"), GetPaint(itemWaist.DisplayColor));

            Bitmap backWaist = GetDisplayColorBitmap(itemWaist.DisplayColor);
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
            canvas.drawRect(itemsRect.get("rightFinger"), GetPaint(itemRightFinger.DisplayColor));

            Bitmap backRightFinger = GetDisplayColorBitmap(itemRightFinger.DisplayColor);
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
            for (int i = 0; i < itemRightFinger.SocketCount; i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 15, top + 15, null);
            }
            for (int i = 0; i < itemRightFinger.GemList.size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemRightFinger.GemList.get(i) + ".png", itemRightFinger.GemList.get(i));
                canvas.drawBitmap(bmp, left + 33, top + 33, null);
            }
        }

        left = 177;
        top = 798;
        if (itemLeftFinger != null) {
            canvas.drawRect(itemsRect.get("leftFinger"), GetPaint(itemLeftFinger.DisplayColor));

            Bitmap backLeftFinger = GetDisplayColorBitmap(itemLeftFinger.DisplayColor);
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
            for (int i = 0; i < itemLeftFinger.SocketCount; i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 15, top + 15, null);
            }
            for (int i = 0; i < itemLeftFinger.GemList.size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemLeftFinger.GemList.get(i) + ".png", itemLeftFinger.GemList.get(i));
                canvas.drawBitmap(bmp, left + 33, top + 33, null);
            }
        }

        left = 717;
        top = 260;
        if (itemNeck != null) {
            canvas.drawRect(itemsRect.get("neck"), GetPaint(itemNeck.DisplayColor));

            Bitmap backNeck = GetDisplayColorBitmap(itemNeck.DisplayColor);
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
            for (int i = 0; i < itemNeck.SocketCount; i++) {
                canvas.drawBitmap(bmpEmptyIcon, left + 36, top + 36, null);
            }
            for (int i = 0; i < itemNeck.GemList.size(); i++) {
                Bitmap bmp = D3API.DownloadBitmap("http://content.battlenet.com.cn/d3/icons-zh-cn/items/large/" + itemNeck.GemList.get(i) + ".png", itemNeck.GemList.get(i));
                canvas.drawBitmap(bmp, left + 54, top + 54, null);
            }
        }
    }
}
