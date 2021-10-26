package com.example.questionpaper.Requests.Verification;

public class PanSubmitRequest  {
    String paDateOfBirth,paImgLink,paName,paNumber;
    String userId;
    public PanSubmitRequest(String paDateOfBirth,String paImgLink, String paName,
                            String paNumber, String userId) {
        this.paDateOfBirth = paDateOfBirth;
        this.paImgLink=paImgLink;
        this.paName=paName;
        this.paNumber=paNumber;
        this.userId = userId;
    }

}