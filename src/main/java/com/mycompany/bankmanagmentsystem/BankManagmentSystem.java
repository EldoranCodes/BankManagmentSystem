/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bankmanagmentsystem;

import java.util.Scanner;

/**
 *
 * @author Eldoran
 */
public class BankManagmentSystem {
       
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.createDatabase();
//        Account testAcc = new Account(0,10000,"lastTest","firstTest","middleTest","emailTest",123456);
//        Account testAcc2 = new Account(0,5999,"lastTest2","firstTest2","middleTest2","emailTest2",123456);
//        control.insertAccount(testAcc);
//        control.insertAccount(testAcc2);
      
//       control.reduceBalance(testAcc2, 45);
//       control.addBalance(testAcc2, 45);
      // control.insertAccount(testAcc);
       //control.updateAccount(testAcc2);
       
//       testing test = new testing();        
//       test.addAccount();

        LogInForm lf = new LogInForm(controller);
        
        lf.setVisible(true);
    }
}
