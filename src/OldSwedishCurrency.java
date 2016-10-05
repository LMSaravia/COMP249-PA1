import java.util.Scanner;

// -------------------------------------------------------
// Assignment 1 Part A
// Written by: Luis Saravia Patron ID 26800505
// For COMP 249 Section U – Winter 2016
// --------------------------------------------------------

/**
 * Defines Old Swedish Currency objects
 * Each OSC will hold an amount of riksdaler,
 * skilling and runstycken.
 * <p>The Old Swedish Currency works as follows:</p> 
 *  <p>1 riksdaler = 48 skilling = 768 runstycken.</p>
 * 	<p>1 skilling = 16 runstycken = 1/48 riksdaler.</p>
 * 	<p>1 runstycken = 1/16 skilling = 1/768 riksdaler.</p>
 * @author Luis Saravia Patron
 * @version 1.0
 */

public class OldSwedishCurrency {


	// Constants
	public static final int MAX_SKILLING = 48;
	public static final int MAX_RUNSTYCKEN = 16;

	// Variables
	private int riksdaler;
	private int skilling;
	private int runstycken;


	// Constructors
	/**
	 * Default constructor with no parameters
	 * Initializes riksdaler, skilling and runstycken variables to zero
	 */
	public OldSwedishCurrency() {

		riksdaler = 0;
		skilling = 0;
		runstycken = 0;
	}

	/**
	 * Constructor that creates a normalized object taking 3 integer arguments.
	 * If one of them is negative all variables are initialized to zero.
	 * @param integer riksdaler amount
	 * @param integer skilling	amount
	 * @param integer runstycken amount
	 */
	public OldSwedishCurrency(int rik, int ski, int run) {

		// initialize to zero
		this();

		// if none of the arguments is negative assign to variables.
		if(rik >= 0 && ski >= 0 && run >= 0) {		
			riksdaler = rik;
			skilling = ski;
			runstycken = run;
		}

		// normalize if necessary	
		if(ski >= MAX_SKILLING || run >= MAX_RUNSTYCKEN) {	
			OldSwedishCurrency.normalizeOSC(this);
		}
	}

	/**
	 * Copy constructor. Creates an equivalent normalized object
	 * @param OldSwedishCurrency object
	 */
	public OldSwedishCurrency(OldSwedishCurrency cur) {

		riksdaler = cur.getRiksdaler();
		skilling = cur.getSkilling();
		runstycken = cur.getRunstycken();
	}


	// Accessors and Mutators

	/**
	 * @return the riksdaler
	 */
	public int getRiksdaler() {

		return riksdaler;
	}

	/**
	 * @return the skilling
	 */
	public int getSkilling() {

		return skilling;
	}

	/**
	 * @return the runstycken
	 */
	public int getRunstycken() {

		return runstycken;
	}

	/**
	 * @param integer riksdaler to set
	 */
	public void setRiksdaler(int rik) { 

		riksdaler = rik;
		OldSwedishCurrency.normalizeOSC(this);
	}

	/**
	 * @param integer skilling to set
	 */
	public void setSkilling(int ski) { 

		skilling = ski;
		OldSwedishCurrency.normalizeOSC(this);		
	}

	/**
	 * @param integer runstycken to set
	 */
	public void setRunstycken(int run) {

		runstycken = run;
		OldSwedishCurrency.normalizeOSC(this);
	}

	// Other methods

	/**
	 * Prompts user to enter integer amounts of riksdaler, skilling and
	 * runstycken, takes the user input and returns a normalized 
	 * OldSwedishCurrency object. If any of the entered values is negative it
	 * will set all variables to zero. 
	 * @return OldSwedishCurrency
	 */
	static OldSwedishCurrency inputOSCPrice() {

		Scanner in = new Scanner(System.in);	// take input from user
		int rik, ski, run;		// store each amount

		System.out.println("Please enter the price in Old Swedish Currency format");
		System.out.println("Use only integers for each amount:");
		System.out.println("Riksdaler: ");
		rik =in.nextInt();
		System.out.println("Skilling: ");
		ski =in.nextInt();
		System.out.println("Runstycken: ");
		run =in.nextInt();

		// create currency object
		OldSwedishCurrency cur = new OldSwedishCurrency(rik, ski, run);

		return cur;
	}

	/**
	 * Normalizes objects of type OldSwedishCurrency
	 * @param OldSwedishCurrency object
	 */
	private static void normalizeOSC(OldSwedishCurrency cur) { 

		int tempRun, tempSki, tempRik; 	// hold values for runstyckens,
		// skillings and riksdaler

		// Put everything into runstycken
		tempRun = cur.convertToRunstycken();
		tempSki = 0;
		tempRik = 0;

		// If runstycken amount is equal to 1 skilling or more convert to skilling
		if(Math.abs(tempRun) >= MAX_RUNSTYCKEN) {
			tempSki = tempRun / MAX_RUNSTYCKEN;
			tempRun %= MAX_RUNSTYCKEN; 	// keep the remainder
		}

		// If skilling amount is equal to 1 riksdaler or more convert to riksdaler
		if(Math.abs(tempSki) >= MAX_SKILLING) {
			tempRik = tempSki / MAX_SKILLING;
			tempSki %= MAX_SKILLING;			
		}

		// Set the normalized values.
		cur.riksdaler = tempRik;
		cur.skilling = tempSki;
		cur.runstycken = tempRun;

	}


