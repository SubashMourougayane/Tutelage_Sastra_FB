package com.example.nadus.tutelage_unisys.Adapters;

/**
 * Created by nadus on 02-01-2018.
 */

public class AssessmentResultCardAdapter {
    String result_name, result_reg, result_tot;

    public AssessmentResultCardAdapter(String result_name, String result_reg, String result_tot) {
        this.result_name = result_name;
        this.result_reg = result_reg;
        this.result_tot = result_tot;
    }

    public String getResult_name() {
        return result_name;
    }

    public void setResult_name(String result_name) {
        this.result_name = result_name;
    }

    public String getResult_reg() {
        return result_reg;
    }

    public void setResult_reg(String result_reg) {
        this.result_reg = result_reg;
    }

    public String getResult_tot() {
        return result_tot;
    }

    public void setResult_tot(String result_tot) {
        this.result_tot = result_tot;
    }
}
