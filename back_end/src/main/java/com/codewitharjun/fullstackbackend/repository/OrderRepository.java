package com.codewitharjun.fullstackbackend.repository;

import com.codewitharjun.fullstackbackend.model.Order1;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order1,Long> {

	Optional<Order1> findFirstByUserId(Long userId);
	Optional <List<Order1>> findByUserId(Long userId);
}
