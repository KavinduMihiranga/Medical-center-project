package model;


import java.time.LocalDate;

public class Patient {
    private String p_Id;
    private String p_Name;
    private String p_Nic;
    private LocalDate p_Dob;
    private String p_Gender;
    private String p_Address;
    private String p_PhoneNo;

    public Patient() {
    }

    public Patient(String p_Id, String p_Name, String p_Nic, LocalDate p_Dob, String p_Gender, String p_Address, String p_PhoneNo) {
        this.p_Id = p_Id;
        this.p_Name = p_Name;
        this.p_Nic = p_Nic;
        this.p_Dob = p_Dob;
        this.p_Gender = p_Gender;
        this.p_Address = p_Address;
        this.p_PhoneNo = p_PhoneNo;
    }

    public String getP_Id() {
        return p_Id;
    }

    public void setP_Id(String p_Id) {
        this.p_Id = p_Id;
    }

    public String getP_Name() {
        return p_Name;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    public String getP_Nic() {
        return p_Nic;
    }

    public void setP_Nic(String p_Nic) {
        this.p_Nic = p_Nic;
    }

    public String getP_Address() {
        return p_Address;
    }

    public void setP_Address(String p_Address) {
        this.p_Address = p_Address;
    }


    public String getP_PhoneNo() {
        return p_PhoneNo;
    }

    public void setP_PhoneNo(String p_PhoneNo) {
        this.p_PhoneNo = p_PhoneNo;
    }

    public String getP_Gender() {
        return p_Gender;
    }

    public void setP_Gender(String p_Gender) {
        this.p_Gender = p_Gender;
    }

    public LocalDate getP_Dob() {
        return p_Dob;
    }

    public void setP_Dob(LocalDate p_Dob) {
        this.p_Dob = p_Dob;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "p_Id='" + p_Id + '\'' +
                ", p_Name='" + p_Name + '\'' +
                ", p_Nic='" + p_Nic + '\'' +
                ", p_Dob=" + p_Dob +
                ", p_Gender='" + p_Gender + '\'' +
                ", p_Address='" + p_Address + '\'' +
                ", p_PhoneNo='" + p_PhoneNo + '\'' +
                '}';
    }
}
