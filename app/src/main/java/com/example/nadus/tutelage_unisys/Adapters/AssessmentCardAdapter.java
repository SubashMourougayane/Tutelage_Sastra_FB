package com.example.nadus.tutelage_unisys.Adapters;

/**
 * Created by nadus on 02-01-2018.
 */

public class AssessmentCardAdapter {
    String testname, subclassname, duration, date, noofstudattended, status;

    public AssessmentCardAdapter(String testname, String subclassname, String duration, String date, String noofstudattended, String status) {
        this.testname = testname;
        this.subclassname = subclassname;
        this.duration = duration;
        this.date = date;
        this.noofstudattended = noofstudattended;
        this.status = status;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getSubclassname() {
        return subclassname;
    }

    public void setSubclassname(String subclassname) {
        this.subclassname = subclassname;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoofstudattended() {
        return noofstudattended;
    }

    public void setNoofstudattended(String noofstudattended) {
        this.noofstudattended = noofstudattended;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
