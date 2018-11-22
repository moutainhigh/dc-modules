package com.lhiot.dc.service;

import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.lhiot.dc.entity.UiPosition;
import com.lhiot.dc.model.UiPositionParam;
import com.lhiot.dc.mapper.UiPositionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author xiaojian  created in  2018/11/20 15:57
 */
@Service
@Slf4j
@Transactional
public class UiPositionService {

    private UiPositionMapper positionMapper;

    public UiPositionService(UiPositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    /**
     * 新增位置
     *
     * @param uiPosition 位置对象
     * @return Tips信息  成功在message中返回Id
     */
    public Tips addUiPosition(UiPosition uiPosition) {
        // 幂等添加
        UiPosition po = positionMapper.findByCode(uiPosition.getCode());
        if (Objects.nonNull(po)) {
            return Tips.warn("位置编码重复，添加失败.");
        }
        positionMapper.insert(uiPosition);
        return Tips.info(uiPosition.getId() + "");
    }


    /**
     * 修改位置
     *
     * @param uiPosition 位置对象
     * @return 执行结果 true 或者 false
     */
    public boolean update(UiPosition uiPosition) {
        return positionMapper.updateById(uiPosition) > 0;
    }


    /**
     * 根据位置ID查找单个位置
     *
     * @param positionId 位置ID
     * @return 商品版块对象
     */
    public UiPosition findById(Long positionId) {
        return positionMapper.findById(positionId);
    }


    /**
     * 根据Id集合批量删除位置信息
     *
     * @param ids 位置ID集合
     * @return 执行结果 true 或者 false
     */
    public boolean batchDeleteByIds(String ids) {
        return positionMapper.deleteByIds(ids) > 0;
    }


    /**
     * 查询位置信息列表
     *
     * @param param 参数
     * @return 分页商品分类信息数据
     */
    public Pages<UiPosition> findList(UiPositionParam param) {
        List<UiPosition> list = positionMapper.findList(param);
        boolean pageFlag = Objects.nonNull(param.getPage()) && Objects.nonNull(param.getRows()) && param.getPage() > 0 && param.getRows() > 0;
        int total = pageFlag ? positionMapper.findCount(param) : list.size();
        return Pages.of(total, list);
    }


}
