package com.example.javasalttest.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ConsumerRequest (
        @NotBlank String name, String address, @NotBlank String city, @NotBlank String province, String status
){
}
