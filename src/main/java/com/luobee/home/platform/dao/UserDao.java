package com.luobee.home.platform.dao;

import com.luobee.home.platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    User getByUsername(String name);
    User getByUsernameAndPassword(String name, String password);
}
