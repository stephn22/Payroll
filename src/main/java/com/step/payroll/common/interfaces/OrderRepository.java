package com.step.payroll.common.interfaces;

import com.step.payroll.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
