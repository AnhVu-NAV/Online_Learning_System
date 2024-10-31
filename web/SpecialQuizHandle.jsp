<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Essay Question</title>
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
            margin-top: 20px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        .question-title {
            font-size: 1.5em;
            font-weight: bold;
            margin-bottom: 15px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }
        .form-group textarea {
            width: 100%;
            height: 100px;
            padding: 10px;
            font-size: 1em;
            border-radius: 4px;
            border: 1px solid #ccc;
            resize: vertical;
        }
        .form-group input[type="file"] {
            display: block;
            margin-top: 5px;
        }
        .submit-button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .submit-button:hover {
            background-color: #388E3C;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Tiêu đề câu hỏi -->
        <div class="question-title">Câu hỏi: Hãy viết về chủ đề "Lợi ích của việc học ngoại ngữ".</div>

        <!-- Form cho người dùng trả lời -->
        <form action="SpecialQuizController" method="post" enctype="multipart/form-data">
            <!-- Trường nhập câu trả lời dạng văn bản -->
            <div class="form-group">
                <label for="textAnswer">Your Answer:</label>
                <textarea name="textAnswer" id="textAnswer" placeholder="Nhập câu trả lời của bạn..." required></textarea>
            </div>

            <!-- Trường tải lên ảnh -->
            <div class="form-group">
                <label for="image">Add Image:</label>
                <input type="file" name="image" id="image" accept="image/*">
            </div>

            <!-- Trường tải lên video -->
            <div class="form-group">
                <label for="video">Add Video:</label>
                <input type="file" name="video" id="video" accept="video/*">
            </div>

            <!-- Nút Submit -->
            <button type="submit" class="submit-button">Submit</button>
        </form>
    </div>
</body>
</html>
