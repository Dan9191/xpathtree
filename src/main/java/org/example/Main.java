package org.example;

import org.example.exception.PathValidationException;
import org.example.models.NodeType;
import org.example.models.SchemaNode;
import org.example.models.XPathTreeNode;
import org.example.utils.Walker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws PathValidationException {


        // Список данных после разбора схемы
        List<SchemaNode> nodes = new ArrayList<>();
        nodes.add(new SchemaNode("root.tag0.element01", NodeType.LEAF));
        nodes.add(new SchemaNode("root.tag0.element02", NodeType.LEAF));
        nodes.add(new SchemaNode("root.tag0", NodeType.ELEMENT));
        nodes.add(new SchemaNode("root.tag1", NodeType.ELEMENT));
        nodes.add(new SchemaNode("root", NodeType.ROOT));

        Walker walker = new Walker(nodes);
        walker.createXPathTree();
        System.out.println(walker.getXPathTree().getRoot().getChildrenNodes().get(0).getChildrenNodes().stream().map(XPathTreeNode::getName).collect(Collectors.toList()));
//
        XPathTreeNode xPathTreeNode = walker.findXPathElement(Arrays.asList("root.tag0".split("\\.")));

       System.out.println(xPathTreeNode.getIndex());
       System.out.println(walker.checkXPathElement(Arrays.asList("root.wwer".split("\\."))));


//        List<String> xPaths = new ArrayList<>();
//        xPaths.add("root.tag0.element01");
//        xPaths.add("root.tag0.element02");
//        xPaths.add("root.tag1[*].element11.atr11.@asd");
//
//        System.out.println(xPaths.get(xPaths.size()-1));




  //      List<String[]> xPathArrays = xPaths.stream().map(string -> string.split("\\.")).collect(Collectors.toList());

    }
}