package se.kth.iv1350.test.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.SaleLog;

public class SaleLogTest {

    @Test
    void testUpdateSaleLog() {

        SaleLog saleLog = new SaleLog();

        Sale sale = new Sale();

        saleLog.updateSaleLog(sale);

        Assertions.assertTrue(saleLog.getFinishedSales().contains(sale), "The sale was not added to the sale log");
    }
}
