<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Integer roleId = (session != null) ? (Integer) session.getAttribute("roleId") : null;

    // Nếu roleId trống hoặc khác 4, chuyển hướng về trang chủ
    if (roleId == null || roleId != 4) {
        response.sendRedirect(request.getContextPath() + "/home");
        return;
    }
%>

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
                background-color: #f9f9f9;
            }
            .container {
                width: 85%;
                height: 80%;
                display: flex;
                flex-direction: row;
                margin-top: 20px;
                gap: 20px;
                position: relative;
                min-height: 500px;
            }
            .quiz-header {
                width: 100%;
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #ececec;
                padding: 15px;
                border-bottom: 2px solid #ddd;
            }
            .timer {
                font-size: 1.0em;
                font-weight: bold;
                color: #444;
                margin-top: 10px;
            }
            .quiz-title {
                font-size: 1.5em;
                font-weight: bold;
                color: #333;
                padding-left: 25px;
            }
            .quiz-navigation {
                width: 20%;
                background-color: #f4f4f4;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                box-sizing: border-box;
            }
            .navigation-title {
                font-weight: bold;
                font-size: 1.1em;
                margin-bottom: 15px;
            }
            .question-list {
                display: grid;
                grid-template-columns: repeat(5, 1fr);
                gap: 8px;
            }
            .question-list button {
                width: 100%;
                padding: 10px;
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .question-list button:hover {
                background-color: #e1e1e1;
            }
            .question-list button[style*="background-color: #ddd;"] {
                background-color: #ddd;
                border-color: #bbb;
            }
            .question-list button[style*="background-color: yellow;"] {
                background-color: #ffeb3b;
                color: #333;
            }
            .view-progress-button {
                background-color: #5a67d8;
                color: white;
                padding: 12px 20px;
                border: none;
                cursor: pointer;
                margin-top: 15px;
                border-radius: 6px;
                width: 100%;
                font-weight: bold;
                transition: background-color 0.3s;

            }
            .view-progress-button:hover {
                background-color: #4756c5;
            }
            .question-content {
                width: 85%;
                padding: 30px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                box-sizing: border-box;
            }
            .question-title {
                font-size: 1.3em;
                font-weight: bold;
                margin-bottom: 15px;
                color: #444;
            }
            .options {
                list-style: none;
                padding: 0;
            }
            .options li {
                margin-bottom: 12px;
            }
            .options input[type="radio"] {
                margin-right: 8px;
            }
            .action-buttons {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }
            .mark-button {
                position: absolute;
                right: 20px;
                top: 10px;
                background-color: #5a67d8;
                color: white;
                padding: 8px 16px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-weight: bold;
                transition: background-color 0.3s;
            }
            .mark-button:hover {
                background-color: #e68900;
            }
            .mark-button:hover {
                background-color: #2d3748;
            }
            .submit-button {
                background-color: #8b5cf6;
                color: white;
                padding: 12px 24px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                font-weight: bold;
                transition: background-color 0.3s;
            }
            .submit-button:hover {
                background-color: #7c3aed;
            }
            .show-hint-button {
                position: absolute;
                bottom: 20px;
                right: 20px;
                background-color: #5a67d8;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-weight: bold;
                transition: background-color 0.3s;
            }
            .show-hint-button:hover {
                background-color: #0056b3;
            }

            /* Style cho pop-up */
            .hint-popup {
                display: none; /* Ẩn pop-up khi mới load */
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: white;
                padding: 20px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
                border-radius: 8px;
                z-index: 1000;
                width: 300px;
                text-align: center;
            }
            .hint-popup h3 {
                margin-top: 0;
            }
            .hint-popup .close-button {
                background-color: #333;
                color: white;
                padding: 5px 10px;
                border: none;
                cursor: pointer;
                border-radius: 5px;
                margin-top: 10px;
            }
            .hint-overlay {
                display: none; /* Ẩn overlay khi mới load */
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.5);
                z-index: 999;
            }
            .next-button, .previous-button {
                background-color: #6366f1;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                font-size: 1em;
                font-weight: bold;
                transition: background-color 0.3s, box-shadow 0.3s;
            }
            .next-button:hover, .previous-button:hover {
                background-color: #4f46e5;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .next-button:active, .previous-button:active {
                background-color: #4338ca;
            }
            .action-buttons {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
                gap: 10px;
            }
            .action-buttons a {
                text-decoration: none;
            }
        </style>

    </head>
    <body>
        <!-- Quiz Header -->
        <div class="quiz-header">

            <div class="quiz-title">${quiz.title}</div>
        </div>

        <!-- Main Container -->
        <div class="container">
            <!-- Quiz Navigation -->
            <div class="quiz-navigation">
                <div class="navigation-title">Quiz Navigation</div>
                <div class="question-list">
                    <c:forEach var="i" begin="1" end="${totalQuestions}">
                        <c:set var="isAnswered" value="${sessionScope.answeredQuestions != null && sessionScope.answeredQuestions[i] != null}" />
                        <c:set var="isMarked" value="${sessionScope.markedQuestions != null && sessionScope.markedQuestions[i] != null}" />
                        <a href="QuizHandleController?quizId=${question.quizId}&questionNumber=${i}">
                            <button 
                                data-question="${i}"
                                style="<c:if test='${isAnswered}'>background-color: #ddd;</c:if> <c:if test='${isMarked}'>background-color: #ffa726;</c:if>">
                                ${i}
                            </button>
                        </a>
                    </c:forEach>
                </div>

                <div class="timer">Time left: <span id="timer"></span></div>
                <!-- Action Buttons -->
                <div class="action-buttons">
                    <c:choose>
                        <c:when test="${questionNumber == 1}">
                            <a href="QuizHandleController?quizId=${question.quizId}&questionNumber=${questionNumber + 1}">
                                <button class="next-button">Next</button>
                            </a>
                        </c:when>
                        <c:when test="${questionNumber == totalQuestions}">
                            <a href="QuizHandleController?quizId=${question.quizId}&questionNumber=${questionNumber - 1}">
                                <button class="previous-button">Previous</button>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="QuizHandleController?quizId=${question.quizId}&questionNumber=${questionNumber - 1}">
                                <button class="previous-button">Previous</button>
                            </a>
                            <a href="QuizHandleController?quizId=${question.quizId}&questionNumber=${questionNumber + 1}">
                                <button class="next-button">Next</button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>




            </div>
            <button class="mark-button" onclick="toggleMark(${question.quizId}, ${questionNumber})">Mark</button>


            <!-- Question Content -->
            <div class="question-content">
                <div class="question-title">
                    Question ${questionNumber}: ${question.content}
                </div>


                <c:set var="selectedOption" value="${sessionScope.answeredQuestions[questionNumber]}" />

                <ul class="options">
                    <c:forEach var="option" items="${options}">
                        <li>

                            <input type="radio" name="option" id="option${option.id}" value="${option.id}"
                                   <c:if test="${option.id == selectedOption}">checked</c:if>
                                   onchange="submitAnswer(${question.quizId}, ${questionNumber}, ${option.id})">
                            <label for="option${option.id}">${option.content}</label>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <!-- Nút Show Hint -->
            <button class="show-hint-button" onclick="showHint()">Show Hint</button>

            <!-- Overlay và Pop-up Hint -->
            <div class="hint-overlay" id="hintOverlay" onclick="hideHint()"></div>
            <div class="hint-popup" id="hintPopup">
                <h3>Maybe you need some help</h3>
                <p id="hintText"><!-- Nơi sẽ hiển thị hint --></p>
                <button class="close-button" onclick="hideHint()">Close</button>
            </div>

        </div>
        <form action="QuizHandleController" method="post">
            <input type="hidden" name="quizId" value="${question.quizId}">
            <input type="hidden" name="questionNumber" value="${questionNumber}">
            <input type="hidden" name="submit" value="true">
            <button type="submit" class="view-progress-button">Submit</button>
        </form>



        <script>
            //Hàm để lưu câu trả lời đã lưu
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

            var timeLeft = <%= request.getAttribute("timeRemaining") %>;

            function startCountdown() {
                var timerDisplay = document.getElementById("timer");
                var countdown = setInterval(function () {
                    // Tính phút và giây
                    var minutes = Math.floor(timeLeft / 60);
                    var seconds = timeLeft % 60;

                    // Định dạng 2 chữ số cho giây
                    seconds = seconds < 10 ? "0" + seconds : seconds;

                    // Hiển thị thời gian còn lại
                    timerDisplay.innerHTML = minutes + ":" + seconds;

                    // Kiểm tra nếu hết giờ
                    if (timeLeft <= 0) {
                        clearInterval(countdown);
                        alert("Thời gian đã hết!");
                        // Điều hướng đến trang khác hoặc gửi form bài thi
                        // window.location.href = "end_test.jsp";
                    }

                    timeLeft -= 1;
                }, 1000);
            }

            window.onload = startCountdown;



            // Hàm để hiển thị hint
            function showHint() {
                const hint = "${question.hint}";
                document.getElementById('hintText').textContent = hint;
                document.getElementById('hintPopup').style.display = 'block';
                document.getElementById('hintOverlay').style.display = 'block';
            }

            // Hàm để ẩn hint
            function hideHint() {
                const hint = "${question.hint}";
                document.getElementById('hintPopup').style.display = 'none';
                document.getElementById('hintOverlay').style.display = 'none';
            }


            // Biến để lưu trạng thái đánh dấu hiện tại
            let isMarked = false;

            function toggleMark(quizId, questionNumber) {
                isMarked = !isMarked; // Đảo trạng thái đánh dấu

                // Cập nhật màu sắc của nút trong navigation ngay lập tức
                const navButton = document.querySelector(`.question-list button[data-question="${questionNumber}"]`);
                if (navButton) {
                    navButton.style.backgroundColor = isMarked ? '#ffa726' : ''; // Màu cam nếu được đánh dấu
                }

                // Gửi yêu cầu POST đến servlet để lưu trạng thái đánh dấu
                const xhr = new XMLHttpRequest();
                xhr.open("POST", "QuizHandleController", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                // Gửi dữ liệu `mark` cùng với `quizId` và `questionNumber`
                const params = "quizId=" + encodeURIComponent(quizId) +
                        "&questionNumber=" + encodeURIComponent(questionNumber) +
                        "&mark=" + encodeURIComponent(isMarked);
                xhr.send(params);

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        console.log("Marked status saved successfully");
                    } else if (xhr.readyState === 4) {
                        console.error("Failed to save marked status:", xhr.status);
                    }
                };
            }

            


        </script>

    </body>


</html>
