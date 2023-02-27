package com.example.javasalttest.requests;

import lombok.Builder;

@Builder
public record ConsumerRequest (
        String name, String address, String city, String province, String status
){
}
