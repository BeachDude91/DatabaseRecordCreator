/*
  * Nicholas MacFarland
  * December 16, 2019 
  * Database Record Creator
  * DBEntry class file
*/

public class DBEntry 
{
	private String columnA,columnB,columnC,columnD,columnE;					//creating instances for columns 
	private String columnF,columnG,columnH,columnI,columnJ;						//A through J
	
	public DBEntry()												//Empty Constructor found here
	{}
	
	public DBEntry(String a,String b, String c, String d, String e	//Full Constructor found here
			, String f, String g, String h, String i, String j)			//that initializes columns A though J
	{																	//to parameters for full constructor.
		columnA = a;
		columnB = b;
		columnC = c;
		columnD = d;
		columnE = e;
		columnF = f;
		columnG = g;
		columnH = h;
		columnI = i;
		columnJ = j;
	}
	
	//*** Getters and Setters found below ***
	public String getA() 
	{
		return columnA;
	}
	public void setA(String a) 
	{
		columnA = a;
	}

	public String getB() 
	{
		return columnB;
	}
	public void setB(String b) 
	{
		columnB = b;
	}

	public String getC() 
	{
		return columnC;
	}
	public void setC(String c) 
	{
		columnC = c;
	}

	public String getD() 
	{
		return columnD;
	}
	public void setD(String d) 
	{
		columnD = d;
	}
	
	public String getE() 
	{
		return columnE;
	}
	public void setE(String e) 
	{
		columnE = e;
	}

	public String getF() 
	{
		return columnF;
	}
	public void setF(String f) 
	{
		columnF = f;
	}

	public String getG() 
	{
		return columnG;
	}
	public void setG(String g) 
	{
		columnG = g;
	}

	public String getH() 
	{
		return columnH;
	}
	public void setH(String h) 
	{
		columnH = h;
	}

	public String getI() 
	{
		return columnI;
	}
	public void setI(String i) 
	{
		columnI = i;
	}

	public String getJ() 
	{
		return columnJ;
	}
	public void setJ(String j) 
	{
		columnJ = j;
	}
	
}
