package com.lhiot.dc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhiot.dc.entity.type.ApplicationType;
import com.lhiot.dc.entity.type.PositionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author xiaojian  created in  2018/11/20 15:58
 */
@Data
@ApiModel
public class UiPosition {
    @ApiModelProperty(notes = "主键Id", dataType = "Long", readOnly = true)
    private Long id;
    @ApiModelProperty(notes = "类别:PRODUCT-商品，ADVERTISEMENT-广告，ARTICLE-文章", dataType = "PositionType")
    private PositionType positionType;
    @ApiModelProperty(notes = "位置编码", dataType = "String")
    private String code;
    @ApiModelProperty(notes = "描述", dataType = "String")
    private String description;
    @ApiModelProperty(notes = "应用类型", dataType = "ApplicationType")
    private ApplicationType[] applicationTypes;

    @JsonIgnore
    public String getApplications() {
        return ApplicationType.convert(applicationTypes);
    }

    public void setApplications(String applicationTypes) {
        this.applicationTypes = ApplicationType.convert(applicationTypes);
    }



}
