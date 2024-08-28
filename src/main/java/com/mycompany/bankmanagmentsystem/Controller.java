/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagmentsystem;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eldoran
 */
public class Controller {
    Account loggedInAccount = new Account();
    
    //DATABASE METHODS  
    final String userHomeDir = System.getProperty("user.home");
    final String databaseName = "BankManagementSystemDB.db";
    final  String databaseURL = "jdbc:sqlite:" + userHomeDir +"/"+ databaseName;
    public boolean isDBExist(){
         //check if database exist
        File databaseFile = new File(userHomeDir+"/"+databaseName);
        return databaseFile.exists();
    }

   
    public void createDatabase() {
        if (!isDBExist()) {
            // Create the database with tables
            try (Connection conn = DriverManager.getConnection(databaseURL)) {
                if (conn != null) {
                    System.out.println("Connected to the database.");

                    // SQL query to create accounts table
                    String createAccountTable = "CREATE TABLE IF NOT EXISTS accounts (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "accBalance REAL DEFAULT 0," +
                            "last_name TEXT NOT NULL," +
                            "first_name TEXT NOT NULL," +
                            "middle_name TEXT NOT NULL," +
                            "email TEXT NOT NULL UNIQUE," +
                            "PIN INTEGER NOT NULL" +  // Added PIN field
                            ");";

                    // SQL query to create transactionHistory table
                    String createTransacTable = "CREATE TABLE IF NOT EXISTS transactionHistory(" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "account_id INTEGER NOT NULL," +
                            "currentBal REAL NOT NULL," +
                            "type TEXT NOT NULL," +
                            "amount REAL NOT NULL," +
                            "date TEXT NOT NULL," +
                            "FOREIGN KEY (account_id) REFERENCES accounts(id)" +
                            ");";

                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(createAccountTable);
                        System.out.println("Accounts table created successfully.");

                        stmt.execute(createTransacTable);
                        System.out.println("TransactionHistory table created successfully.");

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }           
        } else {
            System.out.println("Database already exists!");
        }
    }
    
    //account management
    public String insertAccount(Account acc) {
        String insertAccountSQL = "INSERT INTO accounts (accBalance, last_name, first_name, middle_name, email, PIN) " +
                                   "VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(insertAccountSQL)) {

            pstmt.setDouble(1, acc.getAccountBalance());
            pstmt.setString(2, acc.getLastName());
            pstmt.setString(3, acc.getFirstName());
            pstmt.setString(4, acc.getMiddleName());
            pstmt.setString(5, acc.getEmailAddress());
            pstmt.setInt(6, acc.getPIN());

            pstmt.executeUpdate();
            return "Account inserted successfully!";

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed to insert account: " + ex.getMessage();
        }
    }

