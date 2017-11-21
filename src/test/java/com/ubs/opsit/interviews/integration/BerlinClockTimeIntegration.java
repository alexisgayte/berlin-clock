package com.ubs.opsit.interviews.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.ubs.opsit.interviews.BerlinClockTime;
import com.ubs.opsit.interviews.TimeConverter;

@RunWith(MockitoJUnitRunner.class)
public class BerlinClockTimeIntegration {


	TimeConverter timeConverter;

	@Before
	public void setup() throws Exception {
		timeConverter = new BerlinClockTime();
	}

	@Test
	public void timeConverter00hTest() {
		assertEquals(	"Y" + System.lineSeparator() +
						"OOOO"+ System.lineSeparator() +
						"OOOO"+ System.lineSeparator() +
						"OOOOOOOOOOO"+ System.lineSeparator() +
						"OOOO", timeConverter.convertTime("00:00:00"));

	}


	@Test
	public void timeConverter24hTest() {
		assertEquals(	"Y" + System.lineSeparator() +
						"RRRR"+ System.lineSeparator() +
						"RRRR"+ System.lineSeparator() +
						"OOOOOOOOOOO"+ System.lineSeparator() +
						"OOOO", timeConverter.convertTime("24:00:00"));

	}

}
