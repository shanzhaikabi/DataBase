package com.ssh.entity;

import javax.persistence.*;

@Entity
@Table(name = "class", schema = "darkhome", catalog = "")
public class Clazz {
    private String classId;
    private String className;

    @Id
    @Column(name = "classId", nullable = false, length = 20)
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "className", nullable = true, length = 20)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clazz clazz = (Clazz) o;

        if (classId != null ? !classId.equals(clazz.classId) : clazz.classId != null) return false;
        if (className != null ? !className.equals(clazz.className) : clazz.className != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classId != null ? classId.hashCode() : 0;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        return result;
    }
}
