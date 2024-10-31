# Simple Produts Register for Portfolio

# @author - Bossa Web Solutions

## Installation

The project is a REST API to be consumed by the Angular Project.
It was built with JDK 17, Spring, and H2 Database.
The users are in-memory saved. For testing (after building and running this project, and the Angular frontend project), I have created 2 users.

Steps to test the system:

1. Access localhost:4200.
2. Use the following credentials:
   - User: user
   - Password: 1234
   - Admin: admin
   - Password: admin
3. After logging in with "user", verify that this one can only view the product's list.
4. After logging in with "admin", verify and test the features: Update, Delete, and Insert new products.
