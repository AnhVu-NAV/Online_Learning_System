<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Learnik Footer</title>
    <link rel="stylesheet" type="text/css" href="./style.css">
    <style>
        .footer {
            background-color: #141624;
            color: #BABABF;
            padding: 64px 0;
            text-align: left;
            font-family: Arial, sans-serif;
        }

        .footer-container {
            display: flex;
            justify-content: space-between;
            padding: 0 352px;
        }

        .footer-section {
            width: 25%;
        }

        .footer-section h3 {
            color: white !important; 
            font-size: 18px;
            margin-bottom: 12px;
        }

        .footer-section p,
        .footer-section ul li {
            font-size: 16px;
            margin-bottom: 8px;
        }

        .footer-section ul {
            list-style: none;
            padding: 0;
        }

        .footer-section ul li a {
            color: #BABABF;
            text-decoration: none;
        }

        .footer-newsletter input {
            padding: 12px;
            border: 1px solid #3B3C4A;
            background-color: #181A2A;
            color: #97989F;
            border-radius: 6px;
            margin-bottom: 10px;
            width: 100%;
        }

        .footer-newsletter button {
            padding: 12px 20px;
            background-color: #4B6BFB;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .footer-bottom {
            display: flex;
            justify-content: space-between;
            padding: 32px 352px;
            border-top: 1px solid #242535;
            color: #BABABF;
        }

        .footer-bottom ul {
            display: flex;
            gap: 16px;
            list-style: none;
            padding: 0;
        }

        .footer-bottom ul li a {
            color: #BABABF;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <footer class="footer">
        <div class="footer-container">
            <div class="footer-section">
                <h3>About Learnik</h3>
                <p>Learnik is a premier online learning platform providing affordable courses for a self-paced, adaptable education. Empowering lifelong learners to achieve their goals.</p>
                <p>Email: support@learnik.com</p>
                <p>Phone: +123 456 7890</p>
            </div>
            <div class="footer-section">
                <h3>Quick Links</h3>
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Courses</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Blog</a></li>
                    <li><a href="#">FAQs</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>Popular Categories</h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/CourseList?search=&category=Business">Business</a></li>
                    <li><a href="${pageContext.request.contextPath}/CourseList?search=&category=Technology">Technology</a></li>
                    <li><a href="${pageContext.request.contextPath}/CourseList?search=&category=Health">Health & Wellness</a></li>
                    <li><a href="${pageContext.request.contextPath}/CourseList?search=&category=Language">Language</a></li>
                    <li><a href="${pageContext.request.contextPath}/home">Personal Development</a></li>
                    <li><a href="${pageContext.request.contextPath}//CourseList?search=&category=Art">Arts & Humanities</a></li>
                </ul>
            </div>
            <div class="footer-newsletter">
                <h3 class="title">Subscribe to Updates</h3>
                <p>Get the latest courses and offers directly to your inbox.</p>
                <input type="email" placeholder="Your Email" />
                <button>Subscribe</button>
            </div>
        </div>
        <div class="footer-bottom">
            <p>© Learnik 2024. All Rights Reserved.</p>
            <ul>
                <li><a href="#">Terms of Service</a></li>
                <li><a href="#">Privacy Policy</a></li>
                <li><a href="#">Support</a></li>
            </ul>
        </div>
    </footer>
</body>
</html>
