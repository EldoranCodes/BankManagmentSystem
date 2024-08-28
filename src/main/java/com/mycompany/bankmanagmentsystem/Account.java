/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagmentsystem;



/**
 *
 * @author Eldoran
 */
public class Account {
    private int accountID;
    private double Accountbalance;
    private String lastName;
    private String firstName;
    private String middleName;
    private String emailAddress;
    private int PIN;
    
    public Account() {
        this.accountID = 0; // or another default value
        this.Accountbalance = 0.0;
        this.lastName = "";
        this.firstName = "";
        this.middleName = "";
        this.emailAddress = "";
        this.PIN = 0;
}

    public Account(int accountID, double balance, String lastName, String firstName, String middleName, String emailAddress, int PIN) {
        this.accountID = accountID;
        this.Accountbalance = balance;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.emailAddress = emailAddress;
        this.PIN = PIN;
    }

    public int getAccountID() {
        return accountID;
    }

    public double getAccountBalance() {
        return Accountbalance;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getPIN() {
        return PIN;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setBalance(double balance) {
        this.Accountbalance = balance;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    @Override
    public String toString() {
        return "Account{" + "accountID=" + accountID + ", balance=" + Accountbalance + ", lastName=" + lastName + ", firstName=" + firstName + ", middleName=" + middleName + ", emailAddress=" + emailAddress + ", PIN=" + PIN + '}';
    }
    
    
    
}
