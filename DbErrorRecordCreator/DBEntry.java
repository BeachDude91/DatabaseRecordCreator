/*
  * Nicholas MacFarland
  * December 16, 2019 
  * Database Record Creator
  * DBEntry class file
*/

public class DBEntry 
{
	String columnA,columnB,columnC,columnD,columnE					//creating instances for columns 
		,columnF,columnG,columnH,columnI,columnJ;						//A through J
	
	public DBEntry()												//Empty Constructor found here
	{}
	
	public DBEntry(String a,String b, String c, String d, String e	//Full Constructor found here
			, String f, String g, String h, String i, String j)			//that initializes columns A though J
	{																	//to parameters for full constructor.
		this.columnA = a;
		this.columnB = b;
		this.columnC = c;
		this.columnD = d;
		this.columnE = e;
		this.columnF = f;
		this.columnG = g;
		this.columnH = h;
		this.columnI = i;
		this.columnJ = j;
	}
	
	//*** Getters and Setters found below ***

	public String getA() 
	{
		return columnA;
	}
	public void setA(String a) 
	{
		this.columnA = a;
	}

	public String getB() 
	{
		return columnB;
	}
	public void setB(String b) 
	{
		this.columnB = b;
	}

	public String getC() 
	{
		return columnC;
	}
	public void setC(String c) 
	{
		this.columnC = c;
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
		this.columnE = e;
	}

	public String getF() 
	{
		return columnF;
	}
	public void setF(String f) 
	{
		this.columnF = f;
	}

	public String getG() 
	{
		return columnG;
	}
	public void setG(String g) 
	{
		this.columnG = g;
	}

	public String getH() 
	{
		return columnH;
	}
	public void setH(String h) 
	{
		this.columnH = h;
	}

	public String getI() 
	{
		return columnI;
	}
	public void setI(String i) 
	{
		this.columnI = i;
	}

	public String getJ() 
	{
		return columnJ;
	}
	public void setJ(String j) 
	{
		this.columnJ = j;
	}
	
}
