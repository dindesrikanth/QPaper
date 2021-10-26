package com.example.questionpaper.Requests.Verification;

public class BankAccountSubmitRequest {
    private String bankAccountName,bankAccountNumber,bankAcctProofImg,bankBranchName,bankIfscCode,
            bankName,bankState;
    private String userId;

    public BankAccountSubmitRequest(String bankAccountName,String bankAccountNumber, String bankAcctProofImg,
                            String bankBranchName, String bankIfscCode,String bankName,
                                    String bankState,String userId) {
        this.bankAccountName = bankAccountName;
        this.bankAccountNumber=bankAccountNumber;
        this.bankAcctProofImg=bankAcctProofImg;
        this.bankBranchName=bankBranchName;
        this.bankIfscCode=bankIfscCode;
        this.bankName=bankName;
        this.bankState=bankState;
        this.userId = userId;
    }

}
