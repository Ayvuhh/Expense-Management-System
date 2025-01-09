# **Expense Management System**

The Expense Management System is a robust and user-friendly application designed to help users track, manage, and analyze their financial expenses. This project is built using Java, following modular and object-oriented programming principles to ensure maintainability and scalability.

# **Key Features**

Expense Tracking: Record expenses with details such as amount, category, currency, and date.

Category Management: Organize expenses into predefined or custom categories for better financial insights.

Currency Support: Handle expenses in different currencies, ensuring flexibility for users with international transactions.

Date Management: Automatically record the date of each expense, enabling time-based analysis and reporting.

Modular Architecture: The project structure ensures clear separation of concerns between data models, user interface, and business logic.

## **Project Structure**

The application follows a well-organized structure, with each component serving a specific purpose:

#### **Model Layer (src/model):**

**Expense.java:** Represents an individual expense, including details like amount, category, currency, and date.

**Category.java:** Manages predefined or custom expense categories.

**Currency.java:** Handles different currency types and related operations.

#### **UI Layer (src/ui):**

Contains components responsible for user interaction and graphical interfaces.

#### **Manager Layer (src/manager):**

Implements business logic for managing expenses, categories, and currencies.

#### **Dialog Layer (src/dialog):**

Provides utilities for user prompts and interactions.