package org.example;

import java.io.File;
import java.util.ArrayList;

public class Node {
    private File folder;
    private ArrayList<Node> children;
    private long size;
    private  int level;
    long limit = 50 * 1024;


    public Node(File folder, long limit){
        this.folder = folder;
        this.limit = limit;
        children = new ArrayList<>();
    }

    public File getFolder() {
        return folder;
    }
    public void addChild (Node node){
        node.setLevel(level + 1);
        children.add(node);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        String size = SizeCalculator.humanReadableSize(getSize());
        StringBuilder builder = new StringBuilder();
            builder.append(folder.getName() + " - " + size + "\n");
        for (Node child : children){
            if (child.getSize() < limit){
                continue;
            }
            builder.append("  ".repeat(level+1) + child.toString());
        }

        return builder.toString();
    }

}
