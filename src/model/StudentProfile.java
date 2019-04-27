package model;

import java.util.Set;
import java.util.TreeSet;
import java.time.LocalDate;
import java.io.Serializable;

/**
 * The StudentProfile represents the students profile of
 * full students details of their student number, name, email,
 * submission date and all the selectedModules.
 *
 * @author P1718603X
 */
public class StudentProfile implements Serializable {

    private Course course;
    private LocalDate date;
    private Name studentName;
    private String pNumber, email;
    private Set<Module> selectedModules;
    private static final long serialVersionUID = 1L;

    // Constructor

    /**
     * Default Constructor: Creating the following,
     * pNumber empty string, a new default empty Name object, empty email string,
     * a null date and course values with a Treeset of selected modules.
     */
    public StudentProfile() {
        pNumber = "";
        studentName = new Name();
        email = "";
        date = null;
        course = null;
        selectedModules = new TreeSet<>();
    }

    /**
     * Returns the email.
     *
     * @return the email from the StudentProfile object.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the P Number.
     *
     * @return the pNumber value from the StudentProfile object.
     */
    public String getPnumber() {
        return pNumber;
    }

    /**
     * Returns the course.
     *
     * @return the course name from the StudentProfile object.
     */
    public Course getCourseOfStudy() {
        return course;
    }

    /**
     * Returns the student profile..
     *
     * @return the studentName name from the StudentProfile object.
     */
    public Name getStudentName() {
        return studentName;
    }

    /**
     * Returns the submission date.
     *
     * @return the submissionDate value from the StudentProfile object.w
     */
    public LocalDate getSubmissionDate() {
        return date;
    }

    /**
     * Sets the email.
     *
     * @param email is set by passing a string as the data type.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Clears all the selected modules
     */
    public void clearAllSelectedModules() {
        selectedModules.clear();
    }

    /**
     * Sets the pNumber.
     *
     * @param pNumber is set by passing a string as the data type.
     */
    public void setPnumber(String pNumber) {
        this.pNumber = pNumber;
    }

    /**
     * Sets the date.
     *
     * @param date is set passing a LocalDate type.
     */
    public void setSubmissionDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the courseOfStudy.
     *
     * @param course is set by passing a Course object type.
     */
    public void setCourseOfStudy(Course course) {
        this.course = course;
    }

    /**
     * Returns all the selected modules..
     *
     * @return the allSelectedModules from the StudentProfile object.
     */
    public Set<Module> getAllSelectedModules() {
        return selectedModules;
    }

    /**
     * Sets the studentName.
     *
     * @param studentName is set using a Name object as the type.
     */
    public void setStudentName(Name studentName) {
        this.studentName = studentName;
    }

    /**
     * Adds modules as selected modules.
     *
     * @param module is set passing a Module object as the type.
     * @return the selectedModules from the StudentProfile.
     */
    public boolean addToSelectedModules(Module module) {
        return selectedModules.add(module);
    }

    /**
     * Converts a readable string of the object by overriding the toString from the superclass
     * of pNumber, studentName, email, date, course, and selected modules.
     *
     * @return a well formatted string as to standard convention.
     */
    @Override
    public String toString() {
        return "StudentProfile:[pNumber=" + pNumber + ", studentName="
                + studentName + ", email=" + email + ", date="
                + date + ", course=" + course.actualToString()
                + ", selectedModules=" + selectedModules + "]";
    }

}
