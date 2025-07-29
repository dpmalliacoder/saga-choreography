package com.debi.order.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PurchaseOrderDto {

    private UUID orderId;
    private Integer productId;
    private Integer price;
    private Integer userId;

}