package com.gamsys.localdb.models;

import java.util.Date;

public class Logs {

    Date treatmentStartTimeStamp,treatmentEndTimeStamp;

    int id,status;

    String user_name,key_status,user_id;
    String leftHandSystolic, leftHandDiastolic, leftHandPulse, rightHandSystolic, rightHandDiastolic, rightHandPulse, username, password;


    public Logs() {
        this.id = id;
        this.username = username;
        this.treatmentStartTimeStamp = treatmentStartTimeStamp;
        this.leftHandSystolic = leftHandSystolic;
        this.leftHandDiastolic = leftHandDiastolic;
        this.leftHandPulse = leftHandPulse;
        this.rightHandSystolic = rightHandSystolic;
        this.rightHandDiastolic = rightHandDiastolic;
        this.rightHandPulse = rightHandPulse;
        this.status = status;
    }

    public Logs(int id, String user_id, String user_name, String password, String key_status) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.key_status = key_status;

    }

    public Logs(String user_name, Date starttime, String r_sys, String r_dia, String r_pulse, String l_sys, String l_dia, String l_pulse, int status) {


        this.id = id;
        this.user_name = user_name;
        this.treatmentEndTimeStamp = treatmentEndTimeStamp;
        this.treatmentStartTimeStamp = starttime;
        this.leftHandSystolic = l_sys;
        this.leftHandDiastolic = l_dia;
        this.leftHandPulse = l_pulse;
        this.rightHandSystolic = r_sys;
        this.rightHandDiastolic = r_dia;
        this.rightHandPulse = r_pulse;
        this.status = status;
    }


    /*public Logs(int anInt, String string, String cursorString) {
        this.username = username;
        this.treatmentStartTimeStamp = treatmentStartTimeStamp;
        this.leftHandSystolic = leftHandSystolic;
        this.id = id;
        this.leftHandDiastolic = leftHandDiastolic;
        this.leftHandPulse = leftHandPulse;
        this.rightHandSystolic = rightHandSystolic;
        this.rightHandDiastolic = rightHandDiastolic;
        this.rightHandPulse = rightHandPulse;
        this.status = status;
        this.password = password;
    }*/

   /* public Logs(int anInt, String string, String cursorString, String s, String string1) {
        this.treatmentStartTimeStamp = treatmentStartTimeStamp;
        this.leftHandSystolic = leftHandSystolic;

        this.leftHandDiastolic = leftHandDiastolic;
        this.leftHandPulse = leftHandPulse;
        this.rightHandSystolic = rightHandSystolic;
        this.rightHandDiastolic = rightHandDiastolic;
        this.rightHandPulse = rightHandPulse;
    }


    public Logs(String username, Date starttime, String r_sys, String r_pulse, String l_sys, String l_dia, String l_pulse) {

    }

    public Logs() {

    }
*/

    public Logs(String username,Date treatmentStartTimeStamp, int leftHandDiastolic, int leftHandSystolic, int leftHandPulse, int rightHandSystolic, int rightHandDiastolic, int rightHandPulse) {


    }


    public Logs(Date s, String i, String toString, String string, String parseInt, String s1) {
        this.treatmentEndTimeStamp = treatmentEndTimeStamp;
        this.treatmentStartTimeStamp = treatmentStartTimeStamp;
        this.leftHandSystolic = leftHandSystolic;
        this.user_name = user_name;
        this.leftHandDiastolic = leftHandDiastolic;
        this.leftHandPulse = leftHandPulse;
        this.rightHandSystolic = rightHandSystolic;
        this.rightHandDiastolic = rightHandDiastolic;
        this.rightHandPulse = rightHandPulse;
    }

    public Date getTreatmentEndTimeStamp() {
        return treatmentEndTimeStamp;
    }

    public void setTreatmentEndTimeStamp(Date treatmentEndTimeStamp) {
        this.treatmentEndTimeStamp = treatmentEndTimeStamp;
    }

    public Date getTreatmentStartTimeStamp() {
        return treatmentStartTimeStamp;
    }

    public void setTreatmentStartTimeStamp(Date treatmentStartTimeStamp) {
        this.treatmentStartTimeStamp = treatmentStartTimeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeftHandSystolic() {
        return leftHandSystolic;
    }

    public void setLeftHandSystolic(String leftHandSystolic) {
        this.leftHandSystolic = leftHandSystolic;
    }

    public String getLeftHandDiastolic() {
        return leftHandDiastolic;
    }

    public void setLeftHandDiastolic(String leftHandDiastolic) {
        this.leftHandDiastolic = leftHandDiastolic;
    }

    public String getLeftHandPulse() {
        return leftHandPulse;
    }

    public void setLeftHandPulse(String leftHandPulse) {
        this.leftHandPulse = leftHandPulse;
    }

    public String getRightHandSystolic() {
        return rightHandSystolic;
    }

    public void setRightHandSystolic(String rightHandSystolic) {
        this.rightHandSystolic = rightHandSystolic;
    }

    public String getRightHandDiastolic() {
        return rightHandDiastolic;
    }

    public void setRightHandDiastolic(String rightHandDiastolic) {
        this.rightHandDiastolic = rightHandDiastolic;
    }

    public String getRightHandPulse() {
        return rightHandPulse;
    }

    public void setRightHandPulse(String rightHandPulse) {
        this.rightHandPulse = rightHandPulse;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(int status) {
        this.status = status;


    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getKey_status() {
        return key_status;
    }

    public void setKey_status(String key_status) {
        this.key_status = key_status;
    }
}
