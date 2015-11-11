package com.weizongwei.firework.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by weizongwei on 15-10-24.
 *
 * 对view进行拾色的工具类
 */
public class ViewPickColorsUtil {


    /**
     * 随机的选取view上的一些色值
     * @param view
     * @return
     */
    public static ArrayList<Integer> radomPickColors(View view,int colorsCount) {
        ArrayList<Integer> arrayList=new ArrayList<Integer>();

        if (view != null && view.getVisibility() == View.VISIBLE) {

            Bitmap bitmap=getViewCacheBitmap(view);
            //saveBitmap2SdCard("BBBBBBB",bitmap);
            Log.d("XXXXX","bitmap bytecount = "+ bitmap.getByteCount()+" width = "+bitmap.getWidth()+"  height = "+bitmap.getHeight());
            if (view.getWidth() * view.getHeight() > colorsCount) {
                for (int i_index = 0; i_index < colorsCount; i_index++) {


                    int radomcol=getPositionColor(bitmap,
                            getRadomInt(bitmap.getWidth()),
                            getRadomInt(bitmap.getHeight())

                    );
                    arrayList.add(radomcol);
                }
            }

            bitmap.recycle();

        }
        return arrayList;

    }

    /**
     * 获得View的一些颜色
     *
     * @param v
     * @return
     */
    public static Bitmap getViewCacheBitmap(View v) {
        if (v != null) {
            if (v.getVisibility() == View.VISIBLE) {
                //你需要截图的View
                v.setDrawingCacheEnabled(true);
                v.buildDrawingCache();
                //此处bitmap没判空
                return  v.getDrawingCache();

            } else {
                return null;
            }

        }
        else {
            return null;
        }
    }

    //获得View上某点的颜色
    public static int getPositionColor(Bitmap bitmap, int x, int y) {

        if (bitmap != null) {

            int rgbPixel = bitmap.getPixel(x, y);
            //Log.i("Value", "pixel: " + Integer.toHexString(rgbPixel));
            //Log.i("Value", "rgb: r = " + Color.red(rgbPixel) + "  g = " + Color.green(rgbPixel) +" b = "+Color.blue(rgbPixel));

            return bitmap.getPixel(x, y);//这里返回的可能是负数  只有toHexString 一下才能看到具体的值
                /* Color.rgb(redValue, greenValue, blueValue);*/
            } else {
                return getRandomColor();
            }
    }


    //定义一堆人眼看着比较舒适的颜色 作为无法取到颜色的时候的默认的颜色 通过随机的方式从中选取
    private static int[] DEFUALT_COLORS = {0xFF3496db, 0xFF1abc9c, 0xFFffffff, 0xFFec494e, 0xFF2dc66d, 0xFFf7ba00, 0xFFfe4400, 0xFF0ec816};

    public static int getRandomColor() {
        return DEFUALT_COLORS[getRadomInt(DEFUALT_COLORS.length)];

    }

    /**
     * 根据传递过来的值 而 产生最大的随机数（这个方法提出来仅仅是为了增加代码的重用率）
     *
     * @param i_max 限制产生随机数的最大值
     * @return
     */
    public static int getRadomInt(int i_max) {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        return random.nextInt(i_max) ;
    }

    public static void saveBitmap2SdCard(String bitName,final Bitmap mBitmap){
        File f = new File(Environment.getExternalStorageDirectory()
               +"/" + bitName + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            Log.e("XXXX","在保存图片时出错："+e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
