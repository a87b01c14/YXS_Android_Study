package com.antex.showimageview;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;

/**
 * ImageView的N种加载方法
 * 获取图片的N种方法
 * @author xiaosanyu 2015-11-03
 * */
public class ShowImageViewActivity extends AppCompatActivity {
    private String sdPath = "/sdcard/yxs.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);
        final ImageView imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView1.setImageResource(R.mipmap.ic_launcher);
        imageView2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher,null));
        imageView3.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap
                .ic_launcher));
//        need API23
        imageView4.setImageIcon(Icon.createWithResource(this, R.mipmap.ic_launcher));

        imageView5.setImageURI(Uri.fromFile(new File(sdPath)));

//        需要在线程中执行网络操作
        new Thread() {
            @Override
            public void run() {
                final Bitmap bitmap = getImageFromInternet();
                imageView6.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView6.setImageBitmap(bitmap);
                    }
                });
            }
        }.start();

    }

    /**
     * 已知图片名称获取图片的资源id的方法
     *
     * @param imageName
     * @return resourceID
     */
    public int getImageFromResourceByName(String imageName) {
        Context context = getBaseContext();
        int resId = getResources().getIdentifier(imageName, "mipmap", context.getPackageName());
        return resId;
    }

    /**
     * 利用反射方法
     * 已知图片名称获取图片的资源id的方法
     *
     * @param imageName
     * @return resourceID
     */
    public int getImageFromResourceByReflect(String imageName) {
        Class drawable = R.mipmap.class;
        Field field = null;
        int r_id;
        try {
            field = drawable.getField(imageName);
            r_id = field.getInt(field.getName());
        } catch (Exception e) {
            r_id = R.mipmap.ic_launcher;
            Log.e("ERROR", "PICTURE NOT　FOUND！");
        }
        return r_id;
    }

    /**
     * 从SD卡获取图片
     *
     * @param imageName
     * @return Bitmap
     */
    public Bitmap getImageFromSD(String imageName) {
        return BitmapFactory.decodeFile(sdPath);
    }


    /**
     * 从Assets目录下获取图片方法1
     *
     * @param imageName
     * @return Bitmap
     */
    public Bitmap getImageFromAssets(String imageName) {
        AssetManager am = getAssets();
        InputStream is = null;
        try {
            is = am.open("image/" + imageName);//得到图片流
        } catch (IOException e) {
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(is);
    }

    /**
     * 从Assets目录下获取图片方法2
     *
     * @param imageName
     * @return Bitmap
     */
    public Bitmap getImageFromAssets2(String imageName) {
        InputStream is = getClass().getResourceAsStream("/assets/image/" + imageName);
        return BitmapFactory.decodeStream(is);
    }

    /**
     * 从raw目录下获取图片方法
     *
     * @return Bitmap
     */
    public Bitmap getImageFromRaw() {
        InputStream is = getResources().openRawResource(R.raw.yxs);

        return BitmapFactory.decodeStream(is);
    }

    /**
     * 从网络上获取图片方法
     *
     * @return Bitmap
     */
    public Bitmap getImageFromInternet() {
        String url = "http://pic.baike.soso.com/p/20130128/bki-20130128144516-1340884592.jpg";
        InputStream is = null;
        try {
            is = new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(is);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_image_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
