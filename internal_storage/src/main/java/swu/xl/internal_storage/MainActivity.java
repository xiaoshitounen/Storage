package swu.xl.internal_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fileName = "xl.txt";

        //File filesDir = getFilesDir();
        //System.out.println(filesDir.getAbsolutePath());

        //writeMessageToFile(fileName);
        //addMessageToFile(fileName);
        //readMessageFromFile(fileName);
        //deleteFromFile(fileName);
        //getFileList();
        //addDirInFiles();

        //File cacheDir = getCacheDir();
        //System.out.println(cacheDir.getAbsolutePath());

        try {
            //1.在内部存储空间创建self_dir文件夹
            File self_dir = getDir("self_dir", MODE_PRIVATE);
            //2.获取输出流
            File file = new File(self_dir, "xl.txt");
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

    /**
     * data/data/包名/file/fileName 向文件中写入信息
     * @param fileName
     */
    public void writeMessageToFile(String fileName){
        try {
            //1.使用MODE_PRIVATE模式创建xl.txt文件
            FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
            //2.创建一个实现了Serializable接口的User对象，实现Serializable接口才可以通过流写入
            User user = new User("xl", 21);
            //3.写入并冲刷
            fos.write(user.toString().getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG,e.getMessage());
        } catch (IOException e) {
            Log.d(TAG,e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * data/data/包名/file/fileName 向文件中追加信息
     * @param fileName
     */
    public void addMessageToFile(String fileName){
        try {
            //1.使用MODE_PRIVATE模式创建xl.txt文件
            FileOutputStream fos = openFileOutput(fileName, MODE_APPEND);
            //2.创建一个实现了Serializable接口的User对象，实现Serializable接口才可以通过流写入
            User user = new User("xl", 22);
            //3.写入并冲刷
            fos.write(user.toString().getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG,e.getMessage());
        } catch (IOException e) {
            Log.d(TAG,e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * data/data/包名/file/fileName 读取文件中的信息
     * @param fileName
     */
    public void readMessageFromFile(String fileName){

        try {
            //1.存储读取的数据
            StringBuilder message = new StringBuilder();

            //2.存储每次读取的数据
            byte[] bytes = new byte[1024];

            //3.打开文件
            FileInputStream fis = openFileInput(fileName);

            //4.读取内容
            int len = 0;
            while ((len = fis.read(bytes)) != -1){
                message.append(new String(bytes,0,len));
            }
            fis.close();

            //5.测试
            Log.d(TAG,message.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG,e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,e.getMessage());
        }
    }

    /**
     * data/data/包名/file/fileName 删除文件
     * @param fileName
     */
    public void deleteFromFile(String fileName){
        deleteFile(fileName);
    }

    /**
     * data/data/包名/file/ 文件信息
     */
    public void getFileList(){
        String[] files = fileList();
        for (String file : files) {
            Log.d(TAG,file);
        }
    }

    /**
     * data/data/包名/file/ 创建文件夹
     */
    public void addDirInFiles(){
        File file = new File(getFilesDir().getAbsolutePath(), "/text");
        if (!file.exists()){
            file.mkdir();
        }
    }


}
