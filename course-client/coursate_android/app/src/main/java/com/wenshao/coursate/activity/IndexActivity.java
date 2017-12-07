package com.wenshao.coursate.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.wenshao.coursate.R;
import com.wenshao.coursate.adapter.ViewPagerAdapter;
import com.wenshao.coursate.constant.SpConstant;
import com.wenshao.coursate.fragment.HomeFragment;
import com.wenshao.coursate.fragment.QuestionBankFragment;
import com.wenshao.coursate.util.BottomNavigationViewHelper;
import com.wenshao.coursate.util.SpUtil;

/**
 * Created by wenshao on 2017/9/24.
 * 首页
 */

public class IndexActivity extends ToolBarActivity {
    private Context mContext;
    private ViewPager viewPager;
    private MenuItem menuItem;
    private final static String TAG = "IndexActivity";
    private BottomNavigationView bottomNavigationView;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null)supportActionBar.setDisplayHomeAsUpEnabled(false);
        initViewPager();
        SpUtil.putInt(mContext, SpConstant.GUIDE_VERSION, 1);
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_classify:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_record:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.item_love:
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.item_info:
                                viewPager.setCurrentItem(4);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(final ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(HomeFragment.newInstance());
        adapter.addFragment(HomeFragment.newInstance());
        adapter.addFragment(HomeFragment.newInstance());
        adapter.addFragment(QuestionBankFragment.newInstance());
        adapter.addFragment(HomeFragment.newInstance());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_shopping_cart:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        mToolbar = toolbar;
        toolbar.showOverflowMenu();
        showToolbarHome();
    }

    private void showToolbarHome() {
        mToolbar.removeAllViews();  //清除原有的toolbar
        getLayoutInflater().inflate(R.layout.toolbar_home, mToolbar);

    }

}


