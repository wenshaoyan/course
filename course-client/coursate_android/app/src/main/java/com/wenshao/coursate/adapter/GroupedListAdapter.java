package com.wenshao.coursate.adapter;


import android.content.Context;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.wenshao.coursate.R;
import com.wenshao.coursate.bean.ChildEntity;
import com.wenshao.coursate.bean.GroupEntity;

import java.util.ArrayList;

/**
 * Created by wenshao on 2017/10/14.
 * 首页列表数据适配器
 */

/*
public class GroupedListAdapter extends GroupedRecyclerViewAdapter {
    public GroupedListAdapter(Context context) {
        super(context);
    }

    //返回组的数量
    @Override
    public int getGroupCount() {
        return 0;
    }

    //返回当前组的子项数量
    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    //当前组是否有头部
    @Override
    public boolean hasHeader(int groupPosition) {
        return false;
    }
    //当前组是否有尾部
    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }
    //返回头部的布局id。(如果hasHeader返回false，这个方法不会执行)
    @Override
    public int getHeaderLayout(int viewType) {
        return 0;
    }
    //返回尾部的布局id。(如果hasFooter返回false，这个方法不会执行)
    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }
    //返回子项的布局id。
    @Override
    public int getChildLayout(int viewType) {
        return 0;
    }
    //绑定头部布局数据。(如果hasHeader返回false，这个方法不会执行)
    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {

    }
    //绑定头部布局数据。(如果hasHeader返回false，这个方法不会执行)
    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }
    //绑定子项布局数据。
    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {

    }
    //返回头部的viewType。
    public int getHeaderViewType(int groupPosition){
        return 0;
    }

    //返回尾部的viewType。
    public int getFooterViewType(int groupPosition) {
        return 0;
    }

    //返回子项的viewType。
    public int getChildViewType(int groupPosition, int childPosition) {
        return 0;
    }



}
*/
public class GroupedListAdapter extends GroupedRecyclerViewAdapter {
    private static final int TYPE_HEADER_1 = 1;
    private static final int TYPE_HEADER_2 = 2;
    private static final int TYPE_FOOTER_1 = 3;
    private static final int TYPE_FOOTER_2 = 4;
    private static final int TYPE_CHILD_1 = 5;
    private static final int TYPE_CHILD_2 = 6;

    private ArrayList<GroupEntity> mGroups;

    public GroupedListAdapter(Context context, ArrayList<GroupEntity> groups) {
        super(context);
        mGroups = groups;
    }


    @Override
    public int getGroupCount() {
        return mGroups == null ? 0 : mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<ChildEntity> children = mGroups.get(groupPosition).getChildren();
        return children == null ? 0 : children.size();
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return true;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        System.out.println("===================");

        return R.layout.adapter_header;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return R.layout.adapter_footer;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.adapter_child;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        GroupEntity entity = mGroups.get(groupPosition);
        holder.setText(R.id.title_tv, entity.getHeader());
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
        GroupEntity entity = mGroups.get(groupPosition);
        holder.setText(R.id.footer_tv, entity.getFooter());
    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        ChildEntity entity = mGroups.get(groupPosition).getChildren().get(childPosition);
        holder.setText(R.id.tv_child, entity.getChild());
    }

}
