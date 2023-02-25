package com.example.javasalttest.service;

import com.example.javasalttest.requests.ConsumerRequest;
import com.example.javasalttest.entity.Consumer;
import com.example.javasalttest.requests.DatatableRequest;

import java.util.List;
import java.util.Map;

public interface ConsumerService {
    public List<Consumer> getAll();
    public Map<String, Object> getDatatableConsumer(DatatableRequest request);
    public Consumer createConsumer(ConsumerRequest request);
    public Consumer detailConsumer(Long id);
    public Consumer updateConsumer(ConsumerRequest request, Long id);
    public void deleteConsumer(Long id);
}
