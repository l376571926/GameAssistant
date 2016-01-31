package com.qianfeng.gameassistant.other.widget;


import com.qianfeng.gameassistant.other.utils.ZhuShouHttpUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Li Yiwei
 *
 * @date :  2016/1/14.
 */
public class CircleImageView extends ImageView
{

    public CircleImageView(Context context)
    {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        int width = getWidth();

        //在外面画一个边框
        Paint strokeP = new Paint();
        strokeP.setColor(Color.YELLOW);
        strokeP.setStrokeWidth(2);
        strokeP.setAntiAlias(true);
        strokeP.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(width / 2, width / 2, (width - 5) / 2, strokeP);
    }

    /**
     * 设置ImageView的Url
     *
     * @param url
     */
    public void setImageUrl(final String httpUrl)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                super.run();
                Bitmap bitmap = ZhuShouHttpUtil.downLoadBitmap(httpUrl);
                //加工成圆图片
                final Bitmap circleBitmap = createCircleBitmap(bitmap);

                //在主线程中设置背景图片
                post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        setImageBitmap(circleBitmap);
                    }
                });
            }
        }.start();
    }


    /**
     * 把 源图片 加工成 圆形图片
     *
     * @param resource
     * @return
     */
    private Bitmap createCircleBitmap(Bitmap resource)
    {
        int width = resource.getWidth();

        Paint paint = new Paint();
        //画圆或者弧形要加抗锯齿属性
        paint.setAntiAlias(true);

        //指定图片画板的宽高
        Bitmap circleBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(circleBitmap);


        //画一个和原图片宽高相等的内切圆
        canvas.drawCircle(width / 2, width / 2, (width - 5) / 2, paint);

        //取两图的交集,也就是重合的部分
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //把原图覆盖上去
        canvas.drawBitmap(resource, 0, 0, paint);


        return circleBitmap;
    }
}
