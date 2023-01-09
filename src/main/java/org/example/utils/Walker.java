package org.example.utils;

import lombok.Data;
import org.example.exception.PathValidationException;
import org.example.models.SchemaNode;
import org.example.models.XPathTree;
import org.example.models.XPathTreeNode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Обходчик по дереву элементов xPath.
 */
@Data
public class Walker {

    /**
     * Список данных после разбора схемы.
     */
    private List<SchemaNode> schemaNodes;

    /**
     * Дерево из элементов xPath.
     */
    private XPathTree xPathTree;

    //todo при боевой реализации требуется добавить список только из xpath для оптимизации поиска путей,
    // т.е. дерево будет строиться только если имеется несущесвующий xPath

    public Walker(List<SchemaNode> schemaNodes) {
        this.schemaNodes = schemaNodes;
    }

    /**
     * Добавляет указанные элементы в существующее дереве из элементов xPath.
     */
    public void createXPathTree() {
        if (this.xPathTree == null) {
            this.xPathTree = new XPathTree(new XPathTreeNode(this.schemaNodes.get(0).getElements().get(0)));
        }
        for (int i = 0; i <= this.schemaNodes.size() - 1; i++) {
            addXPathElement(this.schemaNodes.get(i), i);
        }
    }

    /**
     * Добавляет узел в дерево XPathTree, попутно создавая требуемые узлы. Если узел уже создан, то назначает ему индекс.
     *
     * @param schemaNode Данные узла после разбора схемы.
     * @param index      Индекс, по которому узел из дерева XPathTree будет ссылаться на элемент SchemaNode.
     */
    private void addXPathElement(SchemaNode schemaNode, int index) {
        List<String> xPathElements = schemaNode.getElements();
        XPathTreeNode currentNode = xPathTree.getRoot();
        for (int i = 0; i < xPathElements.size(); i++) {
            String xPathElement = xPathElements.get(i);
             if (i == 0) {
                 //нулевой элемент пропускается, так как корень для xml уже создан
                 continue;
             }
            Optional<XPathTreeNode> childrenNode = currentNode.findChildren(xPathElement);
            currentNode = childrenNode.isPresent() ? childrenNode.get() : currentNode.addAndReturnChildrenNode(xPathElement);
             if (i == xPathElements.size() - 1) {
                 //если это крайний элемент xPath для текущей ноды - назначаем ему индекс для ссылки на ноду
                 currentNode.setIndex(Optional.of(index));
             }
        }
    }

    /**
     * Проводит поиск узла xPath в дереве XPathTree.
     *
     * @param xPathElements Элементы xPath, по которым проверяется наличие узла.
     * @return Узел xPath (если найден).
     */
    public XPathTreeNode findXPathElement(List<String> xPathElements) throws PathValidationException {
        XPathTreeNode currentNode = xPathTree.getRoot();
        for (int i = 0; i <= xPathElements.size() - 1; i++) {
            String xPathElement = xPathElements.get(i);
            if (i == 0) {
                //нулевой элемент пропускается
                //todo допилить проверку для нулевого элемента
                continue;
            }
            XPathTreeNode childrenNode = currentNode.findChildren(xPathElement).orElse(null);
            if (childrenNode == null) {
                throw new PathValidationException("Указанный узел не найден, возможные варианты: " +
                        currentNode.getChildrenNodes().stream().map(XPathTreeNode::getName).collect(Collectors.toList()));
            } else {
                currentNode = childrenNode;
            }
        }
        return currentNode;
    }

    /**
     * Проверка наличия узла xPath в дереве XPathTree. В случае отсутствия узла выводиться сообщение об ошибке.
     *
     * @param xPathElements Элементы xPath, по которым проверяется наличие узла.
     * @return Узел xPath (если найден).
     */
    public Optional<PathValidationException> checkXPathElement(List<String> xPathElements) {
        XPathTreeNode currentNode = xPathTree.getRoot();
        for (int i = 0; i <= xPathElements.size() - 1; i++) {
            String xPathElement = xPathElements.get(i);
            if (i == 0) {
                //нулевой элемент пропускается
                //todo допилить проверку для нулевого элемента
                continue;
            }
            XPathTreeNode childrenNode = currentNode.findChildren(xPathElement).orElse(null);
            if (childrenNode == null) {
                return  Optional.of(new PathValidationException("Указанный узел не найден, возможные варианты: "
                   + currentNode.getChildrenNodes().stream().map(XPathTreeNode::getName).collect(Collectors.toList())));
            } else {
                currentNode = childrenNode;
            }
        }
        return Optional.empty();
    }
}
