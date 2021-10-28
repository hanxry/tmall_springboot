package com.hanxry.tmall.dao;

import com.hanxry.tmall.pojo.Product;
import com.hanxry.tmall.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductImageDAO extends JpaRepository<ProductImage, Integer> {
    public List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);

}
