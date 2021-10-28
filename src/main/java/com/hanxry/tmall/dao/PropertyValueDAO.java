package com.hanxry.tmall.dao;

import com.hanxry.tmall.pojo.Product;
import com.hanxry.tmall.pojo.Property;
import com.hanxry.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PropertyValueDAO extends JpaRepository<PropertyValue, Integer> {

    List<PropertyValue> findByProductOrderByIdDesc(Product product);

    PropertyValue getByPropertyAndProduct(Property property, Product product);

}
