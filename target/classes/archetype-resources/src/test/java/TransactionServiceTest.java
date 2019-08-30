package com.ledgefarm.core;
import java.io.IOException;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
/**
 * Unit test for simple App.
 */
public class TransactionServiceTest 
{
    /**
     * Rigorous Test :-)
     */
   
    @Test
    public void GetAllTransaction() throws IOException,LedgefarmException
    {
    	TransactionService service = new TransactionService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
        List<Transaction> transactions =  service.getAll(10,1);
        assertTrue(transactions.size()>0);
    }
   
    
    
}
