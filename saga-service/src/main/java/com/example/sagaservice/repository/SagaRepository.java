package com.example.sagaservice.repository;

import com.example.sagaservice.entity.SagaRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SagaRepository extends JpaRepository<SagaRequestEntity, Long> {
}
