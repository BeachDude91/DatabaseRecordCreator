# Database Record Creator

**Author:** Nicholas MacFarland

---

## Description of This Repo

### The purpose of this repo is:
1. To get records from a CSV file and use SQLite to insert the good records into a database (`.db`) file.
2. Statistics will be written to a log file of the total number of records received, the total valid/successful, and the total invalid.
3. A "bad-record" CSV file will be created. The conditions that kick the records out as bad include the two header records, missing first name, or missing last name.
4. All the remaining good records are written to the database (`.db`) file.

---

## Steps for Getting This App Running

### 1. Download and Configure the SQLite JAR File
You need to get the JAR file that lets the coder use SQLite to execute SQL statements, create a table, and insert the good records into the database (`.db`).

* **Download the SQLite JAR file:** [Xerial SQLite JDBC Downloads](https://bitbucket.org/xerial/sqlite-jdbc/downloads/)

**Setup Instructions:**
* Right-click on your project.
* Go to **Build Path** > **Configure Build Path**.
* Go to the **Libraries** tab.
* Click on **Add External JARs**.
* Select the JAR file you downloaded and click **Apply**.

### 2. Create the `DBEntry` Class
Create a class named `DBEntry` that lets the coder get the column field description and set the specified columns with a field description. The coder can also use either the Empty Constructor or the Full Constructor if needed.

### 3. Select the Input CSV File Path
Use `JFileChooser` to get the proper path for the input CSV file via a pop-up dialogue box. The console window tells the user what the dialogue box is meant to search for.

### 4. Connect to the Database
The connection to the new database (`.db`) file is created with a method that gets the connection for the file. It uses a `try/catch` block to catch any `SQLException` or `ClassNotFoundException`.

### 5. Create the Database Table
Before inserting good records into the database (`.db`) file, a table needs to be created:
* The `createTable` method gets the connection to the file and creates an SQL statement with a total of 10 columns (`A, B, C, D, E, F, G, H, I, J`).
* All columns are `TEXT` data types because they hold the same data imported from the CSV file containing all the bad and good records.
* It executes the update with the SQL statement inside a `try` block, printing a stack trace if an issue occurs.

### 6. Process Records and Generate Logs
The next method uses an instance of the `DBEntry` class to set the values of each column and insert valid records using a prepared statement:
* **Good Records:** Added directly to the database (`.db`) file.
* **Bad Records:** Placed into the `bad-CSV-Records` CSV file by calling the `badCSVFileLoader` method, which uses an `ArrayList` to write values with a comma delimiter. Commas inside quoted fields are ignored as delimiters.
* **Statistics Tracking:** 
  * `incorrectRecords` tracks the number of bad records.
  * `totalRecords` tracks the total records imported.
  * Successful records are calculated by subtracting `incorrectRecords` from `totalRecords`.
  * These statistics are written to a statistics log file.

### 7. Disconnect and Terminate
The `disconnect` method uses a `try/catch` block to check if the connected database isn't null, then closes the connection and terminates the program.

---

## Overview of Approach, Design Choices, and Assumptions

* **Modularity & Usability:** I created the `DBEntry` class so I could easily get and use column data wherever needed. To keep `main` easy to read, I created descriptive method names that explain what each function does. I also used a file chooser so users can select any CSV file path regardless of differences across computers.
* **Method Orchestration:** The `addCSVToDatabase` method calls three core helper methods: one to filter and write bad records to a CSV file, one to generate and print the statistics log, and one to insert good records into the database (`.db`) file.
* **Data Validation Assumptions:** I assumed that bad records included the header letters of the specified columns, or records missing either a first name or a last name (assuming a valid person record requires both a first and last name).
* **User Feedback:** For console output prompts, I wanted the user to know exactly what succeeded at each step. The program prints instructions explaining what the dialogue box is looking for, followed by confirmations when the `.db` file is created, the table is created, and the records are successfully inserted.
