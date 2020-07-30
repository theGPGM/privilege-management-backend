package org.george.pm.mapper;

import org.apache.ibatis.annotations.Param;
import org.george.pm.model.JobLevel;

import java.util.List;

public interface JobLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    List<JobLevel> getAllJobLevels();

    /**
     * 批量删除职称
     * @param ids
     * @return
     */
    int deleteByPrimaryKeys(@Param("ids")Integer[] ids);
}