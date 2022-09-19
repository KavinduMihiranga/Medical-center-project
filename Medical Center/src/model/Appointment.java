package model;

public class Appointment {

    private String appointmentId;
    private String patientId;
    private String sessionId;
    private int appointmentNo;
    private double appointmentFee;

    public Appointment() {
    }

    public Appointment(String appointmentId, String patientId, String sessionId, int appointmentNo, double appointmentFee) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.sessionId = sessionId;
        this.appointmentNo = appointmentNo;
        this.appointmentFee = appointmentFee;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(int appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public double getAppointmentFee() {
        return appointmentFee;
    }

    public void setAppointmentFee(double appointmentFee) {
        this.appointmentFee = appointmentFee;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", appointmentNo=" + appointmentNo +
                ", appointmentFee=" + appointmentFee +
                '}';
    }
}
