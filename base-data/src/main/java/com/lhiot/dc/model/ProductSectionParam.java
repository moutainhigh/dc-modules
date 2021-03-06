package com.lhiot.dc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leon.microx.util.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaojian  created in  2018/11/17 14:33
 */
@ApiModel
@Data
public class ProductSectionParam {

    @ApiModelProperty(notes = "板块主键Id集合", dataType = "String")
    private String ids;
    @ApiModelProperty(notes = "位置ID(多个以英文逗号分隔)", dataType = "String")
    private String positionIds;
    @ApiModelProperty(notes = "父级ID", dataType = "Long")
    private Long parentId;
    @ApiModelProperty(notes = "板块名称", dataType = "String")
    private String sectionName;
    @ApiModelProperty(notes = "起始创建时间", dataType = "Date")
    private Date beginCreateAt;
    @ApiModelProperty(notes = "截止创建时间", dataType = "Date")
    private Date endCreateAt;
    @ApiModelProperty(notes = "是否加载版块下商品上架信息(为空则默认为false)", dataType = "Boolean")
    private Boolean includeShelves;
    @ApiModelProperty(notes = "加载商品上架最大条数(includeShelves为true起用，为空则加载所有)", dataType = "Long")
    private Long includeShelvesQty;
    @ApiModelProperty(notes = "是否加载商品信息((includeShelves为true起作用，为空则默认为false)", dataType = "Boolean")
    private Boolean includeProduct;
    @ApiModelProperty(notes = "版块内商品上架ID", dataType = "Long")
    private Long shelfId;
    @ApiModelProperty(notes = "每页查询条数(为空或0不分页查所有)", dataType = "Integer")
    private Integer rows;
    @ApiModelProperty(notes = "当前页", dataType = "Integer")
    private Integer page;

    @ApiModelProperty(hidden = true)
    private Integer startRow;
    @ApiModelProperty(hidden = true)
    private Boolean pageFlag;
    @ApiModelProperty(hidden = true)
    private List<String> idList;

    @JsonIgnore
    public Integer getStartRow() {
        if (Objects.nonNull(this.rows) && this.rows > 0) {
            return (Objects.nonNull(this.page) && this.page > 0 ? this.page - 1 : 0) * this.rows;
        }
        return null;
    }

    @JsonIgnore
    public Boolean getPageFlag() {
        return Objects.nonNull(this.page) && Objects.nonNull(this.rows) && this.page > 0 && this.rows > 0;
    }

    public List<String> getIdList() {
        return Arrays.asList(StringUtils.tokenizeToStringArray(this.ids, ","));
    }
}
