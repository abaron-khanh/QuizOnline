<%-- 
    Document   : home
    Created on : Feb 4, 2021, 1:26:43 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
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

            ul #create_button{
                float: right;
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

            #cell #search_value{
                padding: 4px 15px;
                width: 218px;
                outline: none;
                border: 1px solid #bcbcbc;
                border-radius: 2px;
            }

            #cell #search_status{
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

            #create_button input{
                background-color: #e8d0a9;
                padding: 7.7px 10px;
                border-radius: 5px;
                border: 2px solid #b7afa3;
                font-family: Georgia, 'Times New Roman', Times, serif;
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
                font-size: 18px;
                padding: 5px;
            }

            #page_table td{
                font-family:Georgia, 'Times New Roman', Times, serif;
                font-size: 15px;
                padding: 5px;
                text-align:center;
            }

            #page_table_question{
                border-radius: 10px;
                margin: 20px auto 0px;
                heigth: auto;
                width: 1700px;
                border: solid;
                background-color:#d8e6df
            }

            #page_table_question th{
                font-family:Georgia, 'Times New Roman', Times, serif;
                font-size: 18px;
                padding: 5px;
                text-align: left;
            }

            #page_table_question td{
                font-family:Georgia, 'Times New Roman', Times, serif;
                font-size: 15px;
                padding: 5px;
                text-align:left;
            }

            #text_field input{
                padding: 7.5px 15px;
                width: 80%;
                border: 1px solid #cccccc;
                outline: none;
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

            #table_question_edit input{
                padding: 7.5px 150px;
                border-radius: 5px;
                background-color: #009999;
                color: #ffffff;
                border: none;
                outline: none;
                margin: auto;
                text-decoration: none;
            }

            #table_question_delete input{
                padding: 7.5px 142px;
                border-radius: 5px;
                background-color: #009999;
                color: #ffffff;
                border: none;
                outline: none;
                margin: auto;
                text-decoration: none;
            }

            #page_index{
                border: solid #79a58f;
                border-radius: 10px;
                font-size: 150%;
                padding: 5px;
                background-color: #e8d0a9;
                margin: 1px;
                font-family: Georgia, 'Times New Roman', Times, serif;
                text-decoration: none;
            }

            #page_number{
                text-align: center;
                margin-top: 25px;
                margin-bottom: 50px;
                text-decoration: none;
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

            body h1{
                text-align: center;
                font-family: Georgia, 'Times New Roman', Times, serif;
                font-size: 350%;
                opacity: 0.5;
                border-radius: 2px;
                width: 1550px;
                margin: 10px auto 10px auto;
            }
            
            #history input{
                background-color: #e8d0a9;
                padding: 7.7px 10px;
                border-radius: 5px;
                margin-top: 0px;
                border: 2px solid #b7afa3;
                font-family: Georgia, 'Times New Roman', Times, serif;
            }
            
            body h2{
                text-align: center;
                font-family: Georgia, 'Times New Roman', Times, serif;
                font-size: 250%;
                opacity: 0.5;
                background: #6d929b;
                border-radius: 2px;
                width: 1700px;
                margin: 20px auto;
            }
        </style>
        <ul>
            <li id="home"><a href="loadSubject">Home</a></li>
            <li id="history">
                <form action="loadHistory" method="POST">
                    <input type="submit" value="History" name="historyAction"/>
                </form>
            </li>
            <li id="cell">
                <form action="searchNameOrStatus" method="POST">
                    <input id="search_value" type="text" name="txtSearchName" placeholder="something..."/> OR
                    <select id="search_status" size="1" name="txtSearchStatus">
                        <option>Activate</option>
                        <option>Deactivate</option>
                    </select>
                    <input id="search_button" type="submit" value="SEARCH" name="actionBtn"/>
                    <input type="hidden" value="1" name="PageIndex"/>
                </form>
            </li>
            <li id="logout_button">
                <div>
                    <form action="logout" method="POST">
                        <input type="submit" value="Logout"/> <br/>
                    </form>
                </div>
            </li>
            <li id="login_username"><a href="#">Welcome,${sessionScope.fullname}</a></li>
            <li id="create_button">
                <form action="loadSubject" method="POST">
                    <input type="submit" value="Create new Question" name="actionBtn"/>
                </form>
            </li>
        </ul>
        <h1>QUIZ ONLINE</h1>
        <c:if test="${empty requestScope.listQuestion and not empty requestScope.subjectList}">
            <table id="page_table" border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th style="width: 400px">Name</th>
                        <th style="width: 200px">Questions in Quiz</th>
                        <th style="width: 200px">Attends</th>
                        <th style="width: 200px">Time(minute)</th>
                        <th style="width: 200px">Number Of Question</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.subjectList}" var="dto">
                        <tr>
                    <form action="editSubject" method="POST">
                        <td style="text-align: center">
                            ${dto.subjectID}
                            <input type="hidden" value="${dto.subjectID}" name="txtSubjectID"/>
                        </td>
                        <td>
                            ${dto.name}               
                        </td>
                        <td>
                            <div id="text_field">
                                <input type="number" value="${dto.quizQuestion}" min="0" max="${dto.numberOfQuestion}" name="txtSubjectQuestion"/>
                            </div>
                        </td>
                        <td>
                            <div id="text_field">
                                <input type="number" value="${dto.numberOfAttemp}" min="1"name="txtSubjectAttemp"/>
                            </div>
                        </td>
                        <td>
                            <div id="text_field">
                                <input type="number" value="${dto.time}" min="1" max="180" name="txtSubjectTime"/>
                            </div>
                        </td>
                        <td>${dto.numberOfQuestion}</td>
                        <td>
                            <div id="action">
                                <input type="submit" value="Update Quiz Detail" name="actionBtn"/>
                            </div>
                        </td>
                    </form>
                    <td>
                        <form action="searchBySubject" method="POST">
                            <div id="action">
                                <input type="submit" value="View Questions" name="actionBtn"/>
                            </div>
                            <input type="hidden" value="${dto.subjectID}" name="subjectID"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${not empty requestScope.listQuestion}">
    <c:if test="${not empty subjectID}">
        <h3>Questions of ${subjectID}</h3>
    </c:if>
    <c:if test="${empty subjectID}">
        <c:if test="${name eq ''}">
            <h3>Questions status: ${status}</h3>
        </c:if>
        <c:if test="${status eq ''}">
            <h3>Search result: ${name}</h3>
        </c:if>
    </c:if>
    <c:forEach items="${requestScope.listQuestion}" var="dto">
        <table id="page_table_question" border="1">
            <thead>
                <tr>
                    <th style="width: 150px">${dto.id} - ${dto.subject}</th>
                    <th>${dto.content}</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>A.</td>
                    <td>${dto.optionA}</td>
                </tr>
                <tr>
                    <td>B.</td>
                    <td>${dto.optionB}</td>
                </tr>
                <tr>
                    <td>C.</td>
                    <td>${dto.optionC}</td>
                </tr>
                <tr>
                    <td>D.</td>
                    <td>${dto.optionD}</td>
                </tr>
                <tr>
                    <td>Answer: ${dto.answer}</td>
                    <td>
                        <form action="edit_question.jsp" method="POST">
                            <div id="table_question_edit">
                                <input type="submit" value="Edit" name="actionBtn"/>
                            </div>
                            <input type="hidden" value="${dto.id}" name="questionID"/>
                            <input type="hidden" value="${dto.content}" name="questionContent"/>
                            <input type="hidden" value="${dto.optionA}" name="optionA"/>
                            <input type="hidden" value="${dto.optionB}" name="optionB"/>
                            <input type="hidden" value="${dto.optionC}" name="optionC"/>
                            <input type="hidden" value="${dto.optionD}" name="optionD"/>
                            <input type="hidden" value="${dto.answer}" name="answer"/>
                            <input type="hidden" value="${dto.status}" name="status"/>
                            <input type="hidden" value="${requestScope.currentPage}" name="currentPage"/>
                            <input type="hidden" value="${requestScope.subjectID}" name="subjectID"/>
                            <input type="hidden" value="${requestScope.name}" name="name"/>
                            <input type="hidden" value="${requestScope.status}" name="status"/>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>Status: ${dto.status}</td>
                    <td>
                        <form action="deleteQuestion" method="POST">
                            <div id="table_question_delete">
                                <input type="submit" value="Delete" name="actionBtn"/>
                            </div>
                            <input type="hidden" value="${dto.id}" name="questionID"/>
                            <input type="hidden" value="${dto.subject}" name="subject"/>
                            <input type="hidden" value="${requestScope.currentPage}" name="PageIndex"/>
                            <input type="hidden" value="${requestScope.subjectID}" name="subjectID"/>
                            <input type="hidden" value="${requestScope.name}" name="txtSearchName"/>
                            <input type="hidden" value="${requestScope.status}" name="txtSearchStatus"/>
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>
    </c:forEach>
    <c:if test="${countHomePage>1}">
        <div id="page_number">     
            <c:forEach begin="1" end="${countHomePage}" var="page">
                <c:if test="${not empty subjectID}">
                    <a id="page_index" href="searchBySubject?PageIndex=${page}&subjectID=${subjectID}">${page}</a>
                </c:if>
                <c:if test="${empty subjectID}">
                    <a id="page_index" href="searchNameOrStatus?PageIndex=${page}&txtSearchName=${name}&txtSearchStatus=${status}">${page}</a>
                </c:if>
            </c:forEach>
        </div>
    </c:if>
</c:if>
<c:if test="${empty requestScope.listQuestion and empty requestScope.subjectList}">
    <h2>Nothing match your search value</h2>
</c:if>
</body>
</html>
