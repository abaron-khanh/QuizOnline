/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhnhq.subject;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class SubjectDTO implements Serializable{
    private String subjectID, name;
    private int quizQuestion, numberOfAttemp, time, numberOfQuestion, numberOfTimesTested;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectID, int numberOfQuestion) {
        this.subjectID = subjectID;
        this.numberOfQuestion = numberOfQuestion;
    }

    public SubjectDTO(String subjectID, String name, int quizQuestion, int numberOfAttemp, int time) {
        this.subjectID = subjectID;
        this.name = name;
        this.quizQuestion = quizQuestion;
        this.numberOfAttemp = numberOfAttemp;
        this.time = time;
    }

    public SubjectDTO(String subjectID, int quizQuestion, int numberOfAttemp, int time) {
        this.subjectID = subjectID;
        this.quizQuestion = quizQuestion;
        this.numberOfAttemp = numberOfAttemp;
        this.time = time;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(int quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    public int getNumberOfAttemp() {
        return numberOfAttemp;
    }

    public void setNumberOfAttemp(int numberOfAttemp) {
        this.numberOfAttemp = numberOfAttemp;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public int getNumberOfTimesTested() {
        return numberOfTimesTested;
    }

    public void setNumberOfTimesTested(int numberOfTimesTested) {
        this.numberOfTimesTested = numberOfTimesTested;
    }
    
    
}
