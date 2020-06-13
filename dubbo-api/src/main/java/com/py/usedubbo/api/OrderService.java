package com.py.usedubbo.api;

import com.py.usedubbo.rpcentity.OrderRpc;

/**
 * @author 彭有
 * @date 2020/6/13
 */
public interface OrderService {

    /**
     * 根据id获取订单信息
     *
     * @param uuid
     * @return
     */
    OrderRpc getOrder(String uuid);
}
