// -------------------------------------------------------
// Assignment 1 Part C
// Written by: Luis Saravia Patron ID 26800505
// For COMP 249 Section U – Winter 2016
// --------------------------------------------------------

import java.util.Scanner;
/**
 * This program allows the user to keep an inventory of house hold items.
 * Each item has a type, description and a price in old swedish currency.
 * The program allows to enter new items, modify them, display by type,
 * display items under a determined price, and show some statistics about the 
 * inventory
 * @author Luis Saravia Patron
 * @version 1.0
 */
public class Driver {

	private final static String PASSWORD = "comp249";
	private final static int MAIN_START = 1;
	private final static int MAIN_END = 6;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Empty array to keep 100 HouseholdGoods objects 
		HouseholdGoods [] goodsArray = new HouseholdGoods [100];

		Scanner kb = new Scanner(System.in); // take input from user
		String jk; 	// remove junk from line of user input
		int choice = 0;	// store user choice in main menu
		int subChoice = 0;	// store user choice in sub-menu
		int itemNum = 0; // store item number selected by user

		// Welcome Message
		System.out.println("------------------------------------------------------");
		System.out.println("     Welcome to Concordia's Household Goods Store");
		System.out.println("------------------------------------------------------");

		// keep prompting the user until they enter a number between 1 and 6 
		while(choice < MAIN_START || MAIN_END < choice) {

			//Display a main menu
			System.out.println("What would you like to do?");
			System.out.println("\t1. Enter a new item in inventory" 
					+ " (password required)");
			System.out.println("\t2. Change information of an item "
					+ "in inventory (password required)");
			System.out.println("\t3. Display all items of a specific"
					+ " type");
			System.out.println("\t4. Display all items under a certain"
					+ " price");
			System.out.println("\t5. Statistics on your inventory");
			System.out.println("\t6. Quit");
			System.out.println("Please enter your choice -->");

			//Take input from user
			choice = kb.nextInt();
			//System.out.println("You entered " + choice);

			switch(choice) {
			case 1: 			// Enter new item in inventory
				// Reset choice
				choice = 0;

				//prompt for password
				if(checkPassword()) {
					// Check for space in the array
					if (checkArraySpace(goodsArray)){

						String tempType, tempDesc;
						OldSwedishCurrency tempCur;

						// Prompt for type and store the entered type
						tempType = HouseholdGoods.inputType();

						//Prompt for description
						System.out.println("Please enter a brief description"
								+ " and press Enter.");

						//Store the entered description
						jk = kb.nextLine(); 	// clears input line after nextInt()
						tempDesc = kb.nextLine();
						//System.out.println("You entered " + tempDesc);

						//Prompt for price and store the input
						tempCur = OldSwedishCurrency.inputOSCPrice();
						System.out.println("You entered " + tempCur);

						// create the object with the entered values
						HouseholdGoods tempGood = new HouseholdGoods(tempType,
								tempDesc, tempCur);

						// Add item to the array
						goodsArray[tempGood.getGoodNumber() - 1] = tempGood;

						// Display to user
						System.out.println("You have created and stored:\n");
						displayItem(goodsArray, tempGood.getGoodNumber());

					} // end of if (checkArraySpace(goodArray))

				} // end of if (checkPassword())

				break;

			case 2: // Change information of an item in inventory
				// Reset choice
				choice = 0;

				//prompt for password
				if(checkPassword()) {

					while (itemNum == 0) {	

						// Prompt for item number
						System.out.println("Please enter the item number you"
								+ " want to change (from 1 to 100).");

						//store item number
						itemNum = kb.nextInt();

						// check for out of bounds
						if (0 < itemNum && itemNum < 101) {

							// check if the item exists 
							if(goodsArray[itemNum - 1] != null) {

								// object display object info
								displayItem(goodsArray, itemNum);
								break;
							}
							else {
								System.out.println("You have entered " + itemNum +
										":");
								System.out.println("The item number is empty.");

								// reset userIndex
								itemNum = 0;

								System.out.println("Enter 0 for Main Menu or"
										+ " any number to try again.");
								if(kb.nextInt() == 0)
									break;
							}

						}
						else {
							System.out.println("You have entered " + itemNum + ":");
							System.out.println("Number out of range!"); 
							itemNum = 0;
						}

					} // end of while (userIndex == 0)

					// if user index = 0 go to Main menu
					if(itemNum == 0)
						break;

					// prompt user to enter the value
					subChoice = 0;
					while(subChoice != 4) { // keep prompting until 4 is entered

						System.out.println("What would you like to change?");
						System.out.println("1. Type");
						System.out.println("2. Description");
						System.out.println("3. Price");
						System.out.println("4. Quit");
						System.out.println("Enter choice >");

						// get user choice
						subChoice = kb.nextInt();

						// make changes to item
						switch(subChoice) {

						case 1:		// change Type
							String tempType = HouseholdGoods.inputType();
							goodsArray[itemNum - 1].setGoodType(tempType);

							System.out.println("Item updated as follows:");
							System.out.println(goodsArray[itemNum - 1]);

							//reset choice
							subChoice = 0;
							break;

						case 2:		// change Description
							System.out.println("Please enter the new Description"
									+ " and press Enter");

							jk = kb.nextLine(); // clear line after nextInt()
							goodsArray[itemNum - 1].setGoodDescription(
									kb.nextLine());

							System.out.println("Item updated as follows:");
							System.out.println(goodsArray[itemNum - 1]);
							//reset choice
							subChoice = 0;
							break;

						case 3:		// change Price
							System.out.println("Please enter the new Price "
									+ "and press Enter");

							goodsArray[itemNum - 1].setPrice(OldSwedishCurrency.inputOSCPrice());

							System.out.println("Item updated:");
							System.out.println(goodsArray[itemNum - 1]);
							//reset choice
							subChoice = 0;
							break;

						case 4:		// Quit
							// reset userIndex
							itemNum = 0;
							break;

						default:	// Invalid choice
							System.out.println("Not a valid choice. Please try "
									+ "again.");
							//reset choice
							subChoice = 0;

						} // end of switch statement

					} // end of while(innerChoice != 4)

					//reset innerChoice
					subChoice = 0;

				} // end of if(checkPassword())

				break;

			case 3: // Display all items of a specific type

				// reset choice
				choice = 0;				

				// Prompt user for type
				System.out.println("What type do you want to display?");

				//Display all available types
				for(int i = 0; i < GoodTypes.values().length; i++){
					System.out.println((i + 1) + ". " + 
							GoodTypes.values()[i].toString());
				}
				System.out.println("Enter choice >");

				// Store user choice
				subChoice = kb.nextInt();				

				// Display all items of selected type in array 
				printAllByType(goodsArray, subChoice);

				// Reset innerChoice
				subChoice = 0;
				break;

			case 4: // Display all items under a certain price

				// reset choice
				choice = 0;

				// Prompt user for price
				System.out.println("Please enter the price");
				OldSwedishCurrency userPrice = OldSwedishCurrency.inputOSCPrice();

				// Display all objects below that price
				printAllBelowPrice(goodsArray, userPrice);

				break;

			case 5: // Statistics on your inventory

				//Prompt user
				while (choice == 5) {

					System.out.println("What information would you like?");
					System.out.println("1. Cost and details of cheapest item");
					System.out.println("2. Cost and details of most costly item");
					System.out.println("3. Number of items of each type");
					System.out.println("4. Average cost of items in inventory");
					System.out.println("5. Quit");
					System.out.println("Enter your choice > ");

					subChoice = kb.nextInt();

					switch(subChoice){
					case 1:		// Cost and details of cheapest item
						itemNum = lowestPrice(goodsArray); // itemNum used to hold
														   // index
						
						// if index is empty the array is empty
						if(goodsArray[itemNum] == null) {
							System.out.println("The array is empty");
							break;
						}
							
						// item requested by user
						displayItem(goodsArray, itemNum + 1);						
						break;

					case 2:		// Cost and details of most costly item
						itemNum = highestPrice(goodsArray); // itemNum used to hold
															// index
						
						// if index is empty the array is empty
						if(goodsArray[itemNum] == null) {
							System.out.println("The array is empty");
							break;
						}
						// item requested by user
						displayItem(goodsArray, itemNum + 1);						
						break;

					case 3:		// Number of items of each type

						int count = 0;

						// count items of each type and display results

						// for every type
						for(int i = 0; i < GoodTypes.values().length; i++){

							// search array items' types
							for(int j = 0; j < goodsArray.length; j++) {

								// if item is null skip
								//if(goodsArray[j] == null)
								//	continue;

								// if there's a matching type
								if(goodsArray[j] != null && 
										GoodTypes.values()[i].toString().equals(
												goodsArray[j].getGoodType())) {

									// increase count
									count ++;									
								}
							}
							System.out.println(count + " " + 
									GoodTypes.values()[i].toString());
							//reset counter
							count = 0;
						}

						break;

					case 4:		// Average cost of items in inventory
						System.out.println("Average price is: " +
								avgPrice(goodsArray));
						break;

					case 5:		// Quit
						// Reset choice
						choice = 0;
						break;

					default:
						// Invalid choice
						System.out.println("Not a valid choice. Please try again.");

					} // end of switch statement

				} // end of while (choice == 5)

				break;

			case 6: // Quit
				// Display closing message and exit
				System.out.println("I hope you have enjoyed.");
						System.out.println("System will close now. Good bye!");
				System.exit(0);				

			} // end of switch statement


		} // end of menu while loop


