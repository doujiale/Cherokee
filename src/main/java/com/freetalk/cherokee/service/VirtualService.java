package com.freetalk.cherokee.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freetalk.cherokee.dao.ModelMapper;
import com.freetalk.cherokee.dto.ModelInfoDTO;
import com.freetalk.cherokee.entity.ModelEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : doujl
 * @className : VirtualService
 * @description : 查询目录
 * @date : 2020-10-27 18:38:30
 */
@Slf4j
@Service
public class VirtualService extends ServiceImpl<ModelMapper, ModelEntity> {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 根据name标识model信息
     *
     * @param name 名称
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ModelInfoDTO getModelInfoById(String name) {
        List<ModelEntity> infoList = modelMapper.selectByName(name);
        return ModelInfoDTO.builder()
                .name(name).infoList(infoList).build();
    }
}
