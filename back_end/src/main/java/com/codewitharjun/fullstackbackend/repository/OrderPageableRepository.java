package com.codewitharjun.fullstackbackend.repository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.codewitharjun.fullstackbackend.model.Order1;

public interface OrderPageableRepository extends PagingAndSortingRepository<Order1, Long>{

	Optional<List<Order1>> findByUserId(Long id, Pageable pageable);
}
