package com.example.gymappface;

public class Challenges {
    private String p_name;
    private String p_description;
    private int p_image;

    public Challenges(String name, String description, int image)
    {
        this.p_name = name;
        this.p_description = description;
        this.p_image = image;
    }

    public String getP_name() {
        return p_name;
    }

    public String getP_description() {
        return p_description;
    }

    public int getP_image() {
        return p_image;
    }
}
