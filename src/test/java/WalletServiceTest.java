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
import ledgefarm.models.Token;
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
	try {
		String filePath = "/home/vicky/Desktop/response.txt";
		System.out.println("check-1");
//		TokenService tokenService = new TokenService("7820cb1194892565d20b7e00e2eee2223fb4cc48a14114fa60a907e713946af3");    // new
//    	Wallet response  = walletService.create("vicky420");
//    	OperatorService operatorService = new OperatorService("7820cb1194892565d20b7e00e2eee2223fb4cc48a14114fa60a907e713946af3");
		BatchService batchService = new BatchService("7820cb1194892565d20b7e00e2eee2223fb4cc48a14114fa60a907e713946af3");
		
//    	List<Token> token = tokenService.get("USD");
//    	String responseStr = EntityUtils.toString();
//		List<Token> ownedToken = operatorService.getTokenOwned();
//		List<Token> issuedToken = operatorService.getTokenIssued();
		List<Batch> batches = new ArrayList<Batch>();
		Batch batch = new Batch();
		batch.setReferenceId("test");
		batch.setBatchId("2ca487165d08fb72abf0bd9230b5f1f695440996d41cefb92fe23bae613a6b39$0");
		batches.add(batch);
		Transaction transaction = batchService.update(batches);
		System.out.println("batch update : " + transaction.getMessage());
//		InputStream streamData = batchService.create();
//		System.out.println("Operator Name : " + ownedToken.get(0).getOperatorName() + " Token : " + ownedToken.get(0).getName() + " Amount : " + ownedToken.get(0).getAmount());
//		System.out.println("Operator Name : " + issuedToken.get(0).getOperatorName() + " Token : " + issuedToken.get(0).getName() + " Amount : " + issuedToken.get(0).getAmount());

		
//        try (FileOutputStream outputStream = new FileOutputStream(new File(filePath))) {
//
//            int read;
//            byte[] bytes = new byte[streamData.available()];
//
//            while ((read = streamData.read(bytes)) != -1) {
//                outputStream.write(bytes, 0, read);
//            }
//
//			// commons-io
//            //IOUtils.copy(inputStream, outputStream);
//
//        }
	} catch(LedgefarmException ex) {
		System.out.println(ex.getMessage());
		System.out.println(ex.getCode());
	}
    	
		//System.out.println(token.get(0).getBalance());
    }
}