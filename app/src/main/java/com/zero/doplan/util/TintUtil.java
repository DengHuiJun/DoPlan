package com.zero.doplan.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.zero.doplan.AppContext;

/**
 * Created by allen on 2017/7/10.
 */

public class TintUtil {

    public static Drawable tintDrawable(Drawable drawable, int color) {
        return tintDrawable(drawable, -1, color);
    }

    public static Drawable tintDrawable(Drawable drawable, int alpha, int color) {
        Paint paint = new Paint();
        if (alpha >= 0) {
            paint.setAlpha(alpha);
        }
        paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        return tintDrawable(drawable, paint);
    }

    private static Drawable tintDrawable(Drawable drawable, Paint paint) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap normalBitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap pressedBitmap = Bitmap.createBitmap(
                    normalBitmap.getWidth(),
                    normalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
            pressedBitmap.setDensity(normalBitmap.getDensity());
            Canvas canvas = new Canvas(pressedBitmap);
            canvas.drawBitmap(normalBitmap, 0, 0, paint);
            return new BitmapDrawable(AppContext.sContext.getResources(), pressedBitmap);
        }
        return drawable;
    }
}
