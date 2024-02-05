package com.example.UddAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdvancedSearchDTO {

    @JsonProperty("B_nameAndLastnameSignAgency")
    private boolean B_nameAndLastnameSignAgency;
    @JsonProperty("B_contractContent")
    private boolean B_contractContent;
    @JsonProperty("B_govNameAndLevel")
    private boolean B_govNameAndLevel;

    private String nameAndLastnameSignAgency;
    private String contractContent;
    private String govNameAndLevel;

    private String nameAndLastnameSignAgencyOperator;
    private String contractContentOperator;
    private String govNameAndLevelOperator;

    public boolean isB_nameAndLastnameSignAgency() {
        return B_nameAndLastnameSignAgency;
    }

    public void setB_nameAndLastnameSignAgency(boolean b_nameAndLastnameSignAgency) {
        B_nameAndLastnameSignAgency = b_nameAndLastnameSignAgency;
    }

    public boolean isB_contractContent() {
        return B_contractContent;
    }

    public void setB_contractContent(boolean b_contractContent) {
        B_contractContent = b_contractContent;
    }

    public boolean isB_govNameAndLevel() {
        return B_govNameAndLevel;
    }

    public void setB_govNameAndLevel(boolean b_govNameAndLevel) {
        B_govNameAndLevel = b_govNameAndLevel;
    }

    public String getNameAndLastnameSignAgency() {
        return nameAndLastnameSignAgency;
    }

    public void setNameAndLastnameSignAgency(String nameAndLastnameSignAgency) {
        this.nameAndLastnameSignAgency = nameAndLastnameSignAgency;
    }

    public String getContractContent() {
        return contractContent;
    }

    public void setContractContent(String contractContent) {
        this.contractContent = contractContent;
    }

    public String getGovNameAndLevel() {
        return govNameAndLevel;
    }

    public void setGovNameAndLevel(String govNameAndLevel) {
        this.govNameAndLevel = govNameAndLevel;
    }

    public String getNameAndLastnameSignAgencyOperator() {
        return nameAndLastnameSignAgencyOperator;
    }

    public void setNameAndLastnameSignAgencyOperator(String nameAndLastnameSignAgencyOperator) {
        this.nameAndLastnameSignAgencyOperator = nameAndLastnameSignAgencyOperator;
    }

    public String getContractContentOperator() {
        return contractContentOperator;
    }

    public void setContractContentOperator(String contractContentOperator) {
        this.contractContentOperator = contractContentOperator;
    }

    public String getGovNameAndLevelOperator() {
        return govNameAndLevelOperator;
    }

    public void setGovNameAndLevelOperator(String govNameAndLevelOperator) {
        this.govNameAndLevelOperator = govNameAndLevelOperator;
    }
}
