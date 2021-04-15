<%-- 
    Document   : history
    Created on : Feb 19, 2021, 2:00:55 PM
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

            #logout_button input{
                background-color: #e8d0a9;
                padding: 7.7px 10px;
                border-radius: 5px;
                border: 2px solid #b7afa3;
                font-family: Georgia, 'Times New Roman', Times, serif;
            }

            ul #login_username{
                float: right;
                margin: 12px 0px 0px;
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

            #cell{
                background-color: #e8d0a9;
                border: 2px solid #b7afa3;
                padding: 3.8px 10px;
                border-radius: 5px;
            }

            #cell #search_username{
                padding: 4px 15px;
                width: 218px;
                outline: none;
                border: 1px solid #bcbcbc;
                border-radius: 2px;
            }

            #cell #search_subject{
                padding: 4px 15px;
                width: 120px;
                outline: none;
                border: 1px solid #bcbcbc;
                border-radius: 2px;
            }

            #cell #search_button{
                padding: 4px 15px;
                width: 100px;
                outline: none;
                border: 1px solid #bcbcbc;
                border-radius: 2px;
                background-color: #b7afa3;
                font-family: Georgia, 'Times New Roman', Times, serif;
            }

            .quiz{
                width: 1680px;
                display: grid;
                grid-template-columns: 400px 400px 400px 400px;
                grid-gap: 26.5px;
                padding: 10px;
                margin: 20px auto;
                background-color: #6d929b;
            }

            .quiz > div{
                background-color: #d8e6df;
                text-align: left;
                padding: 20px 30px;
                font-size: 25px;
                border: solid #79a58f;
                border-radius: 15px;
                margin: 20px;
            }

            #page_index{
                border: solid #79a58f;
                border-radius: 10px;
                font-size: 150%;
                padding: 10px;
                background-color: #d8e6df;
                margin-top: 10px;
                font-family: Georgia, 'Times New Roman', Times, serif;
                text-decoration: none;
                width: 50px;
            }

            #page_number{
                text-align: center;
            }

            #header h1{
                margin: 0px 0px 0px 112px;
                color: #e8d0a9;
            }

            #header_student h1{
                margin: 0px 0px 0px 400px;
                color: #e8d0a9;
            }

            body h2{
                text-align: center;
                font-family: Georgia, 'Times New Roman', Times, serif;
                font-size: 350%;
                opacity: 0.5;
                border-radius: 2px;
                width: 1550px;
                margin: 10px auto 10px auto;
            }
            
            body h3{
                text-align: center;
                font-size: 250%;
                opacity: 0.5;
                background: #6d929b;
                border-radius: 2px;
                width: 1700px;
                margin: 20px auto;
            }
            
            #id
            {
                border: 4px solid #6d929b;
                border-radius: 5px;
                width: 280px;
                margin: 10px auto;
                font-size: 95%;
                text-align: center;
                color: #6d929b;
            }
            
            .quiz > div h4
            {
                margin: 5px 5px 5px 40px;
                font-size: 120%;
            }
            
            #button{
                text-align: right;
                margin-top: 10px;
                padding: 7.5px 15px;
                border-radius: 5px;
                background-color: #c1dad6;
                color: #6d929b;
                border: none;
                outline: none;
                width: 292px;
                text-align: center;
            }
        </style>
        <ul>
            <c:if test="${sessionScope.role eq 'admin'}">
                <li id="home"><a href="loadSubject">Home</a></li>
                </c:if>
                <c:if test="${sessionScope.role eq 'student'}">
                <li id="home"><a href="loadStudentSubject">Home</a></li>
                </c:if>
            <li id="cell">
                <form action="searchHistory" method="POST">
                    <c:if test="${sessionScope.role eq 'admin'}">
                        <input id="search_username" type="text" name="txtSearchUsername" placeholder="type username here"/> OR
                    </c:if>
                    <select id="search_subject" size="1" name="txtSearchSubject">
                        <c:forEach items="${requestScope.subjectList}" var="dto">
                            <option>${dto.subjectID}</option>
                        </c:forEach>
                    </select>
                    <input id="search_button" type="submit" value="SEARCH" name="actionBtn"/>
                    <input type="hidden" value="1" name="PageIndex"/>
                </form>
            </li>
            <c:if test="${sessionScope.role eq 'admin'}">
                <li id="header"><h1>QUIZ ONLINE</h1></li>
                </c:if>
                <c:if test="${sessionScope.role eq 'student'}">
                <li id="header_student"><h1>QUIZ ONLINE</h1></li>
                </c:if>

            <li id="logout_button">
                <div>
                    <form action="logout" method="POST">
                        <input type="submit" value="Logout"/> <br/>
                    </form>
                </div>
            </li>
            <li id="login_username"><a href="#">Welcome,${sessionScope.fullname}</a></li>
        </ul>
        <h2>HISTORY</h2>
        <c:if test="${not empty requestScope.quizList && empty requestScope.quizSearchList}">
            <div class="quiz">
                <c:forEach items="${requestScope.quizList}" var="dto">
                    <div>
                        <h4 id="id">ID: ${dto.quizID}</h4>
                        Username:
                        <h4>${dto.username}</h4>
                        Subject's ID:
                        <h4>${dto.subjectID}</h4>
                        Grade:
                        <h4>${dto.grade} out of 10.0</h4>
                        Date:
                        <h4>${dto.date}</h4>
                        <form action="loadQuizQuestion" method="POST">
                            <input type="hidden" value="${dto.quizID}" name="txtQuizID"/>
                            <input type="hidden" value="${dto.subjectID}" name="txtSubjectID"/>
                            <input id="button" type="submit" value="Review" name="btnReview"/>
                        </form>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${countPage>1}">
                <div id="page_number">
                    <form action="loadHistory" method="POST">
                        <c:forEach begin="1" end="${countPage}" var="page">
                            <input id="page_index" type="submit" value="${page}" name="pageIndex">
                        </c:forEach>
                    </form>
                </div>
            </c:if>
        </c:if>
        <c:if test="${empty requestScope.quizSearchList && empty requestScope.quizList}">
            <h3>Nothing match your search value</h3>
        </c:if>
        <c:if test="${not empty requestScope.quizSearchList}">
            <c:if test="${txtSearchUsername eq ''}">
                <h3>Quizzes of subject: ${txtSearchSubject}</h3>
            </c:if>
            <c:if test="${txtSearchSubject eq ''}">
                <h3>Search username result: ${txtSearchUsername}</h3>
            </c:if>
            <div class="quiz">
                <c:forEach items="${requestScope.quizSearchList}" var="dto">
                    <div>
                        <h4 id="id">ID: ${dto.quizID}</h4>
                        Username:
                        <h4>${dto.username}</h4>
                        Subject's ID:
                        <h4>${dto.subjectID}</h4>
                        Grade:
                        <h4>${dto.grade} out of 10.0</h4>
                        Date:
                        <h4>${dto.date}</h4>
                        <form action="loadQuizQuestion" method="POST">
                            <input type="hidden" value="${dto.quizID}" name="txtQuizID"/>
                            <input type="hidden" value="${dto.subjectID}" name="txtSubjectID"/>
                            <input id="button" type="submit" value="Review" name="btnReview"/>
                        </form>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${countSearchPage>1}">
                <div id="page_number">
                    <form action="searchHistory" method="POST">
                        <c:forEach begin="1" end="${countSearchPage}" var="page">
                            <input id="page_index" type="submit" value="${page}" name="pageIndex">
                            <input type="hidden" value="${requestScope.txtSearchUsername}" name="txtSearchUsername">
                            <input type="hidden" value="${requestScope.txtSearchSubject}" name="txtSearchSubject">
                        </c:forEach>
                    </form>
                </div>
            </c:if>
        </c:if>
    </body>
</html>
