package com.TeamAA.Beer_Journal.PropertyTypes;

import org.w3c.dom.Element;

/**
 * This class represents a property that just has a single string value
 *
 * @author Robert Kempton
 *         Date: 11/2/13
 *         Time: 11:16 AM
 */
public class Text extends PropertyType {

    public Text(Element propertyDOMElement) {
        super(propertyDOMElement);
    }
}
