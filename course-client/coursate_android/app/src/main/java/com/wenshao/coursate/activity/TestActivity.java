package com.wenshao.coursate.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wenshao.coursate.R;
import com.wenshao.coursate.adapter.BaseRecyclerAdapter;
import com.wenshao.coursate.adapter.GroupedListAdapter;
import com.wenshao.coursate.adapter.SmartViewHolder;
import com.wenshao.coursate.bean.BannerBean;
import com.wenshao.coursate.bean.HttpApiResponse;
import com.wenshao.coursate.config.ServerConfig;
import com.wenshao.coursate.model.GroupModel;
import com.wenshao.coursate.util.GlideImageLoader;
import com.wenshao.coursate.util.JsonCallback;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.layout.simple_list_item_2;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by wenshao on 2017/10/14.
 */

public class TestActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener{
    private Context mContext;
    private View mView;
    private View mPageHead;
    private SmartRefreshLayout mRefreshLayout;
    private BezierRadarHeader mRefreshHeader;
    private RecyclerView mRecyclerView;
    private Banner mBanner;


    private boolean isFirstEnter = true;
    private static final String TAG = "HomeFragment";


    private enum Item {
        内容不偏移("下拉的时候列表内容停留在原位不动"),
        内容跟随偏移("下拉的时候列表内容跟随向下偏移"),
        橙色主题("更改为橙色主题颜色"),
        红色主题("更改为红色主题颜色"),
        绿色主题("更改为绿色主题颜色"),
        蓝色主题("更改为蓝色主题颜色"),
        打开左右拖动("打开左右拖动效果"),
        关闭左右拖动("关闭左右拖动效果"),;
        public String name;

        Item(String name) {
            this.name = name;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_test);
        Log.i(TAG, "onCreate: ");

        initUi();
        initData();
    }

    private void initUi() {
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.sf_layout);
        mRefreshHeader = (BezierRadarHeader) findViewById(R.id.header);
        mBanner = (Banner) findViewById(R.id.banner);


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        mRecyclerView.setAdapter(new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()), simple_list_item_2, this) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
                holder.text(android.R.id.text1, model.name());
                holder.text(android.R.id.text2, model.name);
                holder.textColorId(android.R.id.text2, R.color.mainBackground);
            }
        });
        GroupedListAdapter adapter = new GroupedListAdapter(mContext, GroupModel.getGroups(10, 10));
        adapter.setOnHeaderClickListener(new GroupedRecyclerViewAdapter.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                      int groupPosition) {
                Toast.makeText(mContext, "组头：groupPosition = " + groupPosition,
                        Toast.LENGTH_LONG).show();
            }
        });
        adapter.setOnFooterClickListener(new GroupedRecyclerViewAdapter.OnFooterClickListener() {
            @Override
            public void onFooterClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                      int groupPosition) {
                Toast.makeText(mContext, "组尾：groupPosition = " + groupPosition,
                        Toast.LENGTH_LONG).show();
            }
        });
        adapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                     int groupPosition, int childPosition) {
                Toast.makeText(mContext, "子项：groupPosition = " + groupPosition
                                + ", childPosition = " + childPosition,
                        Toast.LENGTH_LONG).show();
            }
        });

        //mRecyclerView.setAdapter(adapter);
        //直接使用GroupGridLayoutManager实现子项的Grid效果
        GroupedGridLayoutManager gridLayoutManager = new GroupedGridLayoutManager(mContext, 4, adapter){
            //重写这个方法 改变子项的SpanSize。
            //这个跟重写SpanSizeLookup的getSpanSize方法的使用是一样的。
            @Override
            public int getChildSpanSize(int groupPosition, int childPosition) {
                if(groupPosition % 2 == 1){
                    return 2;
                }
                return super.getChildSpanSize(groupPosition, childPosition);
            }
        };
        mRecyclerView.setLayoutManager(gridLayoutManager);


        if (isFirstEnter) {
            //mRefreshLayout.autoRefresh();
            isFirstEnter = false;
        }

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                // 多少ms后完成下拉刷新
                refreshlayout.finishRefresh(2000);

            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                // 多少ms后完成上拉刷新
                refreshlayout.finishLoadmore(2000);

            }
        });

    }

    private void initData() {
        final List<String> ImageList = new ArrayList<String>();
        OkGo.<HttpApiResponse<List<BannerBean> >>get(ServerConfig.HTTP_BANNER_V1)                            // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("bannerListGet")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)  // 缓存模式，详细请看缓存介绍
                .params("package_name",mContext.getPackageName())
                //.upJson()
                .execute(new JsonCallback<HttpApiResponse<List<BannerBean>>>() {
                    @Override
                    public void onSuccess(Response<HttpApiResponse<List<BannerBean>>> httpApiResponseResponse) {
                        List<BannerBean> data = httpApiResponseResponse.body().data;
                        for (BannerBean bannerBean:data){
                            ImageList.add(bannerBean.getImage_url());
                        }
                    }

                    @Override
                    public void onError(Response<HttpApiResponse<List<BannerBean>>> response) {
                        super.onError(response);
                        ImageList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv22.jpg");

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mBanner.setImageLoader(new GlideImageLoader());

                        mBanner.setImages(ImageList);
                        mBanner.start();
                    }
                });


    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
