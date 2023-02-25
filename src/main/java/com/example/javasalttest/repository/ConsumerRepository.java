package com.example.javasalttest.repository;

import com.example.javasalttest.entity.Consumer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    @Query("SELECT e FROM Consumer e WHERE e.name LIKE %:name% OR e.city LIKE %:name% OR e.province LIKE %:name% OR e.address LIKE %:name% ORDER BY e.id")
    public List<Consumer> findForPage(@Param("name") String name, Pageable pageable);
}
