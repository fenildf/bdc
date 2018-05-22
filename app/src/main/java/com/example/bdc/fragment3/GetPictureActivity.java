package com.example.bdc.fragment3;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.a00.bdcapp.R;

import java.io.File;
import java.io.FileNotFoundException;

public class GetPictureActivity extends AppCompatActivity {
    private ImageButton album;
    private ImageButton camera;
    private static final int CAMERA = 1025;
    private static final int ALBUM = 1026;
    private File mFolder;
    private String mImgName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_picture);

        album = findViewById(R.id.album);
        camera = findViewById(R.id.camera);

        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    /*
     * 设置从相机获取图片
     */
    private void getImgFromCamra() {
        String state = Environment.getExternalStorageState();
        // 先检测是不是有内存卡。
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            mFolder = new File(Environment.getExternalStorageDirectory(), "bCache");
            // 判断手机中有没有这个文件夹，没有就新建。
            if (!mFolder.exists()) {
                mFolder.mkdirs();
            }
            // 自定义图片名字，这里是以毫秒数作为图片名。
            mImgName = System.currentTimeMillis() + ".jpg";
            Uri uri = Uri.fromFile(new File(mFolder, mImgName));
            // 调用系统拍照功能。
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, CAMERA);
        } else {
            Toast.makeText(this, "未检测到SD卡", Toast.LENGTH_SHORT).show();
        }
    }
    /*
    从本地图库获取图片
     */
    private void getImgFromAlbum() {
                 // 调用本地图库。
                 Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                 intent.setType("image/*");
                 startActivityForResult(intent, ALBUM);
             }

              public void onActivityResult(int requestCode, int resultCode, Intent data) {
                 Bitmap bm;
                 if (resultCode == RESULT_OK && requestCode == CAMERA) {
                         // 调用系统方法获取到的是被压缩过的图片，通过自定义路径轻松获取原始图片。
                         bm = BitmapFactory.decodeFile(mFolder.getAbsolutePath()
                                         + File.separator + mImgName);
                     }

                 if (resultCode == RESULT_OK && requestCode == ALBUM) {
                         try {
                                 if (data != null) {
                                         // 获取本地相册图片。
                                         Uri uri = data.getData();
                                         ContentResolver cr = getContentResolver();
                                         bm = BitmapFactory.decodeStream(cr.openInputStream(uri));
                                     }
                             } catch (FileNotFoundException e) {
                                 e.printStackTrace();
                             }
                     }
             }

}