		kb.close();

	} // end of main()

	private static void displayItem(HouseholdGoods [] arr, int itemNum) {
		if(arr[itemNum - 1] == null)
			System.out.println("Index is empty");
		else {
			System.out.println("\tItem # " + (itemNum));
			System.out.println(arr[itemNum - 1]);
		}
		
	}
	private static int countAllByType(HouseholdGoods[] arr, String type) {
		int count = 0;

		// if array or String are null exit
		if(arr == null || type == null) {
			System.out.println("Error. Null pointer detected.");
			System.out.println("System will close now.");
			System.exit(0);
		}

		//search all array
		for(int i = 0; i < arr.length; i++) {

			// if item is empty skip
			if(arr[i] == null)
				continue;

			// if item type matches selected type increase counter 
			if (arr[i].getGoodType().equals(type))
				count++;	
		}		
		return count;
	}


	private static OldSwedishCurrency avgPrice(HouseholdGoods[] arr) {

		OldSwedishCurrency avg = new OldSwedishCurrency();
		int count = 0; // count added items

		//scan all array
		for(int i = 0; i < arr.length; i++) {

			//skip null pointers
			if (arr[i] != null) {

				//add prices to avg and count items
				avg = avg.add(arr[i].getPrice());
				count++;										
			}			
		}
		//if count is zero return zero currency
		if(count == 0) {
			return avg;
		}
		else {		//else divide sum of prices by added items
					//and return equivalent currency
			
			avg = OldSwedishCurrency.convertFromRunstycken(avg.convertToRunstycken()
					/ count); 
			return avg;
		}
	}

	private static void printAllBelowPrice(HouseholdGoods[] arr, 
			OldSwedishCurrency price) {
		//compare each item with given price
		for(int i = 0; i < arr.length; i++) {

			//skip null pointers
			if (arr[i] != null) {

				//print if item price is lower than price
				if(arr[i].getPrice().compareTo(price) < 0) {
					displayItem(arr, i + 1);
				}
			}
		}
	}


	private static void printAllByType(HouseholdGoods[] arr, int type) {

		//Check for a valid type
		if(type < 1 || GoodTypes.values().length < type) {
			System.out.println("Error. " + type + " is not a valid choice.");
			System.out.println("System will close now");
			System.exit(0);
		}
		else {// Search in the array
			for(int i = 0; i < arr.length; i++) {
				// Skip null pointers in array 
				if (arr[i] != null){
					// Display only selected type
					if(arr[i].getGoodType().equalsIgnoreCase(
							GoodTypes.values()[type -1].toString())) {
						displayItem(arr, i + 1);
					}

				}
			}
		}
	}

	private static boolean checkArraySpace(HouseholdGoods [] arr) {
		// go through all the array. Look for a null pointer
		for(int i = 0; i < arr.length; i++) {
			if (arr[i] == null) {
				//System.out.println("There is available space.");
				return true;
			}
			// it the last item is not null
			if (i == arr.length - 1)
				System.out.println("Sorry, no more space available");
		}

		return false;
	}

	private static boolean checkPassword() {
		Scanner psw = new Scanner(System.in);
		String userPswd;

		// Give user 3 tries to enter password
		for (int i = 0; i < 3; i++){
			System.out.println("Please enter your password and press Enter:");
			userPswd = psw.nextLine();
			if (userPswd.equals(PASSWORD))
				return true;
			else if (i < 2)
				System.out.println("Invalid Password. Please try again.");
		}
		System.out.println("Invalid Password. You have attempted 3 times.");
		return false;
	}

	// will find and return the index of the household item with the
	// lowest price in the array.
	private static int lowestPrice(HouseholdGoods [] arr) {
		int temp = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != null) {
				if (arr[temp].getPrice().compareTo(arr[i].getPrice()) > 0)
					temp = i;

			}

		}
		return temp;
	}

	//	will find and return the index of the of the household item with
	//	the highest price in the array
	private static int highestPrice(HouseholdGoods [] arr) {
		int temp = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != null) {
				if (arr[temp].getPrice().compareTo(arr[i].getPrice()) < 0)
					temp = i;				
			}

		}
		return temp;
	}

}// end of Driver class
