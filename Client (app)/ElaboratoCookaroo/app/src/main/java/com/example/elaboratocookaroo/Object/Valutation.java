package com.example.elaboratocookaroo.Object;

public class Valutation {
    private String username,dish,comment;
    private int grade;
    public Valutation(String username,String dish,String comment,int grade){
        this.username=username;
        this.dish=dish;
        this.comment=comment;
        this.grade=grade;
    }
    public String getUsername() {
        return username;
    }

    public String getDish() {
        return dish;
    }

    public String getComment() {
        return comment;
    }

    public int getGrade() {
        return grade;
    }
}
