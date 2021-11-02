package com.hanxry.tmall.dao;

import com.hanxry.tmall.pojo.Order;
import com.hanxry.tmall.pojo.OrderItem;
import com.hanxry.tmall.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {
    // 在命名里提供OrderByIdDesc，就进行到排序了，就可以不用传Sort参数了---实现排序的另一种方式Sort.by()
    List<OrderItem> findByOrderOrderByIdDesc(Order order);

    List<OrderItem> findByProduct(Product product);
}