    public String removeAccount(Account acc){
       
        String deleteAccQuery = "DELETE FROM accounts WHERE id=?";      
        try(Connection conn = DriverManager.getConnection(databaseURL);
            PreparedStatement pstmt = conn.prepareStatement(deleteAccQuery);){
            
            pstmt.setInt(1,acc.getAccountID());
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0){
                return "Account is deleted successfully!";
            }else return"No Account found with the provided ID";            
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
             return"Database Error in Deleting Account";
        }
    }
    public void updateAccount(Account acc) {
    String updateAccQuery = "UPDATE accounts SET accBalance = ?, last_name = ?, first_name = ?, middle_name = ?, email = ?, PIN = ? WHERE id = ?";
    
        try (Connection conn = DriverManager.getConnection(databaseURL);
             PreparedStatement pstmt = conn.prepareStatement(updateAccQuery)) {

            // Set the parameters from the Account object
            pstmt.setDouble(1, acc.getAccountBalance());
            pstmt.setString(2, acc.getLastName());
            pstmt.setString(3, acc.getFirstName());
            pstmt.setString(4, acc.getMiddleName());
            pstmt.setString(5, acc.getEmailAddress());
            pstmt.setInt(6, acc.getPIN());          
            pstmt.setInt(7, acc.getAccountID());


            // Execute the update
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Account updated successfully!");
            } else {
                System.out.println("No account found with the provided ID.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //other methods  
    public String addBalance(Account acc, double amount) {
        if (amount > 0) {
             acc.setBalance(acc.getAccountBalance() + amount);
             // Call the updateAccount method to update the database
             updateAccount(acc);
             return "Balance added successfully!";
        } else {
             return "Amount must be positive.";
        }
    }
    
    public String reduceBalance(Account acc, double amount) {
        if (amount > 0) {
            if (acc.getAccountBalance() >= amount) {
                acc.setBalance(acc.getAccountBalance() - amount);
                // Call the updateAccount method to update the database
                updateAccount(acc);
               return "Balance deducted successfully!";
            } else {
              return"Insufficient balance.";
            }
        } else {
            return"Amount must be positive.";
        }
    }
    
    public String validateLogIn(int id, int PIN) {
        String query = "SELECT * FROM accounts WHERE id=? AND PIN=?";
        try (Connection conn = DriverManager.getConnection(databaseURL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the parameters for the prepared statement
            pstmt.setInt(1, id);
            pstmt.setInt(2, PIN);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Check if a result was returned
            if (rs.next()) {
                // If a record is found, return a success message or user details
                return "Login successful. Welcome " + rs.getString("first_name") + "!";
            } else {
                // If no record is found, return an error message
                return "Invalid ID or PIN. Please try again.";
            }

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return "An error occurred while trying to log in.";
        }
    }
    
    public String registerAccount(String lastname,String firstname, String middlename,String email){
        int accID = 0; //the id is auto increment
        double initialBal = 0;
        int defaultPIN = 123456;
        
        Account create = new Account(accID,initialBal,lastname,firstname,middlename,email,defaultPIN);
        
        String result = insertAccount(create);
        
        String returnResult = result + "! \n\n"+"Your ID: "+getNextId()+"\n"+" Default PIN: 123456" ;
        return returnResult;
        
    }
    
     public int getNextId() {
        int nextId = 1; // Default to 1 if the table is empty
        try (Connection conn = DriverManager.getConnection(databaseURL);
             Statement stmt = conn.createStatement()) {

            // Query the maximum existing ID
            String query = "SELECT MAX(id) FROM accounts";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                nextId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }
     
     
  
    public void logInAccount(int id) {
      // Ensure loggedInAccount is initialized
      if (loggedInAccount == null) {
          loggedInAccount = new Account();
      }

      // SQL query to pull details of accountID from the database
      String query = "SELECT * FROM accounts WHERE id = ?";

      try (Connection conn = DriverManager.getConnection(databaseURL);
           PreparedStatement pstmt = conn.prepareStatement(query)) {

          // Set the ID parameter in the query
          pstmt.setInt(1, id);

          // Execute the query
          ResultSet rs = pstmt.executeQuery();

          // Check if the ResultSet has data
          if (rs.next()) {
              // Populate loggedInAccount with data from the database
              loggedInAccount.setAccountID(rs.getInt("id"));
              loggedInAccount.setBalance(rs.getDouble("accBalance"));
              loggedInAccount.setLastName(rs.getString("last_name"));
              loggedInAccount.setFirstName(rs.getString("first_name"));
              loggedInAccount.setMiddleName(rs.getString("middle_name"));
              loggedInAccount.setEmailAddress(rs.getString("email"));
              loggedInAccount.setPIN(rs.getInt("PIN"));
              System.out.println("Account has been Successfully set");
          } else {
              // Handle case where no account with the given ID exists
              System.out.println("No account found with ID: " + id);
          }

      } catch (SQLException ex) {
          Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("Error adding logged In Account" + ex);
      }
    }
    
    public String changePin(int newPIN){  
        loggedInAccount.setPIN(newPIN);
        updateAccount(loggedInAccount);  
        return "Changing of PIN is Successfull";
    }
        
    public String closeAccount(){
        //must be acc = 0
             
       String result =  removeAccount(loggedInAccount);
        return result;
    }
//end of the controller class
}
