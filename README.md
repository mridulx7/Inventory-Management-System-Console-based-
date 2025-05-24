# Inventory Management System (Console-Based)

A simple Java console-based application for managing inventory using file storage (`inventory.txt`). It supports basic operations like adding, viewing, updating, and deleting products.

---

## Project Overview

This application allows a user to maintain an inventory list without the use of databases. Data is stored in a local file using Java I/O. It follows a modular design pattern using DAO, Model, and Utility classes.

---

## How to Run

### Prerequisites:
- Java 23 installed
- VS Code (with Java Extension Pack)
- Project set up with `launch.json` included

### Steps:
1. Open the project folder in VS Code.
2. Press `F5` or click `Run > Start Debugging`.
3. Select configuration: `Main`.
4. Use number options in the terminal to interact with the menu.

---

## Features

- ➕ Add new products
- 📋 View all products
- 📝 Update product details
- ❌ Delete a product
- 📄 Data stored in `inventory.txt` file
- 🔁 Persistent storage across sessions
- ✨ Console-based interactive UI with clean prompts

---

## File Structure
InventoryProject/
├── src/
│ ├── main/
│ │ └── Main.java
│ ├── dao/
│ │ └── ProductDAO.java
│ ├── model/
│ │ └── Product.java
│ └── util/
│ └── FileUtil.java
├── inventory.txt
├── .vscode/
  └── launch.json
