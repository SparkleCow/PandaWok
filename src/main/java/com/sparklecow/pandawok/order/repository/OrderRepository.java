package com.sparklecow.pandawok.order.repository;

import com.sparklecow.pandawok.order.entity.Order;
import com.sparklecow.pandawok.order.model.Status;
import com.sparklecow.pandawok.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByClient(User client, Pageable pageable);

    Page<Order> findByClientName(String username, Pageable pageable);

    Page<Order> findByDelivery(User delivery, Pageable pageable);

    Page<Order> findByStatus(Status status, Pageable pageable);
}
