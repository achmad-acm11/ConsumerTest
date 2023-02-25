package com.example.javasalttest.service;

import com.example.javasalttest.requests.ConsumerRequest;
import com.example.javasalttest.entity.Consumer;
import com.example.javasalttest.entity.Status;
import com.example.javasalttest.repository.ConsumerRepository;
import com.example.javasalttest.requests.DatatableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService{
    private final ConsumerRepository repo;
    @Override
    public List<Consumer> getAll() {
        return (List<Consumer>) repo.findAll();
    }

    @Override
    public Map<String, Object> getDatatableConsumer(DatatableRequest request) {
        Map<String, Object> res = new HashMap<>();
        List<Consumer> consumerList = repo.findForPage(request.search(),PageRequest.of(request.start() * request.length(), request.length()));
        res.put("draw", request.draw());
        res.put("recordsTotal", repo.count());
        res.put("recordsFiltered", repo.count());
        res.put("data", consumerList);

        return res;
    }

    @Override
    public Consumer createConsumer(ConsumerRequest request) {
        Consumer consumer = Consumer.builder()
                .name(request.name())
                .address(request.address())
                .city(request.city())
                .province(request.province())
                .registration_date(String.valueOf(LocalDateTime.now()))
                .status(Status.AKTIF)
                .build();

        return repo.save(consumer);
    }

    @Override
    public Consumer detailConsumer(Long id) {
        var consumer = repo.findById(id).orElse(Consumer.builder().build());
        return consumer;
    }

    @Override
    public Consumer updateConsumer(ConsumerRequest request, Long id) {
        var consumer = repo.findById(id).orElse(Consumer.builder().build());
        consumer.setName(request.name());
        consumer.setAddress(request.address());
        consumer.setProvince(request.province());
        consumer.setCity(request.city());
        consumer.setStatus(request.status() == null ? Status.NON_AKTIF : Status.AKTIF);

        return repo.save(consumer);
    }

    @Override
    public void deleteConsumer(Long id) {
        repo.deleteById(id);
    }
}
