package com.codewitharjun.fullstackbackend.repository;

import com.codewitharjun.fullstackbackend.model.Order1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order1, Long> {
    Optional<Order1> findFirstByUserId(Long userId);

    Optional<List<Order1>> findByUserId(Long userId);

	/*@Query("SELECT o FROM Order1 o JOIN FETCH o.userId u WHERE u = :user")
	Optional<List<Order1>> findAllWithUserEmail(@Param("user") User user);*/

    //équivalent à : ( à condition de passer Long dans parametre de la fn du controller)
    //@Query("SELECT o FROM Order1 o JOIN FETCH o.userId u WHERE u.id = :userId")
    //Optional <List<Order1>> findAllWithUserEmail(Long userId);
	
	/*@Query("SELECT o.id, o.price, o.product, u.email FROM Order1 o JOIN o.userId u WHERE u.id = :userId")
	Optional<List<Object[]>> findAllWithUserEmail(@Param("userId") Long userId);
	encore une autre façon mais avec Optional<List<Object[]> on perd nos clefs spécifiques à Order1*/
}
