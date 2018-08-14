package com.TeamAA.Beer_Journal.Database.DTO;

import com.TeamAA.Beer_Journal.JournalController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents a unique Journal Entry and all of its properties
 *
 * @author Robert Kempton
 *         Date: 9/22/13
 *         Time: 9:07 PM
 */
public class JournalEntryDTO {
    /**
     * Id should never be assigned manually, it will get assigned by the dao when it's saved
     */
    private int id;
    /**
     * A hashmap of all of the properties that belong to this journal entry. The key is the property name.
     */
    private HashMap<String, EntryPropertyDTO> properties = new HashMap<String, EntryPropertyDTO>() {
    };

    /**
     * This constructor should be used to create new records
     */
    public JournalEntryDTO() {
    }

    /**
     * This constructor should only be used by the DAO to return already existing records
     *
     * @param id The id of the journalEntry
     */
    public JournalEntryDTO(int id) {
        this.id = id;
        loadProperties();
    }

    public JournalEntryDTO(int id, HashMap<String, EntryPropertyDTO> properties) {
        this.id = id;
        this.properties = properties;
    }

    /**
     * This method loads all of the properties for this journalEntry
     */
    private void loadProperties() {
        properties = JournalController.getEntryPropertyDAO()
                .getEntryProperties(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntryPropertyDTO getProperty(String propertyName) {
        return properties.containsKey(propertyName) ? properties.get(propertyName) : null;
    }

    public HashMap<String, EntryPropertyDTO> getProperties() {
        return properties;
    }

    /**
     * This method adds or updates a property to this journalEntry
     *
     * @param property       The property to update or entrylayout
     * @param saveToDatabase Whether or not to save the property to the database
     */
    public void setProperty(EntryPropertyDTO property, boolean saveToDatabase) {
        if (saveToDatabase) {
            properties.put(property.getPropertyName(), JournalController.getEntryPropertyDAO().saveOrUpdate(property));

        } else {
            properties.put(property.getPropertyName(), property);
        }
    }

    /**
     * This returns a list of all of the available properties on this journalEntry
     *
     * @return the list of available property names
     */
    public List<String> getPropertyNames() {
        if (properties.size() > 0) {
            List<String> propNames = new ArrayList<String>();
            for (String s : properties.keySet()) {
                propNames.add(s);
            }
            return propNames;
        } else
            return null;
    }
}
