/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagmentsystem;

import java.util.Scanner;

/**
 *
 * @author Eldoran
 */
public class testing {
    
        
    public void addAccount(){
        Scanner sc = new Scanner(System.in);

        Controller controller = new Controller();
        controller.createDatabase();
        
        System.out.println("ADD ACCOUNT");
       
        
        System.out.print("enter an initial account balance: ");
        double accBal = sc.nextDouble();
        sc.nextLine();
        System.out.println("enter the last name:");
        String lastName = sc.nextLine();
        System.out.println("enter the first name:");
        String firstName =sc.nextLine();
        System.out.println("enter the middle name:");
        String middleName = sc.nextLine();
        System.out.println("enter the email address:");
        String emailadd = sc.nextLine();
        
        int defaultPIN = 123456;
        Account ac = new Account(0,accBal,lastName,firstName,middleName,emailadd,defaultPIN);
        
        //call the method
        
        controller.insertAccount(ac);
    }
    
}
