# 💳 Banking App with Spring Boot

A simple yet powerful banking application built using the Spring Boot framework. It allows users to manage accounts, perform transactions, and view transaction history — all with proper exception handling and clean RESTful APIs.

---

## 🚀 Features

- **Account Creation**: Create new bank accounts with a unique account number.
- **Fetch Account**: Retrieve details of any account using the account ID.
- **Fetch All Accounts**: Retrieve a list of all existing bank accounts.
- **Deposit**: Deposit money into an account.
- **Withdrawal**: Withdraw money from an account, ensuring sufficient balance.
- **Delete Account**: Delete any account by its ID.
- **Transaction History**: View detailed transaction history with timestamps for each account.
- **Exception Handling**: Graceful handling of all types of exceptions (e.g., insufficient balance, invalid account ID, etc.).

---

## 🛠️ Technologies Used

- **Spring Boot**: For building robust REST APIs and managing application configurations.
- **Spring Data JPA**: To handle ORM and database operations.
- **MySQL Database**: For persisting account and transaction data.
- **Lombok**: To eliminate boilerplate code (getters, setters, constructors, etc.).

---


## 📂 API Endpoints Overview

| Method | Endpoint                          | Description                       |
|--------|-----------------------------------|-----------------------------------|
| POST   | `/api/bankfz/createAccount`       | Create a new account              |
| GET    | `/api/bankfz/{id}`                | Get details of a specific account |
| GET    | `/api/bankfz/admin/accounts`      | Get list of all accounts          |
| PUT    | `/api/bankfz/{id}/deposit`        | Deposit amount into account       |
| PUT    | `/api/bankfz/{id}/withdraw`       | Withdraw amount from account      |
| DELETE | `/api/bankfz/{id}`                | Delete an account                 |
| GET    | `/api/bankfz/{id}/transactions`   | Get transaction history           |
| PUT    | `/api/bankfz/update`              | Updates an account                |

---

## 🧑‍💻 Author

**Faizan Khan**  
> Passionate about backend development and creating efficient, clean, and scalable applications.

---
