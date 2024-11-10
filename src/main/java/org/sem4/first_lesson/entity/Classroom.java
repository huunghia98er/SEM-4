package org.sem4.first_lesson.entity;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/10
 */

public class Classroom {
    private Long id;
    private String name;

    public Classroom() {
    }

    public Classroom(String name) {
        this.name = name;
    }

    public Classroom(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
