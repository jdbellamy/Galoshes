package com.example

import org.springframework.stereotype.Service

import static com.example.Detailed.DetailType.DB_CLIENT


@Service
class OtherService {

    @Detailed(name="getDoubles", type=DB_CLIENT)
    def getDoubles(Integer limit) {
        new Random().doubles().limit(limit)
    }
}
