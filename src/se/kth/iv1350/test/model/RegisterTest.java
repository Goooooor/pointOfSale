package se.kth.iv1350.test.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.model.Payment;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Register;
import se.kth.iv1350.pos.model.Sale;

public class RegisterTest {

    private Register register;

    private Sale sale;

    private Payment payment;

    private Receipt receipt;

    @BeforeEach
    public void prepareTest() {
        register = new Register();
        sale = new Sale();
        payment = new Payment(500, 50);
    }

    @AfterEach
    public void tearDown() {
        register = null;
        sale = null;
        payment = null;
    }

    @Test
    void testRegisterPayment() {
        receipt = register.registerPayment(payment, sale);
        Assertions.assertNotNull(receipt, "Receipt was not created correctly");
    }
}
