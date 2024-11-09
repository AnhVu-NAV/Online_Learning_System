<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Quiz Result</title>
        <style>
           
            .result-container {
                max-width: 600px;
                margin: 20px auto;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 8px;
                background-color: #f9f9f9;
            }
            .result-header {
                text-align: center;
                margin-bottom: 20px;
            }
            .result-header h1 {
                margin: 0;
                font-size: 24px;
            }
            .result-details {
                margin: 20px 0;
            }
            .result-details table {
                width: 100%;
                border-collapse: collapse;
            }
            .result-details table, th, td {
                border: 1px solid #ddd;
            }
            .result-details th, .result-details td {
                padding: 8px;
                text-align: left;
            }
            .result-footer {
                text-align: center;
            }
            .pass-status {
                font-weight: bold;
                color: green;
            }
            .fail-status {
                font-weight: bold;
                color: red;
            }
        </style>
    </head>
    <body>
        
        <!-- Result Container -->
        <div class="result-container">
            <div class="result-header">
                <h1>Quiz Result</h1>
                <p><strong>${correctAnswers} / ${totalQuestions}</strong> Correct Answers</p>
                <p>
                    <strong>Score:</strong> ${percentageScore}%
                </p>
                <p>
                    <strong>Status:</strong>
                    <span class="${isPass ? 'pass-status' : 'fail-status'}">
                        ${isPass ? "Pass" : "Fail"}
                    </span>
                </p>
                <p>Pass Rate: ${passRate}%</p>
            </div>

            <div class="result-details">
                <h2>Details:</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Question</th>
                            <th>Your Answer</th>
                            <th>Correct Answer</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="result" items="${resultDetails}">
                            <tr>
                                <td>${result.questionContent}</td>
                                <td>${result.userAnswer}</td>
                                <td>${result.correctAnswer}</td>
                                <td>
                                    <span style="color: ${result.isCorrect ? 'green' : 'red'};">
                                        ${result.isCorrect ? "Correct" : "Incorrect"}
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="result-footer">
                <button onclick="window.location.href = 'ChapterDisplayController'">Back to Chapters</button>
            </div>
        </div>
    </body>

</html>
