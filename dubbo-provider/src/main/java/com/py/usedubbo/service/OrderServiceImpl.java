package com.py.usedubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.py.usedubbo.api.OrderService;
import com.py.usedubbo.rpcentity.OrderRpc;
import org.springframework.stereotype.Component;

/**
 * @author 彭有
 * @date 2020/6/13
 */
@Service(async = true, timeout = 3000)
//@Service(timeout = 5000)
@Component
public class OrderServiceImpl implements OrderService {


    @Override
    public OrderRpc getOrder(String uuid) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OrderRpc orderRpc = new OrderRpc();
        orderRpc.setUuid(uuid);
        orderRpc.setOrderName("budingxie牌手机");
        return orderRpc;
    }
}
