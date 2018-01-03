package com.example.nadus.tutelage_unisys.DataModels;

/**
 * Created by msuba on 1/2/2018.
 */

public class Blob
{
    public static String Fname;
    public static String Ftype;
    public static String Fdate;
    public  static String Fdesc;

    public  String getFdesc() {
        return Fdesc;
    }

    public  void setFdesc(String fdesc) {
        Fdesc = fdesc;
    }


    public  String getFname() {
        return Fname;
    }

    public  void setFname(String fname) {
        Fname = fname;
    }

    public  String getFtype() {
        return Ftype;
    }

    public  void setFtype(String ftype) {
        Ftype = ftype;
    }

    public  String getFdate() {
        return Fdate;
    }

    public  void setFdate(String fdate) {
        Fdate = fdate;
    }
}
