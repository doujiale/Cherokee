package com.freetalk.cherokee.dto;

import com.freetalk.cherokee.entity.ModelEntity;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ModelInfoDTO implements Serializable {
    private static final long serialVersionUID = -3955021490347054674L;
    private String name;
    private List<ModelEntity> infoList;

}
