package com.example.javasalttest.service;

import com.example.javasalttest.exception.BadRequestException;
import com.example.javasalttest.exception.NotFoundException;
import com.example.javasalttest.requests.ConsumerRequest;
import com.example.javasalttest.entity.Consumer;
import com.example.javasalttest.entity.Status;
import com.example.javasalttest.repository.ConsumerRepository;
import com.example.javasalttest.requests.DatatableRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService{
    private final ConsumerRepository repo;
    private final Validator validator;

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
        Set<ConstraintViolation<ConsumerRequest>> violations = validator.validate(request);
        if (violations.size() > 0){
            Map<String, String> messages = new HashMap<>();
            violations.stream().forEach(new java.util.function.Consumer<ConstraintViolation<ConsumerRequest>>() {
                @Override
                public void accept(ConstraintViolation<ConsumerRequest> consumerRequestConstraintViolation) {
                    messages.put(consumerRequestConstraintViolation.getPropertyPath().toString(), consumerRequestConstraintViolation.getMessage());
                }
            });

            throw new BadRequestException("Bad reqeust", messages);
        }

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
        var consumer = repo.findById(id).orElse(null);
        if (consumer == null) {
            throw new NotFoundException("Consumer not found");
        }
        return consumer;
    }

    @Override
    public Consumer updateConsumer(ConsumerRequest request, Long id) {
        var consumer = repo.findById(id).orElse(null);
        if (consumer == null) {
            throw new NotFoundException("Consumer not found");
        }

        Set<ConstraintViolation<ConsumerRequest>> violations = validator.validate(request);
        if (violations.size() > 0){
            Map<String, String> messages = new HashMap<>();
            violations.stream().forEach(new java.util.function.Consumer<ConstraintViolation<ConsumerRequest>>() {
                @Override
                public void accept(ConstraintViolation<ConsumerRequest> consumerRequestConstraintViolation) {
                    messages.put(consumerRequestConstraintViolation.getPropertyPath().toString(), consumerRequestConstraintViolation.getMessage());
                }
            });

            throw new BadRequestException("Bad reqeust", messages);
        }

        consumer.setName(request.name());
        consumer.setAddress(request.address());
        consumer.setProvince(request.province());
        consumer.setCity(request.city());
        consumer.setStatus(request.status() == null ? Status.NON_AKTIF : Status.AKTIF);

        return repo.save(consumer);
    }

    @Override
    public void deleteConsumer(Long id) {
        var consumer = repo.findById(id).orElse(null);
        if (consumer == null) {
            throw new NotFoundException("Consumer not found");
        }
        repo.deleteById(id);
    }
}
