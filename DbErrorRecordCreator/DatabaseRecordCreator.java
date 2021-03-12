/*
  * Nicholas MacFarland
  * December 16, 2019 
  * Database Record Creator
  * DatabaseRecordCreator.java file
*/

import java.io.*;																
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;


public class DatabaseRecordCreator
{
	/*****************
	 ** Main Begins **
	 *****************/

	public static void main(String[] args) 
	{
		JFileChooser chooser = new JFileChooser();					//Where user searches for 
		System.out.println("\t~~~~~~~ Instructions and what has "				//csv file and giving
				+ "been successful found below: ~~~~~~~"						//instructions for user
				+ "\n\t   ~~~~~~~ Choose the "	    
				+ "path for the csv file in 'Dialog Box' ~~~~~~~");
		
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
		String csvFilePath = f.getAbsolutePath();							//getting path of csv file
		
		System.out.println("This is the path for csv file:\n" 				//printing instructions to
				+ csvFilePath + "\n");											//console
		
		Connection db = connect("Junior_Challenge_Table.db");				//creating and connecting to
		System.out.println("\t- .db file created...");  						//the ".db" file
							    												//stating that this file
																				//was created.
		createTable(db);
		System.out.println("\t- Table was created to '.db' file...");		//creating and printing that the
																				//was created to db file
		
		addCSVToDatabase(db,csvFilePath);									//inserting good records to db
		System.out.println("\t- Information inserted to '.db' file and "		//file and bad to bad-records
				+ "'bad-csv-record.csv file'\n");								//file. Printing to console
																				//to show confirmation.
		
		disconnect(db);														//disconnecting the db file.
		
	}
	
	/*****************
	 ** Main Ends ****
	 *****************/
	
	/*-------------------------------*/
	
	/*********************
	 ** Methods Begin ****
	 *********************/
	
	
	//This method connects the db file by getting the connection.
    private static Connection connect(String path) 
    {
        String url = "jdbc:sqlite:" + path;						//getting the url db file path
        
        Connection conn = null;									//initializing the connection to null
        															//at start
        try 
        {
            Class.forName("org.sqlite.JDBC");					//using sqlite
            
            conn = DriverManager.getConnection(url);			//getting connection for the db file.
           
        } catch (SQLException e) 								//catching errors
        	{ e.printStackTrace();} 
          catch (ClassNotFoundException e)
        	{ e.printStackTrace(); }
        
        return conn;											//returning the connection to use it.
    }
    
    //The disconnect method closes the connection when 
    	//the connection is not equal to null
    private static void disconnect(Connection c) 
    {
        try
        { 
        	if(c != null)										//if connection not equal to null
        	{ 
        		c.close(); 										//close connection
        	}
        } catch(SQLException e)									//catching errors
        	{ e.printStackTrace(); }
    }
	
    //The create table method uses the connection;
    	//creates the sql statement to create table,
    	//sets up by creating statement and executing it.
	private static void createTable(Connection c)
	{
        Statement stmt;
        String sql = "CREATE TABLE Junior_Challenge_Table"		//creating the sql statement to create
        		+ "("												//the table named "Junior_Challenge_Table"
                	+ "A    	TEXT NULL,"
                	+ "B        TEXT NULL,"
                	+ "C   		TEXT NULL,"
                	+ "D		TEXT NULL,"
                	+ "E		TEXT NULL,"
                	+ "F		TEXT NULL,"
                	+ "G		TEXT NULL,"
                	+ "H		TEXT NULL,"
                	+ "I		TEXT NULL,"
                	+ "J		TEXT NULL"
                + ");";		

        try 
        {
            stmt = c.createStatement();							//creating statement to the connection
            
            stmt.executeUpdate(sql);							//executing the sql statement
            
        } catch (SQLException e) 								//catching errors
        	{ e.printStackTrace();}
	}
	
