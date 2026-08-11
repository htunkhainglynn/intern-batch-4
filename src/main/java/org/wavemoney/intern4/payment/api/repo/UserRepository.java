package org.wavemoney.intern4.payment.api.repo;

import org.springframework.stereotype.Repository;
import org.wavemoney.intern4.payment.api.entity.User;

@Repository
public class UserRepository {

    public User createUser(User user) {
        System.out.printf("Saving User to Database: " + user);
        return user;
    }
}
