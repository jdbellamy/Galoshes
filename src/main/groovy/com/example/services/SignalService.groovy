package com.example.services

import com.example.dao.SignalRepository
import com.example.models.SignalElement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SignalService {

    @Autowired SignalRepository repository

    public List<SignalElement> signals() {
        repository.findAll()
    }

    public SignalElement getSignal(String id) {
        repository.findOne(id)
    }

    public List<SignalElement> correlated(String corrId) {
        repository.findByCorrelationId(corrId)
    }
}
