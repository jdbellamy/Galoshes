package com.example.models

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import org.springframework.data.annotation.Id

import java.time.LocalDateTime

import static com.example.utils.SomeDefaults.timestampFormatter

@Builder(prefix = "")
@Canonical(excludes = ['timestampFormatter'])
class SignalElement {

    @Id String id
    String correlationId
    String transactionId
    LocalDateTime timestamp
    Map<String, String> details

    @Override
    String toString() {
        """
        SIGNAL ----------------------
        id: $id
        timestamp: ${timestampFormatter.format(timestamp)}
        correlationId: $correlationId
        transactionId: $transactionId
        details:
        $details
        -----------------------------
        """
    }
}
