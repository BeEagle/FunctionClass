package cchao.org.mylibrary.photo.imageconversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by chenchao on 15/11/5.
 */
public class ImageCompress {

    private ByteArrayOutputStream baos;
    
    //图片地址
    private String imagePath;

    //图片压缩率
    private int compressionRadio;

    public ImageCompress(String imagePath, int compressionRadio){
        this.imagePath = imagePath;
        this.compressionRadio = compressionRadio;
    }

    /**
     * 返回压缩图片
     * @return
     */
    public Bitmap getSmallBitmap() {
        Bitmap bitmap = null;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        options.inJustDecodeBounds = false;
        try{
            baos = new ByteArrayOutputStream();
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
        return bitmap;
    }

    /**
     * 计算图片压缩比
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
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
