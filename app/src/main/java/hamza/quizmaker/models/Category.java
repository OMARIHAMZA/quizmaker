package hamza.quizmaker.models;

public class Category {

    private String categoryId;
    private String categoryName;
    private int categoryImageResource;

    public Category(String categoryId, String categoryName, int categoryImageResource) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImageResource = categoryImageResource;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryImageResource() {
        return categoryImageResource;
    }

    public void setCategoryImageResource(int categoryImageResource) {
        this.categoryImageResource = categoryImageResource;
    }
}
