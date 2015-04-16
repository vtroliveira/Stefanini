/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.ApplicationScoped;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@ApplicationScoped
public class Utils implements Serializable {
    private final String pattern = "dd/MM/yyyy";
 
    public String onlyDate(final Date date) {
        return getDefault().format(date);
    }
    
    public Date onlyDate(final String date) throws ParseException {
        return getDefault().parse(date);
    }
    
    public DateTime toDateTime(final String date) {
        return getDefaultJoda().parseDateTime(date);
    }
    
    public Date today() {
        final DateTime dt = new DateTime()
                                .hourOfDay().setCopy(0)
                                .minuteOfHour().setCopy(0)
                                .secondOfMinute().setCopy(0)
                                .toDateTime();
        
        return dt.toDate();
    }
    
    private SimpleDateFormat getDefault() {
        return new SimpleDateFormat(pattern);
    }
    
    private DateTimeFormatter getDefaultJoda() {
        return DateTimeFormat.forPattern(pattern);
    }
    
    public String replaceSpacesToHtml(final String text) {
        final StringBuilder sb = new StringBuilder();
        for (final String str : text.split("\n")) {
            sb.append(str.replace("\n", "").trim()).append("<br/>");
        }
        return sb.toString();
    }
}
