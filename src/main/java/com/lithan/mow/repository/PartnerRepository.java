package com.lithan.mow.repository;


import com.lithan.mow.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {
    public Optional<Partner> findByEmail(String email);
}
