package com.wenshao.dal.dao;

import com.wenshao.dal.bean.UserAuto;
import com.wenshao.dal.bean.UserAutoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserAutoMapper {
    int countByExample(UserAutoExample example);

    int deleteByExample(UserAutoExample example);

    int insert(UserAuto record);

    int insertSelective(UserAuto record);

    List<UserAuto> selectByExampleWithRowbounds(UserAutoExample example, RowBounds rowBounds);

    List<UserAuto> selectByExample(UserAutoExample example);

    int updateByExampleSelective(@Param("record") UserAuto record, @Param("example") UserAutoExample example);

    int updateByExample(@Param("record") UserAuto record, @Param("example") UserAutoExample example);
}