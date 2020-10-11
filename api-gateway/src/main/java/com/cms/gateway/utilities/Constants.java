package com.cms.gateway.utilities;

public class Constants {

    public static final String USERNAME_TAKEN = "Username already taken.";

    public static final String SUCCESS_REGISTER = "Successfully registered.";
    public static final String SUCCESS_LOGIN = "Successfully logged in.";

    public static final String ERROR_SOMETHING_WENT_WRONG = "Error. Something went wrong.";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    public static final String PRIVILEGE_READ = "PRIVILEGE_READ";
    public static final String PRIVILEGE_WRITE = "PRIVILEGE_WRITE";


    /**********************************/
    /*** General messages ************/
    /*********************************/

    //Vendor Messages
    public static final String VENDOR_MESSAGE_SUCCESSFUL = "Successful.";
    public static final String VENDOR_MESSAGE_FAILED = "Failed.";
    public static final String VENDOR_MESSAGE_ACCOUNT_CREATION_FAILED = "Account Creation Failed.";
    public static final String VENDOR_MESSAGE_UNAUTHORIZED = "Unauthorized";

    //Vendor Message Code
    public static final Integer VENDOR_MESSAGE_CODE_SUCCESSFUL = 200;
    public static final Integer VENDOR_MESSAGE_CODE_FAILED = 300;
    public static final Integer VENDOR_MESSAGE_CODE_EXISTING_WALLET_ACCOUNT = -1001;
    public static final Integer VENDOR_MESSAGE_CODE_INVALID_USER_ACCOUNT = -1002;

    //Vendor Message Description
    public static final String VENDOR_MESSAGE_DESCRIPTION_SUCCESSFUL = "Successful.";
    public static final String VENDOR_MESSAGE_DESCRIPTION_FAILED = "Failed.";
    public static final String VENDOR_MESSAGE_DESCRIPTION_EXISTING_WALLET_ACCOUNT = "Existing Wallet Account";
    public static final String INVALID_USER_ACCOUNT = "Invalid User Account.";


    /*********************************/
    /*** Request wrapper messages ***/
    /********************************/

    public static final String REQUIRED_FIRSTNAME = "Field firstName required.";
    public static final String REQUIRED_FIRSTNAME_NOT_EMPTY = "Field firstName should not be empty.";

    public static final String REQUIRED_LASTNAME = "Field lastName required.";
    public static final String REQUIRED_LASTNAME_NOT_EMPTY = "Field lastName should not be empty.";

    public static final String REQUIRED_USERNAME = "Field username required.";
    public static final String REQUIRED_USERNAME_NOT_EMPTY = "Field username should not be empty.";

    public static final String REQUIRED_WALLET_ID = "Field walletId required.";

    public static final String REQUIRED_EMAIL = "Field emailAddress required.";
    public static final String REQUIRED_EMAIL_NOT_EMPTY = "Field emailAddress should not be empty.";
    public static final String REQUIRED_EMAIL_INVALID = "Field emailAddress is invalid.";

    public static final String REQUIRED_MERCHANT_WALLET_ID = "Field merchantWalletId required.";
    public static final String REQUIRED_MERCHANT_WALLET_ID_NOT_EMPTY = "Field merchantWalletId should not be empty.";

    public static final String REQUIRED_PASSWORD = "Field password required.";
    public static final String REQUIRED_PASSWORD_NOT_EMPTY = "Field password should not be empty.";


    /*********************************/
    /*** ALGORITHM *******************/
    /********************************/
    public static final String	ALGORITHM_SHA2_512 = "SHA-512";

}
