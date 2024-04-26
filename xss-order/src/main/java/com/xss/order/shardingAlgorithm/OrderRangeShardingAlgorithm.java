package com.xss.order.shardingAlgorithm;


import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 分片键自定义算法
 *
 * @author xss
 * @version 1.0
 * @date 2024/04/13
 */
public class OrderRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>();
        Range<Long> range = shardingValue.getValueRange();
        for (String each : availableTargetNames) {
            Long lowerEndpoint = range.lowerEndpoint();
            Long upperEndpoint = range.upperEndpoint();
            if (lowerEndpoint % 4 == 1 && upperEndpoint % 4 == 2 && each.endsWith("order_1")) {
                result.add(each);
            } else if (lowerEndpoint % 4 == 2 && upperEndpoint % 4 == 0 && each.endsWith("order_2")) {
                result.add(each);
            } else if (lowerEndpoint % 4 == 0 && upperEndpoint % 4 == 3 && each.endsWith("order_3")) {
                result.add(each);
            } else if (lowerEndpoint % 4 == 3 && (upperEndpoint == null || upperEndpoint % 4 == 1) && each.endsWith("order_4")) {
                result.add(each);
            }
        }
        return result;
    }
}
