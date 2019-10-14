import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ledgefarm.exceptions.LedgefarmException;
import ledgefarm.service.TokenService;

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
	public TokenService tokenService;
	public OperatorService operatorService;
	public BatchService batchService;
	//final static Logger LOG = LoggerFactory.getLogger(GetWalletPageSteps.class);

@Test
    public void CreateWallet() throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException
    {
	// try {
	// 	accessKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	// 	WalletService walletService = new WalletService(accessKey);
    // 	Wallet response  = walletService.create("test");
    // 	System.out.println("wallet : " + response.getAccessKey());
    	
    	
	// } catch(LedgefarmException ex) {
	// 	System.out.println(ex.getMessage());
	// 	System.out.println(ex.getCode());
	// }
    	
		//System.out.println(token.get(0).getBalance());
    }
}