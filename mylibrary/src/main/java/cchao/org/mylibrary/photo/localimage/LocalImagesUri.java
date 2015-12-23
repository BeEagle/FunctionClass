package cchao.org.mylibrary.photo.localimage;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cchao.org.mylibrary.photo.localimage.util.ImageBucket;
import cchao.org.mylibrary.photo.localimage.util.ImageItem;
import cchao.org.mylibrary.photo.localimage.util.ImagesHelper;

/**
 * 获取本地所有图片地址
 * Created by chenchao on 15/12/23.
 */
public class LocalImagesUri {

    private static ImagesHelper mImagesHelper;
    private static List<ImageBucket> mImageBucket = new ArrayList<ImageBucket>();
    private static List<ImageItem> mImageItem = new ArrayList<ImageItem>();

    public static List<ImageItem> getLocalImagesUri(Context context) {
        mImagesHelper = ImagesHelper.getHelper();
        mImagesHelper.init(context);
        mImageBucket = mImagesHelper.getImagesBucketList(false);
        if (mImageBucket.isEmpty()) {
            mImageBucket.clear();
        }
        if (mImageItem.isEmpty()) {
            mImageItem.clear();
        }
        for (ImageBucket imageBucket : mImageBucket) {
            mImageItem.addAll(imageBucket.imageList);
        }
        return mImageItem;
    }
}
