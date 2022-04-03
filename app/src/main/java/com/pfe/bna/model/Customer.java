package com.pfe.bna.model;

import java.util.HashMap;

public class Customer {
    private String name = "";
    private String familyName = "";
    private String cin = "";
    private String fullAddress = "";
    private String mobile = "";
    private String rib = "";
    private Boolean bnaCustomer = true;
    private String bank = "";
    private String agency = "";
    private String gender = "";
    private String birthday = "";
    private String town = "";
    private String username = "";
    private String password = "";
    private String vaccinPass = "";

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public Boolean getBnaCustomer() {
        return bnaCustomer;
    }

    public void setBnaCustomer(Boolean bnaCustomer) {
        this.bnaCustomer = bnaCustomer;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVaccinPass() {
        return vaccinPass;
    }

    public void setVaccinPass(String vaccinPass) {
        this.vaccinPass = vaccinPass;
    }

    public HashMap<String, String> toParams() {
        // variable to hold the params hashmap
        HashMap<String, String> params = new HashMap<>();
        if (!getName().isEmpty()) {
            params.put("name", getName());
        }
        if (!getFamilyName().isEmpty()) {
            params.put("familyName", getFamilyName());
        }
        if (!getCin().isEmpty()) {
            params.put("cin", getCin());
        }
        if (!getFullAddress().isEmpty()) {
            params.put("fullAddress", getFullAddress());
        }
        if (!getMobile().isEmpty()) {
            params.put("mobile", getMobile());
        }
        if (!getRib().isEmpty()) {
            params.put("rib", getRib());
        }
        params.put("bnaCustomer", getBnaCustomer().toString());
        if (!getBank().isEmpty()) {
            params.put("bank", getBank());
        }
        if (!getAgency().isEmpty()) {
            params.put("agency", getAgency());
        }
        if (!getGender().isEmpty()) {
            params.put("gender", getGender());
        }
        if (!getBirthday().isEmpty()) {
            params.put("birthday", getBirthday());
        }
        if (!getTown().isEmpty()) {
            params.put("town", getTown());
        }
        if (!getUsername().isEmpty()) {
            params.put("username", getUsername());
        }
        if (!getPassword().isEmpty()) {
            params.put("password", getPassword());
        }
        return params;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", cin='" + cin + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                ", mobile='" + mobile + '\'' +
                ", rib='" + rib + '\'' +
                ", bnaCustomer=" + bnaCustomer +
                ", bank='" + bank + '\'' +
                ", agency='" + agency + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", town='" + town + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", vaccinPass='" + vaccinPass + '\'' +
                '}';
    }
}
