package com.example.offline_answer;

public class Player {

    String name;
    String answer;
    boolean ansFlag;
    boolean chkFlag;

    void setName(String newName){
        this.name = newName;
    }

    void setAnswer(String newAnser){
        this.answer = newAnser;
        ansFlag = !ansFlag;
    }

    void switchChkFlag(){
        chkFlag = !chkFlag;
    }
}