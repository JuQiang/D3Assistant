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

import java.util.ArrayList;

import iamfqq.d3assistant.Friend;
import iamfqq.d3assistant.HeroProfileSimple;

/**
 * Created by JUQIANG-PC on 2017/3/6.
 */

public class FriendView extends View {
    private Paint penName;
    private Friend friend;
    private Bitmap headBackgroundBitmap;
    private int width, height,index;

    public String getBattleTag(){
        return this.friend.getProfileID();
    }
    public String getNickName(){
        return this.friend.getNickName();
    }
    public FriendView(Context context, int index, Friend friend) {
        super(context);

        this.index=index;
        this.friend = friend;

        penName = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        penName.setColor(Color.WHITE);
        penName.setStrokeJoin(Paint.Join.ROUND);
        penName.setStrokeCap(Paint.Cap.ROUND);
        penName.setStrokeWidth(3);
        penName.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        headBackgroundBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.auriel);
        width = headBackgroundBitmap.getWidth();
        height = headBackgroundBitmap.getHeight();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width/4+300*3,height/4+8*3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(index%2==0) {
            canvas.drawColor(0xff12110f);
        }
        else{
            canvas.drawColor(0xff171614);
        }
        canvas.drawBitmap(headBackgroundBitmap,
                new Rect(0,0,width,height),
                new Rect(0,0,width/4,height/4),
                null);
        canvas.drawText(this.friend.nickName, width/4+8*3, height/4/2+21, penName);
    }

}
