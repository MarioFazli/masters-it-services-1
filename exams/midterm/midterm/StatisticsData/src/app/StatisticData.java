package app;

import lib.MedicinalProduct;
import lib.ProductCategory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StatisticData {
    private static final List<MedicinalProduct> data = supplyProducts();
    public static List<MedicinalProduct> supplyProducts(){
        List<MedicinalProduct> list = new ArrayList<>();
        Random random = new Random();
        for(int i =0; i < 30; i++){
            // ASCII codes for A - Z = 65 - 90
            String description = String.valueOf((char)(65 + random.nextInt(26))) + (10 + random.nextInt(90));
            ProductCategory category = ProductCategory.values()[random.nextInt(5)];
            // bound 90 resolving into 89 so [10, 99]
            int qty = (10 + random.nextInt(90));
            // nextDouble() ranges between 0 and 1 so [0, 100]
            double price = 100*random.nextDouble();
            list.add(new MedicinalProduct(description, category, qty, price));
        }
        return list;
    }

    public static void sortList(){
        // if there are only unique categories in data then .distinct() length will be equal
        // to data length otherwise it contains duplicate categories
        if(data.stream().map(MedicinalProduct::getCategory).distinct().count() < data.stream().map(MedicinalProduct::getCategory).count()){
            System.out.println("Duplicate categories:");
            data.stream().sorted(Comparator.comparing(MedicinalProduct::getCategory))
                    .collect(Collectors.toList()).forEach(System.out::println);
        }
        else{
            System.out.println("No duplicate categories:");
            data.stream().sorted(Comparator.comparing(MedicinalProduct::getPrice).reversed())
                    .collect(Collectors.toList()).forEach(System.out::println);
        }
    }

    public static void computeTotalCost(ProductCategory category){
        data.stream().filter((m) -> m.getCategory() == category).map((m) -> m.getPrice() * m.getQty()).forEach((m) -> System.out.printf("Total cost: %.2f\n", m));
    }
    public static void sortPerCategory(ProductCategory category){
        List<MedicinalProduct> list = data.stream().filter((m)-> m.getCategory() == category)
                .sorted((m1,m2)-> Integer.compare(Integer.parseInt(m2.getINV_NUMBER()),Integer.parseInt(m1.getINV_NUMBER()))).collect(Collectors.toList());
        list.stream().map(MedicinalProduct::toString).forEach(System.out::println);
    }

    public static void groupByCategory(){
        data.stream().collect(Collectors
                        .groupingBy(MedicinalProduct::getCategory, Collectors.counting()))
                .forEach((medicinalProduct,count) -> System.out.printf("Category %s[%s} contains %d products.\n", medicinalProduct.toString(), medicinalProduct.getCategoryName(),count));
    }

    public static void main(String[] args) {
        System.out.println("a) sortList()");
        sortList();
        System.out.println("\nb) sortPerCategory()");
        sortPerCategory(ProductCategory.E);
        System.out.println("\nc) computeTotalCost()");
        computeTotalCost(ProductCategory.E);
        System.out.println("\nd) groupByCategory");
        groupByCategory();



    }
}
