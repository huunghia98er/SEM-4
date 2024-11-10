package org.sem4.first_lesson.entity;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/07
 */

public class Student {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Long classId;

    public Student() {
    }

    public Student(String name, String email, String phone, String address, Long classId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.classId = classId;
    }

    public Student(Long id, String name, String email, String phone, String address, Long classId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.classId = classId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
