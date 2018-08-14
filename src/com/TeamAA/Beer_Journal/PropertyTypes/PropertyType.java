package com.TeamAA.Beer_Journal.PropertyTypes;

import android.content.Context;
import android.util.Log;
import com.TeamAA.Beer_Journal.JournalController;
import com.TeamAA.Beer_Journal.R;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;

/**
 * This class represents the available property types.
 * To define a new propertyType, you will need to extend this class, then write a new definition in the property_definitions.xml resource
 *
 * @author Robert Kempton
 *         Date: 11/2/13
 *         Time: 11:02 AM
 */
public abstract class PropertyType {
    /**
     * All of the available propery types
     */
    public static HashMap<String, PropertyType> propertyTypes = new HashMap<String, PropertyType>();

    /**
     * The unique name of the property
     */
    public static final String NAME = "name";

    /**
     * The type of the property. The supported types can be found in {@link #loadPropertyTypes(android.content.Context)}
     * To create a new type, extend this class and write the definition in the property_definitions.xml resource.
     * Then entrylayout the
     */
    public static final String TYPE = "type";
    /**
     * The displayable name of the property
     */
    public static final String DISPLAY_NAME = "displayName";

    /**
     * This enum defines the supported property types.
     * The name of the enum value is used for type resolution
     * so it must exactly match whats defined in the property_definitions.xml resource.
     * To entrylayout a new property, entrylayout it to this enum, then entrylayout it to {@link #handleLoadingType(org.w3c.dom.Element)}
     */
    public static enum SupportedTypes {
        intRange, selectList, text
    }

    ;

    /**
     * The name of this property
     */
    protected String name;

    /**
     * The type of this property
     */
    protected String type;

    /**
     * The display name of this property
     */
    protected String displayName;

    public PropertyType(Element propertyDOMElement) {
        this.name = propertyDOMElement.getAttribute(NAME);
        this.type = propertyDOMElement.getAttribute(TYPE);
        this.displayName = propertyDOMElement.getAttribute(DISPLAY_NAME);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static void addProperty(PropertyType propertyType) {
        propertyTypes.put(propertyType.getName(), propertyType);
    }

    /**
     * This method creates a new instance of the specific property type and adds
     * it to the propertyTypes hashmap
     *
     * @param property The DOM element to build the property with
     */
    private static void handleLoadingType(Element property) {
        String type = property.getAttribute(TYPE);

        //this uses else if statements instead of a switch so we can use the enum's name
        if (type.equals(SupportedTypes.intRange.name()))
            addProperty(new IntRange(property));

        else if (type.equals(SupportedTypes.selectList.name()))
            addProperty(new SelectList(property));

        else if (type.equals(SupportedTypes.text.name()))
            addProperty(new Text(property));

        else
            Log.w(JournalController.LOG_TAG, "Unsupported property: " + property.getAttribute("type") + " defined in property_definitions.xmll");
    }

    /**
     * This method creates instances of all of the defined properties in the property_definitions.xml resource
     *
     * @param context
     */
    public static void loadPropertyTypes(Context context) {
        //parse the xml file and build the properties
        InputStream inputStream = context.getResources().openRawResource(R.xml.property_definitions);
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
            //normalize to guarantee text is represented correctly
            doc.getDocumentElement().normalize();
            NodeList properties = doc.getElementsByTagName("property");

            for (int i = 0; i < properties.getLength(); i++) {
                Element property = (Element) properties.item(i);
                handleLoadingType(property);
            }
        } catch (Exception e) {
            Log.e(JournalController.LOG_TAG, "Exception occurred in loadPropertyTypes", e);
        }
    }

}
