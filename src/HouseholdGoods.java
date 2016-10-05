import java.util.Scanner;

// -------------------------------------------------------
// Assignment 1 Part B
// Written by: Luis Saravia Patron ID 26800505
// For COMP 249 Section U – Winter 2016
// --------------------------------------------------------

/**
 * This class manipulates household goods, which contain a String to 
 * describe the type of household good (Electronics, Appliance, Furniture)
 * a description of the household good (String) and its price (object of
 * type OldSwedishCurrency). It also keeps track of the amount of household
 * goods initialized.
 * @author Luis Saravia Patron
 * @version 1.0
 */

public class HouseholdGoods {

	private static int goodNumber = 0;
	private String goodType;
	private String description;
	private OldSwedishCurrency price;

	// Default constructor
	/**
	 * Default constructor. Initializes the description to the null
	 * string, the price to 0 riksdaler, 0 skilling, 0 runstycken, 
	 * and increment the instantiated object counter by 1 to reflect
	 * the creation of a new object.
	 */
	public HouseholdGoods() {
		goodType = "";
		description = "";
		price = new OldSwedishCurrency();
		goodNumber ++;
	}

	// Parameterized constructor
	/**
	 * Constructor which takes 3 arguments (two Strings and an object
	 * of type OldSwedishCurrency), initializes the instance variables
	 * to the given values and increment the instantiated object counter
	 * by 1 to reflect the creation of a new object. 
	 * @param String type
	 * @param String description
	 * @param Old SwedishCurency object price
	 */
	public HouseholdGoods(String type, String description, OldSwedishCurrency price) {
		goodType = type;
		this.description = description;
		this.price = new OldSwedishCurrency(price);
		goodNumber ++;
	}

	// Accessors and mutators
	/**
	 * @return the goodNumber
	 */
	public int getGoodNumber() {
		return goodNumber;
	}

	/**
	 * @return the goodType
	 */
	public String getGoodType() {
		return goodType;
	}

	/**
	 * @return the goodDescription
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the price as an OldSwedishCurrency object
	 */
	public OldSwedishCurrency getPrice() {
		OldSwedishCurrency tempPrice = new OldSwedishCurrency(price);
		return tempPrice;
	}

	/**
	 * @param String object, the good type to set.
	 */
	public void setGoodType(String s) {
		GoodTypes type = GoodTypes.valueOf(s.toUpperCase());
		this.goodType = type.toString();
	}

	/**
	 * @param String object, the good description to set
	 */
	public void setGoodDescription(String description) {
		this.description = description;
	}

	/**
	 * @param OldSwedishCurrencyprice object, the price to set
	 */
	public void setPrice(OldSwedishCurrency price) {
		this.price = price;
	}


	// Other methods

	/**
	 * Prompts user to enter an item type showing the all the valid types.
	 * Takes the user input, validates it with GoodTypes values and returns
	 * an equivalent String with a fixed format.
	 * If an invalid type is entered it starts again. 
	 * @return String object
	 */
	static String inputType() {

		String tempType = null; 	// will store the string to return
		String userType = null; 	// will store user input

		Scanner in = new Scanner(System.in); 	// takes input from user 

		while(tempType == null) {	// prompt until it gets a valid entry

			System.out.println("Please enter the item type.");
			System.out.print("Valid types are");
			
			//Display available GoodTypes			
			for(int i = 0; i < GoodTypes.values().length; i++) {
				if (i == (GoodTypes.values().length - 1))
					System.out.println(" and \"" + GoodTypes.values()[i].toString() + "\"");
				else
					System.out.print(" \"" + GoodTypes.values()[i].toString() + "\",");
			}

			userType = in.nextLine(); 	// store user input

			// compare user input with all valid Types
			for(int i = 0; i < GoodTypes.values().length; i++) {

				// Add flexibility by ignoring case and 
				// leading and trailing blank spaces
				if(userType.trim().equalsIgnoreCase(GoodTypes.values()[i].
						toString())) {

					// if a match is found set the type to the formatted value
					tempType = GoodTypes.values()[i].toString();
					break;
				}			
			}

			if (tempType == null) {
				System.out.println(userType + " is an invalid Type.");				
				System.out.println("Please try again.");
			}
			else 
				break;
		}
		return tempType;
	}


	/**
	 * Tests if two HouseholdGoods objects are equal and returns a boolean
	 * value. 
	 * @param HouseHoldGood object
	 * @return boolean
	 */
	public boolean equals(HouseholdGoods h) {
		if(h == null)
			return false;

		else if (getClass() != h.getClass())
			return false;

		else {
			HouseholdGoods otherGood = (HouseholdGoods) h;

			return (goodType.equals(otherGood.goodType)) && 
					description.equals(otherGood.description) &&
					price.equals(otherGood.price);
		}		
	}

	/**
	 * Returns a String with the content of the calling HouseHoldGood
	 * object.
	 * <p>Format:</p>
	 * <p>Type: a valid type</p>
	 * <p>Description: a brief description</p>
	 * <p>Price: x riksdaler, x skilling, x runstycken</p>
	 * @param HouseHoldGood object
	 * @return String object 
	 */
	public String toString() {

		return ("Type: " + goodType + "\nDescription: "+ description +
				"\nPrice: " + price + "\n");
	}

}// end of HouseHoldGoods class
