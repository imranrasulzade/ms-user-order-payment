package com.example.paymentservice.mapper;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.entity.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PaymentMapper {
    public abstract PaymentDto toDto(PaymentEntity entity);
    public abstract PaymentEntity toEntity(PaymentDto dto);
}
