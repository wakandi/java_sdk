import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ledgefarm.exceptions.LedgefarmException;
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
		WalletService service =  new WalletService("d27d9e7d067bc16d16e3516559a26796dda34f259561dfa7f6067434341105c4");
		try {
			WalletRequest walletRequest;
//			System.out.println("========== Wallet Create ==========");
//			walletRequest = new WalletRequest();
//			walletRequest.setWallet("seno");
//			walletRequest.setName("Seno suko");
//			walletRequest.setEmail("senosuko@gmail.com");
//			walletRequest.setCountryCode("+91");
//			walletRequest.setPhone("9529737955");
//			walletRequest.setIsPublic(false);
//			Wallet wallet = service.create(walletRequest);
//			System.out.println("Wallet " + wallet.getAccessKey());
//			System.out.println("Wallet " + wallet.getWallet());
//			System.out.println("========== Wallet Create ==========");
//			System.out.println("\n");
			
			System.out.println("========== Wallet Update ==========");
			walletRequest = new WalletRequest();
			walletRequest.setBlocked(true);
			walletRequest.setWallet("seno@devop1.example.com");
			walletRequest.setName("Seno suko");
			walletRequest.setEmail("senosuko@gmail.com");
			walletRequest.setCountryCode("+91");
			walletRequest.setPhone("9529737955");
			wallet = service.update(walletRequest);
			System.out.println("Wallet blocked " + wallet.getBlocked());
			System.out.println("Wallet isPublic " + wallet.getIsPublic());
			System.out.println("========== Wallet Update ==========");
			System.out.println("\n");
			
			System.out.println("========== Wallet Update ==========");
			walletRequest = new WalletRequest();
			walletRequest.setBlocked(true);
			walletRequest.setWallet("seno@devop1.example.com");
			walletRequest.setName("Seno suko");
			walletRequest.setEmail("senosuko@gmail.com");
			walletRequest.setCountryCode("+91");
			walletRequest.setPhone("9529737955");
			wallet = service.update(walletRequest);
			System.out.println("Wallet blocked " + wallet.getBlocked());
			System.out.println("Wallet isPublic " + wallet.getIsPublic());
			System.out.println("========== Wallet Update ==========");
			System.out.println("\n");
			
			System.out.println("========== Wallet Update ==========");
			walletRequest = new WalletRequest();
			walletRequest.setBlocked(false);
			walletRequest.setWallet("seno@devop1.example.com");
			walletRequest.setName("Seno suko");
			walletRequest.setEmail("senosuko@gmail.com");
			walletRequest.setCountryCode("+91");
			walletRequest.setPhone("9529737955");
			wallet = service.update(walletRequest);
			System.out.println("Wallet blocked " + wallet.getBlocked());
			System.out.println("Wallet isPublic " + wallet.getIsPublic());
			System.out.println("========== Wallet Update ==========");
			System.out.println("\n");
			
			System.out.println("========== Wallet Update ==========");
			walletRequest = new WalletRequest();
			walletRequest.setBlocked(false);
			walletRequest.setWallet("seno@devop1.example.com");
			walletRequest.setName("Seno suko");
			walletRequest.setEmail("senosuko@gmail.com");
			walletRequest.setCountryCode("+91");
			walletRequest.setPhone("9529737955");
			wallet = service.update(walletRequest);
			System.out.println("Wallet blocked " + wallet.getBlocked());
			System.out.println("Wallet isPublic " + wallet.getIsPublic());
			System.out.println("========== Wallet Update ==========");
			System.out.println("\n");
			
			System.out.println("========== Directory Search ==========");
			List<Wallet> wallets = service.search("shen@devop1.example.com", null);
			for (int i = 0; i < wallets.size(); i++) {
				System.out.println("Name                  " + wallets.get(i).getName());
				System.out.println("Wallet                " + wallets.get(i).getWallet());
				System.out.println("operator country      " + wallets.get(i).getOperator().getCountry());
				System.out.println("operator domain       " + wallets.get(i).getOperator().getDomainName());
				System.out.println("operator organization " + wallets.get(i).getOperator().getDomainName());
			}
			System.out.println("========== Directory Search ==========");
			System.out.println("\n");
		}
		catch(LedgefarmException ex)
		{
			System.out.println("ex " + ex.getMessage());
		}
    }
	
//	@Test
//	public void Issue() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, LedgefarmException {
//		try {
//			TokenService tokenService = new TokenService("d27d9e7d067bc16d16e3516559a26796dda34f259561dfa7f6067434341105c4");
//			List<Fee> fee = new ArrayList<Fee>();
//			Fee _fee = new Fee();
//			_fee.setToWallet("shenaa@devop1.example.com");
//			_fee.setAmount(1.0);
//			_fee.setMemo("test memo");
//			fee.add(_fee);
//			
//			Transaction response = tokenService.issue("shenaaaa@devop1.example.com", "USD", 100.0, fee);
//			System.out.println("token : " + response.getMessage());
//			
//		}
//		catch(LedgefarmException ex)
//		{
//			System.out.println("ex"+ex.getMessage());
//		}
//		
//	}
//	
//	@Test
//    public void MultiTransfer() throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException
//    {
//		TokenService service =  new TokenService("cd1da99fd187db18822f0fc0e56f4d053bf658c92aa7b9e564f249f9daa35faf");
//		try {
//			System.out.println("========== Multipal Transfer ==========");
//			
//			List<Fee> fee = new ArrayList<Fee>();
//			Fee _fee = new Fee();
//			_fee.setToWallet("plunket@devop1.example.com");
//			_fee.setAmount(1.0);
//			_fee.setMemo("Multi transfer fee memo");
//			fee.add(_fee);
//			
//			List<BatchTransferRequest> batch = new ArrayList<BatchTransferRequest>();
//			BatchTransferRequest _batch = new BatchTransferRequest();
//			_batch.setToWallet("plunketrio@devop1.example.com");
//			_batch.setAmount(5);
//			_batch.setMemo("Account clear");
//			batch.add(_batch);
//			BatchTransferRequest _batch1 = new BatchTransferRequest();
//			_batch1.setToWallet("shena@devop1.example.com");
//			_batch1.setAmount(5);
//			_batch1.setMemo("Account clear");
//			batch.add(_batch);
//			Transaction transaction = service.batchTransfer("USD", 10, "multipal transfer", fee, batch);
//			System.out.println("TransactionId " + transaction.getTransactionId());
//			System.out.println("Message " + transaction.getMessage());
//			System.out.println("========== Multipal Transfer ==========");
//			System.out.println("\n");
//		}
//		catch(LedgefarmException ex)
//		{
//			System.out.println("ex " + ex.getMessage());
//		}
//    }
	
}