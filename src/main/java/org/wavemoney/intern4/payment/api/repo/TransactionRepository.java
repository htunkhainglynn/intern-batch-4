package org.wavemoney.intern4.payment.api.repo;

import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.wavemoney.intern4.payment.api.entity.Transaction;

public interface TransactionRepository extends MongoRepository<@NonNull Transaction, @NonNull String> {

}
