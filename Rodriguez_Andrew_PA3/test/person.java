/**
 * @author Andrew Rodriguez
 * @version 1.0
 * @since February 9 2020
 * <p>
 * The person class is an abstract class containing the attributes that need to be carried across all two types of person
 * that is customer and branch manager.
 */

public abstract class person {

    /**
     * Attributes that carry for all different classes of type person.
     * The attributes consist of basic information that every person should have.
     */
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;


    /**
     * Constructors used for person class. Tried to incorporate all cases including the default.
     */
    public person() {
    }

    public person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public person(String firstName, String lastName, String dateOfBirth, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getters and setters used for account class.
     */

    /**
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
