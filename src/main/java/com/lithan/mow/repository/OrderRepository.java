package com.lithan.mow.repository;

import com.lithan.mow.constraint.EStatus;
import com.lithan.mow.entity.Customer;
import com.lithan.mow.entity.Order;
import com.lithan.mow.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(EStatus eStatus);

    List<Order> findByOrderedBy(Customer member);

    List<Order> findByPreparedBy(Partner partner);

    List<Order> findByDeliveredBy(Customer driver);

    List<Order> findByStatusIsNotAndOrderedBy(EStatus status, Customer member);

    List<Order> findByStatusAndOrderedBy(EStatus status, Customer member);

    List<Order> findByStatusAndPreparedBy(EStatus status, Partner partner);

    List<Order> findByStatusAndDeliveredBy(EStatus status, Customer driver);
}
