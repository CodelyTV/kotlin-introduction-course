package com.codely.demo;

import java.util.Objects;

public class KittenJava {
    private final String name;

    KittenJava(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KittenJava that = (KittenJava) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "KittenJava{" +
                "name='" + name + '\'' +
                '}';
    }
}



