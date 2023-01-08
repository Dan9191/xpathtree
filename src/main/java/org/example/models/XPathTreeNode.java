package org.example.models;

import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * Узел, используемый для строительства дерева из элементов xPath.
 */
@Data
public class XPathTreeNode {

    //todo надо будет убрать родителя
    /**
     * Родитель узла (пуст для корневого элемента xPath).
     */
    private XPathTreeNode parent;

    /**
     * Имя узла.
     */
    private String name;

    /**
     * Индекс по которому можно найти узел в плоском списке после обхода схемы.
     */
    private int index;

    /**
     * Список дочерних узлов (пуст для листового элемента xPath).
     */
    private List<XPathTreeNode> childrenNodes;

    public XPathTreeNode(String name) {
        this.name = name;
    }

    /**
     * Поиск узла среди дочерних узлов по его имени.
     *
     * @param childrenName Имя дочернего узла.
     * @return Ркзультат поиска.
     */
    public Optional<XPathTreeNode> findChildren(String childrenName) {
        return childrenNodes.stream()
                .filter(children -> children.getName().equals(childrenName))
                .findFirst();
    }

    /**
     * Создает дочерний узел у текущего узла и возвращает его.
     *
     * @param childrenName Имя нового дочернего узла
     * @return Дочерний узел.
     */
    public XPathTreeNode addAndReturnChildrenNode(String childrenName) {
        XPathTreeNode childrenNode = new XPathTreeNode(name);
        childrenNodes.add(childrenNode);
        return childrenNode;
    }
}
