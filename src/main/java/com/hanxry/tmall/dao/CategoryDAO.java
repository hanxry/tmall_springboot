package com.hanxry.tmall.dao;

import com.hanxry.tmall.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 分类信息 Dao组件
 *
 * @author hanxry
 */
public interface CategoryDAO extends JpaRepository<Category, Integer> {

}