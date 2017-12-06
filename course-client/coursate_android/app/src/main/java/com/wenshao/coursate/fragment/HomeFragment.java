package com.wenshao.coursate.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.wenshao.coursate.adapter.BaseRecyclerAdapter;
import com.wenshao.coursate.adapter.GroupedListAdapter;
import com.wenshao.coursate.adapter.SmartViewHolder;
import com.wenshao.coursate.bean.BannerBean;
import com.wenshao.coursate.bean.HttpApiResponse;
import com.wenshao.coursate.config.ServerConfig;
import com.wenshao.coursate.model.GroupModel;
import com.wenshao.coursate.util.JsonCallback;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;



import com.wenshao.coursate.R;
import com.wenshao.coursate.util.GlideImageLoader;

/**
 * Created by wenshao on 2017/9/27.
 * 首页页面
 */

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {
    private Context mContext;
    private View mView;
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

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, null);

        mContext = getContext();
        initUi();
        initData();
        /*Window window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);*/
        //window.setStatusBarColor("#11111");   //这里动态修改颜色
        return mView;
    }

    private void initUi() {
        mRefreshLayout = (SmartRefreshLayout) mView.findViewById(R.id.sf_layout);
        mRefreshHeader = (BezierRadarHeader) mView.findViewById(R.id.header);
        mBanner = (Banner) mView.findViewById(R.id.banner);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rv_list);
        mRefreshLayout.setEnableScrollContentWhenLoaded(false);//是否在加载完成时滚动列表显示新的内容

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

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


        mRecyclerView.setAdapter(adapter);
        //直接使用GroupGridLayoutManager实现子项的Grid效果
        GroupedGridLayoutManager gridLayoutManager = new GroupedGridLayoutManager(mContext, 4, adapter){
            //重写这个方法 改变子项的SpanSize。
            //这个跟重写SpanSizeLookup的getSpanSize方法的使用是一样的。
            @Override
            public int getChildSpanSize(int groupPosition, int childPosition) {
                return 2;
            }

        };
        mRecyclerView.setLayoutManager(gridLayoutManager);


        if (isFirstEnter) {
            mRefreshLayout.autoRefresh();
            isFirstEnter = false;
        }
        addListener();


    }

    private void addListener() {
        mRefreshLayout.setOnMultiPurposeListener(new OnMultiPurposeListener() {
            // 往下拉 持续
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {

            }

            // 自动释放 持续
            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {

            }

            // 执行开始刷新的动画 一次刷新中执行一次
            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int extendHeight) {
                // Log.i(TAG, "onHeaderStartAnimator");

            }

            // 刷新动画介绍 一次刷新中执行一次
            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {
                // Log.i(TAG, "onHeaderFinish");

            }

            @Override
            public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {

            }

            @Override
            public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {

            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int extendHeight) {

            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {

            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }

            // 开始刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }

            // 状态改变  状态包括 None:空闲状态 PullDownToRefresh:下拉状态  ReleaseToRefresh:释放状态 Refreshing:刷新中  RefreshFinish:刷新完成
            @Override
            public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
                // Log.i(TAG, "333" + oldState.name() + "========" + newState.name());

            }
        });

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
        int[] ints = {
                R.drawable.guide_1_1,
                R.drawable.guide_1_2,
                R.drawable.guide_1_3
        };
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
