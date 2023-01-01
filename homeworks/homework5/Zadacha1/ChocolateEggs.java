package Zadacha1;

import java.util.ArrayList;
import java.util.Random;

public class ChocolateEggs {
    private static ArrayList<Integer> chooseEggs(double money){
        ArrayList<Integer> chosenEggs = new ArrayList<>();
        int tries = (int)Math.floor(money / 0.5);
        for(int i = 0; i < tries; i++){
            Random randomEgg = new Random();
            int chosenEgg = randomEgg.nextInt(10);
            chosenEggs.add(chosenEgg);
        }
        return chosenEggs;
    }

    private static double calculatePriceFor10Eggs(ArrayList<Integer> chosenEggs){
        int[] eggCounter = new int[10];
        for(Integer egg: chosenEggs){
            eggCounter[egg]++;
        }

        double price = 0;
        for(int count: eggCounter){
            if(count == 0){
                price += 0.5;
            }
            price += 0.5;
        }
        return price;
    }

    public static void main(String[] args) {
        double average = 0;
        int tries = 10000;
        for(int i = 0; i < tries; i ++){
            ArrayList<Integer> chosenEggs = chooseEggs(7);
            double price = calculatePriceFor10Eggs(chosenEggs);
            average += price;
        }
        System.out.printf("The price to buy all required pictures on average is: %.2f", average/tries);
    }
}
