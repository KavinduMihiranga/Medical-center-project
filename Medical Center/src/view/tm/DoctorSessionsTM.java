package view.tm;

import javafx.scene.control.Button;


import java.time.LocalDate;
import java.time.LocalTime;

public class DoctorSessionsTM {
    private String sessionId;
    private LocalDate date;
    private LocalTime startTime;
    private int activePatients;
    private Button availability;

    public DoctorSessionsTM() {
    }

    public DoctorSessionsTM(String sessionId, LocalDate date, LocalTime startTime, int activePatients, Button availability) {
        this.sessionId = sessionId;
        this.date = date;
        this.startTime = startTime;
        this.activePatients = activePatients;
        this.availability = availability;
    }

    public DoctorSessionsTM(String sessionId, LocalDate date, LocalTime startTime,int activePatients) {
        this.sessionId = sessionId;
        this.date = date;
        this.startTime = startTime;
        this.activePatients=activePatients;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getActivePatients() {
        return activePatients;
    }

    public void setActivePatients(int activePatients) {
        this.activePatients = activePatients;
    }

    public Button getAvailability() {
        return availability;
    }

    public void setAvailability(Button availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "DoctorSessionsTM{" +
                "sessionId='" + sessionId + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", activePatients=" + activePatients +
                ", availability=" + availability +
                '}';
    }
}
