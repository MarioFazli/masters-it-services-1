package lib;

import java.util.Random;

public class MedicinalProduct implements Comparable<MedicinalProduct> {
    private String description;
    private ProductCategory category;
    private int qty;
    private double price;
    // it's unique and never changes -> can be final
    private final String INV_NUMBER;
    // static counter for keeping count of the number of objects
    private static int counter;

    // Generating inventory number using static counter -> ensuring that it is always unique
    private String generateInventoryNumber(){
        int copyCounter = ++counter;
        int digitNumber = 0;
        while(copyCounter != 0){
            copyCounter /= 10;
            digitNumber++;
        }
        String invNumber = "";
        // 7 sybmbols in string -> 7 - number of digits of counter and the other are "0"
        for(int i =0; i < 7 - digitNumber; i++){
            invNumber += "0";
        }
        return invNumber + counter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description != null){
            this.description = description;
        }
        else{
            this.description = "";
        }
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        if(qty >= 0){
            this.qty = qty;
        }
        else{
            this.qty = 0;
        }

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price >= 0){
            this.price = price;
        }
        else{
            this.price = 0;
        }

    }

    public String getINV_NUMBER() {
        return INV_NUMBER;
    }

    public MedicinalProduct(){
        INV_NUMBER = generateInventoryNumber();
        setDescription("");
        setCategory(ProductCategory.E);
        setQty(1);
        setPrice(1);
    }

    public MedicinalProduct(String description, ProductCategory category, int qty, double price){
        INV_NUMBER = generateInventoryNumber();
        setDescription(description);
        setCategory(category);
        setQty(qty);
        setPrice(price);
    }

    public MedicinalProduct(MedicinalProduct medicinalProduct){
        INV_NUMBER = generateInventoryNumber();
        setDescription(medicinalProduct.getDescription());
        setCategory(medicinalProduct.getCategory());
        setQty(medicinalProduct.getQty());
        setPrice(medicinalProduct.getPrice());
    }

    @Override
    public String toString(){
        return String.format("Product Information: %s, %s, %s, %d, %.2f", INV_NUMBER, description, category.toString(), qty, price);
    }


    @Override
    public int compareTo(MedicinalProduct o) {
        return Integer.compare(Integer.parseInt(INV_NUMBER), Integer.parseInt(o.getINV_NUMBER()));
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof MedicinalProduct){
            return this.description.equals(((MedicinalProduct) o).getDescription())
                    && this.category.toString().equals(((MedicinalProduct) o).getCategory().toString());
        }
        return false;
    }
}
