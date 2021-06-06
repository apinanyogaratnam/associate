package com.apinanyogaratnam;

import java.util.LinkedList;
import java.sql.*;

public class Main {
    private static MainHelper helperMethods = new MainHelper();
    private static Print printClass = new Print();
    private static Secrets secrets = new Secrets();
    private static SQL sql = new SQL();

    public static void main(String[] args) {
        LinkedList<User> allUsers = new LinkedList<>();
        LinkedList<Company> allCompanies = new LinkedList<>();

        loadDBUserData(allUsers);
        loadDBCompanyData(allCompanies, allUsers);
        createNewCompany("Apple", allCompanies);
        printClass.print(allUsers);
        printClass.printCompanies(allCompanies);
    }

    public static User createNewUser(String firstName, String lastName, String username, LinkedList<User> allUsers) {
        if (helperMethods.isValidUser(username, allUsers)) return null;
        if (firstName == null || lastName == null) return null;
        User newUser = new User(firstName, lastName, username, allUsers);

        // if user already exists, nothing happens
        sql.addObjectToDB(newUser);

        return newUser;
    }

    public static Company createNewCompany(String name, LinkedList<Company> allCompanies) {
        if (helperMethods.isValidCompany(name, allCompanies)) return null;
        Company newCompany = new Company(name, allCompanies);

        // if company already exists, nothing happens
        sql.addObjectToDB(newCompany);

        return newCompany;
    }

    public static void loadDBUserData(LinkedList<User> allUsers) {
        try {
            // connect to database
            Connection connection = DriverManager.getConnection(secrets.url, secrets.username, secrets.password);

            // create a statement
            Statement statement = connection.createStatement();

            // execute SQL query
            String query = "SELECT * FROM users";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String username = result.getString("username");
                String friends = result.getString("friends");

                User user = createNewUser(firstName, lastName, username, allUsers);
                if (user != null) user.addFriends(friends, allUsers);
            }

            connection.close();
        } catch (Exception e){
            e.printStackTrace();
            printClass.print("Unable to load users into allUsers");
        }
    }

    public static void loadDBCompanyData(LinkedList<Company> allCompanies, LinkedList<User> allUsers) {
        try {
            // connect to database
            Connection connection = DriverManager.getConnection(secrets.url, secrets.username, secrets.password);

            // create a statement
            Statement statement = connection.createStatement();

            // execute SQL query
            String query = "SELECT * FROM companies";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String name = result.getString("name");
                String networkList = result.getString("network_list");
                String followersList = result.getString("followers_list");
                Company company = createNewCompany(name, allCompanies);

                if (company != null) {
                    company.addNetworks(networkList, allCompanies);
                    company.addFollowers(followersList, allUsers);
                }

            }

            connection.close();
        } catch (Exception e){
            e.printStackTrace();
            printClass.print("Unable to load users into allUsers");
        }
    }


}
