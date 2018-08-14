import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Employee {
      private int ID;
      private String lastName;
      private String name;
      private String surname;
      private LocalDate birthDay;
      private String position;
      private String subDivision;
      private int roomNumber;
      private int officialTelefon;
      private String eMail;
      private BigDecimal salary;
      private LocalDate dateOfHiring;
      private String notes;

     static Set<Employee> employees = new LinkedHashSet<>();

    public Employee() { }

    public Employee(int ID, String lastName, String name, String surname, LocalDate birthDay, String position,
                    String subDivision, int roomNumber, int officialTelefon, String eMail,
                    BigDecimal salary, LocalDate dateOfHiring, String notes) {
        this.ID = ID;
        this.lastName = lastName;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.position = position;
        this.subDivision = subDivision;
        this.roomNumber = roomNumber;
        this.officialTelefon = officialTelefon;
        this.eMail = eMail;
        this.salary = salary;
        this.dateOfHiring = dateOfHiring;
        this.notes = notes;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSubDivision() {
        return subDivision;
    }

    public void setSubDivision(String subDivision) {
        this.subDivision = subDivision;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getOfficialTelefon() {
        return officialTelefon;
    }

    public void setOfficialTelefon(int officialTelefon) {
        this.officialTelefon = officialTelefon;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
        salary.setScale(2,BigDecimal.ROUND_DOWN);
    }

    public LocalDate getDateOfHiring() {
        return dateOfHiring;
    }

    public void setDateOfHiring(LocalDate dateOfHiring) {
        this.dateOfHiring = dateOfHiring;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return ID +"; " + lastName + "; "+ name + "; " + surname +"; " + birthDay + "; "+ position +
                "; " + subDivision  + "; " + roomNumber + "; "+ officialTelefon + "; "+ eMail +"; " + salary +
               "; " + dateOfHiring + "; "+ notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return ID == employee.ID &&
                roomNumber == employee.roomNumber &&
                officialTelefon == employee.officialTelefon &&
                salary == employee.salary &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(surname, employee.surname) &&
                Objects.equals(birthDay, employee.birthDay) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(subDivision, employee.subDivision) &&
                Objects.equals(eMail, employee.eMail) &&
                Objects.equals(dateOfHiring, employee.dateOfHiring) &&
                Objects.equals(notes, employee.notes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID, lastName, name, surname, birthDay, position,
                subDivision, roomNumber, officialTelefon, eMail, salary, dateOfHiring, notes);
    }
}
