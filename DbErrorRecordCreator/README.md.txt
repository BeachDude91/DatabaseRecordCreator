  ==========================================================================================
  ================ Database Record Creator README.md =======================================
  ==========================================================================================

			***************************************
			***** Author: Nicholas MacFarland *****
			***************************************

=====================================
===== Description of this repo: =====
=====================================

    -------------------------------------------
    ----- The purpose of this repo is: --------
    -------------------------------------------
	 a.)To get records from a csv file; using sqlite to 
	      insert the good records into a database(.db) file.
    	 b.)Statistics will be writen to a log file of the total number of records received; the total 
	      valid/successful, and the total invalid. 	      
	 c.)A "bad-record" csv file will be created.  The conditions that kick the records out as bad
              include the two header records, missing first name or last name.  
         D.)All the remaining good records are written to the database(.db) file.

   -------------------------------------------------
   ------ Steps for getting this app running: ------
   -------------------------------------------------
	a.)You needed to get the jar file that would let the coder use "SQLite"
	      to use sql statements to create a table and insert the good records 
	      within the table found in the database(.db).
		*The instructions and where I had found the proper jar file can be found here:
		       
			The site to find jar file for SQLite:
				https://bitbucket.org/xerial/sqlite-jdbc/downloads/

			Instructions found below:
				* Right Click on your project
				* Go to 'Build Path'
				* Go to 'Configure Build Path'
				* Go to 'Libraries' tab
				* Click on 'Add External JARs'
				* Select JAR file you downloaded and click apply.

	b.)What was first created was a class named, "DBEntry" which lets the coder get
	      the column field description and set the specified columns with a field 
	      description. The coder can also use either the Empty Constructor or the
	      Full Constructor if needed.
	c.)You needed to figure out how to get the proper path for the input csv file.
              I used "JFileChooser" to get the path within a Dialogue Box   
	      that pops up. The Console window tells the user what the Dialogue box is
	      meant to search for.
	d.)The connection to the new database(.db) file is created with a method that gets
	      the connection for the file. It then uses a try/catch to catch if there is an
	      SQLException or an ClassNotFoundException.
	e.)Before inserting good records into the database(.db) file; a table is needing
	      to be created. The method "createTable" gets the connection to the file and
              creates an sql statement that has a total of 10 columns, "A,B,C,D,E,F,G,H,I,J". 
	      All of the columns are text data types because they hold the same data when 
	      imported from the csv file containing all the bad and good records. It then 
	      executes the update with the sql statement. The method tries the sql; if there
	      is an issue with the sql, it prints a stack trace.
	f.)The next method that has an instance of the "DBEntry" class that sets the values of
	      each column and inserts the records by calling the insert function that then 
	      gets the column value and places it into the prepared statement. It adds the CSV
	      file records that are good to the database(.db) file; and places the bad records
	      into the "Bad-Records" csv file by calling the "badCSVFileLoader" method that
	      creates the "bad-CSV-Records" csv file; uses an ArrayList to add the values
	      to the bad records csv file with a delimiter that is a comma. By default; a
	      new column is a comma as its delimiter. In some cases; a field would be a quoted
	      field that held a comma within its field. The commas within these quotes were
	      ignored as being a comma delimiter. The incorrectRecords counter adds a
	      count to the amount of records that were bad. The totalRecords counter receives
	      the total records being imported, and the successful records is subtracting the
	      totalRecords counter to the incorrectRecords counter. These statistics are
	      printed to the statistics-log method by calling it and then the log is created.
	g.)The method "disconnect" uses a try/catch to check if the connected db isn't null;
	      then it closes the connection, terminating the program.

   --------------------------------------------------------
   ------ Overview of your approach, design choices, ------
   ------               and assumptions:             ------
   --------------------------------------------------------
	a.)I chose to create a class; so I could choose to get the column and to use it
	      wherever it was needed. I wanted the program to be easy to read within
	      main; so I decided to create methods that explains what they do in the
	      method name. I wanted the user to be able to choose any kind of path
	      for the csv file that held the records because not all paths are the same
	      on all computers.
	b.)The addCSVToDatabase method calls three methods; one of which gets the bad records;
	      and place them within the bad record csv file. Another is creating the
	      statistics log and printing the statistics to it. The last one is inserting the
	      good records to the database(.db) file.
	c.)I assumed that the records that were bad had the header letters of the specified
	      column. The rest of the other bad records held a first name, but not a
	      last name. Other bad records had a last name, but not a first name. Assuming
	      that a person has to have a first and last name for it to be a good record.
	d.)My design choice for the output prompt when the program is ran; I wanted the user
	      to know exactly what has been successful, so I put instructions for what the
	      dialogue box was meant for. Another is telling the user that the .db file was
	      created; the table was created, and that the information was inserted into the
	      database(.db) file.