package com.wish.ws.timediff.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wish.ws.timediff.annotations.DateFormatToApply;
import com.wish.ws.timediff.domain.DateRequest;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class CustomDateDeserializer<T> extends StdDeserializer<Date> {

    private SimpleDateFormat formatter = null;

    public CustomDateDeserializer() {
        this(null);
    }

    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        String date = jsonparser.getText();
        try {

            DateFormatToApply ano= jsonparser.getParsingContext().getCurrentValue().getClass()
                    .getDeclaredField(jsonparser.getParsingContext().getCurrentName())
                    .getDeclaredAnnotation(DateFormatToApply.class);

            if(ano!=null){
                formatter= new SimpleDateFormat(ano.dateFormat());
                System.out.println(ano.dateFormat());
            }


            //System.out.println(jsonparser.getCurrentValue().getClass().getAnnotations());
             return formatter.parse(date);
        } catch (ParseException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatDateToString(Date date, String format,
                                            String timeZone) {


        // null check
        if (date == null) return null;
        // create SimpleDateFormat object with input format
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // default system timezone if passed null or empty
        if (timeZone == null || "".equalsIgnoreCase(timeZone.trim())) {
            timeZone = Calendar.getInstance().getTimeZone().getID();
        }
        // set timezone to SimpleDateFormat
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        // return Date in required format with timezone as String
        return sdf.format(date);
    }

}
