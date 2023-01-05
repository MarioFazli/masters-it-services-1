package lib;

public class MedicalProduct implements Comparable<MedicalProduct> {
    private String description;
    private ProductCategory category;
    private int qty;
    private double price;
    private final String INV_NUMBER;
    private static int count;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
            System.out.println("Forbidden value for qty");
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
            System.out.println("Forbidden value for price");
        }

    }

    public String getINV_NUMBER() {
        return INV_NUMBER;
    }

    private String generateInvNumber(){
        int copy = ++count;
        int counter = 0;
        while(copy != 0){
            counter++;
            copy /= 10;
        }
        String invNumber = "";
        for(int i = 0 ; i <= copy; i++){
            invNumber += "0";
        }
        return invNumber + counter;
    }

    public MedicalProduct(){
        INV_NUMBER = generateInvNumber();
        setDescription("");
        setCategory(ProductCategory.A);
        setQty(1);
        setPrice(5);
    }

    public MedicalProduct(String description, ProductCategory category, int qty, double price){
        INV_NUMBER = generateInvNumber();
        setDescription(description);
        setCategory(category);
        setQty(qty);
        setPrice(price);
    }

    public MedicalProduct(MedicalProduct medicalProduct){
        INV_NUMBER = generateInvNumber();
        setDescription(medicalProduct.getDescription());
        setCategory(medicalProduct.getCategory());
        setQty(medicalProduct.getQty());
        setPrice(medicalProduct.getPrice());
    }

    @Override
    public String toString(){
        return String.format("%s, %s, %s, %d, %.2f", INV_NUMBER, description, category.toString(), qty, price);
    }


    @Override
    public int compareTo(MedicalProduct o) {
        return this.getINV_NUMBER().compareTo(o.generateInvNumber());
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof  MedicalProduct){
            return this.getDescription().equals(((MedicalProduct) o).getDescription())
                    && this.getCategory().toString().equals(((MedicalProduct) o).getCategory().toString());
        }
        return false;
    }

}
