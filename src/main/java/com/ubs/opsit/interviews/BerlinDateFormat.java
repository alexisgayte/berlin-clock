package com.ubs.opsit.interviews;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;

public class BerlinDateFormat extends DateFormat {

	private static final long serialVersionUID = 1L;

	final static int HOUR_FOR_THE_24_LOGIC = 24;
	private boolean pairDay24hLogic;

	public BerlinDateFormat() {
		this(false);
	}

	public BerlinDateFormat(boolean pairDay24h) {
		super();
		pairDay24hLogic = pairDay24h;
	}

	@Override
	public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {

		// TODO need more story regarding the logic of 24H vs 00H which are
		// different.
		if (pairDay24hLogic && date.getDate() % 2 == 0) {
			return pairDay24hLogic(date, toAppendTo);
		}

		// Check it out if we can use the time lib and java >7
		toAppendTo	.append(formatSecondsRow(date.getSeconds()))
					.append(System.lineSeparator())
					.append(formatHours1Row(date.getHours()))
					.append(System.lineSeparator())
					.append(formatHours2Row(date.getHours()))
					.append(System.lineSeparator())
					.append(formatMinutes1Row(date.getMinutes()))
					.append(System.lineSeparator())
					.append(formatMinutes2Row(date.getMinutes()));
		return toAppendTo;
	}

	@Override
	public Date parse(String source, ParsePosition pos) {
		throw new NotImplementedException();
	}

	private StringBuffer pairDay24hLogic(Date date, StringBuffer toAppendTo) {

		return toAppendTo	.append(formatSecondsRow(date.getSeconds()))
							.append(System.lineSeparator())
							.append(formatHours1Row(HOUR_FOR_THE_24_LOGIC))
							.append(System.lineSeparator())
							.append(formatHours2Row(HOUR_FOR_THE_24_LOGIC))
							.append(System.lineSeparator())
							.append(formatMinutes1Row(date.getMinutes()))
							.append(System.lineSeparator())
							.append(formatMinutes2Row(date.getMinutes()));
	}

	private String formatSecondsRow(int seconds) {
		if (seconds % 2 == 0)
			return "Y";
		else
			return "O";
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
