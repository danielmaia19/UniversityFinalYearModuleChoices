package model;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.io.Serializable;

/**
 * A Course class is used to represent a course and modules associated to it.
 * Add modules to the course and return all the modules in the course.
 * Normal course name setters and getters are possible.
 *
 * @author P1718603X
 */
public class Course implements Serializable, Comparable<Course> {

    // Fields
    private String courseName;
    private Map<String, Module> modules;
    private static final long serialVersionUID = 1L;

    // Constructors

    /**
     * Creates a Course by setting the courseName when a new object is created.
     *
     * @param courseName the course name as a string data type.
     */
    public Course(String courseName) {
        this.courseName = courseName;
        modules = new HashMap<>();
    }

    /**
     * Returns the course name.
     *
     * @return the course name from the Course object.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the course name.
     *
     * @param name is set using a string as the data type.
     */
    public void setCourseName(String name) {
        courseName = name;
    }

    /**
     * Returns a single module by code.
     *
     * @return the module by code.
     */
    public Module getSingleModuleByCode(String code) {
        return modules.get(code);
    }

    /**
     * Returns all the modules.
     *
     * @return all the modules from the Course object.
     */
    public Collection<Module> getAllModulesOnCourse() {
        return modules.values();
    }

    /**
     * Adds module to the course object.
     *
     * @param module is set passing a Module object.
     */
    public void addModuleToCourse(Module module) {
        modules.put(module.getModuleCode(), module);
    }

    /**
     * Creates a readable string of the course name and its modules.
     *
     * @return a well formatted string of the course name and modules associated to it.
     */
    public String actualToString() {
        return "Course:[courseName=" + courseName + ", modules=" + modules + "]";
    }

    /**
     * Converts a readable string of the object by overriding the toString from the superclass
     * of Course name.
     *
     * @return a well formatted string as to standard convention.
     */
    @Override
    public String toString() {
        //a non-standard toString that simply returns the course name,
        //so as to assist in displaying courses correctly in a ComboBox<Course>
        return courseName;
    }

    /**
     * Compares course name by implementing the Comparable interface.
     *
     * @param other uses a object of type Course.
     * @return a int as the result to compare the objects.
     */
    @Override
    public int compareTo(Course other) {
        int result = this.getCourseName().compareTo(other.getCourseName());

        if (result == 0) {
            result = this.getCourseName().compareTo(other.getCourseName());
        }

        return result;
    }
}
