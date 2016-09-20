package com.google.zxing.client.android.domain;

/**
 * Created by SQISOFT on 2016-09-20.
 */
public class ScancodeDomain {

    private String barcode;
    private String QRcode;

    public ScancodeDomain() {
        barcode = "디폴트";
        QRcode = "디폴트";
    }

    public String getQRcode() {
        return QRcode;
    }

    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }



}
