package com.iiitb.academic.util;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class MainClass {

    public static void main(String args[]){

        ZonedDateTime dt = ZonedDateTime.now();
        System.out.println("Time:"+dt);
        System.out.println("Formatted:"+Utility.getFormattedTime(dt));


        OffsetDateTime odt = OffsetDateTime.now();
        System.out.println("ODT: "+odt);
        System.out.println("Formatted:"+odt.format(Utility.formatter));

    }
}
