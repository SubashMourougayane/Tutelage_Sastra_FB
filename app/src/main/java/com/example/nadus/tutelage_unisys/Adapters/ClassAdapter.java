package com.example.nadus.tutelage_unisys.Adapters;

import java.util.ArrayList;

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

    public ClassAdapter(String classes, ArrayList<String> subs) {
        this.classname = classes;
        this.subjects = subs;
    }

    ArrayList<String> subjects;

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }
}
