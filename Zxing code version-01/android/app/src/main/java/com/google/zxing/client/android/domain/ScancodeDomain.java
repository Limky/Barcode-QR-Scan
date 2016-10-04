package com.google.zxing.client.android.domain;

/**
 * Created by SQISOFT on 2016-09-20.
 */
public class ScancodeDomain {

    int index;
    private String barcode;
    private String Beacon;
    private String Scantime;

    public ScancodeDomain() {
        barcode = "디폴트";
        Beacon = "디폴트";
        Scantime = "777";
    }

    public ScancodeDomain(int index, String Beacon , String barcode, String Scantime) {
        this.index = index;
        this.Beacon = Beacon;
        this.barcode = barcode;
        this.Scantime = Scantime;

    }

    public String getScantime() {
        return Scantime;
    }

    public void setScantime(String Scantime) {
        this.Scantime = Scantime;
    }

    public String getBeacon() {
        return Beacon;
    }

    public void setBeacon(String Beacon) {
        this.Beacon = Beacon;
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
        return "\nindex " + index +"\n\n" + "Beacon  = " + Beacon + "\n\n" + "Barcode = " + barcode+"\n";
    }

}
