package wenshao.com.coursate.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wenshao.com.coursate.R;
import wenshao.com.coursate.transformer.DepthPageTransformer;


/**
 * Created by wenshao on 2017/9/23.
 * 引导页面
 */

public class GuideActivity extends Activity implements View.OnClickListener {
    private Context mContext;
    private final static String TAG = "GuideActivity";
    private ViewPager vp_guide_image;
    private int[] mImageList;
    private String[][] mImageTextList;
    private List<ImageView> mImageViewList;
    private Button btn_start;
    private LinearLayout ll_points;
    private ImageView img_red_point;
    private TextView text_guide_image_title;
    private TextView text_guide_image_des;
    private TextView text_next;
    private TextView text_skip;
    private int mDistance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_guide);
        Log.i(TAG, "onCreate: ");


        initUi();
        initData();
    }

    private void initUi() {
        vp_guide_image = (ViewPager) findViewById(R.id.vp_guide_image);
        vp_guide_image.setPageTransformer(true, new DepthPageTransformer());

//        btn_start = (Button) findViewById(R.id.btn_start);
        ll_points = (LinearLayout) findViewById(R.id.ll_points);
        img_red_point = (ImageView) findViewById(R.id.img_red_point);
        text_guide_image_title = (TextView) findViewById(R.id.text_guide_image_title);
        text_guide_image_des = (TextView) findViewById(R.id.text_guide_image_des);
        text_next = (TextView) findViewById(R.id.text_next);
        text_skip = (TextView) findViewById(R.id.text_skip);
        text_next.setOnClickListener(this);
        text_skip.setOnClickListener(this);


        // viewpager添加监听事件
        vp_guide_image.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // 页面滑动过程中  position当前的位置   positionOffset 滑动的百分比 positionOffsetPixels 滑动的像素
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.i(TAG, "onPageScrolled: position:"+position+",positionOffset:"+positionOffset+",positionOffsetPixels:"+positionOffsetPixels);
                // 红色点移动的距离
                int newDistance = (int) (positionOffset * mDistance) + position * mDistance;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) img_red_point.getLayoutParams();
                layoutParams.leftMargin = newDistance;
                img_red_point.setLayoutParams(layoutParams);

            }

            // 页面被选中
            @Override
            public void onPageSelected(int position) {
                text_guide_image_title.setText(mImageTextList[position][0]);
                text_guide_image_des.setText(mImageTextList[position][1]);

            }

            // 页面状态发生变化
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        mImageList = new int[]{
                R.drawable.guide_1_1,
                R.drawable.guide_1_2,
                R.drawable.guide_1_3
        };
        mImageTextList = new String[][]{{
                "最好的课程", "你身边的多用途学习工具"
        }, {
                "优质课堂", "在这里你可以挑选自己想学的科目内容"
        }, {
                "能力课堂", "多方面学习，助你提升各项能力"
        }};
        text_guide_image_title.setText(mImageTextList[0][0]);
        text_guide_image_des.setText(mImageTextList[0][1]);
        ImageView imageView;
        mImageViewList = new ArrayList<>();
        ImageView pointView;

        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < mImageList.length; i++) {
            // 添加图片
            imageView = new ImageView(mContext);
            imageView.setBackgroundResource(mImageList[i]);

            mImageViewList.add(imageView);
            // 添加小圆点
            pointView = new ImageView(mContext);
            pointView.setImageResource(R.drawable.shape_point_gray);

            layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            if (i > 0) {
                layoutParams.leftMargin = 20;
            }
            pointView.setLayoutParams(layoutParams);

            ll_points.addView(pointView);
        }

        // 监听ll_points绘制完成后调用
        ll_points.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_points.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mDistance = ll_points.getChildAt(1).getLeft() - ll_points.getChildAt(0).getLeft();
                //Log.i(TAG, "onGlobalLayout: "+ll_points.getChildAt(2).getLeft());
                //Log.i(TAG, "onGlobalLayout: " + ll_points.getChildAt(1).getLeft());
            }
        });

        vp_guide_image.setAdapter(new GuideAdapter());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_skip:     // 跳往首页
                startActivity(new Intent(this,IndexActivity.class));
                finish();
                break;
            case R.id.text_next:     // 下一步
                if (vp_guide_image.getCurrentItem() == mImageList.length-1){
                    startActivity(new Intent(this,IndexActivity.class));
                    finish();
                }else{
                    vp_guide_image.setCurrentItem(vp_guide_image.getCurrentItem() + 1, true);
                }

                break;
        }


    }

    private class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        // 初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImageViewList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // 销毁item布局
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
