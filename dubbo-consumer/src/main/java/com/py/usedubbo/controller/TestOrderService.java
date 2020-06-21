package com.py.usedubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.py.usedubbo.api.OrderService;
import com.py.usedubbo.rpcentity.OrderRpc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author 彭有
 * @date 2020/6/13
 */
@RestController
public class TestOrderService {

    @Reference(async = true, timeout = 3000)
    private OrderService orderService;

    @GetMapping("/getOrder")
    public String getOrder(String uuid) {
        OrderRpc order = orderService.getOrder(uuid);

        Future<OrderRpc> orderFuture = RpcContext.getContext().getFuture();
        try {
            OrderRpc orderRpc = orderFuture.get();
            if (order == null) {
                order = orderRpc;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = null;
        try {
            orderJson = objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        JsonNode node = objectMapper.readTree(orderJson);
        return orderJson;
    }
}
