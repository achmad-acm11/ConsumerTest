package com.example.javasalttest.requests;

import lombok.Builder;

@Builder
public record DatatableRequest(
        String draw, Integer start, Integer length, String search
) {
}
