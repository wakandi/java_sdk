package com.ledgefarm.core;
import java.io.IOException;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
/**
 * Unit test for simple App.
 */
public class WalletServiceTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void CreateWallet() throws IOException,LedgefarmException
    {
    	WalletService service = new WalletService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
        Wallet wallet =  service.create("satishsomani11");
        assertNotNull(wallet);
    }
    @Test(expected = LedgefarmException.class)
    public void CreateWalletException() throws IOException,LedgefarmException
    {
    	WalletService service = new WalletService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
        Wallet wallet =  service.create("satishsomani11");
        assertNotNull(wallet);
    }

    @Test
    public void GetWallet() throws IOException,LedgefarmException
    {
    	WalletService service = new WalletService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
        List<Wallet> wallet =  service.get("satishsomani11@wakandi-network");
        assertTrue(wallet.size()>0);
    }
    @Test
    public void GetAllWallet() throws IOException,LedgefarmException
    {
    	WalletService service = new WalletService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
        List<Wallet> wallet =  service.getAll(10,1);
        assertTrue(wallet.size()>0);
    }
    @Test(expected = LedgefarmException.class)
    public void BlockWallet() throws IOException,LedgefarmException
    {
    	WalletService service = new WalletService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
        Wallet wallet =  service.block("satishsomani@wakandi-network");
        assertNotNull(wallet);
    }

    @Test(expected = LedgefarmException.class)
    public void UnblockWallet() throws IOException,LedgefarmException
    {
    	WalletService service = new WalletService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
        Wallet wallet =  service.unblock("satishsomani@wakandi-network");
        assertNotNull(wallet);
    }
    
    
}
