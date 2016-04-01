package com.example.models

import groovy.transform.Canonical
import org.springframework.data.annotation.Id

@Canonical
class SignalElement {

    @Id String id
    String correlationId
    String transactionId
    Map<String, String> details

    @Override
    String toString() {
        """
        id: $id
        correlationId: $correlationId
        transactionId: $transactionId
        details:
        $details
        -----------------------------
        """
    }
}
