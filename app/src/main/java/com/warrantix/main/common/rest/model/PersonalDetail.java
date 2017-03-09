package com.warrantix.main.common.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PersonalDetail {

    @SerializedName("employer")
    @Expose
    private String employer;
    @SerializedName("salaried")
    @Expose
    private Boolean salaried;
    @SerializedName("monthlySalary")
    @Expose
    private String monthlySalary;
    @SerializedName("bank")
    @Expose
    private String bank;

    @SerializedName("joiningDate")
    @Expose
    private String joiningDate;
    @SerializedName("workExperience")
    @Expose
    private String workExperience;
    @SerializedName("yearsCity")
    @Expose
    private String yearsCity;
    @SerializedName("yearsHome")
    @Expose
    private String yearsHome;

    @SerializedName("residenceType")
    @Expose
    private String residenceType;

    @SerializedName("dob")
    @Expose
    private String dob;

    @SerializedName("EMIs")
    @Expose
    private String EMIs;

    @SerializedName("incentives")
    @Expose
    private String incentives;

    @SerializedName("isSpouseWorking")
    @Expose
    private Boolean isSpouseWorking;

    @SerializedName("salaryOfSpouse")
    @Expose
    private String salaryOfSpouse;

    @SerializedName("wantFinance")
    @Expose
    private String wantFinance;

    @SerializedName("callMeTime")
    @Expose
    private String callMeTime;



    /**
     *
     * @return
     * The employer
     */
    public String getEmployer() {
        return employer;
    }

    /**
     *
     * @param employer
     * The employer
     */
    public void setEmployer(String employer) {
        this.employer = employer;
    }

    /**
     *
     * @return
     * The salaried
     */
    public Boolean getSalaried() {
        return salaried;
    }

    /**
     *
     * @param salaried
     * The salaried
     */
    public void setSalaried(Boolean salaried) {
        this.salaried = salaried;
    }

    /**
     *
     * @return
     * The monthlySalary
     */
    public String getMonthlySalary() {
        return monthlySalary;
    }

    /**
     *
     * @param monthlySalary
     * The monthlySalary
     */
    public void setMonthlySalary(String monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    /**
     *
     * @return
     * The bank
     */
    public String getBank() {
        return bank;
    }

    /**
     *
     * @param bank
     * The bank
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     *
     * @return
     * The joiningDate
     */
    public String getJoiningDate() {
        return joiningDate;
    }

    /**
     *
     * @param joiningDate
     * The joiningDate
     */
    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    /**
     *
     * @return
     * The workExperience
     */
    public String getWorkExperience() {
        return workExperience;
    }

    /**
     *
     * @param workExperience
     * The workExperience
     */
    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    /**
     *
     * @return
     * The yearsCity
     */
    public String getYearsCity() {
        return yearsCity;
    }

    /**
     *
     * @param yearsCity
     * The yearsCity
     */
    public void setYearsCity(String yearsCity) {
        this.yearsCity = yearsCity;
    }

    /**
     *
     * @return
     * The yearsHome
     */
    public String getYearsHome() {
        return yearsHome;
    }

    /**
     *
     * @param yearsHome
     * The yearsHome
     */
    public void setYearsHome(String yearsHome) {
        this.yearsHome = yearsHome;
    }

    /**
     *
     * @return
     * The residenceType
     */
    public String getResidenceType() {
        return residenceType;
    }

    /**
     *
     * @param residenceType
     * The residenceType
     */
    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    /**
     *
     * @return
     * The dob
     */
    public String getDob() {
        return dob;
    }

    /**
     *
     * @param dob
     * The dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     *
     * @return
     * The EMIs
     */
    public String getEMIs() {
        return EMIs;
    }

    /**
     *
     * @param EMIs
     * The EMIs
     */
    public void setEMIs(String EMIs) {
        this.EMIs = EMIs;
    }


    /**
     *
     * @return
     * The incentives
     */
    public String getIncentives() {
        return incentives;
    }

    /**
     *
     * @param incentives
     * The incentives
     */
    public void setIncentives(String incentives) {
        this.incentives = incentives;
    }


    /**
     *
     * @return
     * The isSpouseWorking
     */
    public Boolean getIsSpouseWorking() {
        return isSpouseWorking;
    }

    /**
     *
     * @param isSpouseWorking
     * The isSpouseWorking
     */
    public void setIsSpouseWorking(Boolean isSpouseWorking) {
        this.isSpouseWorking = isSpouseWorking;
    }

    /**
     *
     * @return
     * The salaryOfSpouse
     */
    public String getSalaryOfSpouse() {
        return salaryOfSpouse;
    }

    /**
     *
     * @param salaryOfSpouse
     * The salaryOfSpouse
     */
    public void setSalaryOfSpouse(String salaryOfSpouse) {
        this.salaryOfSpouse = salaryOfSpouse;
    }


    /**
     *
     * @return
     * The wantFinance
     */
    public String getWantFinance() {
        return wantFinance;
    }

    /**
     *
     * @param wantFinance
     * The wantFinance
     */
    public void setWantFinance(String wantFinance) {
        this.wantFinance = wantFinance;
    }

    /**
     *
     * @return
     * The callMeTime
     */
    public String getCallMeTime() {
        return callMeTime;
    }

    /**
     *
     * @param callMeTime
     * The callMeTime
     */
    public void setCallMeTime(String callMeTime) {
        this.callMeTime = callMeTime;
    }

}