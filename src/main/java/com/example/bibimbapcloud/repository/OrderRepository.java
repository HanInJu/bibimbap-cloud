package com.example.bibimbapcloud.repository;

import com.example.bibimbapcloud.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
