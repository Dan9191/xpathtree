package org.example;

import org.example.models.NodeType;
import org.example.models.SchemaNode;
import org.example.models.XPathTreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        // Список данных после разбора схемы
        List<SchemaNode> nodes = new ArrayList<>();
        nodes.add(new SchemaNode("root.tag0.element01", NodeType.LEAF));
        nodes.add(new SchemaNode("root.tag0.element02", NodeType.LEAF));
        nodes.add(new SchemaNode("root.tag0", NodeType.ELEMENT));
        nodes.add(new SchemaNode("root", NodeType.ROOT));








        List<String> xPaths = new ArrayList<>();
        xPaths.add("root.tag0.element01");
        xPaths.add("root.tag0.element02");
        xPaths.add("root.tag1[*].element11.atr11.@asd");

        System.out.println(xPaths.get(xPaths.size()-1));




  //      List<String[]> xPathArrays = xPaths.stream().map(string -> string.split("\\.")).collect(Collectors.toList());

    }
}