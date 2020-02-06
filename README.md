## Basic Information
Java maven package can be used in operator application to communicate with Wakandi network. This package is available on Maven repository “[https://mvnrepository.com](https://mvnrepository.com)" with the name "com.ledgefarm.core.” This needs to be included as a reference in your application.

### Installation

-   Maven: In this we need to run following dependency need to be added in pom.xml file.
><dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.5</version>
</dependency>

*Compatibility of package is with Java version 7 and above versions

### Configuration

This package uses some configuration from the application configuration file “config.properties” This file should be available in the user directory of your application.

In the “config.properties” file, a section needs to be added with the following settings:  

```
    apiurl = http://host:port/api/v1/
    apikey = xxxxxxxxxxx
```

The settings are used to make the application interact with Ledgefarm core.

-   LedgefarmApiUrl: It is the hosted URL of the Ledgefarm Core API. This URL should end with a version of the application i.e.  http://host:port/api/[version]/. As of now, the current version is v1.

-   ApiKey: This is the unique key for each operator that is validating the identity of the operator on each request.
    
The complete section comprising the above information needs to be added in the file to use the maven jar file in java application.

## Services

Services that are available in this package:

-   Wallet Service
-   Token Service
-   Transaction Service
-   Operator Service

## Usage
### Wallet Service
Wallet service is used to perform all operations related to the wallet like creating wallet, blocking and unblocking a wallet, obtain wallet data etc.
#### Methods:
-   Create : This function is used to create a wallet. You need to pass a username of the wallet (Walletname) that needs to be created and some basic information about the user to available that wallet for other operator. In response, the packageit will return the wallet address and access Key for that wallet. This access Key will be used for further operations using this wallet.

```
		WalletService walletService = new WalletService(“xxxxxxxxxxxxxxxxxxxxxx”);
		WalletRequest walletRequest = new WalletRequest();
		walletRequest.setWallet("xxxxxxx");
		walletRequest.setName("xxxxx xxxxx");
		walletRequest.setEmail("abc@wallxxxxx");
		walletRequest.setCountryCode("+91");
		walletRequest.setPhone("xxxxxxxxxx");
		walletRequest.setIsPublic(true);
		Wallet walletResponse = walletService.create(walletRequest);
```

-   Get: This function is used to obtain the information of a particular wallet by using there walletname. Admin access key need to be used here for getting the wallet of a user.

```
        String walletName = “abc@wallxxxxx”;
        WalletService walletService = new WalletService(“xxxxxxxxxxxxxxxxxxxxxx”);
        List<Wallet> walletResponse = walletService.get(walletName);
```

-   GetAll: This function is used to get the list of all the wallets that are registered with the operator. In this limit (pagesize) and offset (starting index position) need to be passed to get the page wise records. Maximum value of limit is 1000 and minimum value is 1 and maximum value of offset can be any positive number and minimum is 1. Admin access key need to be used here for getting list of wallets. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.

```
		int limit =10;
		int offset = 1;
		WalletService walletService = new WalletService(“xxxxxxxxxxxxxxxxxxxxxx”);
		List<Wallet> walletListResponse = walletService.getAll(limit, offset);
```

-   Search: This function is used to search a wallet. User can search wallet using wallet address, phone number and email. Admin access key is required for searching the wallet in directory service.

```
        String walletName = “abc@wallxxxxx”;
        WalletService walletService = new WalletService(“xxxxxxxxxxxxxxxxxxxxxx”);
        List<Wallet> walletResponse = walletService.search(walletName, null);
        
        String email = “abc@test.com”;
        List<Wallet> walletResponse = walletService.search(email, null);
        
        String phone = “98xxxxxxxx”;
        String countryCode = “+91”;
        List<Wallet> walletResponse = walletService.search(phone, countryCode);
```

-   Update: This function is used to update a wallet. The Operator needs to pass a wallet address to update the wallet and information that need to be updated. Admin access key is required for unblocking the wallet.

```
		WalletService walletService = new WalletService(“xxxxxxxxxxxxxxxxxxxxxx”);
		WalletRequest walletRequest = new WalletRequest();
		walletRequest.setWallet("xxxxxxx");
		walletRequest.setName("xxxxx xxxxx");
		walletRequest.setEmail("abc@wallxxxxx");
		walletRequest.setCountryCode("+91");
		walletRequest.setPhone("xxxxxxxxxx");
		walletRequest.setIsPublic(true);
		Wallet unblockResponse = walletService.update(walletRequest)
```

### Token Service
Asset service is used to perform all the operations related to assets. Which includes issue, transfer, withdraw and request asset etc.
#### Methods:
-   Get token: This function is used to get the token which is flowing in the current network. It will provide the total supply(the total token circulation in the particular network.

```
		string token = "USD"
		TokenService tokenService = new TokenService("xxxxxxxxxxxxxxxxxxxxxx");
		List<Token> tokenResponse = await tokenService.get(token);
```

-   Issue:  This function is used to issue tokens to the wallet. The Operator needs to pass the wallet address, token name, amount, transactionId and fee. Here fee parameter is totally depends on operator, operator can choose to apply fee on this transaction. Admin access key is required to issue tokens to a wallet. TransactionId is optional and it  is reference id of token received by operator from global network, this needs to be provided in case of global transaction.

```
		string walletName = "abc@wallxxxxx";
		string token= "USD";
		double amount=100;
		List<Fee> fee = new List<Fee>();
		TokenService tokenService = new TokenService("xxxxxxxxxxxxxxxxxxxxxx");
		Transaction transactionResponse = await tokenService.issue(walletName, token, amount, fee);
		
		String transactionId = "xxxxxxxxxxxxxxxxxxxxxx";
		Transaction transactionResponse = await tokenService.issue(walletName, token, transactionId, amount, fee);
```
-   Transfer : This function is used to transfer the asset from one user to another user. In this function wallet address of the user to whom asset need to be transferred, asset name, amount and list of all applicable fees need to be passed for the transferring asset to the user. Here user’s access key (sender) need to be passed to transfer asset from wallet to wallet.

```
		String walletName = “abc@wallxxxxx”;
		String asset= “USD”;
		Double amount=100;
		List<Fee> fee = new ArrayList<Fee>();
		TokenService tokenService = new TokenService(“xxxxxxxxxxxxxxxxxxxxxx”);
		Transaction TransactionResponse = tokenService.transfer(walletName, asset, amount, fee);
```

-   Withdraw: This function is used to withdraw assets from the user. In this function wallet address of the user from asset need to be withdrawn, asset name, amount and list of all applicable fees need to be passed for the withdrawing assets from the user. Admin access key need to be used to withdrawing assets from user's wallet.

```
		String walletName = “abc@wallxxxxx”;
		String asset= “USD”;
		Double amount=100;
		List<Fee> fee = new ArrayList<Fee>();
		TokenService tokenService = new TokenService(“xxxxxxxxxxxxxxxxxxxxxx”);
		Transaction TransactionResponse = tokenService.withdraw(walletName, asset, amount, fee);

```

-   Request: This function is used to request assets from other user. In this function wallet address of the user from asset need to be requested, asset name and amount need to be passed for the requesting assets. User’s access key (request sender) need to be used here for sending requests to other users.

```
		String fromWalletName= “abc@wallxxxxx”;
		String asset= “USD”;
		Double walletName =100;
		TokenService tokenService = new TokenService(“xxxxxxxxxxxxxxxxxxxxxx”);
		Transaction TransactionResponse = assetService.request(fromWalletName, asset, amount);
```

-   Batch Transfer : This function is used to transfer tokens from one wallet to multiple wallets. The Operator needs to pass wallet address to which the token needs to be transferred, token name, amount, and list of all applicable fees for transferring tokens to the wallet. Here, wallet’s access token (sender) is required to transfer the tokens from wallet.

```
			TokenService service =  new TokenService(“xxxxxxxxxxxxxxxxxxxxxx”);
			List<Fee> fee = new ArrayList<Fee>();
			Fee _fee = new Fee();
			_fee.setToWallet("abc@wallxxxxx");
			_fee.setAmount(1.0);
			_fee.setMemo("Multi transfer fee memo");
			fee.add(_fee);
			
			List<BatchTransferRequest> batch = new ArrayList<BatchTransferRequest>();
			BatchTransferRequest _batch = new BatchTransferRequest();
			_batch.setToWallet("abc@wallxxxxx");
			_batch.setAmount(5);
			_batch.setMemo("Account clear");
			batch.add(_batch);
			BatchTransferRequest _batch1 = new BatchTransferRequest();
			_batch1.setToWallet("abc@wallxxxxx");
			_batch1.setAmount(5);
			_batch1.setMemo("Account clear");
			batch.add(_batch);
			Transaction transaction = service.batchTransfer("USD", 10, "multipal transfer", fee, batch);
```

### Transaction Service
Transaction service is used to get the information of a specific transaction or list of transactions.
#### Methods:
-   GetAll: This function is used to get the list of all transactions of the operator . In this limit (pagesize) and offset (starting index position) need to be passed  to get the page wise records. Admin access key need to be used here for getting list of transactions. Maximum value of limit is 1000 and minimum value is 1 and maximum value of offset can be any positive number and minimum is 1. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.

```
        int limit = 10;
        int offset = 1;
        TransactionService transactionService = new TransactionService(“xxxxxxxxxxxxxxxxxxxxxx”);
        List<Transaction> transactionListResponse = transactionService.getAll(limit, offset);
```

-   Get: This function is used to get the list of transaction by TransactionId. Admin access key need to be used here for getting list of transactions. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.

```
        string transactionId = 12sddadxxxxxxxx;
        TransactionService transactionService = new TransactionService("xxxxxxxxxxxxxxxxxxxxxx");
        List<Transaction> transactionListResponse = await transactionService.Get(transactionId);
```

-   GetAllByToken: This function is used to get the list of all transactions of the operator by token . In this limit (pagesize) and offset (starting index position) need to be passed to get the page wise records. Maximum value of limit is 1000 and minimum value is 1 and maximum value of offset can be any positive number and minimum is 1. Admin access key need to be used here for getting list of transactions. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.

```
        int limit =10;
        int offset = 1;
        string token = "USD";
        TransactionService transactionService = new TransactionService("xxxxxxxxxxxxxxxxxxxxxx");
        List<Transaction> transactionListResponse = await transactionService.GetAllByToken(token,limit, offset);
```

-   GetAllByWallet: This function is used to get the list of all transactions of the operatorby wallet . In this limit (pagesize) and offset (starting index position) need to be passed  to get the page wise records.  Maximum value of limit is 1000 and minimum value is 1 and maximum value of offset can be any positive number and minimum is 1. Admin access key need to be used here for getting list of transactions. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.

```
        int limit =10;
        int offset = 1;
        string wallet = "abc@wallxxxx";
        TransactionService transactionService = new TransactionService("xxxxxxxxxxxxxxxxxxxxxx");
        List<Transaction> transactionListResponse = await transactionService.GetAllByWallet(wallet, limit, offset);
```

### Operator Service
Operator service is used to get the information about token issued, token owned and operator balance over network.
#### Methods:
-   Get Token Balance On Global Network: This function is used to get information about total balance over the global network.

```
        OperatorService operatorService = new OperatorService("xxxxxxxxxxxxxxxxxxxxxx");
        List<Token> operatorResponse = await operatorService.Get();
```

-   Get Issued Token: This function is used to check how many tokens operator issued to other operators on global network.

```
        OperatorService operatorService = new OperatorService("xxxxxxxxxxxxxxxxxxxxxx");
        List<GlobalToken> tokenIssuedListResponse = operatorService.getTokenIssued();
```

-   Get Owned Token: This function is used to check how many tokens operator issued to other operators on global network.

```
        OperatorService operatorService = new OperatorService("xxxxxxxxxxxxxxxxxxxxxx");
        List<GlobalToken> tokenIssuedListResponse = operatorService.getTokenOwned();        