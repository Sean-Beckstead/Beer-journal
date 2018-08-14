package com.TeamAA.Beer_Journal.PropertyTypes;

import android.util.Log;
import com.TeamAA.Beer_Journal.JournalController;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class represents a property that has some numerical value that is within the specific range.
 * You can figure out how it's supposed to be displayed by looking at the displayType.
 *
 * @author Robert Kempton
 *         Date: 11/2/13
 *         Time: 11:14 AM
 */
public class IntRange extends PropertyType {
    /**
     * The range xml tag
     */
    public static final String RANGE = "range";
    /**
     * The min xml tag
     */
    public static final String MIN = "min";
    /**
     * The max xml tag
     */
    public static final String MAX = "max";
    /**
     * The displayType xml tag
     */
    public static final String DISPLAY_TYPE = "displayType";
    /**
     * The display type of slider, as in a slider bar
     */
    public static final String SLIDER = "slider";
    /**
     * The display type of stars, as in star rating
     */
    public static final String STARS = "stars";
    /**
     * The minimum value of the range
     */
    private int minValue;
    /**
     * The maximum value of the range
     */
    private int maxValue;
    /**
     * The display type of the property
     */
    private String displayType;

    public IntRange(Element propertyDOMElement) {
        super(propertyDOMElement);
        buildRange(propertyDOMElement);
        buildDisplayType(propertyDOMElement);
    }

    /**
     * This method gets the min and max values for the range
     *
     * @param propertyDOMElement The property DOM element
     */
    private void buildRange(Element propertyDOMElement) {
        NodeList rangeEnds = propertyDOMElement.getElementsByTagName(RANGE).item(0).getChildNodes();
        for (int i = 0; i < rangeEnds.getLength(); i++) {
            Element element = (Element) rangeEnds.item(i);

            if (element.getTagName().equalsIgnoreCase(MIN))
                this.minValue = Integer.parseInt(element.getTextContent());

            else if (element.getTagName().equalsIgnoreCase(MAX))
                this.maxValue = Integer.parseInt(element.getTextContent());

            else
                Log.w(JournalController.LOG_TAG, "Invalid range tag:" + element.getTagName());
        }
    }

    /**
     * This method gets the display type of the property
     *
     * @param propertyDOMElement The property DOM element
     */
    private void buildDisplayType(Element propertyDOMElement) {
        this.displayType = propertyDOMElement.getElementsByTagName(DISPLAY_TYPE).item(0).getTextContent();
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getDisplayType() {
        return displayType;
    }
}
