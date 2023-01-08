package org.example.models;

import lombok.Data;

/**
 * Дерево из элементов xPath.
 */
@Data
public class XPathTree {

    /**
     * Корень дерева.
     */
    private XPathTreeNode root;

    public XPathTree(XPathTreeNode xPathTreeNode) {
        this.root = xPathTreeNode;
    }
}
