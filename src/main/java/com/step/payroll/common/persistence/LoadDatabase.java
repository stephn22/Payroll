package com.step.payroll.common.persistence;

import com.step.payroll.common.interfaces.EmployeeRepository;
import com.step.payroll.common.interfaces.OrderRepository;
import com.step.payroll.entities.Employee;
import com.step.payroll.entities.Order;
import com.step.payroll.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        return args -> {
            log.info("Preloading " + employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar")));
            log.info("Preloading " + employeeRepository.save(new Employee("Frodo", "Baggins", "ring bearer")));

            employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));
        };
    }
}
