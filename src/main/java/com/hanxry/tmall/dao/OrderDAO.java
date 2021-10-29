package com.hanxry.tmall.dao;

import com.hanxry.tmall.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order,Integer>{
}
