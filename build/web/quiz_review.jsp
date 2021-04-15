<%-- 
    Document   : quiz_review
    Created on : Feb 25, 2021, 11:09:24 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review Page</title>
    </head>
    <body>
        <style>
            body{
                background-color: #6d929b;
                background-size: cover;
                background-repeat: no-repeat;
            }

            body h1{
                text-align: center;
                font-family: Georgia, 'Times New Roman', Times, serif;
                font-size: 350%;
                opacity: 0.5;
                border-radius: 2px;
                width: 1550px;
                margin: 10px auto 10px auto;
            }

            #question_form
            {
                width: 1700px;
                margin: 10px auto;
                border: 4px solid #c1dad6;
                border-radius: 5px;
                background-color: whitesmoke;
            }

            #question_form p
            {
                margin: 10px 30px; 
            }

            #question_content
            {
                font-size: 150%;
                background: #c1dad6;
                padding: 15px;
                border-radius: 5px;
            }

            #option
            {
                font-size: 120%;
                padding-left: 50px;
            }

            #button{
                margin: 20px auto;
                width: 500px;
                background-color: #6d929b;
            }

            #button a{
                text-decoration: none;
                font-family: Georgia, 'Times New Roman', Times, serif;
                color: #6d929b;
                padding: 10px 220px;
                background-color: #c1dad6;
                border-radius: 5px;
                border: 4px solid whitesmoke;
                font-size: 150%;
            }
        </style>
        <h1>Review ${requestScope.subjectID}</h1>
        <c:forEach items="${requestScope.listQuestion}" var="dto" varStatus="counter">
            <div id="question_form">
                <c:choose>
                    <c:when test="${dto.answer eq dto.studentAnswer}">
                        <p id="question_content">Question ${counter.count}: ${dto.content}</p>
                    </c:when>
                    <c:otherwise>
                        <p style="color: red" id="question_content">Question ${counter.count}: ${dto.content}</p>
                    </c:otherwise>
                </c:choose>
                <p id="option">A. ${dto.optionA}</p>
                <p id="option">B. ${dto.optionB}</p>
                <p id="option">C. ${dto.optionC}</p>
                <p id="option">D. ${dto.optionD}</p>
                <p >Correct answer: ${dto.answer}</p>
                <p >Your choice: ${dto.studentAnswer}</p>
            </div>
        </c:forEach>
        <div id="button">
            <a href="loadHistory">Return</a>
        </div>
    </body>
</html>
