/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zadacha4;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 * @author eck
 */
class Employee {
    private UUID еmployeeID;

    public static enum Gender {

        MALE, FEMALE
    }

    private String name;
    private Gender gender;
    private LocalDate dob;
    private double income;

    private Employee(String name, Gender gender, LocalDate dob,
            double income) {
        
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.income = income;
    }

    public Employee() {
        this.еmployeeID = UUID.randomUUID();
    }

    public double getIncome() {
        return income;
    }

    public static List<Employee> persons() {
        Employee p1 = new Employee( "Jake", Gender.MALE, LocalDate.of(1971,
                Month.JANUARY, 1), 2343.0);
        Employee p2 = new Employee("Jack", Gender.MALE, LocalDate.of(1972,
                Month.JULY, 21), 7100.0);
        Employee p3 = new Employee( "Jane", Gender.FEMALE, LocalDate.of(1973,
                Month.MAY, 29), 5455.0);
        Employee p4 = new Employee("Jode", Gender.MALE, LocalDate.of(1974,
                Month.OCTOBER, 16), 1800.0);
        Employee p5 = new Employee( "Jeny", Gender.FEMALE, LocalDate.of(1975,
                Month.DECEMBER, 13), 1234.0);
        Employee p6 = new Employee( "Jason", Gender.MALE, LocalDate.of(1976,
                Month.JUNE, 9), 3211.0);

        List<Employee> persons = Arrays.asList(p1, p2, p3, p4, p5, p6);

        return persons;
    }

    public static void statistics() {
        List<Employee> employees = persons();
        System.out.println("Sum of employees' income: " + employees.stream().mapToDouble(Employee::getIncome).sum());
    }

    public static void personsStatsByGenderCount(){
        List<Employee> employees = persons();
        System.out.println("Sum of female employees' income: " + employees.stream().filter((employee) -> employee.gender == Gender.FEMALE).mapToDouble(Employee::getIncome).sum());
        System.out.println("Sum of male employees' income: " + employees.stream().filter((employee) -> employee.gender == Gender.MALE).mapToDouble(Employee::getIncome).sum());
    }

    public static void personsStatsByGenderList(){
        List<Employee> employees = persons();
        System.out.println("\nFemale employees: ");
        employees.stream().filter((employee) -> employee.gender == Gender.FEMALE).forEach(employee -> System.out.println(employee.name));
        System.out.println("\nMale employees: ");
        employees.stream().filter((employee) -> employee.gender == Gender.MALE).forEach(employee -> System.out.println(employee.name));
    }

    @Override
    public String toString(){
        String gender = this.gender == Gender.MALE ? "male" : "female";
        return String.format("Employee details: %s, %s, %s, %.2f",еmployeeID.toString(), name, gender, income);
    }
}

