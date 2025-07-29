package com.debi.order.dto.events.payment;

import lombok.Data;

import java.util.Date;
import java.util.UUID;
import com.debi.order.dto.PaymentDto;
import com.debi.order.dto.events.Event;

@Data
public class PaymentEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private PaymentDto payment;
    private PaymentStatus paymentStatus;

    public PaymentEvent() {
    }

    public PaymentEvent(PaymentDto payment, PaymentStatus status) {
        this.payment = payment;
        this.paymentStatus = status;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}