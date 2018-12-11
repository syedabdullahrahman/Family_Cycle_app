
package com.example.user.familycyclefinal.objects;
/*
public class Kazi {
    Long kaziLicenceNumber;
    String kaziUserName;
    String kaziEmail;
    String kaziPassword;

    public Kazi() {

    }
    public Kazi(String kaziUserName, String kaziEmail, String kaziPassword) {
        this.kaziUserName = kaziUserName;
        this.kaziEmail = kaziEmail;
        this.kaziPassword = kaziPassword;
    }

    public Long getKaziLicenceNumber() {
        return kaziLicenceNumber;
    }

    public void setKaziLicenceNumber(Long kaziLicenceNumber) {
        this.kaziLicenceNumber = kaziLicenceNumber;
    }

    public String getKaziUserName() {
        return kaziUserName;
    }

    public void setKaziUserName(String kaziUserName) {
        this.kaziUserName = kaziUserName;
    }

    public String getKaziEmail() {
        return kaziEmail;
    }

    public void setKaziEmail(String kaziEmail) {
        this.kaziEmail = kaziEmail;
    }

    public String getKaziPassword() {
        return kaziPassword;
    }

    public void setKaziPassword(String kaziPassword) {
        this.kaziPassword = kaziPassword;
    }
}
*/


import org.json.JSONException;
import org.json.JSONObject;

public class Kazi {

    String kaziLicenceNumber;
    String kaziUserName;
    String kaziEmail;
    String kaziPassword;
    String name;
    String eduBackground;
    String dob;
    String nid;
    String tin;
    String officeAddress;
    String preferedArea;
    String contact;
    String authorised;

    public Kazi() {

    }

    public Kazi(String kaziLicenceNumber, String name, String eduBackground, String dob, String nid, String tin, String officeAddress, String preferedArea, String contact, String kaziUserName, String kaziEmail, String kaziPassword, String authorised) {
        this.kaziLicenceNumber = kaziLicenceNumber;
        this.name = name;
        this.eduBackground = eduBackground;
        this.dob = dob;
        this.nid = nid;
        this.tin = tin;
        this.officeAddress = officeAddress;
        this.preferedArea = preferedArea;
        this.contact = contact;
        this.kaziUserName = kaziUserName;
        this.kaziEmail = kaziEmail;
        this.kaziPassword = kaziPassword;
        this.authorised = authorised;
    }

    public String getAuthorised() {
        return authorised;
    }

    public void setAuthorised(String authorised) {
        this.authorised = authorised;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEduBackground(String eduBackground) {
        this.eduBackground = eduBackground;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public void setPreferedArea(String preferedArea) {
        this.preferedArea = preferedArea;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getEduBackground() {
        return eduBackground;
    }

    public String getDob() {
        return dob;
    }

    public String getNid() {
        return nid;
    }

    public String getTin() {
        return tin;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public String getPreferedArea() {
        return preferedArea;
    }

    public String getContact() {
        return contact;
    }

    public String getKaziLicenceNumber() {
        return kaziLicenceNumber;
    }

    public void setKaziLicenceNumber(String kaziLicenceNumber) {
        this.kaziLicenceNumber = kaziLicenceNumber;
    }

    public String getKaziUserName() {
        return kaziUserName;
    }

    public void setKaziUserName(String kaziUserName) {
        this.kaziUserName = kaziUserName;
    }

    public String getKaziEmail() {
        return kaziEmail;
    }

    public void setKaziEmail(String kaziEmail) {
        this.kaziEmail = kaziEmail;
    }

    public String getKaziPassword() {
        return kaziPassword;
    }

    public void setKaziPassword(String kaziPassword) {
        this.kaziPassword = kaziPassword;
    }

    public void makeObject(JSONObject j) {

        try {
            kaziLicenceNumber = (String) j.get("kaziLicenceNumber");
            kaziUserName = (String) j.get("kaziUserName");
            kaziEmail = (String) j.get("kaziEmail");
            kaziPassword = (String) j.get("kaziPassword");
            name = (String) j.get("name");
            eduBackground = (String) j.get("eduBackground");
            dob = (String) j.get("dob");
            nid = (String) j.get("nid");
            tin = (String) j.get("tin");
            officeAddress = (String) j.get("officeAddress");
            preferedArea = (String) j.get("preferedArea");
            contact = (String) j.get("contact");
            authorised = (String) j.get("authorised");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}