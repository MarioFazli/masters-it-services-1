package app;

import lib.MedicinalProduct;
import lib.ProductCategory;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsData {
    private static final List<MedicinalProduct> data = supplyProducts();
    public static List<MedicinalProduct> supplyProducts(){
        List<MedicinalProduct> list = new ArrayList<>();
        Random random = new Random();
        for(int i =0; i < 30; i++){
            String description = String.valueOf((char)(65 + random.nextInt(26))) + (10 + random.nextInt(90));
            list.add(new MedicinalProduct(description, ProductCategory.values()[random.nextInt(5)]
                    , (10 + random.nextInt(90)), 100*random.nextDouble()));
        }
        return list;
    }

    public static boolean tooManyUndefined(){
        int eProducts = (int)data.stream().filter((m)-> m.getCategory() == ProductCategory.E).count();
        int otherProducts = (int)data.stream().filter((m)-> m.getCategory() != ProductCategory.E).count();

        return (eProducts/(otherProducts*1.0)*100.0) > 0.10;
    }

    public static void sortPerCategory(ProductCategory category){
        List<MedicinalProduct> list = data.stream().filter((m)-> m.getCategory() == category).sorted((m1,m2)-> m2.getINV_NUMBER().compareTo(m1.getINV_NUMBER())).collect(Collectors.toList());
        list.stream().map(MedicinalProduct::toString).forEach(System.out::println);
    }

    public static boolean comparePrice(ProductCategory pCategory){
        double average1 = data.stream().filter((m)-> m.getCategory() == pCategory).mapToDouble(MedicinalProduct::getPrice).average().getAsDouble();
        double average2 = data.stream().filter((m)-> m.getCategory() != pCategory).mapToDouble(MedicinalProduct::getPrice).average().getAsDouble();
        return average1 < average2;
    }

    public static void groupByCategory(){
        data.stream().collect(Collectors.
                groupingBy(MedicinalProduct::getCategory, Collectors.counting())).
                forEach((m,n) -> System.out.printf("Category %s[%s] contains %d products\n", m.toString(), m.getCategory(),n));
    }

    public static void main(String[] args) {
        System.out.println("a) tooManyUndefined()");
        System.out.println(tooManyUndefined());
        System.out.println("b) sortPerCategory()");
        sortPerCategory(ProductCategory.E);
        System.out.println("c) comparePrice()");
        System.out.println(comparePrice(ProductCategory.E));
        System.out.println("d) groupByCategory");
        groupByCategory();
    }



}
