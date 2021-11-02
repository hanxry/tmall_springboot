package com.hanxry.tmall.service;

import com.hanxry.tmall.dao.UserDAO;
import com.hanxry.tmall.pojo.User;
import com.hanxry.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    /**
     * 判断某个名称是否已经被使用过了。
     *
     * @param name
     * @return
     */
    public boolean isExist(String name) {
        User user = getByName(name);
        return null != user;
    }

    public User getByName(String name) {
        return userDAO.findByName(name);
    }

    public User get(String name, String password) {
        return userDAO.getByNameAndPassword(name, password);
    }

    public Page4Navigator<User> list(int start, int size, int navigatePages) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page pageFromJPA = userDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    public void add(User user) {
        userDAO.save(user);
    }

}
