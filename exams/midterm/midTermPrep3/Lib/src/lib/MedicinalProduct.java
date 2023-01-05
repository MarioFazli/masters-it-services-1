package lib;

public class MedicinalProduct implements Comparable<MedicinalProduct> {
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
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getINV_NUMBER() {
        return INV_NUMBER;
    }

    private String generateInvNumber(){
        int copy = ++count;
        String inv = "";
        int digit = 0;
        while(copy != 0){
            digit++;
            copy /= 10;
        }
        for(int i = 0; i <= 7-digit; i++){
            inv += "0";
        }
        return inv + count;
    }

    public MedicinalProduct(){
        INV_NUMBER = generateInvNumber();
        setDescription("");
        setCategory(ProductCategory.E);
        setQty(1);
        setPrice(1);
    }

    public MedicinalProduct(String description, ProductCategory category, int qty, double price){
        INV_NUMBER = generateInvNumber();
        setDescription(description);
        setCategory(category);
        setQty(qty);
        setPrice(price);
    }

    public MedicinalProduct(MedicinalProduct medicinalProduct){
        INV_NUMBER = generateInvNumber();
        setDescription(medicinalProduct.getDescription());
        setCategory(medicinalProduct.getCategory());
        setQty(medicinalProduct.getQty());
        setPrice(medicinalProduct.getPrice());
    }

    @Override
    public String toString(){
        return String.format("%s, %s, %s, %d, %.2f", INV_NUMBER, description, category.toString(), qty, price);
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
