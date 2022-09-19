package model;


import java.time.LocalDate;
import java.time.LocalTime;

public class Session {
    private String session_Id;
    private String d_Id;
    private int maxNoOfPatient;
    private int currentAppointmentNo;
    private LocalDate sessionDate;
    private LocalTime sessionStartTime;
    private LocalTime sessionEndTime;

    public Session(String session_id, LocalDate sessionDate, LocalTime sessionStartTime, LocalTime sessionEndTime, int maxNoOfPatient, int currentAppointmentNo) {
    }

    public Session(String session_Id, String d_Id, int maxNoOfPatient, int currentAppointmentNo, LocalDate sessionDate, LocalTime sessionStartTime, LocalTime sessionEndTime) {
        this.session_Id = session_Id;
        this.d_Id = d_Id;
        this.maxNoOfPatient = maxNoOfPatient;
        this.currentAppointmentNo = currentAppointmentNo;
        this.sessionDate = sessionDate;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
    }

    public Session(String session_Id, int maxNoOfPatient, LocalDate sessionDate, LocalTime sessionStartTime, LocalTime sessionEndTime) {
        this.session_Id = session_Id;
        this.maxNoOfPatient = maxNoOfPatient;
        this.sessionDate = sessionDate;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
    }

    public Session(LocalDate date, LocalTime sessionStartTime, LocalTime sessionEndTime, int maxNoOfPatient, int currentAppointmentNo) {
        this.sessionDate=date;
        this.sessionStartTime=sessionStartTime;
        this.sessionEndTime=sessionEndTime;
        this.maxNoOfPatient=maxNoOfPatient;
        this.currentAppointmentNo=currentAppointmentNo;
    }


    public String getSession_Id() {
        return session_Id;
    }

    public void setSession_Id(String session_Id) {
        this.session_Id = session_Id;
    }

    public int getMaxNoOfPatient() {
        return maxNoOfPatient;
    }

    public void setMaxNoOfPatient(int maxNoOfPatient) {
        this.maxNoOfPatient = maxNoOfPatient;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public LocalTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public LocalTime getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(LocalTime sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public String getD_Id() {
        return d_Id;
    }

    public void setD_Id(String d_Id) {
        this.d_Id = d_Id;
    }

    public int getCurrentAppointmentNo() {
        return currentAppointmentNo;
    }

    public void setCurrentAppointmentNo(int currentAppointmentNo) {
        this.currentAppointmentNo = currentAppointmentNo;
    }

    @Override
    public String toString() {
        return "Session{" +
                "session_Id='" + session_Id + '\'' +
                ", d_Id='" + d_Id + '\'' +
                ", maxNoOfPatient=" + maxNoOfPatient +
                ", sessionDate=" + sessionDate +
                ", sessionStartTime=" + sessionStartTime +
                ", sessionEndTime=" + sessionEndTime +
                '}';
    }
}
