package model;

public class TestCategory {
    private String testCategoryId;
    private String testCategory;

    public TestCategory() {
    }

    public TestCategory(String testCategoryId, String testCategory) {
        this.testCategoryId = testCategoryId;
        this.testCategory = testCategory;
    }

    public String getTestCategoryId() {
        return testCategoryId;
    }

    public void setTestCategoryId(String testCategoryId) {
        this.testCategoryId = testCategoryId;
    }

    public String getTestCategory() {
        return testCategory;
    }

    public void setTestCategory(String testCategory) {
        this.testCategory = testCategory;
    }

    @Override
    public String toString() {
        return "TestCategory{" +
                "testCategoryId='" + testCategoryId + '\'' +
                ", testCategory='" + testCategory + '\'' +
                '}';
    }
}
