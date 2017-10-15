package com.wenshao.coursate.model;

import com.wenshao.coursate.bean.ChildEntity;
import com.wenshao.coursate.bean.GroupEntity;
import com.wenshao.coursate.bean.VideoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wenshao on 2017/10/14.
 * 分组
 */

public class GroupModel {

    /**
     * 获取组列表数据
     *
     * @param groupCount    组数量
     * @param childrenCount 每个组里的子项数量
     */
    public static ArrayList<GroupEntity> getGroups(int groupCount, int childrenCount) {
        ArrayList<GroupEntity> groups = new ArrayList<>();
        //new GroupEntity()
        for (int i = 0; i < groupCount; i++) {
            ArrayList<ChildEntity> children = new ArrayList<>();
            for (int j = 0; j < childrenCount; j++) {
                children.add(new ChildEntity("第" + (i + 1) + "组第" + (j + 1) + "项"));
            }
            groups.add(new GroupEntity("第" + (i + 1) + "组头部",
                    "第" + (i + 1) + "组尾部", children));
        }
        return groups;
    }
    /**
     * 将获取的数据转为组列表数据
     */
    public static ArrayList<GroupEntity> getGroups(Map<String,List<VideoBean>> videos) {
        ArrayList<GroupEntity> groups = new ArrayList<>();

        return groups;
    }



}