<%-- 
    Document   : edit_question
    Created on : Feb 6, 2021, 11:32:14 PM
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
        <script type="text/javascript">
            function validate()
            {
                var a = document.getElementById("a");
                var b = document.getElementById("b");
                var c = document.getElementById("c");
                var d = document.getElementById("d");
                var e = document.getElementById("e");
                var valid = true;
                if (a.value.length <= 0)
                {
                    alert("Content can not be empty!");
                    valid = false;
                } else if (b.value.length <= 0)
                {
                    alert("Option A can not be empty!");
                    valid = false;
                } else if (c.value.length <= 0)
                {
                    alert("Option B can not be empty!");
                    valid = false;
                } else if (d.value.length <= 0)
                {
                    alert("Option C can not be empty!");
                    valid = false;
                } 
                else if (e.value.length <= 0)
                {
                    alert("Option D can not be empty!");
                    valid = false;
                }
                else if (b.value == c.value || b.value == d.value || b.value == e.value )
                {
                    alert("Option A is dublicated!");
                    valid = false;
                }
                else if (c.value == b.value || c.value == d.value || c.value == e.value )
                {
                    alert("Option B is dublicated!");
                    valid = false;
                }
                else if (d.value == b.value || d.value == c.value || d.value == e.value )
                {
                    alert("Option C is dublicated!");
                    valid = false;
                }
                else if (e.value == b.value || e.value == c.value || e.value == d.value )
                {
                    alert("Option D is dublicated!");
                    valid = false;
                }
                return valid;
            }
            ;
        </script>
        <style>
            body{
                background-color: #c1dad6;
                background-size: cover;
                background-repeat: no-repeat;
            }

            #header
            {
                margin: 80px auto 20px;
                text-align: center;
                font-family: Georgia, 'Times New Roman', Times, serif;
                font-size: 200%;
                color: black;
                padding: 10px;
                background-color: #6d929b;
                opacity: 70%;
                border-radius: 10px;
                width: 20%;
                text-shadow: 5px -5px 5px white;
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

            #text_field{
                margin-bottom: 10px;
            }

            #text_field input{
                padding: 7.5px 15px;
                width: 92%;
                border: 1px solid #cccccc;
                outline: none;
                background-color: #e8d0a9;
            }

            #button{
                text-align: right;
                margin-top: 10px;
            }

            #button input{
                padding: 7.5px 15px;
                border-radius: 5px;
                background-color: #c1dad6;
                color: #6d929b;
                border: none;
                outline: none;
            }

            #home{
                text-decoration: none;
                font-family: Georgia, 'Times New Roman', Times, serif;
                color: #6d929b;
                padding: 1px 15px;
                background-color: #e8d0a9;
                border-radius: 2px;
                border: 2px dotted #6d929b;
            }

            #combo_box{
                width: 250px;
                margin-bottom: 10px;
            }

            #combo_box select{
                border: solid;
                width: 400px;
                margin: auto;
                padding: 7.5px 15px;
                border: 1px solid #bcbcbc;
                font-size: 90%;
                background-color: #e8d0a9;
            }
        </style>
        <div id="header">EDIT QUESTION</div>
        <div id="login_form">
            <form action="editQuestion" method="POST" onsubmit="return validate()">
                Content:
                <div id="text_field">
                    <input id="a" type="text" value="${param.questionContent}" name="txtContent"/> <br/>
                </div>
                Option A:
                <div id="text_field">
                    <input id="b" type="text" value="${param.optionA}" name="txtA"/> <br/>
                </div>
                Option B:
                <div id="text_field">
                    <input id="c" type="text" value="${param.optionB}" name="txtB"/> <br/>
                </div>
                Option C:
                <div id="text_field">
                    <input id="d" type="text" value="${param.optionC}" name="txtC"/> <br/>
                </div>
                Option D:
                <div id="text_field">
                    <input id="e" type="text" value="${param.optionD}" name="txtD"/> <br/>
                </div>
                Answer:
                <div id="combo_box">
                    <select name="txtAnswer" size="1">
                        <option>A</option>
                        <option>B</option>
                        <option>C</option>
                        <option>D</option>
                    </select> <br/>
                </div>
                Status:
                <div id="combo_box">
                    <select name="txtStatus" size="1">
                        <option>Activate</option>
                        <option>Deactivate</option>
                    </select> <br/>
                </div>
                <input type="hidden" value="${param.questionID}" name="txtID"/>
                <input type="hidden" value="${param.currentPage}" name="PageIndex"/>
                <input type="hidden" value="${param.subjectID}" name="subjectID"/>
                <input type="hidden" value="${param.name}" name="txtSearchName"/>
                <input type="hidden" value="${param.status}" name="txtSearchStatus"/>
                <div id="button">
                    <input type="submit" value="Update" name="actionBtn"/>
                </div>
            </form>
            <a id="home" href="loadSubject">Back to home page</a>
        </div> 
    </body>
</html>
