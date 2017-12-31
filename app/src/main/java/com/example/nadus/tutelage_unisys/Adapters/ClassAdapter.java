package com.example.nadus.tutelage_unisys.Adapters;

/**
 * Created by HP on 11/16/2017.
 */

public class ClassAdapter {

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    String classname;

    public String[] getA()
    {
        return a;
    }

    public void setA(String[] a)
    {
        this.a = a;
    }

    String[] a;
    public ClassAdapter(String typename,String[] b)
    {
        this.classname=typename;
        this.a=b;
    }
}
