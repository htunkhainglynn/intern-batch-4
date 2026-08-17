package org.wavemoney.intern4.payment.api.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.wavemoney.intern4.payment.api.entity.User;
import org.wavemoney.intern4.payment.api.entity.Wallet;

import java.util.Optional;

@Repository
public interface WalletRepository extends MongoRepository<Wallet, String> {
    Optional<Wallet> findByPhone(String phone);
    void deleteByPhone(String phone);
}
