package com.example.drewk.audioeq;

import io.realm.RealmModel;
import io.realm.RealmObject;

public class Profile extends RealmObject {
    private String name;
    private double progress1;
    private double progress2;
    private double progress3;
    private double progress4;
    private double progress5;

    public Profile() {
    }

    public Profile(String name, double progress1, double progress2,
                   double progress3, double progress4, double progress5) {
        this.name = name;
        this.progress1 = progress1;
        this.progress2 = progress2;
        this.progress3 = progress3;
        this.progress4 = progress4;
        this.progress5 = progress5;
    }

    public String getName() {
        return name;
    }

    public double getProgress1() {
        return progress1;
    }

    public double getProgress2() {
        return progress2;
    }

    public double getProgress3() {
        return progress3;
    }

    public double getProgress4() {
        return progress4;
    }

    public double getProgress5() {
        return progress5;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProgress1(double progress1) {
        this.progress1 = progress1;
    }

    public void setProgress2(double progress2) {
        this.progress2 = progress2;
    }

    public void setProgress3(double progress3) {
        this.progress3 = progress3;
    }

    public void setProgress4(double progress4) {
        this.progress4 = progress4;
    }

    public void setProgress5(double progress5) {
        this.progress5 = progress5;
    }

}
