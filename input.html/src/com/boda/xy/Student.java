package com.boda.xy;

public class Student {
    private String id;
    private String name;

    // 无参构造方法
    public Student() {
    }

    // 带参构造方法
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // 访问方法
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // 修改方法
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "'}";
    }
}