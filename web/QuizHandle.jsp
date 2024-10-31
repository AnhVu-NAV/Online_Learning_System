<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quiz Handle</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
                background-color: #f7f7f7;
            }
            .container {
                width: 80%;
                display: flex;
                flex-direction: row;
                margin-top: 20px;
            }
            .quiz-header {
                width: 100%;
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #e0e0e0;
                padding: 10px;
            }
            .timer {
                font-size: 1.2em;
                font-weight: bold;
                color: #333;
            }
            .quiz-title {
                font-size: 1.5em;
                font-weight: bold;
                color: #444;
            }
            .quiz-navigation {
                width: 20%;
                background-color: #f0f0f0;
                padding: 15px;
                box-sizing: border-box;
            }
            .navigation-title {
                font-weight: bold;
                margin-bottom: 10px;
            }
            .question-list {
                display: grid;
                grid-template-columns: repeat(5, 1fr);
                gap: 10px;
            }
            .question-list button {
                width: 100%;
                padding: 10px;
                background-color: #fff;
                border: 1px solid #ccc;
                cursor: pointer;
            }
            .question-list button:hover {
                background-color: #ddd;
            }
            .question-content {
                width: 80%;
                padding: 15px;
                background-color: #fff;
                box-sizing: border-box;
            }
            .question-title {
                font-size: 1.2em;
                margin-bottom: 10px;
            }
            .options {
                list-style: none;
                padding: 0;
            }
            .options li {
                margin-bottom: 8px;
            }
            .action-buttons {
                display: flex;
                justify-content: space-between;
                margin-top: 15px;
            }
            .mark-button {
                background-color: #5a5a5a;
                color: white;
                padding: 8px 16px;
                border: none;
                cursor: pointer;
            }
            .mark-button:hover {
                background-color: #444;
            }
            .view-progress-button {
                background-color: #6a5acd;
                color: white;
                padding: 8px 16px;
                border: none;
                cursor: pointer;
                margin-top: 15px;
                width: 100%;
            }
            .view-progress-button:hover {
                background-color: #5748c4;
            }
            .submit-button {
                background-color: #b18cd9;
                color: white;
                padding: 10px 16px;
                border: none;
                cursor: pointer;
            }
            .submit-button:hover {
                background-color: #9e78c1;
            }
        </style>
    </head>
    <body>
        <!-- Quiz Header -->
        <div class="quiz-header">
            <div class="timer">Time left <span>0:24:16</span></div>
            <div class="quiz-title">Quiz Title: Notification</div>
        </div>

        <!-- Main Container -->
        <div class="container">
            <!-- Quiz Navigation -->
            <div class="quiz-navigation">
                <div class="navigation-title">Quiz Navigation</div>
                <div class="question-list">
                    <c:forEach var="i" begin="1" end="${totalQuestions}">

                        <c:set var="isAnswered" value="${sessionScope.answeredQuestions != null && sessionScope.answeredQuestions[i] != null}" />
                        <a href="QuizHandleController?quizId=${question.quizId}&questionNumber=${i}">
                            <button
                                style="<c:if test='${i == questionNumber}'>background-color: #ddd;</c:if> <c:if test='${isAnswered}'>background-color: yellow;</c:if>">
                                ${i}
                            </button>
                        </a>
                    </c:forEach>
                </div>

                <button class="view-progress-button">View Progress</button>
            </div>

            <!-- Question Content -->
            <div class="question-content">
                <div class="question-title">
                    Câu hỏi ${questionNumber}: ${question.content}
                </div>

                
                <c:set var="selectedOption" value="${sessionScope.answeredQuestions[questionNumber]}" />

                <ul class="options">
                    <c:forEach var="option" items="${options}">
                        <li>
                            
                            <input type="radio" name="option" id="option${option.id}" value="${option.id}"
                                   <c:if test="${option.id == selectedOption}">checked</c:if>
                                   onchange="submitAnswer(${question.quizId}, ${questionNumber}, ${option.id})">
                            <label for="option${option.id}">${option.explanation}</label>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </div>

        <script>
            function submitAnswer(quizId, questionNumber, selectedOptionId) {
                if (quizId == null || questionNumber == null || selectedOptionId == null) {
                    console.error("Một hoặc nhiều biến bị null hoặc undefined:", {quizId, questionNumber, selectedOptionId});
                    return;
                }

                const params = "quizId=" + encodeURIComponent(quizId) +
                        "&questionNumber=" + encodeURIComponent(questionNumber) +
                        "&selectedOption=" + encodeURIComponent(selectedOptionId);

                console.log("Params:", params);

                const xhr = new XMLHttpRequest();
                xhr.open("POST", "QuizHandleController", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xhr.send(params);

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4) {
                        if (xhr.status === 200) {
                            console.log("Câu trả lời đã được lưu thành công.");
                        } else {
                            console.log("Lỗi khi lưu câu trả lời:", xhr.status);
                        }
                    }
                };
            }

        </script>

    </body>


</html>
