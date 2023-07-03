package com.lithan.mow.repository;

import com.lithan.mow.entity.Customer;
import com.lithan.mow.constraint.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    @Query("SELECT c.active FROM Customer c WHERE c.email = :email")
    Boolean findActiveByEmail(@Param("email") String email);

    int countAllBy();

    List<Customer> findAllByRoleNotOrderByCreatedAtDesc(ERole role);

    List<Customer> findAllByRole(ERole role);

    List<Customer> findAllByRoleAndActive(ERole role, boolean active);
}
