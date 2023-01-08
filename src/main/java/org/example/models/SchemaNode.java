package org.example.models;

import lombok.Data;

import java.util.List;

/**
 * Данные узла после разбора схемы.
 */
@Data
public class SchemaNode {

    /**
     * Имя узла.
     */
    private String name;

    /**
     * Тип узла.
     */
    private NodeType nodeType;

    /**
     * Путь к узлу из элементов xPath.
     */
    private List<String> elements;

    /**
     * Конструктор для формирования узла.
     *
     * @param xPath    Абсолютный(ненулевой) путь по схеме до элемента.
     * @param nodeType Тип элемента.
     */
    public SchemaNode(String xPath, NodeType nodeType) {
        this.elements = List.of(xPath.split("\\."));
        this.name = elements.get(elements.size() - 1);
        this.nodeType = nodeType;
    }
}
