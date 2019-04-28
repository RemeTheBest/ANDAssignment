package com.example.gymappface;

public class Stat {
    private String Name;
    private String Value;

    public Stat(String name) {
        this.Name = name;
    }

    public String getName() {
        return this.Name;
    }

    public String getValue() {
        return this.Value;
    }

    public void setValue(String value) {
        this.Value = value;
    }
}
