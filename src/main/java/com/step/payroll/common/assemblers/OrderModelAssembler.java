package com.step.payroll.common.assemblers;

import com.step.payroll.controllers.OrderController;
import com.step.payroll.entities.Order;
import com.step.payroll.enums.Status;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    @Override
    public EntityModel<Order> toModel(Order order) {
        var orderModel = EntityModel.of(order,
                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));

        if (order.getStatus() == Status.IN_PROGRESS) {
            orderModel.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));

            orderModel.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
        }

        return orderModel;
    }
}
