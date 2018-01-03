package com.example.nadus.tutelage_unisys.Adapters;

/**
 * Created by HP on 12/29/2017.
 */

public class StudentAdapter {
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    String studentName;
    public StudentAdapter(String s)
    {
        this.studentName=s;
    }
}