	//This method adds the good records to the database(.db) file;
		//accumulates the statistics of total records received,
		//successful valid records, and invalid records. It calls
		//a method to create a log and print the statistics to it.
		//calls another method to place the bad records to the
		//bad-csv-record.csv file.
	private static void addCSVToDatabase(Connection c, String csvFile)
	{

        BufferedReader reader = null;
        

        DBEntry b;												//creating variable to use the DBEntry class
        															//that was created.
        
        String[] vals = null;									//This 'vals' array is used to import records
        
        int incorrectRecords = 0;								//initializing accumulators for incorrect
        int totalRecordsReceived = 0;								//records and total records received.
        
        String sql = "INSERT INTO Junior_Challenge_Table"		//creating the sql statement to insert
        		+ "("												//the good records to database(.db)
                	+ "A,"											//file.
                	+ "B,"
                	+ "C,"
                	+ "D,"
                	+ "E,"
                	+ "F,"
                	+ "G,"
                	+ "H,"
                	+ "I,"
                	+ "J"
                + ") values(?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement pstmt = null;

        try
        {
        	pstmt = c.prepareStatement(sql);					//preparing the sql statement to insert.
        	
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile)
            	,"UTF-8"));
 
            for(String line; (line = reader.readLine()) != null;)				//for how big the file is
            {
                
            	
            	totalRecordsReceived++;							//accumulating the total records received.
            	
            													//Each line takes the form: "A,B,C,D,E..."
            														//ignoring commas in fields...
            	vals = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            	
                if(((vals[0].contains("A")) && (vals[1].contains("B")) 				//if record is similar
                		&& (vals[2].contains("C")) && (vals[3].contains("D")) 			//to header column
                		&& (vals[4].contains("E")) && (vals[5].contains("F"))			//names, and first or
                		&& (vals[6].contains("G")) && (vals[7].contains("H")) 			//last name empty.
                		&& (vals[8].contains("I")) && (vals[9].contains("J"))) 
                		|| (vals[0].isEmpty()) || (vals[1].isEmpty()))
                {
                	badCSVFileLoader(vals);						//call bad csv file loader, place bad records
                													//in created csv file..
                	
                	incorrectRecords++;							//accumulate the incorrect records counter.
                }
                else											//else; use DBEntry class to set the field
                {													//description for that record
                	
	                b = new DBEntry();							//creating an instance of the DBEntry here.
	                b.setA(vals[0]);								
	                b.setB(vals[1]);							//setting the records to the specified 
	                b.setC(vals[2]);								//element of the vals array for each
	                b.setD(vals[3]);								//column.
	                b.setE(vals[4]);
	                b.setF(vals[5]);
	                b.setG(vals[6]);
	                b.setH(vals[7]);
	                b.setI(vals[8]);
	                b.setJ(vals[9]);
	                
	                insert(b, pstmt);							//calling insert method to insert the
	                												//values into the prepared statement
                }//end of if statement								//and then executing the prepared
                													//statement.
            }//end of for-loop										
            
            createStatisticsLog(totalRecordsReceived,incorrectRecords);	//calling the create statistics log
            																//method to create a log and
            																//print them to it.
            
        } catch (FileNotFoundException e)						//catching errors
        	{ e.printStackTrace();} 
        catch (UnsupportedEncodingException e) 
        	{ e.printStackTrace();}
        catch (IOException e) 
        	{ e.printStackTrace();} 
        catch (SQLException e)
        	{ e.printStackTrace();}
        finally													//finally; if prepared statement not
        {															//equal to null.
            if((pstmt != null))
            {
                try
                { 
                	pstmt.close(); 								//close the prepared statement
                }
                catch (SQLException e) 							//catching errors
                	{ e.printStackTrace();}
            }
        }
    }
	
	
	//This method gets the prepared statmenet and the instance of the DBEntry class
		//to use the getter of each column and insert it to the prepared statement
    private static void insert(DBEntry b, PreparedStatement pstmt) throws SQLException 
    {    	
        pstmt.setString(1,b.getA());      				//inserting each getter of a specified column
        pstmt.setString(2, b.getB());      					//and inserting it to the prepared statement.
        pstmt.setString(3, b.getC());
        pstmt.setString(4, b.getD());
        pstmt.setString(5, b.getE());
        pstmt.setString(6, b.getF());
        pstmt.setString(7, b.getG());
        pstmt.setString(8, b.getH());
        pstmt.setString(9, b.getI());
        pstmt.setString(10, b.getJ());

        pstmt.executeUpdate();							//executing prepared statement
    }

    //This method creates a log to place the statistics within it.
    private static void createStatisticsLog(int totalRecordsReceived, int incorrectRecords)
    {
																			// A Logger object is used
																				//to log messages for a 
    	Logger statisticsLogger = Logger.getLogger("StatisticsLog"); 	//specific system or 
    																			//application component.
    															
    	FileHandler statisticsFileHandler;							//setting a file handler
    			
    	try 
    	{
    	 
    																			  //Setting handler
    		statisticsFileHandler = new FileHandler("Statistics-Log.log", true);	//to true = append
    		statisticsLogger.addHandler(statisticsFileHandler);						//data to file.
    	 
    																	//Print a brief summary
    		SimpleFormatter formatter = new SimpleFormatter();				//of the log record in a
    		statisticsFileHandler.setFormatter(formatter);					//human readable format.
    	 
    		String message = "\nFollowing are the statistics:\n\t- "    //Log an INFO message.
    				+totalRecordsReceived +" records received.\n\t- "
    				+(totalRecordsReceived - incorrectRecords) 			//subtracting to get
    				+" records were successful.\n\t- "						//successful records
    				+incorrectRecords+" records were invalid.";

    		statisticsLogger.info(message);								//printing message
    				
    	} catch (SecurityException e) 									//catching errors
    		{e.printStackTrace();} 
    	  catch (IOException e) 
    		{e.printStackTrace();} 
    }
    
    
    //This method creates a new csv file; adds each record using an array list,
    	//and then closing the writer file.
    private static void badCSVFileLoader(String[] vals) throws IOException
    {
        String badCsvFile = "bad-CSV-Records.csv";						//creating bad csv record file name
        File newFile = new File(badCsvFile);
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile,true));	//append each record
        																				//and getting file
        
        List<String> badRecordArray = new ArrayList<>();					
        
        badRecordArray.add(vals[0]);									//adding records to each specified
        badRecordArray.add(vals[1]);										//column.
        badRecordArray.add(vals[2]);
        badRecordArray.add(vals[3]);
        badRecordArray.add(vals[4]);
        badRecordArray.add(vals[5]);
        badRecordArray.add(vals[6]);
        badRecordArray.add(vals[7]);
        badRecordArray.add(vals[8]);
        badRecordArray.add(vals[9]);

        String collect = badRecordArray.stream().collect(Collectors.joining(",")); //setting delimeter
        																				//to a comma.
        writer.write(collect);											//writing records to bad csv record
        																	//file.
        writer.write("\n");												//setting new line for a new record

        writer.close();													//closing the writer file
    }
    
	/*****************
	 ** Methods End **
	 *****************/
}