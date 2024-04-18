package irish.bla.ssm.config;

import irish.bla.ssm.domain.PaymentEvent;
import irish.bla.ssm.domain.PaymentState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StateMachineConfigTest {
    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Test
    void testStateMachine() {
        StateMachine<PaymentState,PaymentEvent> machine = factory.getStateMachine();
        machine.start();
        System.out.println(machine.getState().toString());

        machine.sendEvent(PaymentEvent.PRE_AUTHORIZE);
        System.out.println(machine.getState().toString());
        machine.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);
        System.out.println(machine.getState().toString());
    }

}