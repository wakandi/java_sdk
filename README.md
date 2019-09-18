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
-   Transaction Service
-   Asset Service

## Usage
### Wallet Service
Wallet service is used to perform all operations related to the wallet like creating wallet, blocking and unblocking a wallet, obtain wallet data etc.
#### Methods:
-   Create : This function is used to create a user wallet. You need to pass a username of the wallet (Walletname) that needs to be created. In response, the packageit will return the wallet address and access token for that wallet. This access token will be used for further operations using this wallet.
```
String walletName = “abc@wallxxxxx”;
WalletService walletService = new WalletService(“xxxxxxxxxxxxxxxxxxxxxx”);
var walletResponse = walletService.create(walletName);
```
-   Get: This function is used to obtain the information of a particular wallet by using there walletname. Admin access token need to be used here for getting the wallet of a user.
```
        String walletName = “abc@wallxxxxx”;
        WalletService walletService = new WalletService(“xxxxxxxxxxxxxxxxxxxxxx”);
        var walletResponse = walletService.get(walletName);
```
-   GetAll: This function is used to get the list of all the wallets that are registered with the operator. In this limit (pagesize) and offset (starting index position) need to be passed to get the page wise records. Admin access token need to be used here for getting list of wallets. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.
```
        int limit =10;
        int offset = 1;
        WalletService walletService = new WalletService(“xxxxxxxxxxxxxxxxxxxxxx”);
        var walletListResponse = walletService.getAll(limit, offset);
```

-   Block: This function is used to block the user wallet. User wallet address need to pass for blocking a wallet. Admin access token need to be used here for blocking user wallet.
```
          String walletName = “abc@wallxxxxx”;
          WalletService walletService = new WalletService(“xxxxxxxxxxxxxxxxxxxxxx”);
          var blockResponse = walletService.block(walletName);
```
-   Unblock: This function is used to unblock the user wallet. User wallet address need to pass for unblocking a wallet. Admin access token need to be used here for unblocking user wallet.
```
   String walletName = “abc@wallxxxxx”;
   WalletService walletService = new WalletService(“xxxxxxxxxxxxxxxxxxxxxx”);
   var unblockResponse = walletService.unblock(walletName)
```
### Asset Service
Asset service is used to perform all the operations related to assets. Which includes issue, transfer, withdraw and request asset etc.
#### Methods:
-   Issue: This function is used to Issue the asset to the user. In this function wallet address of the user to whom asset need to be issued, asset name, amount and list of all applicable fees need to be passed for the issuing token to the user. Admin access token need to be used here for issuing asset to a user.

```
String walletName = “abc@wallxxxxx”;
String asset= “USD”;
Double amount=100;
List<Fee> fee = new ArrayList<Fee>();
AssetService assetService = new AssetService(“xxxxxxxxxxxxxxxxxxxxxx”);
var transactionResponse = assetService.issue(walletName, asset, amount, fee);
```
-   Transfer : This function is used to transfer the asset from one user to another user. In this function wallet address of the user to whom asset need to be transferred, asset name, amount and list of all applicable fees need to be passed for the transferring asset to the user. Here user’s access token (sender) need to be passed to transfer asset from wallet to wallet.
```
String walletName = “abc@wallxxxxx”;
String asset= “USD”;
Double amount=100;
List<Fee> fee = new ArrayList<Fee>();
AssetService assetService = new AssetService(“xxxxxxxxxxxxxxxxxxxxxx”);
var TransactionResponse = assetService.transfer(walletName, asset, amount, fee);
```
-   Withdraw: This function is used to withdraw assets from the user. In this function wallet address of the user from asset need to be withdrawn, asset name, amount and list of all applicable fees need to be passed for the withdrawing assets from the user. Admin access token need to be used to withdrawing assets from user's wallet.

```
String walletName = “abc@wallxxxxx”;
String asset= “USD”;
Double amount=100;
List<Fee> fee = new ArrayList<Fee>();
AssetService assetService = new AssetService(“xxxxxxxxxxxxxxxxxxxxxx”);
var TransactionResponse = assetService.withdraw(walletName, asset, amount, fee);

```

-   Request: This function is used to request assets from other user. In this function wallet address of the user from asset need to be requested, asset name and amount need to be passed for the requesting assets. User’s access token (request sender) need to be used here for sending requests to other users.

```
String fromWalletName= “abc@wallxxxxx”;
String asset= “USD”;
Double walletName =100;
AssetService assetService = new AssetService(“xxxxxxxxxxxxxxxxxxxxxx”);
var TransactionResponse = assetService.request(fromWalletName, asset, amount);
```
### Transaction Service
Transaction service is used to get the information of a specific transaction or list of transactions.
#### Methods:
-   GetAll: This function is used to get the list of all transactions of the operator . In this limit (pagesize) and offset (starting index position) need to be passed  to get the page wise records. Admin access token need to be used here for getting list of transactions. For example if we have 30 records and page size is 10 then 3 calls need to make with 1,11,21 as offset and 10 as page size in each request.
```
        int limit =10;
        int offset = 1;
        TransactionService transactionService = new TransactionService(“xxxxxxxxxxxxxxxxxxxxxx”);
        var walletListResponse = transactionService.getAll(limit, offset);
```