package irish.bla.ssm.service;

import irish.bla.ssm.domain.Payment;
import irish.bla.ssm.domain.PaymentEvent;
import irish.bla.ssm.domain.PaymentState;
import irish.bla.ssm.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {
    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void before() {
        payment = Payment.builder()
                .amount(new BigDecimal("12.99"))
                .build();
    }

    @Test
    void preAuth() {
        Payment savedPayment = paymentService.newPayment(payment);
        StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());
        Payment retrievedPayment = paymentRepository.findById(savedPayment.getId()).orElseThrow();

        System.out.println("--------------");
        System.out.println(retrievedPayment);
        System.out.println("--------------");

    }
}