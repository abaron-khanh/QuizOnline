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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <style>
            body{
                background-color: #c1dad6;
                background-size: cover;
                background-repeat: no-repeat;
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
                padding: 5.6px 10px;
                border-radius: 5px;
                margin-top: 10px;
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

            #page_table{
                border-radius: 10px;
                margin: 50px auto 0px;
                heigth: auto;
                width: 1700px;
                border: solid;
                background-color:#d8e6df
            }

            #page_table th{
                font-family:Georgia, 'Times New Roman', Times, serif;
                font-size: 30px;
                padding: 5px;
                background-color: #6d929b;
            }

            #page_table td{
                font-family:Georgia, 'Times New Roman', Times, serif;
                font-size: 25px;
                padding: 5px;
                text-align:center;
                height: 60px;
            }

            #action input{
                padding: 7.5px 15px;
                border-radius: 5px;
                background-color: #009999;
                color: #ffffff;
                border: none;
                outline: none;
                margin: auto;
                text-decoration: none;
            }
            
            #history input{
                background-color: #e8d0a9;
                padding: 7.7px 10px;
                border-radius: 5px;
                border: 2px solid #b7afa3;
                font-family: Georgia, 'Times New Roman', Times, serif;
            }
            
            #header h1{
                margin: 0px 200px 0px 565px;
                color: #e8d0a9;
            }
        </style>
        <ul>
            <li id="home"><a href="loadStudentSubject">Home</a></li>
            <li id="history">
                <form action="loadHistory" method="POST">
                    <input type="submit" value="History" name="historyAction"/>
                </form>
            </li>
            <li id="header"><h1>QUIZ ONLINE</h1></li>
            <li id="logout_button">
                <div>
                    <form action="logout" method="POST">
                        <input type="submit" value="Logout"/> <br/>
                    </form>
                </div>
            </li>
            <li id="login_username"><a href="#">Welcome,${sessionScope.fullname}</a></li>
        </ul>
        <table id="page_table" border="1">
            <thead>
                <tr>
                    <th>Subject's ID</th>
                    <th>Name</th>
                    <th>Number of question</th>
                    <th>Attends</th>
                    <th>Time(minute)</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.subjectList}" var="dto">
                    <tr>
                        <td>${dto.subjectID}</td>
                        <td>${dto.name}</td>
                        <td>${dto.quizQuestion}</td>
                        <td>${dto.numberOfTimesTested}/${dto.numberOfAttemp}</td>
                        <td>${dto.time}</td>
                        <td>
                            <c:if test="${dto.quizQuestion > 0 and dto.numberOfTimesTested != dto.numberOfAttemp and dto.numberOfTimesTested < dto.numberOfAttemp}">
                                <form action="loadQuiz" method="POST">
                                    <div id="action">
                                        <input type="submit" value="Attemp quiz now"/>
                                    </div>
                                    <input type="hidden" value="${dto.subjectID}" name="subjectID"/>
                                    <input type="hidden" value="${dto.quizQuestion}" name="numberOfQuestion"/>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>

