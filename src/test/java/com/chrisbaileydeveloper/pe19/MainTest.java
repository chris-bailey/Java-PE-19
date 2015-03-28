package com.chrisbaileydeveloper.pe19;

import static org.junit.Assert.assertTrue;
import java.util.Map;
import org.junit.Test;
import com.chrisbaileydeveloper.pe19.Main;

public class MainTest {

	@Test
	public void testCountSundays() {
		// Test all Sundays on first of month from 1 Jan 1900 to 1 Jan 1901
		int sundaysExpected = 2;
		int sundaysResult = Main.countSundays(5, "Jan", 1901);
		assertTrue(sundaysExpected == sundaysResult);

		// Test all Sundays on first of month from 1 Jan 1900 to 1 Jan 1906
		int sixYearsExpected = 11;
		int sixYearsResult = Main.countSundays(8, "Jan", 1906);
		assertTrue(sixYearsExpected == sixYearsResult);

		// Test invalid data
		int invalidExpected = -1;
		int invalidResult = Main.countSundays(32, "Jan", 1901);
		assertTrue(invalidExpected == invalidResult);

	}

	@Test
	public void testNumberOfDays() {
		// Test regular 30 day month - September 2015
		int thirtyExpected = 30;
		int thirtyResult = Main.numberOfDays(9, 2015);
		assertTrue(thirtyExpected == thirtyResult);

		// Test regular 31 day month
		int thirtyOneExpected = 31;
		int thirtyOneResult = Main.numberOfDays(1, 2016);
		assertTrue(thirtyOneExpected == thirtyOneResult);

		// Test 'Feb 2016' - regular leap year
		int regularLeapExpected = 29;
		int regularLeapResult = Main.numberOfDays(2, 2016);
		assertTrue(regularLeapExpected == regularLeapResult);

		// Test 'February 2300' - century but not a leap year
		int notCenturyLeapExpected = 28;
		int notCenturyLeapResult = Main.numberOfDays(2, 2300);
		assertTrue(notCenturyLeapExpected == notCenturyLeapResult);

		// Test 'February 2400' - century and a leap year
		int centuryLeapExpected = 29;
		int centuryLeapResult = Main.numberOfDays(2, 2400);
		assertTrue(centuryLeapExpected == centuryLeapResult);

	}

	@Test
	public void testPopulateDateMap() {
		Map<String, Integer> dateMap = Main.populateDateMap();

		// Test February
		int expected = 2;
		int result = dateMap.get("February");
		assertTrue(expected == result);
	}

}
