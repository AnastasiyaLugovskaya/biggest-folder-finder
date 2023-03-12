package org.example;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    private static long multiplier = 1024;
    public static void main( String[] args ) {



        String folderPath = "D:\\Installed games";
        File file = new File(folderPath);
        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println("Size = " + humanReadableSize(size));
        System.out.println(getFolderSize(file));
        long duration = (System.currentTimeMillis() - start)/1000;
        System.out.println(duration + " seconds");
        System.out.println("MachineSize = " + getSizeFromHumanReadable(humanReadableSize(size)));
    }

    public static long getFolderSize(File folder){
        if(folder.isFile()){
            return folder.length();
        }
        long sum = 0;
        File [] files = folder.listFiles();
        for (File file : files){
            sum += getFolderSize(file);
        }
        return sum;
    }
    public static String humanReadableSize (long size) {
        String humanSize = "";
        double value = 0.0;
        if (size < multiplier){
            humanSize = size + "B";
        } else if (size<(multiplier * multiplier)) {
            value = size/multiplier;
            humanSize = Math.round(value) + "Kb";
        } else if (size >= (multiplier * multiplier) && size < (multiplier*multiplier*multiplier)) {
            value = size / (multiplier * multiplier);
            humanSize = Math.round(value) + "Mb";
        } else {
            value = size / (multiplier*multiplier*multiplier);
            humanSize =  Math.round(value) + "Gb";
        }
        return humanSize;
    }
    public static long getSizeFromHumanReadable(String size){
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
