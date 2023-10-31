public class User {
    private String surName;
    private String name;
    private String middleName;
    private String birthDate;
    private Integer phoneNumber;
    private String sex;

    public User(String surName, String name, String middleName, String birthDate, Integer phoneNumber, String sex) {
        this.surName = surName;
        this.name = name;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    public String getSurName() {
        return surName;
    }

    @Override
    public String toString() {
        return "<" + surName + ">" +
                "<" + name + ">" +
                "<" + middleName + ">" +
                "<" + birthDate + ">" +
                "<" + phoneNumber + ">"+
                "<" + sex + ">";
    }
}
