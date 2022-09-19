package model;

public class Test {
    private String testId;
    private String testCategoryId;
    private String testType;
    private double testFee;
    private int duration;

    public Test() {
    }

    public Test(String testId, String testCategoryId, String testType, double testFee, int duration) {
        this.testId = testId;
        this.testCategoryId = testCategoryId;
        this.testType = testType;
        this.testFee = testFee;
        this.duration = duration;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public double getTestFee() {
        return testFee;
    }

    public void setTestFee(double testFee) {
        this.testFee = testFee;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTestCategoryId() {
        return testCategoryId;
    }

    public void setTestCategoryId(String testCategoryId) {
        this.testCategoryId = testCategoryId;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testId='" + testId + '\'' +
                ", testCategoryId='" + testCategoryId + '\'' +
                ", testType='" + testType + '\'' +
                ", testFee=" + testFee +
                ", duration=" + duration +
                '}';
    }
}
