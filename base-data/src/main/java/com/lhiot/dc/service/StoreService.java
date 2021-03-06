package com.lhiot.dc.service;

import com.leon.microx.util.Position;
import com.leon.microx.util.StringUtils;
import com.leon.microx.web.result.Pages;
import com.lhiot.dc.entity.Store;
import com.lhiot.dc.model.StoreSearchParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangfeng created in 2018/9/22 9:03
 **/
@Service
@Slf4j
@Transactional
public class StoreService {

    public Pages<Store> filtrate(List<Store> list, StoreSearchParam param) {
        //根据应用类型过滤
        Stream<Store> storeStream = list.stream().filter(store -> this.typeNotEmpty(store, param));
        if (Objects.nonNull(param.getLat()) && Objects.nonNull(param.getLng())) {
            //根据经纬度过滤
            storeStream = storeStream.peek(store -> this.distance(store, param))
                    .filter(store -> Objects.nonNull(store.getDistance()) && param.getDistance() >= store.getDistance())
                    .sorted(Comparator.comparing(Store::getDistance));
        }

        List<Store> result = storeStream.collect(Collectors.toList());
        int total = result.size();
        if (Objects.nonNull(param.getPage()) && Objects.nonNull(param.getRows())) {
            //分页
            result = result.stream().skip((param.getPage() - 1) * param.getRows())
                    .limit(param.getRows()).collect(Collectors.toList());
        }
        return Pages.of(total, result);
    }

    private boolean typeNotEmpty(Store store, StoreSearchParam param) {
        if (Objects.isNull(param.getApplicationType())) {
            return true;
        }
        return Arrays.asList(StringUtils.tokenizeToStringArray(store.getApplicationType(), ",")).contains(param.getApplicationType());
    }

    private void distance(Store store, StoreSearchParam param) {
        if (Objects.nonNull(store.getLongitude()) && Objects.nonNull(store.getLatitude())) {
            Position.GCJ02 gcj02 = Position.GCJ02.of(param.getLng(), param.getLat());
            BigDecimal distance = gcj02.distance(Position.GCJ02.of(store.getLongitude().doubleValue(), store.getLatitude().doubleValue()));
            store.setDistance(distance.doubleValue());
        }
    }
}
