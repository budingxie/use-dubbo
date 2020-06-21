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

//    @Reference(
//            async = true,
//            timeout = 3000,
//            parameters = {"getOrder.timeout", "3000", "getOrder2.timeout", "5000", "getOrder.onreturn", "myCallback.callback"}
//    )
//    private OrderService orderService;

    @Reference(timeout = 5000)
    private OrderService orderService;

    /**
     * 同步调用、同步返回
     *
     * @param uuid
     * @return
     */
    @GetMapping("/getOrder")
    public String getOrder(String uuid) {
        long startTime = System.currentTimeMillis();
        OrderRpc order = orderService.getOrder(uuid);
        OrderRpc order1 = orderService.getOrder(uuid);
        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = null;
        try {
            orderJson = objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time consuming : " + (endTime - startTime) + "ms");
//        JsonNode node = objectMapper.readTree(orderJson);
        return orderJson;
    }
    /**
     * 异步调用、异步返回
     */
//    @GetMapping("/getOrder")
//    public String getOrder(String uuid) {
//        long startTime = System.currentTimeMillis();
//
//        OrderRpc order = orderService.getOrder(uuid);
//        OrderRpc order1 = orderService.getOrder(uuid);
//
//        Future<OrderRpc> orderFuture = RpcContext.getContext().getFuture();
//        Future<OrderRpc> orderFuture1 = RpcContext.getContext().getFuture();
//        try {
//            OrderRpc orderRpc = orderFuture.get();
//            OrderRpc orderRpc1 = orderFuture1.get();
//            if (order == null) {
//                order = orderRpc;
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String orderJson = null;
//        try {
//            orderJson = objectMapper.writeValueAsString(order);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("time consuming : " + (endTime - startTime) + "ms");
////        JsonNode node = objectMapper.readTree(orderJson);
//        return orderJson;
//    }
}