	/**
	 * Converts the content of the calling object to its equivalent 
	 * in runstycken value and return it as an integer.
	 * @return integer rustycken amount
	 */
	public int convertToRunstycken() {

		int tempRun = this.runstycken;	// hold the runstycken amount

		// Convert skilling to runstycken and add amount
		tempRun += this.skilling * MAX_RUNSTYCKEN;

		// convert riksdaler into runstycken and add amount
		tempRun += this.riksdaler * MAX_SKILLING * MAX_RUNSTYCKEN;	

		return tempRun;				
	}


	/**
	 * Takes an integer runstycken value and returns a normalized 
	 * OldSwedishCurrency object. If the value is negative all variables
	 * will be initialized to zero.
	 * @param runstyckenAmount
	 * @return normalized OldSwedishCurrency object
	 */
	public static OldSwedishCurrency convertFromRunstycken(int runstyckenAmount) {

		// create a new OldSwedishCurrency object
		OldSwedishCurrency tempCur;

		// if the passed amount is zero or negative use default 
		// constructor. All variables will be set to zero
		if(runstyckenAmount <= 0) {

			tempCur = new OldSwedishCurrency();

		} else {	// a positive amount will be normalized by constructor. 	

			tempCur = new OldSwedishCurrency(0, 0, runstyckenAmount);
		}

		return tempCur;	 
	}


	/**
	 * Creates a new OldSwedishCurrency object containing the
	 * sum of the invoking and passed OldSwedishCurrency objects	
	 * @param OldSwedishCurrency object
	 * @return OldSwedishCurrency object
	 */
	public OldSwedishCurrency add(OldSwedishCurrency cur) {

		// convert the 2 amounts to runstyckens and add
		int tempRun = this.convertToRunstycken() + cur.convertToRunstycken();

		// create a new normalized object passing the amount of runstycken
		OldSwedishCurrency tempCur = convertFromRunstycken(tempRun);		

		return tempCur;
	}


	/**
	 * Substracts 2 OldSwedishCurrency objects and returns a new one
	 * containing the substraction of the invoking and passed 
	 * OldSwedishCurrency objects. If the resulting value is negative
	 * sets all of the instance variables to 0.
	 * @param OldSwedishCurrency object
	 * @return OldSwedishCurrency object
	 */
	public OldSwedishCurrency substract(OldSwedishCurrency cur) {
		int tempRun; 	// hold the total amount of runstycken 

		OldSwedishCurrency tempCur;	// object to return

		// convert the 2 amounts to runstyckens and substract
		tempRun = this.convertToRunstycken() - cur.convertToRunstycken();

		// create a new normalized object
		tempCur = convertFromRunstycken(tempRun);					

		return tempCur;	
	}


	/**
	 * Compares the values of the instance variables of two OldSwedishCurrency
	 * objects and returns true if they are equal or false otherwise.
	 * @param OldSwedishCurrency object
	 * @return boolean
	 */
	public boolean equals(OldSwedishCurrency cur) {

		// if the object is not initialized return false
		if(cur == null)
			return false;

		// if it is an object from a different class return false
		else if(getClass() != cur.getClass())
			return false;

		// else compare each variable
		else {
			OldSwedishCurrency otherCurrency = (OldSwedishCurrency) cur;
			return (riksdaler == otherCurrency.riksdaler &&
					skilling == otherCurrency.skilling &&
					runstycken == otherCurrency.runstycken);
		}
	}

	/**
	 * Compares 2 OldSwedishCurrency objects and returns -1, 0, or 1
	 * depending upon whether the invoking object is less than, equal
	 * to, or greater than the passed OldSwedishCurrency object.
	 * @param OldSwedishCurrency object
	 * @return integer (-1 or 0 or 1)
	 */
	public int compareTo(OldSwedishCurrency cur) {

		if (this.convertToRunstycken() < cur.convertToRunstycken()) {
			return -1;
		} else if (this.convertToRunstycken() == cur.convertToRunstycken()) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * Returns a String with the content of the calling OldSwedishCurrency
	 * object
	 * @param OldSwedishCurrency object
	 * @return String. Format:"x riksdaler, y skilling, z runstycken"
	 */
	public String toString() {

		String text = (riksdaler + " riksdaler, " + skilling +
				" skilling, " +	runstycken + " runstycken");

		return text;
	}

} // end of class OldSwedishCurrency
