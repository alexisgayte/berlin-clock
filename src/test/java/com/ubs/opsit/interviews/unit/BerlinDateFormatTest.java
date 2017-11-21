package com.ubs.opsit.interviews.unit;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.ubs.opsit.interviews.BerlinDateFormat;

@RunWith(MockitoJUnitRunner.class)
public class BerlinDateFormatTest {

	private BerlinDateFormat berlinClockTime;

	@Before
	public void setup() throws Exception {
		berlinClockTime = new BerlinDateFormat();
	}

	@Test
	public void zeroHourTest() throws ParseException {

		String format = berlinClockTime.format(new Date(0,0,0,0,0,0));

		assertEquals("Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", format);
	}


	@Test
	public void tenHourTest() throws ParseException {

		String format = berlinClockTime.format(new Date(0,0,0,10,0,0));

		assertEquals("Y\nRROO\nOOOO\nOOOOOOOOOOO\nOOOO", format);
	}


	@Test
	public void normalTwentyFourHourTest() throws ParseException {

		String format = berlinClockTime.format(new Date(0,0,0,24,0,0));

		assertEquals("Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", format);
	}

	@Test
	public void twentyFourHourSpecialLogicTest() throws ParseException {

		berlinClockTime =  new BerlinDateFormat(true);

		String format = berlinClockTime.format(new Date(0,0,2,0,0,0));

		assertEquals("Y\nRRRR\nRRRR\nOOOOOOOOOOO\nOOOO", format);
	}


}
