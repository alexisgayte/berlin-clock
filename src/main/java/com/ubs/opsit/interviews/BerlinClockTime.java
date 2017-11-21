package com.ubs.opsit.interviews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BerlinClockTime implements TimeConverter {


	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
	private BerlinDateFormat berlinDateFormat = new BerlinDateFormat(true);

    @Override
    public String convertTime(String aTime) {
    	Date date;
    	try {
    		date = simpleDateFormat.parse(aTime);
    	} catch (ParseException e){
    		// TODO shall we need an exception system; if so we need to define it in the interface.
    		// TODO need to ask for more story.
    		throw new RuntimeException();

    	}

    	return berlinDateFormat.format(date);

    }

}