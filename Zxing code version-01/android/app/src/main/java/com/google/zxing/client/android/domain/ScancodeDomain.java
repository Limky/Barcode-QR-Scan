package com.google.zxing.client.android.domain;

/**
 * Created by SQISOFT on 2016-09-20.
 */
public class ScancodeDomain {

    int index;
    private String barcode;
    private String QRcode;

    public ScancodeDomain() {
        barcode = "디폴트";
        QRcode = "디폴트";
    }

    public ScancodeDomain(int index, String QRcode , String barcode) {
        this.index = index;
        this.QRcode = QRcode;
        this.barcode = barcode;

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "\nindex " + index +"\n\n" + "QRcode  = " + QRcode + "\n\n" + "Barcode = " + barcode+"\n";
    }

}
