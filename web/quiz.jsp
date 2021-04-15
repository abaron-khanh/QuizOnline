<%-- 
    Document   : student
    Created on : Feb 17, 2021, 1:50:26 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <script type="text/javascript">
            var totalSecond = ${requestScope.time};
            var min = Math.floor(totalSecond / 60);
            var dom = document.getElementById("timer");

            function setTime()
            {
                var dom = document.getElementById("timer");
                if ((totalSecond % 60) == 0) {
                    min = min - 1;
                }
                dom.value = (min) + " minutes " + ((totalSecond - 1) % 60) + " seconds";
                document.getElementById("time").value = (min) + " minutes " + ((totalSecond - 1) % 60) + " seconds";
                totalSecond = totalSecond - 1;
                if ((min * 60) == 0 && totalSecond == 0) {
                    finishQuiz();
                }
                setTimeout("setTime()", 1000);
            }

            function finishQuiz()
            {
                window.location.replace("quizGraded");
            }
        </script>
    </head>
    <body onload="setTime()">
        <style>
            body{
                background-color: #c1dad6;
                background-size: cover;
                background-repeat: no-repeat;
            }

            #question_box
            {
                border: 2px solid black;
                border-radius: 5px;
                width: 1350px;
                float: right;
                background: #6d929b;
            }

            #question_box label
            {
                font-size: 120%;
            }

            #question_box input
            {
                margin: 15px 20px;
            }

            #number_grid
            {
                border: 2px solid black;
                border-radius: 5px;
                width: 300px;
                float: left;
                display: grid;
                grid-template-columns: 45px 45px 45px 45px 45px;
                grid-gap: 10px;
                background: #6d929b;
            }

            #number_grid input
            {
                width: 45px;
                height: 40px;
                margin: 10px 17.2px 10px;
            }

            #subject_name
            {
                width: 1700px;
                height: 50px;
                text-align: center;
                font-family: Georgia, 'Times New Roman', Times, serif;
                margin: 10px auto;
                font-size: 250%;
            }

            h4
            {
                width: 300px;
                text-align: center;
                font-family: Georgia, 'Times New Roman', Times, serif;
                font-size: 120%;
                margin-bottom: 10px;
                margin-top: 0px;
            }

            h5
            {
                width: 300px;
                text-align: left;
                font-family: Georgia, 'Times New Roman', Times, serif;
                font-size: 120%;
                margin-top: 50px;
                margin-bottom: 5px;
            }

            #timer_box
            {
                border: 2px solid black;
                border-radius: 5px;
                width: 300px;
                float: left;
            }

            #timer_box input
            {
                background-color: #c1dad6;
                border: none;
                padding: 5px;
                font-size: 120%;
                color: black;
                text-align: center;
                width: 290px;
            }

            ul {
                list-style-type: none;
                overflow: hidden;
                background: #6d929b;
                padding: 5px;
                border-radius: 2px;
                width: 1690px;
                margin: auto;
            }

            li {
                float: left;
                padding: 5px;
                margin: 5px;
            }

            ul #logout_button{
                float: right;
            }

            ul #login_username{
                float: right;
                margin: 12px 0px 0px;

            }

            #home{
                background-color: #e8d0a9;
                border: 2px solid #b7afa3;
                padding: 7.7px 10px;
                border-radius: 5px;
            }
            #home a{
                background-color: #e8d0a9;
                text-decoration: none;
                font-family: Georgia, 'Times New Roman', Times, serif;
                color: black;
            }

            #logout_button input{
                background-color: #e8d0a9;
                padding: 7.7px 10px;
                border-radius: 5px;
                border: 2px solid #b7afa3;
                font-family: Georgia, 'Times New Roman', Times, serif;
            }

            #login_username a{
                background-color: #b7afa3;
                padding: 5px 10px;
                border-radius: 5px;
                border: 2px solid #e8d0a9;
                font-family: Georgia, 'Times New Roman', Times, serif;
                text-decoration: none;
                color: #ffffff;
            }

            #page_body
            {
                width: 1700px;
                margin: 20px auto;
            }

            #next_button
            {
                padding: 7.5px 150px;
                border-radius: 5px;
                background-color: #c1dad6;
                color: black;
                border: none;
                outline: none;
                margin: 10px;
                text-decoration: none;
                float: right;
                font-family: Georgia, 'Times New Roman', Times, serif;
            }

            #previous_button
            {
                padding: 7.5px 150px;
                border-radius: 5px;
                background-color: #c1dad6;
                color: black;
                border: none;
                outline: none;
                margin: 10px;
                text-decoration: none;
                float: left;
                font-family: Georgia, 'Times New Roman', Times, serif;
            }

            #submit_button
            {
                border: 2px solid black;
                border-radius: 5px;
                width: 300px;
                float: left;
                margin-top: 10px;
                margin-right: 1100px;
                background-color: #6d929b;
            }

            #submit_button input
            {
                background-color: #6d929b;
                border: none;
                padding: 5px;
                font-size: 120%;
                color: black;
                text-align: center;
                width: 300px;
                margin: auto;
                font-family: Georgia, 'Times New Roman', Times, serif;
            }

            #question_content
            {
                background-color: #c1dad6;
                margin: 5px;
                padding: 5px;
                font-size: 150%;
                font-family: Georgia, 'Times New Roman', Times, serif;
                border-radius: 5px;
            }
            
            #header h1{
                margin: 0px 200px 0px 715px;
                color: #e8d0a9;
            }
        </style>
        <ul>
            <li id="header"><h1>QUIZ ONLINE</h1></li>
            <li id="login_username"><a href="#">Welcome,${sessionScope.fullname}</a></li>
        </ul>
        <h1 id="subject_name">${sessionScope.subject.getSubjectID()}</h1>
        <div id="page_body">
            <div id="question_form">
                <form action="updateQuizProgress" method="POST">
                    <input id="time" type="hidden" name="remainingTime"/>
                    <input type="hidden" name="questionIndex" value="${requestScope.currentIndex}"/>
                    <div id="question_box">
                        <p id="question_content">Question ${requestScope.currentIndex + 1}: ${requestScope.currentQuestion.getContent()}</p>
                        <!--///-->
                        <c:choose>
                            <c:when test="${requestScope.currentQuestion.getStudentAnswer() eq 'A'}">
                                <input type="radio" id="A" name="answer" value="A" checked="true">
                                <label for="A">${requestScope.currentQuestion.getOptionA()}</label><br>
                                <input type="radio" id="B" name="answer" value="B">
                                <label for="B">${requestScope.currentQuestion.getOptionB()}</label><br>
                                <input type="radio" id="C" name="answer" value="C">
                                <label for="C">${requestScope.currentQuestion.getOptionC()}</label><br>
                                <input type="radio" id="D" name="answer" value="D">
                                <label for="D">${requestScope.currentQuestion.getOptionD()}</label><br>
                            </c:when>
                            <c:when test="${requestScope.currentQuestion.getStudentAnswer() eq 'B'}">
                                <input type="radio" id="A" name="answer" value="A">
                                <label for="A">${requestScope.currentQuestion.getOptionA()}</label><br>
                                <input type="radio" id="B" name="answer" value="B" checked="true">
                                <label for="B">${requestScope.currentQuestion.getOptionB()}</label><br>
                                <input type="radio" id="C" name="answer" value="C">
                                <label for="C">${requestScope.currentQuestion.getOptionC()}</label><br>
                                <input type="radio" id="D" name="answer" value="D">
                                <label for="D">${requestScope.currentQuestion.getOptionD()}</label><br>
                            </c:when>
                            <c:when test="${requestScope.currentQuestion.getStudentAnswer() eq 'C'}">
                                <input type="radio" id="A" name="answer" value="A">
                                <label for="A">${requestScope.currentQuestion.getOptionA()}</label><br>
                                <input type="radio" id="B" name="answer" value="B">
                                <label for="B">${requestScope.currentQuestion.getOptionB()}</label><br>
                                <input type="radio" id="C" name="answer" value="C" checked="true">
                                <label for="C">${requestScope.currentQuestion.getOptionC()}</label><br>
                                <input type="radio" id="D" name="answer" value="D">
                                <label for="D">${requestScope.currentQuestion.getOptionD()}</label><br>
                            </c:when>
                            <c:when test="${requestScope.currentQuestion.getStudentAnswer() eq 'D'}">
                                <input type="radio" id="A" name="answer" value="A">
                                <label for="A">${requestScope.currentQuestion.getOptionA()}</label><br>
                                <input type="radio" id="B" name="answer" value="B">
                                <label for="B">${requestScope.currentQuestion.getOptionB()}</label><br>
                                <input type="radio" id="C" name="answer" value="C">
                                <label for="C">${requestScope.currentQuestion.getOptionC()}</label><br>
                                <input type="radio" id="D" name="answer" value="D" checked="true">
                                <label for="D">${requestScope.currentQuestion.getOptionD()}</label><br>
                            </c:when>
                            <c:otherwise>
                                <input type="radio" id="A" name="answer" value="A">
                                <label for="A">${requestScope.currentQuestion.getOptionA()}</label><br>
                                <input type="radio" id="B" name="answer" value="B">
                                <label for="B">${requestScope.currentQuestion.getOptionB()}</label><br>
                                <input type="radio" id="C" name="answer" value="C">
                                <label for="C">${requestScope.currentQuestion.getOptionC()}</label><br>
                                <input type="radio" id="D" name="answer" value="D">
                                <label for="D">${requestScope.currentQuestion.getOptionD()}</label><br>
                            </c:otherwise>                        
                        </c:choose>
                        <c:if test="${requestScope.currentIndex != 0}">
                            <input id="previous_button" type="submit" value="Previous" name="quizAction"/>
                        </c:if>
                        <c:if test="${requestScope.currentIndex < requestScope.quizSize - 1}">
                            <input id="next_button" type="submit" value="Next" name="quizAction"/>
                        </c:if>
                    </div>
                    <br/>
                    <h4>QUESTION</h4>
                    <div id="number_grid">
                        <c:forEach begin="1" end="${requestScope.quizSize}" var="number">
                            <input type="submit" value="${number}" name="quizAction"/>
                        </c:forEach>
                    </div>
                    <br/>
                    <h5>Time left:</h5>
                    <div id="timer_box">
                        <input type="text" id="timer" disabled=""/>
                    </div>
                    <div id="submit_button">
                        <input type="submit" value="Submit" name="quizAction"/>
                    </div>
                </form>
            </div>

        </div>
    </body>
</html>
