package com.lhiot.dc.mapper;


import com.lhiot.dc.domain.ProductShelf;
import com.lhiot.dc.domain.ProductShelfParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author xiaojian created in 2018/11/14 11:44
 **/
@Mapper
@Repository
public interface ProductShelfMapper {

    /**
     * 新增商品上架
     *
     * @param ProductShelf对象
     * @return 执行结果
     */
    int insert(ProductShelf productShelf);


    /**
     * 修改商品上架
     *
     * @param ProductShelf对象
     * @return 执行结果
     */
    int updateById(ProductShelf productShelf);


    /**
     * 根据ID查找单个商品上架
     *
     * @param shelfId
     * @return 商品上架对象
     */
    ProductShelf findById(Long shelfId);


    /**
     * 删除商品上架集合
     *
     * @param ids
     * @return 执行结果
     */
    int deleteByIds(String ids);


    /**
     * 根据传入商品规格ID集合，查询所属的商品上架集合
     *
     * @param specificationIds 商品规格id集合
     * @return 商品上架集合
     */
    List<ProductShelf> findListBySpecificationIds(String specificationIds);


    /**
     * 查询商品上架信息列表
     *
     * @param param
     * @return 商品上架信息列表
     */
    List<ProductShelf> findList(ProductShelfParam param);

    /**
     * 查询商品上架信息总数
     *
     * @param param
     * @return 总数
     */
    int findCount(ProductShelfParam param);


}