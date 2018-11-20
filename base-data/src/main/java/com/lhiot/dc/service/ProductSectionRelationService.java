package com.lhiot.dc.service;

import com.leon.microx.util.StringUtils;
import com.lhiot.dc.domain.ProductSectionRelation;
import com.lhiot.dc.mapper.ProductSectionRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiaojian  created in  2018/11/16 9:18
 */
@Service
@Slf4j
@Transactional
public class ProductSectionRelationService {
    private ProductSectionRelationMapper relationMapper;

    public ProductSectionRelationService(ProductSectionRelationMapper relationMapper) {
        this.relationMapper = relationMapper;
    }


    /**
     * 新增商品上架与版块关系
     *
     * @param ProductSectionRelation对象
     * @return 新增商品版块Id
     */
    public Long addRelation(ProductSectionRelation productSectionRelation) {
        relationMapper.insert(productSectionRelation);
        return productSectionRelation.getId();
    }

    /**
     * 新增批量商品上架与版块关系
     *
     * @param sectionId
     * @param shelfIds
     * @return 执行结果
     */
    public boolean addRelationList(Long sectionId, String shelfIds) {
        List<ProductSectionRelation> psrList = new ArrayList<>();
        List<String> shelfIdList = Arrays.asList(StringUtils.tokenizeToStringArray(shelfIds, ","));
        ProductSectionRelation productSectionRelation;
        for (String shelfId : shelfIdList) {
            productSectionRelation = new ProductSectionRelation();
            productSectionRelation.setSectionId(Long.valueOf(sectionId));
            productSectionRelation.setShelfId(Long.valueOf(shelfId));
            psrList.add(productSectionRelation);
        }
        return relationMapper.insertList(psrList) > 0;
    }


    /**
     * 删除商品上架与版块关系
     *
     * @param relationId
     * @return 执行结果 true 或者 false
     */
    public boolean deleteRelation(Long relationId) {
        return relationMapper.deleteById(relationId) > 0;
    }


    /**
     * 批量删除商品上架与版块关系
     *
     * @param sectionId
     * @param shelfIds
     * @return 执行结果 true 或者 false
     */
    public boolean deleteRelationList(Long sectionId, String shelfIds) {
        return relationMapper.deleteRelationList(sectionId, shelfIds) > 0;
    }


}
