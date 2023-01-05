import lib.MedicalProduct;
import lib.ProductCategory;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticData {
    private static List<MedicalProduct> data = supplyProducts();
    public static List<MedicalProduct> supplyProducts(){
        Random random = new Random();
        List<MedicalProduct> medicalProducts = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            char ch = (char) (65 + random.nextInt(26));
            String description = String.valueOf(ch) + (10 + random.nextInt(91));
            medicalProducts.add(new MedicalProduct(description, ProductCategory.values()[random.nextInt(5)], (10 + random.nextInt(91)), 100*random.nextDouble()));
        }
        return medicalProducts;
    }

    public static boolean tooManyUndefined(){
        int e = (int) data.stream().filter((m) -> m.getCategory() == ProductCategory.E).count();
        int els = (int) data.stream().filter((m) -> m.getCategory() != ProductCategory.E).count();
        return els/(e*100.0) > 0.1;
    }

    public static void sortPerCategory(ProductCategory category){
        List<MedicalProduct> list = data.stream().filter((m) -> m.getCategory() == category)
                .sorted((m1,m2)-> Integer.compare(Integer.parseInt(m2.getINV_NUMBER()),Integer.parseInt(m1.getINV_NUMBER())))
                .collect(Collectors.toList());
        for(MedicalProduct m : list){
            System.out.println(m.toString());
        }
    }

    public static boolean comparePrice(ProductCategory pCategory){
        double average1 = data.stream().filter((m) -> m.getCategory() == pCategory).mapToDouble(MedicalProduct::getPrice).average().getAsDouble();
        double average2 = data.stream().filter((m) -> m.getCategory() != pCategory).mapToDouble(MedicalProduct::getPrice).average().getAsDouble();
        return average1 < average2;
    }

    public static void groupByCategory(){
        Map<ProductCategory, Long> s = data.stream().collect(Collectors.groupingBy(MedicalProduct::getCategory, Collectors.counting()));
        for(Map.Entry<ProductCategory,Long> entry : s.entrySet()){
            System.out.printf("Category %s[%s] contains %d products\n", entry.getKey().toString(), entry.getKey().getCategory(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("a) tooManyUndefined()");
        System.out.println(tooManyUndefined());
        System.out.println("b) sortPerCategory()");
        sortPerCategory(ProductCategory.E);
        System.out.println("c) comparePrice()");
        System.out.println(comparePrice(ProductCategory.E));
        System.out.println("d) groupByCategory()");
        groupByCategory();
    }

}
