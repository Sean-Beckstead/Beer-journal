package com.TeamAA.Beer_Journal.PropertyTypes;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.List;

/**
 * This class represents a property that contains a list of values that the user can choose from
 *
 * @author Robert Kempton
 *         Date: 11/2/13
 *         Time: 11:15 AM
 */
public class SelectList extends PropertyType {
    /**
     * The values xml tag
     */
    public static final String VALUES = "values";
    /**
     * The list of values to choose from
     */
    private List<String> values;

    public SelectList(Element propertyDOMElement) {
        super(propertyDOMElement);
        buildValues(propertyDOMElement);
    }

    /**
     * This method builds the list of values
     *
     * @param propertyDOMElement The property DOM element
     */
    private void buildValues(Element propertyDOMElement) {
        NodeList xmlValues = propertyDOMElement.getElementsByTagName(VALUES).item(0).getChildNodes();
        for (int i = 0; i < xmlValues.getLength(); i++) {
            values.add(xmlValues.item(i).getTextContent());
        }
    }

    public List<String> getValues() {
        return values;
    }
}
