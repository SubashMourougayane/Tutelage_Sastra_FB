package com.example.nadus.tutelage_unisys.Adapters;

import java.util.ArrayList;

/**
 * Created by nadus on 03-01-2018.
 */

public class AssessmentQuestionAdapter {

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    String op1,op2,op3,op4;

    public AssessmentQuestionAdapter(String op1, String op2, String op3, String op4, String correctOP, String question) {
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.correctOP = correctOP;
        this.question = question;
    }

    public String getCorrectOP() {
        return correctOP;
    }

    public void setCorrectOP(String correctOP) {
        this.correctOP = correctOP;
    }

    String correctOP;



    public void setQuestion(String question) {
        this.question = question;
    }


    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    String question;
   ArrayList<String> options=new ArrayList<>();

}
