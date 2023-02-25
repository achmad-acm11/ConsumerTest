package com.example.javasalttest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "Consumers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consumer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nama", length = 255)
    private String name;
    @Column(name = "alamat", columnDefinition = "text")
    private String address;
    @Column(name = "kota", length = 100)
    private String city;
    @Column(name = "provinsi", length = 100)
    private String province;
    @Column(name = "tgl_registrasi", columnDefinition = "datetime")
    private String registration_date;
    @Enumerated(EnumType.STRING)
    private Status status;
}
