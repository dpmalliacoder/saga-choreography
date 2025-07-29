package com.debi.order.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.debi.order.entity.PurchaseOrder;
import com.debi.order.repository.PurchaseOrderRepository;

import java.util.List;

@Service
public class OrderQueryService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public List<PurchaseOrder> getAll() {
        return this.purchaseOrderRepository.findAll();
    }

}
