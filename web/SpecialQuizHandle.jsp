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
        .upload-container {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 20px;
        }
        .upload-section {
            flex: 1;
            margin-right: 20px;
        }
        .preview-container {
            margin-top: 10px;
            text-align: center;
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
            background-color: #f9f9f9;
            
        }
        .preview-container img, .preview-container video {
            max-width: 100%;
            max-height: 100%;
        }
        .submit-button {
            padding: 10px 20px;
            background-color: #5a67d8;
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
    <script>
        function previewFile(input, type) {
            const file = input.files[0];
            const previewContainer = document.getElementById(type + 'Preview');

            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    if (type === 'image') {
                        previewContainer.innerHTML = '<img src="' + e.target.result + '" alt="Image Preview">';
                    } else if (type === 'video') {
                        previewContainer.innerHTML = '<video controls><source src="' + e.target.result + '" type="' + file.type + '"></video>';
                    }
                };
                reader.readAsDataURL(file);
            } else {
                previewContainer.innerHTML = 'No file chosen';
            }
        }
    </script>
</head>
<body>
    <div class="container">
      
        <div class="question-title">Câu hỏi: Hãy viết về chủ đề "Lợi ích của việc học ngoại ngữ".</div>

        <!-- Form cho người dùng trả lời -->
        <form action="SpecialQuizController" method="post" enctype="multipart/form-data">
            
            <div class="form-group">
                <label for="textAnswer">Your Answer:</label>
                <textarea name="textAnswer" id="textAnswer" placeholder="Nhập câu trả lời của bạn..." required></textarea>
            </div>

            <!-- Container cho phần tải lên file -->
            <div class="upload-container">
                <div class="upload-section">
                    <label for="image">Add Image:</label>
                    <input type="file" name="image" id="image" accept="image/*" onchange="previewFile(this, 'image')">
                    <div class="preview-container" id="imagePreview">No file chosen</div>
                </div>
                <div class="upload-section">
                    <label for="video">Add Video:</label>
                    <input type="file" name="video" id="video" accept="video/*" onchange="previewFile(this, 'video')">
                    <div class="preview-container" id="videoPreview">No file chosen</div>
                </div>
            </div>

            <!-- Nút Submit -->
            <button type="submit" class="submit-button">Continue</button>
        </form>
    </div>
</body>
</html>
