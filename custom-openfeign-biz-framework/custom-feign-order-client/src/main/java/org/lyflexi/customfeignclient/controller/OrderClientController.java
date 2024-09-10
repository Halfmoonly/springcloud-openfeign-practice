package org.lyflexi.customfeignclient.controller;

import jakarta.annotation.Resource;

import org.lyflexi.customcloudfeignapi.entity.Order;
import org.lyflexi.customcloudfeignapi.result.Result;
import org.lyflexi.customfeignclient.feign.client.OrderClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lyflexi
 * @project: debuginfo_jdkToFramework
 * @Date: 2024/7/27 14:13
 */
// curl http://localhost:9000/consumer/feign/order/get/1
@RestController
public class OrderClientController {

    @Resource
    private OrderClient orderClient;

    @GetMapping(value = "/consumer/feign/order/get/{id}")
    public Result<Order> getOrderById(@PathVariable("id") Long id)
    {
        Order order = orderClient.getOrderById(id);
        return new Result<>(order);
    }
}