package ledgefarm.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ledgefarm.exceptions.LedgefarmException;
import ledgefarm.models.Batch;
import ledgefarm.models.Transaction;

public class BatchService extends LedgefarmService {

	public BatchService(String accessKey) {
		super(accessKey);
	}
	public Transaction update(List<Batch> batches) throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(batches));
		JsonObject jsonObject = jsonTree.getAsJsonObject();
		JsonObject responseObject = super.sendHttpPut(jsonObject, "batch");
		return this.mapToTransactionObject(responseObject);
	}
	
	public InputStream create() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, LedgefarmException {
		InputStream dataStream = super.sendFileHttpGet(null, "batch");
		return dataStream; 
	}
	
	public Transaction settle(String filePath) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, LedgefarmException {
		File file = new File(filePath);
		HttpEntity postData = MultipartEntityBuilder.create().addBinaryBody("uploadAchFile",file).build();
		JsonObject responseObject = super.sendFileHttpPost(postData, "batch");
		return this.mapToTransactionObject(responseObject);
	}
	
//	private File convertToFile(JsonObject jsonObject) throws LedgefarmException, IOException{
//		boolean success = jsonObject.get("success").getAsBoolean();
//		if (success) {
//			String fileString = jsonObject.get("data").getAsString();
//			InputStream inputStream = new ByteArrayInputStream( fileString.getBytes() );
//			 final File tempFile = File.createTempFile("Auto", "json");
//		        tempFile.deleteOnExit();
//		        try (FileOutputStream out = new FileOutputStream(tempFile)) {
//		            IOUtils.copy(inputStream, out);
//		        }
//		        return tempFile;
//			//return fileString;		
//		}
//		JsonObject object = jsonObject.getAsJsonObject("error");
//		throw new LedgefarmException(object.get("message").getAsString(), object.get("error").getAsString());
//	}
	
	private Transaction mapToTransactionObject(JsonObject jsonObject) throws LedgefarmException {
		boolean success = jsonObject.get("success").getAsBoolean();
		if (success) {
			JsonElement obj = gson.fromJson(jsonObject.getAsJsonObject("data").toString(), JsonElement.class);
			return gson.fromJson(obj, Transaction.class);
		}
		JsonObject object = jsonObject.getAsJsonObject("error");
		throw new LedgefarmException(object.get("message").getAsString(), object.get("error").getAsString());
	}
	
}
