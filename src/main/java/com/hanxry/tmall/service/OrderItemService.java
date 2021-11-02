package com.hanxry.tmall.service;

import com.hanxry.tmall.dao.OrderItemDAO;
import com.hanxry.tmall.pojo.Order;
import com.hanxry.tmall.pojo.OrderItem;
import com.hanxry.tmall.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemDAO orderItemDAO;
    @Autowired
    ProductImageService productImageService;

    /**
     * OrderItemService，提供对OrderItem的业务操作，其中主要是 fill 方法。
     * 从数据库中取出来的 Order 是没有 OrderItem集合的，这里通过 OrderItemDAO 取出来再放在 Order的 orderItems属性上。
     * 除此之外，还计算订单总数，总金额等等信息。
     *
     * @param orders
     */
    public void fill(List<Order> orders) {
        for (Order order : orders)
            fill(order);
    }

    public void fill(Order order) {
        List<OrderItem> orderItems = listByOrder(order);
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : orderItems) {
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
            productImageService.setFirstProductImage(oi.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);

    }

    public void add(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }

    public void update(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }

    public OrderItem get(int id) {
        return orderItemDAO.getOne(id);
    }

    public void delete(int id) {
        orderItemDAO.deleteById(id);
    }

    public int getSaleCount(Product product) {
        List<OrderItem> ois = listByProduct(product);
        int result = 0;
        for (OrderItem oi : ois) {
            if (null != oi.getOrder())
                if (null != oi.getOrder() && null != oi.getOrder().getPayDate())
                    result += oi.getNumber();
        }
        return result;
    }

    public List<OrderItem> listByProduct(Product product) {
        return orderItemDAO.findByProduct(product);
    }

    public List<OrderItem> listByOrder(Order order) {
        return orderItemDAO.findByOrderOrderByIdDesc(order);
    }

}
