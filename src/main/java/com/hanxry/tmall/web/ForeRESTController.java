package com.hanxry.tmall.web;

import com.hanxry.tmall.pojo.Category;
import com.hanxry.tmall.service.CategoryService;
import com.hanxry.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台就不太一样了，比如首页，既有分类，又有产品，还有用户登录信息，购买数量信息。 那么前台页面与哪个Controller对应合适呢？
 * 是对应CategoryController呢？还是对应UserController呢？ 似乎都不合适。
 * 所以就创建了一个新的Controller: ForeRESTController,专门用来对应前台页面的路径。
 *
 * @author hanxry
 * @since 2021/11/1
 */
@RestController
public class ForeRESTController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/forehome")
    public Object home() {
        List<Category> cs = categoryService.list();
        productService.fill(cs);
        productService.fillByRow(cs);
        categoryService.removeCategoryFromProduct(cs);
        return cs;
    }
}
