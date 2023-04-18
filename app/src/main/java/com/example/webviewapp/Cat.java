package com.example.webviewapp;

public class Cat {
    private String name = "";

    Cat(){

    }

    Cat(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getInfo(){
        return "This cat is " + name;
    }
}