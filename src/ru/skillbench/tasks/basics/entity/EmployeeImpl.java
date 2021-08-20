package ru.skillbench.tasks.basics.entity;

public class EmployeeImpl implements Employee{
    private int salary = 1000;
    private String firstName;
    private String lastName;
    private Employee manager;


    public int getSalary() {
        return salary;
    }

    public void increaseSalary(int value) {
        salary += value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getManagerName() {
        if (manager != null) {
            return manager.getFullName();
        }
        return "No manager";
    }

    public Employee getTopManager() {
        Employee m = this;
        if(manager != null){
            m = manager.getTopManager();
        }
        return m;
    }

    public static void main (String[] args){
        Employee a = new EmployeeImpl();
        Employee b = new EmployeeImpl();
        Employee c = new EmployeeImpl();
        a.setManager(b);
        b.setManager(c);
        Employee t = a.getTopManager();
    }
}
