package com.ubs.opsit.interviews.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ubs.opsit.interviews.BerlinClockTime;
import com.ubs.opsit.interviews.BerlinDateFormat;

@RunWith(MockitoJUnitRunner.class)
public class BerlinClockTimeTest {

	@Mock
	SimpleDateFormat simpleDateFormat;

	@Mock
	BerlinDateFormat berlinDateFormat;

	@InjectMocks
	BerlinClockTime berlinClockTime = new BerlinClockTime();

	@Captor
    ArgumentCaptor<String> captorString;

	@Captor
    ArgumentCaptor<Date> captorDate;

	@Captor
    ArgumentCaptor<StringBuffer> captorStringBuffer;

	@Captor
    ArgumentCaptor<FieldPosition> captorFieldPosition;


	private Date date;
	private String dateParam;

	@Before
	public void setup() throws Exception {
		date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		dateParam = simpleDateFormat.format(date);

	}

	@Test
	public void normal_call_should_call_simple_date_format_with_the_param_test() throws ParseException {

		// when

		when(simpleDateFormat.parse(any())).thenReturn(date);
		when(berlinDateFormat.format(Mockito.any(Date.class), any(), any())).thenReturn(new StringBuffer(dateParam));
		// then

		berlinClockTime.convertTime(dateParam);

		// expect element are the same

		verify(simpleDateFormat).parse(captorString.capture());
		assertEquals("Date param is not as expected.", dateParam, captorString.getValue());

	}


	@Test
	public void normal_call_should_call_berlin_date_format_with_date_as_param_test() throws ParseException {

		// when

		when(simpleDateFormat.parse(any())).thenReturn(date);
		when(berlinDateFormat.format(Mockito.any(Date.class), any(), any())).thenReturn(new StringBuffer(dateParam));
		// then

		berlinClockTime.convertTime(dateParam);

		// expect element are the same

		verify(berlinDateFormat).format(captorDate.capture(), captorStringBuffer.capture(), captorFieldPosition.capture());
		assertEquals("Date give as parameter to berlin date format is not as expected.", date, captorDate.getValue());

	}

}
