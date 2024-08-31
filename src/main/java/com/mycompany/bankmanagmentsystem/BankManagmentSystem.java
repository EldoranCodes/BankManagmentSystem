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
        
        LogInForm lf = new LogInForm(controller);
        
        lf.setVisible(true);
    }
}
