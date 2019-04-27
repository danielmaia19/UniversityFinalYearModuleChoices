package model;

import java.util.Objects;
import java.io.Serializable;

/**
 * Module class is used to represent a Module.
 * A module consists moduleCode, moduleName, credits, whether its mandatory and its run plan.
 * This class overrides the hashcode, equals, compareTo and toString methods
 *
 * @author P1718603X
 */
public class Module implements Comparable<Module>, Serializable {

    //Fields
    private int credits;
    private Delivery runPlan;
    private boolean mandatory;
    private String moduleCode, moduleName;
    private static final long serialVersionUID = 1L;

    /**
     * Creates a Module by setting the module code, module name, credits, mandatory, and run plan when
     * a new object is created.
     *
     * @param moduleCode the module code set as a string data type.
     * @param moduleName the module name set as a string data type.
     * @param credits    the credits set as a int data type.
     * @param mandatory  the mandatory set whether or not the module is mandatory as a boolean.
     * @param runPlan    the run plan set as a enum data type.
     */
    public Module(String moduleCode, String moduleName, int credits, boolean mandatory, Delivery runPlan) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.credits = credits;
        this.mandatory = mandatory;
        this.runPlan = runPlan;
    }

    /**
     * Converts Mandatory from a string to a boolean value of true or false.
     *
     * @param value to be passed to convert to the corresponding boolean value.
     * @return true if the value is yes or false if the value is no.
     */
    public boolean convertMandatoryToBoolean(String value) {
        boolean result;

        if (value.toLowerCase().equals("yes")) result = true;
        else if (value.toLowerCase().equals("no")) {
            result = false;
        } else {
            throw new IllegalArgumentException("The was a problem with the mandatory value");
        }

        return result;
    }

    /**
     * Returns the credits.
     *
     * @return the credits from the Module object.
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Returns the relevant run plan enum.
     *
     * @return the run plan enum from the Module object.
     */
    public Delivery getRunPlan() {
        return runPlan;
    }

    /**
     * Returns a boolean of whether or not the module is mandatory.
     *
     * @return a boolean of whether or not the module is mandatory.
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Returns the module code.
     *
     * @return the moduleCode from the Module object.
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Returns the module name.
     *
     * @return the moduleName from the Module object.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Creates a readable string of the module code, module name, credits, mandatory and run plan.
     *
     * @return a well formatted string of the moduleCode, moduleName, credits, mandatory and runPlan.
     */
    public String actualToString() {
        return "Module:[moduleCode=" + moduleCode + ", moduleName=" + moduleName +
                ", credits=" + credits + ", mandatory=" + mandatory + ", runPlan=" + runPlan + "]";
    }

    /**
     * Sets the credits.
     *
     * @param credits is set using a string as the data type.
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Sets the run plan.
     *
     * @param runPlan is set passing a Delivery enum object type.
     */
    public void setRunPlan(Delivery runPlan) {
        this.runPlan = runPlan;
    }

    /**
     * Sets it mandatory.
     *
     * @param mandatory is set passing a boolean as the data type.
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * Sets the module code.
     *
     * @param moduleCode is set passing a String as the data type.
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Sets the module name.
     *
     * @param moduleName is set passing a String as the data type.
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Converts Mandatory from boolean value to a string value of yes or no.
     *
     * @param mandatory boolean passed to convert to the corresponding string.
     * @return yes if the value is true or no if the value is false.
     */
    public String convertMandatoryToString(boolean mandatory) {
        if (mandatory) {
            return "yes";
        } else if (mandatory == false) {
            return "no";
        } else {
            throw new IllegalArgumentException("There was a incorrect value for delivery " + "\"" + mandatory + "\"");
        }
    }

    /**
     * Converts a readable string of the object by overriding the toString from the superclass
     * of module code and module name.
     *
     * @return a well formatted string as to standard convention.
     */
    @Override
    public String toString() {
        //a non-standard toString that simply returns the module code and name,
        //so as to assist in displaying modules correctly in a ListView<Module> in the view
        //-Note- you may customise this if you wish to do so.
        return moduleCode + " : " + moduleName;
    }

    /**
     * Compares this Module to the specified object. The result is true if and
     * only if the argument is not null and is a Module object that has the same
     * mandatory, credits, moduleCode, moduleName and runPlan as this object.
     *
     * @param obj the object to compare this Module against.
     * @return true if the given object represents a Module equivalent to this Module, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Module other = (Module) obj;

        return this.mandatory == other.mandatory && this.credits == other.credits &&
                this.moduleCode.equals(other.moduleCode) && this.moduleName.equals(other.moduleName) &&
                this.runPlan.equals(other.runPlan);

    }

    /**
     * Compares module code, credits, mandatory, module name and run plan
     * by implementing the Comparable interface.
     *
     * @param other uses a object of type Module.
     * @return a int as the result to compare the objects.
     */
    @Override
    public int compareTo(Module other) {
        int result = this.moduleCode.compareTo(other.moduleCode);

        if (result == 0) {
            result = Integer.compare(this.credits, other.credits);

            if (result == 0) {
                result = Boolean.compare(other.mandatory, this.mandatory);

                if (result == 0) {
                    result = this.moduleName.compareTo(other.moduleName);

                    if (result == 0) {
                        result = this.runPlan.compareTo(other.runPlan);
                    }
                }

            }
        }

        return result;
    }

    /**
     * Returns a hash of mandatory, credits, module code, module name and run plan.
     *
     * @return a hash of mandatory, credits, moduleCode, moduleName and runPlan.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mandatory, credits, moduleCode, moduleName, runPlan);
    }
}
