package com.example.uur.newtest;

import android.graphics.Bitmap;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import android.support.v4.util.LruCache;

/**
 * Created by Uğur on 11.2.2015.
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageCache {

    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory /8;

        return cacheSize;
    }
    public LruBitmapCache() {
        this(getDefaultLruCacheSize());
    }

    public LruBitmapCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes()*value.getHeight() /1024;
    }

    @Override
    public Bitmap getBitmap(String s) {
        return get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        put(s,bitmap);
    }
}
