package com.debi.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.debi.order.dto.PurchaseOrderDto;
import com.debi.order.dto.events.order.OrderEvent;
import com.debi.order.entity.PurchaseOrder;
import com.debi.order.dto.events.order.OrderStatus;
import reactor.core.publisher.Sinks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderStatusPublisher {

    Logger log = LoggerFactory.getLogger(OrderStatusPublisher.class);

    @Autowired
    private Sinks.Many<OrderEvent> orderSupplier;

    public void raiseOrderEvent(final PurchaseOrder purchaseOrder, OrderStatus orderStatus){
        var dto = PurchaseOrderDto.of(
                purchaseOrder.getId(),
                purchaseOrder.getProductId(),
                purchaseOrder.getPrice(),
                purchaseOrder.getUserId()
        );
        var orderEvent = new OrderEvent(dto, orderStatus);
        var result = this.orderSupplier.tryEmitNext(orderEvent);
        log.info("Emitting OrderEvent: {} | result: {}", orderEvent, result);
    }

}
