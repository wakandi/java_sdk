## Basic Information
Java maven package can be used in operator application to communicate with Wakandi network. This package is available on Maven repository "[https://mvnrepository.com](https://mvnrepository.com)" with the name "com.ledgefarm.core." This needs to be included as a reference in your application.

 ### Installation

-   Maven: In this we need to run following dependency need to be added in pom.xml file.
><dependency>
    <groupId>com.ledgefarm.core</groupId>
    <artifactId>ledgefarm</artifactId>
    <version>1.0.0</version>
</dependency>

*Compatibility of package is with Java version 7 and above versions

 ### Configuration

This package uses some configuration from the application configuration file "config.properties" This file should be available in the user directory of your application.

In the "config.properties" file, a section needs to be added with the following settings:  
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
-   Transaction Service
-   Token Service

## Usage
### Wallet Service
Wallet service is used to perform all operations related to the wallet like creating wallet, blocking and unblocking a wallet, obtain wallet data etc.
#### Methods:
-   Create : This function is used to create a wallet. You need to pass a walletname of the wallet that needs to be created. In response, the packageit will return the wallet address and accessKey for that wallet. This accessKey will be used for further operations using this wallet.
```
        String walletName = "abc@wallxxxxx";
        WalletService walletService = new WalletService("xxxxxxxxxxxxxxxxxxxxxx");
        var walletResponse = walletService.create(walletName);
```
-   Get: This function is used to obtain the information of a particular wallet by using there walletname. Admin accessKey need to be used here for getting information of a particular wallet.
```
        String walletName = "abc@wallxxxxx";
        WalletService walletService = new WalletService("xxxxxxxxxxxxxxxxxxxxxx");
        var walletResponse = walletService.get(walletName);
```
-   GetAll: This function is used to get the list of all the wallets that are registered with the operator. In this limit (pagesize) and offset (starting index position) need to be passed to get the page wise records. Admin accessKey need to be used here for getting list of wallets. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.
```
        int limit =10;
        int offset = 1;
        WalletService walletService = new WalletService("xxxxxxxxxxxxxxxxxxxxxx");
        var walletListResponse = walletService.getAll(limit, offset);
```

-   Block: This function is used to block the wallet. Wallet address need to pass for blocking a wallet. Admin accessKey need to be used here for blocking wallet.
```
        String walletName = "abc@wallxxxxx";
        WalletService walletService = new WalletService("xxxxxxxxxxxxxxxxxxxxxx");
        var blockResponse = walletService.block(walletName);
```
-   Unblock: This function is used to unblock the wallet. Wallet address need to pass for unblocking a wallet. Admin accessKey need to be used here for unblocking wallet.
```
        String walletName = "abc@wallxxxxx";
        WalletService walletService = new WalletService("xxxxxxxxxxxxxxxxxxxxxx");
        var unblockResponse = walletService.unblock(walletName)
```
### Token Service
Token service is used to perform all the operations related to tokens. Which includes issue, transfer, withdraw and request token etc.
#### Methods:
-   Issue: This function is used to Issue the token to the wallet. In this function wallet address of the wallet to whom token need to be issued, token name, amount and list of all applicable fees need to be passed for the issuing token to the wallet. Admin accessKey need to be used here for issuing token to a wallet.

```
        String walletName = "abc@wallxxxxx";
        String token= "USD";
        Double amount=100;
        List<Fee> fee = new ArrayList<Fee>();
        TokenService tokenService = new TokenService("xxxxxxxxxxxxxxxxxxxxxx");
        var transactionResponse = tokenService.issue(walletName, token, amount, fee);
```
-   Transfer : This function is used to transfer the token from one wallet to another wallet. In this function wallet address of the wallet to whom token need to be transferred, token name, amount and list of all applicable fees need to be passed for the transferring token to the wallet. Here wallet's accessKey (sender) need to be passed to transfer token from wallet to wallet.
```
        String walletName = "abc@wallxxxxx";
        String token= "USD";
        Double amount=100;
        List<Fee> fee = new ArrayList<Fee>();
        TokenService tokenService = new TokenService("xxxxxxxxxxxxxxxxxxxxxx");
        var TransactionResponse = tokenService.transfer(walletName, token, amount, fee);
```
-   Withdraw: This function is used to withdraw tokens from a wallet. In this function wallet address of the wallet from token need to be withdrawn, token name, amount and list of all applicable fees need to be passed for the withdrawing tokens from the wallet. Admin accessKey need to be used to withdrawing tokens from wallet.

