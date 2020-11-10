package com.freetalk.cherokee.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freetalk.cherokee.entity.ModelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author doujl
 * @since 2020-03-25
 */
@Repository
@Mapper
public interface ModelMapper extends BaseMapper<ModelEntity> {

    List<ModelEntity> selectByName(@Param("name") String name);

}
