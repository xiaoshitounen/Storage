package swu.xl.external_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ninePublicDir();

        //privateDir();

        //privateDirTest();

        //测试图片的文件操作
        /*Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        if (bitmap != null){
            ExternalStorageHelper.saveBitmapToExternalStoragePrivateFilesDir(bitmap,"bitmap.png",this);
        }else {
            Toast.makeText(this, "图片为空", Toast.LENGTH_SHORT).show();
        }*/

        //测试普通字节流的操作
        /*ExternalStorageHelper.saveFileToExternalStoragePrivateFilesDir(
                new User("xl",22).toString().getBytes(),
                Environment.DIRECTORY_DOCUMENTS,
                "text.txt",
                this
        );*/
    }

    /**
     * 外部九大公共目录
     */
    public void ninePublicDir(){
        //根目录
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d(TAG,root);

        //音乐类型
        String music_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath();
        Log.d(TAG,music_path);
        //图片类型
        String picture_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        Log.d(TAG,picture_path);
        //电影类型
        String movies_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath();
        Log.d(TAG,movies_path);
        //照片类型
        String dcim_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        Log.d(TAG,dcim_path);
        //下载类型
        String download_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        Log.d(TAG,download_path);
        //文档类型
        String document_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        Log.d(TAG,document_path);
        //铃声类型
        String ring_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath();
        Log.d(TAG,ring_path);
        //闹钟提示音类型
        String alarm_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).getAbsolutePath();
        Log.d(TAG,alarm_path);
        //通知提示音类型
        String notification_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath();
        Log.d(TAG,notification_path);
    }

    /**
     * 外部私有目录以及缓存目录
     */
    public void privateDir(){
        //私有目录
        String files = getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath();
        Log.d(TAG,files);

        //缓存目录
        String cache = getExternalCacheDir().getAbsolutePath();
        Log.d(TAG,cache);
    }

    /**
     * 外部私有目录写入文件测试
     */
    public void privateDirTest(){
        try {
            //1.在内部存储空间创建self_dir文件夹
            File externalFilesDir = getExternalFilesDir(null);
            //2.获取输出流
            File file = new File(externalFilesDir, "xl.txt");
            if (!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);//覆盖写入
            //FileOutputStream fos = new FileOutputStream(file,true); 追加写入
            //3.写入信息
            User user = new User("xl", 22);
            fos.write(user.toString().getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
