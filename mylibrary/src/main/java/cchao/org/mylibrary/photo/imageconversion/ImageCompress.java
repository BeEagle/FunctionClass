package cchao.org.mylibrary.photo.imageconversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 压缩图片功能类
 * Created by chenchao on 15/11/5.
 */
public class ImageCompress {

    private ByteArrayOutputStream baos;
    private FileOutputStream fileOutputStream;
    //图片地址
    private String imagePath;
    //图片压缩率
    private int compressionRadio;

    private Bitmap bitmap;
    private BitmapFactory.Options options;
    private byte[] bitmapBytes;;

    public ImageCompress(String imagePath, int compressionRadio){
        this.imagePath = imagePath;
        this.compressionRadio = compressionRadio;
    }


    /**
     * 压缩图片
     * @param reqWidth  宽
     * @param reqHeight 高
     */
    private void compressImage(int reqWidth, int reqHeight){
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        options = new BitmapFactory.Options();
        baos = new ByteArrayOutputStream();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        try{
            bitmap = BitmapFactory.decodeFile(imagePath, options);
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRadio, baos);
        }finally{
            try {
                if(baos != null)
                    baos.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存Bitmap对象为图片文件
     * @param file
     * @param bmp
     * @return
     */
    public boolean saveBitmap(File file, Bitmap bmp) {
        fileOutputStream = null;
        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            if (bmp == null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRadio, fileOutputStream);
            } else {
                bmp.compress(Bitmap.CompressFormat.JPEG, compressionRadio, fileOutputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file.exists();
    }

    /**
     * 返回压缩图片Bitmap对象
     * @param reqWidth  宽
     * @param reqHeight 高
     * @return
     */
    public Bitmap getSmallBitmap(int reqWidth, int reqHeight) {
        compressImage(reqWidth, reqHeight);
        return bitmap;
    }


    /**
     * 返回转化为base64字符串图片
     * @param reqWidth  宽
     * @param reqHeight 高
     * @return
     */
    public String getSmallString(int reqWidth, int reqHeight){
        compressImage(reqWidth, reqHeight);
        bitmapBytes = baos.toByteArray();
        return Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
    }

    /**
     * 计算图片压缩比
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        final int height = options.outHeight;
        final int widht = options.outWidth;
        int inSampleSize = 1;
        if(height > reqHeight || widht > reqWidth){
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) widht / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
