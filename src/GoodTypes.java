/**
 * GoodTypes is the enum set that stores the good types available
 * for the HouseholdGoods class.
 * @author Luis
 * @version 1.0
 *
 */
enum GoodTypes {

	ELECTRONICS("Electronics"),
	APPLIANCE("Appliance"),
	FURNITURE("Furniture");

	private String text;

	GoodTypes (String name) {
		text = name;
	}

	/**
	 * Returns a String object that displays the GoodType
	 * with formatted text
	 * @return String object
	 */
	public String toString() {
		return text;
	}
}
