package com.debi.order.dto.events.inventory;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
import com.debi.order.dto.InventoryDto;
import com.debi.order.dto.events.Event;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private InventoryDto inventory;
    private InventoryStatus status;
    
}
