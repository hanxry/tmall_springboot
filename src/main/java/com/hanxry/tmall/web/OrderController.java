package com.hanxry.tmall.web;

import com.hanxry.tmall.pojo.Order;
import com.hanxry.tmall.service.OrderItemService;
import com.hanxry.tmall.service.OrderService;
import com.hanxry.tmall.util.Page4Navigator;
import com.hanxry.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    /**
     * 分页查询
     *
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    @GetMapping("/orders")
    public Page4Navigator<Order> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start < 0 ? 0 : start;
        Page4Navigator<Order> page = orderService.list(start, size, 5);
        orderItemService.fill(page.getContent());
        //这里就调用了 removeOrderFromOrderItem 方法
        orderService.removeOrderFromOrderItem(page.getContent());
        return page;
    }

    /**
     * 订单发货
     *
     * @param oid
     * @return
     * @throws IOException
     */
    @PutMapping("deliveryOrder/{oid}")
    public Object deliveryOrder(@PathVariable int oid) throws IOException {
        Order o = orderService.get(oid);
        o.setDeliveryDate(new Date());
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return Result.success();
    }
}

