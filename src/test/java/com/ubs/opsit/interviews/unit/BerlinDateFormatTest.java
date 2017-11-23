package com.ubs.opsit.interviews.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.NotImplementedException;
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
		berlinClockTime = new BerlinDateFormat(false, "\n");
	}

	@Test
	public void format_zero_hourTest() throws ParseException {

		String format = berlinClockTime.format(new Date(0, 0, 0, 0, 0, 0));

		assertEquals("Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", format);
	}

	@Test
	public void format_ten_hourTest() throws ParseException {

		String format = berlinClockTime.format(new Date(0, 0, 0, 10, 0, 0));

		assertEquals("Y\nRROO\nOOOO\nOOOOOOOOOOO\nOOOO", format);
	}

	@Test
	public void format_ten_ten_tenTest() throws ParseException {

		String format = berlinClockTime.format(new Date(0, 0, 0, 10, 10, 10));

		assertEquals("Y\nRROO\nOOOO\nYYOOOOOOOOO\nOOOO", format);
	}

	@Test
	public void format_normal_twenty_four_hourTest() throws ParseException {

		String format = berlinClockTime.format(new Date(0, 0, 0, 24, 0, 0));

		assertEquals("Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", format);
	}

	@Test
	public void format_twenty_four_hour_special_logicTest() throws ParseException {

		berlinClockTime = new BerlinDateFormat(true, "\n");

		String format = berlinClockTime.format(new Date(0, 0, 2, 0, 0, 0));

		assertEquals("Y\nRRRR\nRRRR\nOOOOOOOOOOO\nOOOO", format);
	}

	@Test
	public void format_special_line_separatorTest() {

		berlinClockTime =  new BerlinDateFormat(false, "K");
		String format = berlinClockTime.format(new Date(0, 0, 0, 0, 0, 0));

		assertEquals("YKOOOOKOOOOKOOOOOOOOOOOKOOOO", format);
	}

	@Test(expected = NotImplementedException.class)
	public void parseTest() throws ParseException {

		berlinClockTime.parse("Y\nRRRR\nRRRR\nOOOOOOOOOOO\nOOOO");

	}

	@Test
	public void cloneTest() {

		berlinClockTime = (BerlinDateFormat) berlinClockTime.clone();
		String format = berlinClockTime.format(new Date(0, 0, 0, 0, 0, 0));

		assertEquals("Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", format);

	}

	@Test
	public void equalTest() {

		BerlinDateFormat berlinClockTimeClone = (BerlinDateFormat) berlinClockTime.clone();

		assertTrue(berlinClockTimeClone.equals(berlinClockTime));

	}

}
