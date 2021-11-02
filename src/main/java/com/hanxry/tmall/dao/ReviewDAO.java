package com.hanxry.tmall.dao;

import com.hanxry.tmall.pojo.Product;
import com.hanxry.tmall.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewDAO extends JpaRepository<Review, Integer> {

    List<Review> findByProductOrderByIdDesc(Product product);

    int countByProduct(Product product);

}