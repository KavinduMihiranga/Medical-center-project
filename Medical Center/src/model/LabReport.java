package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LabReport {
    private String labReportId;
    private LocalDateTime testedDate;
    private double amount;
    private ArrayList<TestLabReport> tests;
    public LabReport() {
    }

    public LabReport(String labReportId, LocalDateTime testedDate, double amount, ArrayList<TestLabReport> tests) {
        this.labReportId = labReportId;
        this.testedDate = testedDate;
        this.amount = amount;
        this.tests = tests;
    }

    public LabReport(String labReportId, LocalDateTime testedDate, double amount) {
        this.labReportId = labReportId;
        this.testedDate = testedDate;
        this.amount = amount;
    }

    public String getLabReportId() {
        return labReportId;
    }

    public void setLabReportId(String labReportId) {
        this.labReportId = labReportId;
    }

    public LocalDateTime getTestedDate() {
        return testedDate;
    }

    public void setTestedDate(LocalDateTime testedDate) {
        this.testedDate = testedDate;
    }

    public ArrayList<TestLabReport> getTests() {
        return tests;
    }

    public void setTests(ArrayList<TestLabReport> tests) {
        this.tests = tests;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "LabReport{" +
                "labReportId='" + labReportId + '\'' +
                ", testedDate=" + testedDate +
                ", amount=" + amount +
                ", tests=" + tests +
                '}';
    }
}
