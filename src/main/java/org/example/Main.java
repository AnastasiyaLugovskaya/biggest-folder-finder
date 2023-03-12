package org.example;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

import static org.example.Node.*;
import static org.example.SizeCalculator.*;

public class Main {

    public static void main(String[] args) {


        String folderPath = "C:\\Users\\nasta\\Desktop\\Coding";
        File file = new File(folderPath);
        Node root = new Node(file, 50*1024);
        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root);
        long duration = (System.currentTimeMillis() - start);
        System.out.println(duration + " ms");
        System.out.println("MachineSize = " + getSizeFromHumanReadable(humanReadableSize(root.getSize())));
    }


}
