package com.example.nadus.tutelage_unisys.Adapters;

/**
 * Created by HP on 12/28/2017.
 */

public class FileAdapter {
    String fname;
    String[] g;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String[] getG() {
        return g;
    }

    public void setG(String[] g) {
        this.g = g;
    }
    public FileAdapter(String fn,String[] v)
    {
      this.fname=fn;
      this.g=v;
    }
}
