package com.zhangds.common.util;

import java.math.BigDecimal;

/**
 * Stream 使用BigDecimal求和过滤null
 * @author: zhangds
 * @date: 2020/9/9 9:26
 */
public class BigDecimalUtils {

    public static BigDecimal ifNullSet0(BigDecimal in) {
        if (in != null) {
            return in;
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal sum(BigDecimal ...in){
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < in.length; i++){
            result = result.add(ifNullSet0(in[i]));
        }
        return result;
    }

}
