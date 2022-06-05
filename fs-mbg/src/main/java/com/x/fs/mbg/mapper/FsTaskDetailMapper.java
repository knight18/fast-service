package com.x.fs.mbg.mapper;

import com.x.fs.mbg.model.FsTaskDetail;
import com.x.fs.mbg.model.FsTaskDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FsTaskDetailMapper {
    long countByExample(FsTaskDetailExample example);

    int deleteByExample(FsTaskDetailExample example);

    int deleteByPrimaryKey(@Param("taskDate") Integer taskDate, @Param("taskWorkSn") Integer taskWorkSn, @Param("taskStage") Integer taskStage, @Param("fsTask") Integer fsTask);

    int insert(FsTaskDetail record);

    int insertSelective(FsTaskDetail record);

    List<FsTaskDetail> selectByExample(FsTaskDetailExample example);

    FsTaskDetail selectByPrimaryKey(@Param("taskDate") Integer taskDate, @Param("taskWorkSn") Integer taskWorkSn, @Param("taskStage") Integer taskStage, @Param("fsTask") Integer fsTask);

    int updateByExampleSelective(@Param("record") FsTaskDetail record, @Param("example") FsTaskDetailExample example);

    int updateByExample(@Param("record") FsTaskDetail record, @Param("example") FsTaskDetailExample example);

    int updateByPrimaryKeySelective(FsTaskDetail record);

    int updateByPrimaryKey(FsTaskDetail record);
}