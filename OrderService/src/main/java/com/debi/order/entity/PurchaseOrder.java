package com.debi.order.entity;

import lombok.Data;
import lombok.ToString;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import java.util.UUID;
import com.debi.order.dto.events.order.OrderStatus;
import com.debi.order.dto.events.payment.PaymentStatus;   
import com.debi.order.dto.events.inventory.InventoryStatus;


@Data
@Entity
@ToString
public class PurchaseOrder {

    @Id
    private UUID id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private InventoryStatus inventoryStatus;

    @Version
    private int version;

}
