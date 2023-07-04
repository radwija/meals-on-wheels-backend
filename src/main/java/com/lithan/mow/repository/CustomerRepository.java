package com.lithan.mow.repository;

import com.lithan.mow.entity.Customer;
import com.lithan.mow.constraint.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c.active FROM Customer c WHERE c.email = :email")
    Boolean findActiveByEmail(@Param("email") String email);

    Optional<Customer> findByEmail(String email);

    Boolean existsByEmail(String email);

    List<Customer> findByRole(ERole roles);

    List<Customer> findByRoleAndActive(ERole roles, boolean active);

    List<Customer> findByRoleIsNot(ERole roles);

    List<Customer> findByActive(boolean active);


}
