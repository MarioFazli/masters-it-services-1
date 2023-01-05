package lib;

public enum ProductCategory {
    A ("Drugs"),
    B ("Cosmetics"),
    C ("Devices"),
    D ("Other"),
    E ("Undefined");
    private final String category;
    ProductCategory(String category){
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
