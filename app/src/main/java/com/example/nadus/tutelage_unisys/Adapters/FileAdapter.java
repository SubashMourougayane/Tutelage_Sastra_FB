package com.example.nadus.tutelage_unisys.Adapters;

/**
 * Created by HP on 12/28/2017.
 */

public class FileAdapter {
     String Fname;
     String Ftype;
     String Fdate;
     String Fdesc;
     String Fauthor;

    public FileAdapter(String fname, String ftype, String fdate, String fdesc, String fauthor) {
        Fname = fname;
        Ftype = ftype;
        Fdate = fdate;
        Fdesc = fdesc;
        Fauthor = fauthor;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getFtype() {
        return Ftype;
    }

    public void setFtype(String ftype) {
        Ftype = ftype;
    }

    public String getFdate() {
        return Fdate;
    }

    public void setFdate(String fdate) {
        Fdate = fdate;
    }

    public String getFdesc() {
        return Fdesc;
    }

    public void setFdesc(String fdesc) {
        Fdesc = fdesc;
    }

    public String getFauthor() {
        return Fauthor;
    }

    public void setFauthor(String fauthor) {
        Fauthor = fauthor;
    }
}
