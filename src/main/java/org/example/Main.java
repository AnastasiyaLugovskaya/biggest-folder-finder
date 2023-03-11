package org.example;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    public static void main( String[] args ) {


        String folderPath = "D:\\Installed games";
        File file = new File(folderPath);
        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println("Size = " + size);
        System.out.println(getFolderSize(file));
        long duration = (System.currentTimeMillis() - start)/1000;
        System.out.println(duration + " seconds");
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

}
