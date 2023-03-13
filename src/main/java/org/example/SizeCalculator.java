package org.example;

import java.io.File;

public class SizeCalculator {
    private static long multiplier = 1024;


    public static String humanReadableSize (long size) {
        String humanSize = "";
        double value = 0.0;
        if (size < multiplier){
            humanSize = Math.round(size*100)/100.0 + "B";
        } else if (size<(multiplier * multiplier)) {
            value = size/multiplier;
            humanSize = Math.round(value*100)/100.0 + "Kb";
        } else if (size >= (multiplier * multiplier) && size < (multiplier*multiplier*multiplier)) {
            value = size / (multiplier * multiplier);
            humanSize = Math.round(value*100)/100.0 + "Mb";
        } else {
            value = size / (multiplier*multiplier*multiplier);
            humanSize =  Math.round(value*100)/100.0 + "Gb";
        }
        return humanSize;
    }
    public static long getSizeFromHumanReadable(String size){
        System.out.println(size);
//        System.exit(0);
        long machineSize = 0;
        String regexB = "\\d+B";
        String regexKb = "\\d+Kb";
        String regexMb = "\\d+Mb";
        String regexGb = "\\d+Gb";
        if (size.matches(regexB)){
            machineSize = Long.parseLong(size.replaceAll("[^0-9]+", ""));
        } else if (size.matches(regexKb)) {
            machineSize = Long.parseLong(size.replaceAll("[^0-9]+", "")) *
                    multiplier;
        } else if (size.matches(regexMb)) {
            machineSize = Long.parseLong(size.replaceAll("[^0-9]+", "")) *
                    multiplier * multiplier;
        } else if (size.matches(regexGb)){
            machineSize = Long.parseLong(size.replaceAll("[^0-9]+", "")) *
                    multiplier * multiplier * multiplier;
        }
        return machineSize;
    }
}