```
        String walletName = "abc@wallxxxxx";
        String token= "USD";
        Double amount=100;
        List<Fee> fee = new ArrayList<Fee>();
        TokenService tokenService = new TokenService("xxxxxxxxxxxxxxxxxxxxxx");
        var TransactionResponse = tokenService.withdraw(walletName, token, amount, fee);

```

-   Request: This function is used to request tokens from other wallet. In this function wallet address of the wallet from token need to be requested, token name and amount need to be passed for the requesting tokens. wallet's accessKey (request sender) need to be used here for sending requests to other wallets.

```
        String fromWalletName= "abc@wallxxxxx";
        String token= "USD";
        Double walletName =100;
        TokenService tokenService = new TokenService("xxxxxxxxxxxxxxxxxxxxxx");
        var TransactionResponse = tokenService.request(fromWalletName, token, amount);
```
-   Accept: This function is used to accept token request from other user. In this function tokenRequestId and the fee charged by the operator need to pass for the approving token request. User’s access Key (requestee) need to be used here for approving request.

```
        String tokenRequestId= "ab34434xxxx";
        List<Fee> fee = new ArrayList<Fee>();
        TokenService tokenService = new TokenService("xxxxxxxxxxxxxxxxxxxxxx");
        var TransactionResponse = tokenService.accept(tokenRequestId, fee);
```

-   Reject: This function is used to reject token request from other user. In this function tokenRequestId need to pass for the approving token request. User’s access Key (requestee) need to be used here for rejecting request.

```
        String tokenRequestId= "ab34434xxxx";
        TokenService tokenService = new TokenService("xxxxxxxxxxxxxxxxxxxxxx");
        var TransactionResponse = tokenService.reject(tokenRequestId);
```

### Transaction Service
Transaction service is used to get the information of a specific transaction or list of transactions.
#### Methods:
-   GetAll: This function is used to get the list of all transactions of the operator . In this limit (pagesize) and offset (starting index position) need to be passed  to get the page wise records. Admin accessKeyneed to be used here for getting list of transactions. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.

```
        int limit =10;
        int offset = 1;
        TransactionService transactionService = new TransactionService("xxxxxxxxxxxxxxxxxxxxxx");
        var walletListResponse = transactionService.getAll(limit, offset);
```

-   Get: This function is used to get the list of transaction by TransactionId. Admin access token need to be used here for getting list of transactions. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.

```
	string transactionId = "12sddadxxxxxxxx";
        TransactionService transactionService = new TransactionService("xxxxxxxxxxxxxxxxxxxxxx");
        var walletListResponse = transactionService.get(transactionId);
```

-   GetByToken: This function is used to get the list of all transactions of the operator by token . In this limit (pagesize) and offset (starting index position) need to be passed to get the page wise records. Admin access token need to be used here for getting list of transactions. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.

```
        int limit =10;
        int offset = 1;
	string token = "USD";
        TransactionService transactionService = new TransactionService("xxxxxxxxxxxxxxxxxxxxxx");
        var walletListResponse = transactionService.getByToken(token, limit, offset);
```

-   GetByWallet: This function is used to get the list of all transactions of the operatorby wallet . In this limit (pagesize) and offset (starting index position) need to be passed  to get the page wise records. Admin access token need to be used here for getting list of transactions. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.

```
        int limit =10;
        int offset = 1;
	string wallet = "abc@wallxxxx";
        TransactionService transactionService = new TransactionService("xxxxxxxxxxxxxxxxxxxxxx");
        var walletListResponse = transactionService.getByWallet(wallet, limit, offset);
```
