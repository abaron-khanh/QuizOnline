/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhnhq.quizdetail;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author PC
 */
public class QuizDetailDTO implements Serializable{
    private String quizID, subjectID, username;
    private float grade;
    private Date date;

    public QuizDetailDTO() {
    }

    public QuizDetailDTO(String quizID, String subjectID, String username, float grade, Date date) {
        this.quizID = quizID;
        this.subjectID = subjectID;
        this.username = username;
        this.grade = grade;
        this.date = date;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
