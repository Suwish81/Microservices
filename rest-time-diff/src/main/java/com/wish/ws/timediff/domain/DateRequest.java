package com.wish.ws.timediff.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wish.ws.timediff.annotations.DateFormatToApply;
import com.wish.ws.timediff.converter.CustomDateDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class DateRequest {
     @JsonDeserialize(using = CustomDateDeserializer.class)
     @DateFormatToApply(dateFormat = "yyyy-MM-dd")
     private Date inpuDate;

}
