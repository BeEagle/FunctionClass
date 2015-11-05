package cchao.org.mylibrary.photo.multiimageshow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cchao.org.mylibrary.R;
import cchao.org.mylibrary.photo.zoom.ViewPagerFixed;

/**
 * 仿微信查看多张图片
 * Created by chenchao on 15/10/21.
 */
public class ImagePagerActivity extends FragmentActivity {

    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";

    private TextView indicator;
    private ViewPagerFixed viewPagerFixed;

    //第几张图片
    private int pagerPosition;
    //图片地址
    private ArrayList<String> urls = new ArrayList<String>();

    /**
     * 查看图片
     * @param activity  当前activity
     * @param pagerPosition 第一张图片下标，0开始
     * @param urls  图片地址列表
     */
    public static void openImagePaper(Activity activity, int pagerPosition, ArrayList<String> urls){
        Intent intent = new Intent(activity, ImagePagerActivity.class);
        intent.putExtra(EXTRA_IMAGE_INDEX, pagerPosition);
        intent.putStringArrayListExtra(EXTRA_IMAGE_URLS, urls);
        activity.startActivity(intent);
    }

    protected void onCreate(Bundle saveBundle){
        super.onCreate(saveBundle);
        setContentView(R.layout.activity_multi_imagepaper);

        initImageLoad();

        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        //pagerPosition = 0;

        urls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);

        viewPagerFixed = (ViewPagerFixed) findViewById(R.id.imagepager);
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), urls);
        viewPagerFixed.setAdapter(mAdapter);
        indicator = (TextView) findViewById(R.id.indicator);

        CharSequence text = getString(R.string.viewpager_indicator, 1, viewPagerFixed.getAdapter().getCount());
        indicator.setText(text);
        // 更新下标
        viewPagerFixed.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator, arg0 + 1, viewPagerFixed.getAdapter().getCount());
                indicator.setText(text);
            }

        });
        if (saveBundle != null) {
            pagerPosition = saveBundle.getInt(STATE_POSITION);
        }
        viewPagerFixed.setCurrentItem(pagerPosition);
    }

    /**
     * 初始化Imageload，可放入Application类中
     */
    private void initImageLoad(){
        //ImageLoad初始化
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder() //
                .showImageForEmptyUri(R.mipmap.ic_launcher) //
                .showImageOnFail(R.mipmap.ic_launcher) //
                .cacheInMemory(true) //
                .cacheOnDisk(true) //
                .build();//
        ImageLoaderConfiguration config = new ImageLoaderConfiguration//
                .Builder(getApplicationContext())//
                .defaultDisplayImageOptions(defaultOptions)//
                .discCacheSize(50 * 1024 * 1024)//
                .discCacheFileCount(100)// 缓存一百张图片
                .writeDebugLogs()//
                .build();//
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, viewPagerFixed.getCurrentItem());
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public ArrayList<String> fileList;

        public ImagePagerAdapter(FragmentManager fm, ArrayList<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position);
            return ImageDetailFragment.newInstance(url);
        }

    }

    public void onResume() {
        super.onResume();
    }
    public void onPause() {
        super.onPause();
    }
}