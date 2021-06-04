package com.apinanyogaratnam;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<User> allUsers = new LinkedList<>();
        LinkedList<Company> allCompanies = new LinkedList<>();

        createNewUser("Apinan", "Yogaratnam", "apinanyogaratnam", allUsers);
        createNewCompany("McDonald's", allCompanies);
    }

    public static User createNewUser(String firstName, String lastName, String username, LinkedList<User> allUsers) {
        Print printClass = new Print();
        User newUser = new User(firstName, lastName, username, allUsers);
        if (!newUser.createdSuccessfully) return null;
        newUser.print(allUsers);

        return newUser;
    }

    public static Company createNewCompany(String name, LinkedList<Company> allCompanies) {
        Print printClass = new Print();
        Company newCompany = new Company(name, allCompanies);
        newCompany.print(allCompanies);

        return newCompany;
    }

}
