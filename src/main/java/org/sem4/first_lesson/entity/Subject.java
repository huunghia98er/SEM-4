package org.sem4.first_lesson.entity;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/10
 */
public class Subject {
    private Long id;
    private String name;
    private int hours;

    public Subject() {
    }

    public Subject(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    public Subject(Long id, String name, int hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
