/*
* Print Folder Structure Introduction:
  * The Print Folder Structure problem asks us to take an input of file paths and print out the files in each folder.
  * This problem requires us to create a tree-like structure representing the folder hierarchy to make it easier for us to traverse.
*
* Print Folder Structure Problem
* Given a list of file paths, print all of the files in each of the folders.
* For example:
* Input: files = [ "/webapp/assets/html/a.html", "/webapp/assets/html/b.html", "/webapp/assets/js/c.js", "/webapp/index.html" ]
* Output: -- webapp -- assets -- html -- a.html -- b.html -- js -- c.js -- index.html
*
*
* Algo: Print Folder Structure Solutions
* To solve this problem, we can create a tree-like structure to represent the folder hierarchy.
* We start with an empty root node and iterate through each file path.
* For each file path, we split it into individual folder names and traverse the tree from the root, creating new nodes as needed for each folder.
* By the end of it, we have a tree that represents the folder structure.
* To print all the files in each folder, we use a depth-first search (DFS) approach.
    *  We define a recursive function that takes a current node and an indentation level.
    * At each node, we print the folder name with the appropriate indentation. If the current node has children (sub-folders), we recursively call the function for each child, incrementing the indentation level. If a node does not have any children, it means it is a file, and we print it with the proper indentation.
* */

import java.util.*;
import java.util.stream.Collectors;

// Trie Node
class Node{
    String name;
    Map<String, Node> children;
    boolean isFile = false;

    Node(String name, boolean isFile){
        this.name = name;
        this.children = new HashMap<>();
        this.isFile = isFile;
    }
}

class PathParser {
    static List<String> parsePath(String path) {
        return Arrays.stream(path.split("/"))
                .filter(s -> !s.isEmpty())
                .toList();
    }

    private List<String> parsePath2(String path) {
        List<String> parts = new ArrayList<>();
        for (String part : path.split("/")) {
            if (!part.isEmpty()) parts.add(part);
        }
        return parts;
    }
}

// we can create a tree-like structure to represent the folder hierarchy.
class Tree{

    Node root;

    // We start with an empty root node
    Tree(){
        this.root = new Node("", false);
    }

    // mkdir
    public void insertFile(String filePath){
        String[] fileFolders = filePath.split("/");

        // iterate through each file path to insert next new node in the sequence as the child node of the parent
        Node current = root;
        for(int i = 1; i < fileFolders.length; i++){
            String fileFolder = fileFolders[i];
            if(!current.children.containsKey(fileFolder)){
                current.children.put(fileFolder, new Node(fileFolder, false));
            }
            // update current folder path as new subfolder
            current =  current.children.get(fileFolder);
        }
    }

    public boolean createPath(String filePath, String value){
        String[] fileFolders = filePath.split("/");

        // iterate through each file path to insert next new node in the sequence as the child node of the parent
        Node current = root;
        String fileFolder = root.name;
        for(int i = 1; i < fileFolders.length; i++){
           fileFolder = fileFolders[i];
            if(!current.children.containsKey(fileFolder)){
                if(i < fileFolders.length - 1){
                    return false;
                } else {
                    current.children.put(fileFolder, new Node(fileFolder, false));
                }
            }
            // update current folder path as new subfolder
            current =  current.children.get(fileFolder);
        }
        if(!current.children.containsKey(value)){
            current.children.put(value, new Node(value, true));
        }
        return true;
    }

    public String get(String filePath){
        String[] fileFolders = filePath.split("/");
        Node current = root;
        String fileFolder = root.name;
        for(int i = 1; i < fileFolders.length; i++){
            fileFolder = fileFolders[i];
            if(!current.children.containsKey(fileFolder)){
                return "-1";
            }
            current = current.children.get(fileFolder);
        }
        // String.join(", ", current.children.values());
        return current.children.values().stream().map(a -> a.name).sorted().collect(Collectors.joining(", ", "", ""));
    }

    public void dfs(Node node, String indent){
        if(!indent.isEmpty())
            System.out.print(indent + "-- " + node.name);

        for(Node child: node.children.values()){
            dfs(child, " ");
        }
    }

    public void printFiles(){
        this.dfs(root, "");
    }

    public String listFiles(String fileFolderPath){
        String[] fileFolders = fileFolderPath.split("/");
        Node current = root;
        String fileFolder = root.name;
        for(int i = 1; i < fileFolders.length; i++){
            fileFolder = fileFolders[i];
            if(!current.children.containsKey(fileFolder)){
                return "-1";
            }
            current = current.children.get(fileFolder);
        }
        // String.join(", ", current.children.values());
        return current.children.values().stream().filter(f -> f.isFile).map(f -> f.name).sorted().collect(Collectors.joining(", ", "", ""));
    }
}

public class DesignInMemFileSystem {

    public static void main(String[] args) {
        String[] files = {
                "/webapp/assets/html/a.html",
                "/webapp/assets/html/b.html",
                "/webapp/assets/js/c.js",
                "/webapp/index.html"
        };

        Tree tree = new Tree();
        for (String file : files) {
            tree.insertFile(file);
        }

        tree.printFiles();
        System.out.println();

        System.out.println(tree.createPath("/a", "1"));
        System.out.println(tree.createPath("/a/b", "2"));
        System.out.println(tree.get("/a/b"));
        System.out.println(tree.createPath("/a/b/c", "3"));
        System.out.println(tree.get("/a/b/c"));
        System.out.println(tree.get("/a/b"));
        System.out.println(tree.listFiles("/a/b"));
    }

}
