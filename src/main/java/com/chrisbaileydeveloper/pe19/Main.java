package com.chrisbaileydeveloper.pe19;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Euler #19 - Counting Sundays
 * 
 * @author Chris Bailey
 */

public class Main {
	static final Map<String, Integer> dateMap = populateDateMap();

	public static void main(String[] args) {
		int numberSundays = countSundays(31, "Dec", 2000) - countSundays(1, "Jan", 1901);

		System.out.println("Number of Sundays: " + numberSundays);
	}

	/**
	 * Returns all of the Sundays on the first of the month from Monday, January
	 * 1st 1900 to the date specified. Allowable range is from year 1900 to
	 * 10,000.
	 *
	 * @param finalDay the chosen day
	 * @param month the chosen month
	 * @param finalYear the chosen year
	 * @return the number of Sundays on the first of the month
	 * 
	 */

	public static int countSundays(int finalDay, String stringMonth,
			int finalYear) {

		// Check if stringMonth is valid.
		if (!dateMap.containsKey(stringMonth)) {
			return -1;
		}

		// Convert stringMonth to integer.
		int finalMonth = dateMap.get(stringMonth);

		// Validate the date given.
		if (!validDate(finalDay, finalMonth, finalYear)) {
			return -1;
		}

		// Initialize weekday, month, year to Monday, 01 Jan 1900.
		// Day will always be the first of the month.
		int weekday = 1; // Weekdays -> Sunday = 0, Monday = 1, ...
		int month = 1;
		int year = 1900;

		// Initialize numSundays and monthLength
		int numSundays = 0;
		int monthLength = numberOfDays(month, year);

		// While current date is less than the chosen date.
		// Check if month is greater within current year, or if year has incremented.
		while (!(month > finalMonth && year == finalYear) && !(year > finalYear)) {
			
			// Check if it is Sunday.
			if (weekday == 0) {
				numSundays++;
			}

			// Set weekday to the first of the following month.
			int weekdayOffset = monthLength % 7;
			for (int i = 0; i < weekdayOffset; i++) {
				weekday = (weekday == 6) ? 0 : weekday + 1;
			}

			// Increment month
			if (month != 12) { // Not December.
				month++;
			} else { // December.  Increment Year.
				month = 1;
				year++;
			}

			// Calculate the number of days in the new month.
			monthLength = numberOfDays(month, year);
		}

		return numSundays;
	}

	/**
	 * Returns the number of days in a given month and year.
	 * 
	 * @param month of the year
	 * @param year
	 * @return the number of days in the month given
	 */

	static int numberOfDays(int month, int year) {
		if (month == 2) { // February
			if (year % 100 != 0) { // Not a century.
				if (year % 4 != 0) { // Year not divisible by 4.
					return 28;
				} else { // Leap year
					return 29;
				}
			} else { // Is a century.
				if (year % 400 != 0) {// Century not divisible by 400.
					return 28;
				} else { // Leap year
					return 29;
				}
			}
		} else if (month == 9 || month == 4 || month == 6 || month == 11) {
			// September, April, June, November = 30.
			return 30;
		} else {// All other months are 31.
			return 31;
		}
	}

	/**
	 * Validates the date given to make sure it exists.
	 * 
	 * @param finalDay day
	 * @param finalMonth month
	 * @param finalYear year
	 * @return true if valid date, false otherwise
	 */
	static boolean validDate(int finalDay, int finalMonth, int finalYear) {
		// Validate year.
		if (finalYear < 1900 || finalYear > 10000) {
			return false;
		}

		// Validate month.
		if (finalMonth < 1 || finalMonth > 12) {
			return false;
		}

		// Validate day.
		int daysInMonth = numberOfDays(finalMonth, finalYear);
		if (finalDay < 1 || finalDay > daysInMonth) {
			return false;
		}

		// Date is valid.
		return true;
	}

	/**
	 * Populates a HashMap that holds the months of the year in string format
	 * with their corresponding integer.
	 * 
	 * @return a map that holds String/Integer pairs for the months of the year
	 */
	static Map<String, Integer> populateDateMap() {
		@SuppressWarnings("serial")
		Map<String, Integer> dateMap = new HashMap<String, Integer>() {
			{
				put("Jan", 1);
				put("January", 1);
				put("Feb", 2);
				put("February", 2);
				put("Mar", 3);
				put("March", 3);
				put("Apr", 4);
				put("April", 4);
				put("May", 5);
				put("June", 6);
				put("July", 7);
				put("Aug", 8);
				put("August", 8);
				put("Sept", 9);
				put("September", 9);
				put("Oct", 10);
				put("October", 10);
				put("Nov", 11);
				put("November", 11);
				put("Dec", 12);
				put("December", 12);
			}
		};

		return dateMap;

	}
}
