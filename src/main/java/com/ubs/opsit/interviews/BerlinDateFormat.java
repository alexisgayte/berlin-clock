package com.ubs.opsit.interviews;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;

public class BerlinDateFormat extends DateFormat {

	private static final long serialVersionUID = 1L;

	final static int HOUR_FOR_THE_24_LOGIC = 24;
	private boolean pairDay24hLogic;
	private String lineSeparator;

	public BerlinDateFormat() {
		this(false, System.lineSeparator());
	}

	public BerlinDateFormat(boolean pairDay24h, String lineSeparator) {
		super();
		setCalendar(Calendar.getInstance());
		setNumberFormat(NumberFormat.getInstance());
		pairDay24hLogic = pairDay24h;
		this.lineSeparator = lineSeparator;

	}

	@Override
	public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {

		calendar.setTime(date);
		// TODO need more story regarding the logic of 24H vs 00H which are
		// different.
		if (pairDay24hLogic && calendar.get(Calendar.DAY_OF_MONTH) % 2 == 0) {
			return pairDay24hLogic(date, toAppendTo);
		}

		// Check it out if we can use the time lib and java >7
		toAppendTo	.append(formatSecondsRow(calendar.get(Calendar.SECOND)))
					.append(lineSeparator)
					.append(formatHours1Row(calendar.get(Calendar.HOUR_OF_DAY)))
					.append(lineSeparator)
					.append(formatHours2Row(calendar.get(Calendar.HOUR_OF_DAY)))
					.append(lineSeparator)
					.append(formatMinutes1Row(calendar.get(Calendar.MINUTE)))
					.append(lineSeparator)
					.append(formatMinutes2Row(calendar.get(Calendar.MINUTE)));
		return toAppendTo;
	}

	@Override
	public Date parse(String source, ParsePosition pos) {
		throw new NotImplementedException();
	}

	private StringBuffer pairDay24hLogic(Date date, StringBuffer toAppendTo) {

		return toAppendTo	.append(formatSecondsRow(calendar.get(Calendar.SECOND)))
							.append(System.lineSeparator())
							.append(formatHours1Row(HOUR_FOR_THE_24_LOGIC))
							.append(System.lineSeparator())
							.append(formatHours2Row(HOUR_FOR_THE_24_LOGIC))
							.append(System.lineSeparator())
							.append(formatMinutes1Row(calendar.get(Calendar.MINUTE)))
							.append(System.lineSeparator())
							.append(formatMinutes2Row(calendar.get(Calendar.MINUTE)));
	}

	private String formatSecondsRow(int seconds) {
		return (seconds % 2 == 0) ? "Y" : "O";
	}

	private String formatHours1Row(int hour) {
		int numberOfRedCells = hour / 5;
		return formatRow("R", numberOfRedCells, 4);
	}

	private String formatHours2Row(int hour) {
		int numberOfRedCells = hour % 5;
		return formatRow("R", numberOfRedCells, 4);
	}

	private String formatMinutes1Row(int minutes) {
		char[] row = formatRow("Y", minutes / 5, 11).toCharArray();
		changeToRedIfYellow(row, 2);
		changeToRedIfYellow(row, 5);
		changeToRedIfYellow(row, 8);
		return new String(row);
	}

	private String formatMinutes2Row(int minutes) {
		int numberOfRedCells = minutes % 5;
		return formatRow("Y", numberOfRedCells, 4);
	}

	private String formatRow(String light, int times, int length) {
		return StringUtils.repeat(light, times) + StringUtils.repeat("O", length - times);
	}

	private void changeToRedIfYellow(char[] row, int index) {
		if (row[index] == 'Y')
			row[index] = 'R';
	}

}
