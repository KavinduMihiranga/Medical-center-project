package model;

public class Doctor {
    private String D_Id;
    private String D_Name;
    private String D_Address;
    private String D_Speciality;
    private String D_PhoneNo;
    private String D_MBBSNo;
    private double D_Charge;

    public Doctor() {
    }

    public Doctor(String d_Id, String d_Name, String d_Address, String d_Speciality, String d_PhoneNo, String d_MBBSNo, double d_Charge) {
        D_Id = d_Id;
        D_Name = d_Name;
        D_Address = d_Address;
        D_Speciality = d_Speciality;
        D_PhoneNo = d_PhoneNo;
        D_MBBSNo = d_MBBSNo;
        D_Charge = d_Charge;
    }

    public String getD_Id() {
        return D_Id;
    }

    public void setD_Id(String d_Id) {
        D_Id = d_Id;
    }

    public String getD_Name() {
        return D_Name;
    }

    public void setD_Name(String d_Name) {
        D_Name = d_Name;
    }

    public String getD_Address() {
        return D_Address;
    }

    public void setD_Address(String d_Address) {
        D_Address = d_Address;
    }

    public String getD_Speciality() {
        return D_Speciality;
    }

    public void setD_Speciality(String d_Speciality) {
        D_Speciality = d_Speciality;
    }

    public String getD_PhoneNo() {
        return D_PhoneNo;
    }

    public void setD_PhoneNo(String d_PhoneNo) {
        D_PhoneNo = d_PhoneNo;
    }

    public String getD_MBBSNo() {
        return D_MBBSNo;
    }

    public void setD_MBBSNo(String d_MBBSNo) {
        D_MBBSNo = d_MBBSNo;
    }

    public double getD_Charge() {
        return D_Charge;
    }

    public void setD_Charge(double d_Charge) {
        D_Charge = d_Charge;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "D_Id='" + D_Id + '\'' +
                ", D_Name='" + D_Name + '\'' +
                ", D_Address='" + D_Address + '\'' +
                ", D_Speciality='" + D_Speciality + '\'' +
                ", D_PhoneNo='" + D_PhoneNo + '\'' +
                ", D_MBBSNo='" + D_MBBSNo + '\'' +
                ", D_Charge=" + D_Charge +
                '}';
    }
}
