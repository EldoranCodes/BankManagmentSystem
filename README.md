# Simple Banking Management System

## Tech Stack
- **Language:** Java
- **Database:** SQLite
- **IDE:** NetBeans
- **Database Tool:** DB Browser for SQLite

## Features
- **User Registration:** 
  - Validates input to ensure all required fields are correctly filled.
- **Secure Login:** 
  - Log in using an ID and a secure PIN (numeric only).
- **Account Management:**
  - **View Account Balance:** Displays the current balance in the user’s account.
  - **Deposit Funds:** Ensures that the amount to be deposited is divisible by 100. Validates that the input is a numeric value.
  - **Withdraw Funds:** Similar to deposit functionality, it checks that the amount to be withdrawn is divisible by 100 and validates numeric input.
  - **Change PIN:** Allows users to securely change their PIN, verifying the new PIN before updating it.
  - **Close Account:** Only allows account closure if the balance is zero, ensuring no outstanding funds.
  - **Log Out:** Prompts the user with a Yes/No confirmation before logging out.

## Learnings
- **SQLite Integration:** 
  - Learned to connect Java applications with SQLite using JDBC.
  - Gained experience in executing SQL queries for CRUD operations within a Java application.
- **MVC Design Pattern:**
  - Applied the Model-View-Controller architecture to separate concerns, making the application more modular and maintainable.
  - Improved understanding of how to structure code for better scalability and reusability.
- **Object-Oriented Programming (OOP):**
  - Deepened understanding of OOP concepts such as encapsulation, inheritance, and polymorphism.
  - Implemented abstract classes and interfaces to create a flexible and extendable codebase.
- **Validation and Error Handling:**
  - Implemented input validation and error handling to ensure data integrity and improve user experience.
  - Developed custom error messages to guide users when incorrect data is entered.
- **Swing for GUI Development:**
  - Created a user-friendly graphical interface using Swing, enhancing the application’s usability.
  - Worked with components like `JOptionPane`, `JButton`, and `JLabel` to build an interactive UI.
- **Version Control:**
  - Gained experience using GitHub for version control, including committing changes, branching, and writing meaningful commit messages.
  - Created a GitHub repository to showcase the project and track progress.
