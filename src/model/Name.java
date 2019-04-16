package model;

import java.io.Serializable;

/**
 * A name has a first name and a family name.
 * There is an option to produce a full name by combining these.
 *
 * @author P1718603X
 */
public class Name implements Serializable {

    // Fields
    private String firstName, familyName;

    // Constructors

    /**
     * Default Constructor that creates the first and last name as empty strings.
     */
    public Name() {
        firstName = "";
        familyName = "";
    }

    // Methods

    /**
     * Returns the full name.
     *
     * @return the full name name from the Name object.
     */
    public String getFullName() {
        if (firstName.equals("") && familyName.equals("")) {
            return "";
        } else {
            return firstName + " " + familyName;
        }
    }

    /**
     * Returns the first name.
     *
     * @return the first name name from the Name object.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the family name.
     *
     * @return the last name name from the Name object.
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Creates a Name by setting the first name and family name when
     * a new object is created.
     *
     * @param firstName  the firstName set as the string data type.
     * @param familyName the lastName set as the string data type.
     */
    public Name(String firstName, String familyName) {
        this.firstName = firstName;
        this.familyName = familyName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName is set by passing a string as the data type.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the family name.
     *
     * @param familyName is set by passing a string as the data type.
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * Converts a readable string of the object by overriding the toString from the superclass
     * of first name and family name.
     *
     * @return a well formatted string as to standard convention.
     */
    @Override
    public String toString() {
        return "Name:[firstName=" + firstName + ", familyName=" + familyName + "]";
    }
}
