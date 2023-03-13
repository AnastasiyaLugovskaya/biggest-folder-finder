package org.example;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

import static org.example.Node.*;
import static org.example.SizeCalculator.*;

public class Main {

    public static void main(String[] args) {
//        String [] argums = {"-d", "C:\\Users\\nasta\\Desktop\\", "-l",  "50Kb"};
        ParametersBag bag = new ParametersBag(args);


        String folderPath = bag.getPath();
        File file = new File(folderPath);
        Node root = new Node(file, bag.getLimit());
        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root);
        long duration = (System.currentTimeMillis() - start);

    }


}
