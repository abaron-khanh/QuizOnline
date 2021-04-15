<%-- 
    Document   : quiz_result
    Created on : Feb 18, 2021, 9:54:31 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <style>
            body
            {
                background-color: #c1dad6;
                background-size: cover;
                background-repeat: no-repeat;
            }

            #title
            {
                text-align: center;
                margin-top: 200px;
                font-size: 80px;
                font-family: Georgia, 'Times New Roman', Times, serif;
                color: #6d929b;
                margin-bottom: 30px;
            }
            
            #login_form{
                width: 100%;
                max-width: 400px;
                margin: 20px auto;
                background-color: #6d929b;
                padding: 15px;
                border: 2px dotted #cccccc;
                border-radius: 10px;
                opacity: 0.7;
            }
            
            #box_title{
                color: #c1dad6;
                opacity: 1;
                background-color: #6d929b;
                width: 150px;
                text-align: center;
                border: 3px solid #c1dad6;
                border-radius: 4px;
            }
            
            h4{
                margin-left: 30px;
                font-size: 150%;
            }
            
            #create_new{
                text-decoration: none;
                font-family: Georgia, 'Times New Roman', Times, serif;
                color: #c1dad6;
                padding: 1px 15px;
                background-color: #6d929b;
                border-radius: 2px;
                border: 3px solid #c1dad6;
            }
        </style>
        <div id="title">Your Quiz Result</div>
        <div id="login_form">
            <h2 id="box_title">${requestScope.subjectID}</h2>
            <h4>Username: ${sessionScope.username}</h4>
            <h4>Correct answers: ${requestScope.correct_answer} / ${requestScope.number_of_question}</h4>
            <h4>Grade: ${requestScope.quiz_result} out of 10.0</h4>
            <a id="create_new" href="loadStudentSubject">Back to home page</a>
        </div>
    </body>
</html>
