package com.example.javasalttest.service;

import com.example.javasalttest.entity.Consumer;
import com.example.javasalttest.entity.Status;
import com.example.javasalttest.repository.ConsumerRepository;
import com.example.javasalttest.requests.ConsumerRequest;
import com.example.javasalttest.requests.DatatableRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsumerServiceImplTest {

    @Mock
    private ConsumerRepository repository;
    private ConsumerService service;

    @BeforeEach
    void setUp() {
        service = new ConsumerServiceImpl(repository);
    }

    @Test
    void getAll() {
        service.getAll();

        verify(repository).findAll();
    }

    @Test
    void createConsumer() {
        ConsumerRequest request = ConsumerRequest.builder()
                .name("Budi")
                .address("Jl. Sejahtera")
                .city("Bogor")
                .province("Jawa Barat")
                .build();

        given(repository.save(any())).willReturn(Consumer.builder()
                .name(request.name())
                .address(request.address())
                .city(request.city())
                .province(request.province())
                .registration_date(String.valueOf(LocalDateTime.now()))
                .status(Status.AKTIF).build()
        );
        service.createConsumer(request);

        ArgumentCaptor<Consumer> consumerArgumentCaptor = ArgumentCaptor.forClass(Consumer.class);
        verify(repository).save(consumerArgumentCaptor.capture());
    }

    @Test
    void detailConsumer() {
        given(repository.findById(1L)).willReturn(
                Optional.ofNullable(Consumer.builder()
                        .name("Budi")
                        .address("Jl. Sejahtera")
                        .city("Bogor")
                        .province("Jawa Barat")
                        .registration_date(String.valueOf(LocalDateTime.now()))
                        .status(Status.AKTIF).build())
        );
        service.detailConsumer(1L);

        verify(repository).findById(1L);
    }

    @Test
    void updateConsumer() {
        ConsumerRequest request = ConsumerRequest.builder()
                .name("Budi Update")
                .address("Jl. Sejahtera")
                .city("Bogor")
                .province("Jawa Barat")
                .build();
        given(repository.findById(1L)).willReturn(
                Optional.ofNullable(Consumer.builder()
                        .name("Budi")
                        .address("Jl. Sejahtera")
                        .city("Bogor")
                        .province("Jawa Barat")
                        .registration_date(String.valueOf(LocalDateTime.now()))
                        .status(Status.AKTIF).build())
        );

        service.updateConsumer(request, 1L);
        ArgumentCaptor<Consumer> consumerArgumentCaptor = ArgumentCaptor.forClass(Consumer.class);
        verify(repository).findById(1L);
        verify(repository).save(consumerArgumentCaptor.capture());
    }

    @Test
    void deleteConsumer() {
        service.deleteConsumer(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void getDatatableConsumer(){
        DatatableRequest request = DatatableRequest.builder()
                .draw(String.valueOf(1))
                .start(0)
                .length(10)
                .build();
        List<Consumer> list = List.of(Consumer.builder()
                .name("Budi")
                .address("Jl. Sejahtera")
                .city("Bogor")
                .province("Jawa Barat")
                .registration_date(String.valueOf(LocalDateTime.now()))
                .status(Status.AKTIF).build()
        );
        given(repository.findForPage(any(),any())).willReturn(
                list
        );

        service.getDatatableConsumer(request);
        verify(repository).findForPage(null, PageRequest.of(request.start() * request.length(), request.length()));
    }
}