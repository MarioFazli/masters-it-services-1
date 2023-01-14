package lib;

public enum ProductCategory {
    A ("Drugs"),
    B ("Cosmetics"),
    C ("Devices"),
    D ("Other"),
    E ("Undefined");

    private final String categoryName;

    ProductCategory(String categoryName){
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }
}
