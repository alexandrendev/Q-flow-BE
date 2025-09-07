package com.qflow.Qflow.core.ports;

import com.qflow.Qflow.core.entity.user.User;

public interface UserRepository {

    User findById(Long id);
    User findByEmail(String email);
    void save(User user);

}
