package com.ledgefarm.core;
import java.io.IOException;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
/**
 * Unit test for simple App.
 */
public class TokenServiceTest 
{
    /**
     * Rigorous Test :-)
     */

    @Test(expected = LedgefarmException.class)
    public void issueToken() throws IOException,LedgefarmException
    {
        List<Fee> arList = new ArrayList<Fee>();
        Fee fee = new Fee();
        fee.setTo("k2@wakandi-network");
        fee.setName("testfee");
        fee.setAmount(new Double(1));
        arList.add(fee);
        TokenService service = new TokenService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
	    Transaction transaction = service.issue("satishsomani@wakandi-network","ORA",new Double(100),arList);
        assertNotNull( transaction );
    }

    @Test(expected = LedgefarmException.class)
    public void transferToken() throws IOException,LedgefarmException
    {
        List<Fee> arList = new ArrayList<Fee>();
        Fee fee = new Fee();
        fee.setTo("k2@wakandi-network");
        fee.setName("testfee");
        fee.setAmount(new Double(1));
        arList.add(fee);
    	TokenService service = new TokenService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
	    Transaction transaction = service.transfer("k2@wakandi-network","ORA",new Double(5),arList);
        assertNotNull( transaction );
    }
    @Test(expected = LedgefarmException.class)
    public void withdrawToken() throws IOException,LedgefarmException
    {
        List<Fee> arList = new ArrayList<Fee>();
        Fee fee = new Fee();
        fee.setTo("k2@wakandi-network");
        fee.setName("testfee");
        fee.setAmount(new Double(1));
        arList.add(fee);
    	TokenService service = new TokenService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
	    Transaction transaction = service.withdraw("satishsomani@wakandi-network","ORA",new Double(5),arList);
        assertNotNull( transaction );
    }

    @Test(expected = LedgefarmException.class)
    public void requestToken() throws IOException,LedgefarmException
    {
    	TokenService service = new TokenService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
	    Transaction transaction = service.request("satishsomani@wakandi-network","ORA",new Double(5));
        assertNotNull( transaction );
    }

    @Test
    public void getAssetByName() throws IOException,LedgefarmException
    {
    	TokenService service = new TokenService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
        List<Token> assets =  service.get("ORA");
        assertTrue(assets.size()>0);
    }

    @Test
    public void GetAsset() throws IOException,LedgefarmException
    {
    	TokenService service = new TokenService("c8f09cb114ef4574c5065360ec931a1ad334c7d877acdd897dfc1818c4ea0fd6");
        List<Token> assets =  service.get();
        assertTrue(assets.size()>0);
    }
}
