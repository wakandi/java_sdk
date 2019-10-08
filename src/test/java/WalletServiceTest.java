import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ledgefarm.exceptions.LedgefarmException;
import ledgefarm.models.*;
import ledgefarm.service.*;

public class WalletServiceTest {
	public String actualWalletName;
	public String walletName = "testakandi";
	public WalletService walletService;
	public Wallet wallet;
	public String accessKey;
	public String walletAddress;
	public List<Wallet> walletList;
	public String tokenName;
	public int dataSize;
	public int tokensSize;
	public double balance;
	public double netBalance;
	//final static Logger LOG = LoggerFactory.getLogger(GetWalletPageSteps.class);

@Test
    public void CreateWallet() throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException
    {
	try {
		String filePath = "E:\\response.json";
    	BatchService walletService = new BatchService("25476666bb967d1297be12a08079e878e3554d256e331534aa6f1ae8f9a08f7c");
    	JsonArray response  = walletService.create();
    	//String responseStr = EntityUtils.toString(response.getEntity());
    	System.out.println(response);
	}
	catch(LedgefarmException ex) {
		System.out.println(ex.getMessage());
		System.out.println(ex.getCode());
	}
    	
		//System.out.println(token.get(0).getBalance());
    }
}