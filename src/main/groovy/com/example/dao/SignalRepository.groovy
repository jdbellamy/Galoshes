package com.example.dao

import com.example.models.SignalElement
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
public interface SignalRepository extends MongoRepository<SignalElement, String> {

    List<SignalElement> findByCorrelationId(String correlationId)
    List<SignalElement> findByTransactionId(String transactionId)

}