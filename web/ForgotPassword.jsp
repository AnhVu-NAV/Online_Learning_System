<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Forgot Password</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="register/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="register/css/styleAdmin.css">
    <style>
        /* Add your CSS styles here */

        /* Container and form styling */
        .container {
            max-width: 600px;
            /* Increase the width of the container */
            margin: 0 auto;
        }

        .forgot-password-content {
            background: #fff;
            padding: 40px 50px;
            /* Add more padding to make the form larger */
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        .forgot-password-form {
            text-align: center;
            /* Center the form text */
        }

        .form-title {
            text-align: center;
            /* Center the title */
            font-size: 24px;
            margin-bottom: 25px;
        }

        .form-group {
            position: relative;
            margin-bottom: 15px;
            /* Reduced margin between form groups */
        }

        .form-group label {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            left: 15px;
            color: #888;
        }

        .form-group input {
            width: calc(100% - 30px);
            /* Adjusted width with reduced padding */
            padding: 10px 15px;
            /* Reduced padding */
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .form-button {
            width: 100%;
            /* Make the button take up full width */
        }

        .form-submit {
            width: calc(100% - 30px);
            /* Adjusted width with reduced padding */
            padding: 10px;
            background: #86B817;
            border: none;
            color: #fff;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
            /* Reduced margin-top */
        }

        #back-to-signin {
            width: calc(100% - 30px);
            /* Adjusted width with reduced padding */
            padding: 10px;
            border: none;
            background: none;
            /* No background color */
            color: #5cb85c;
            cursor: pointer;
            text-decoration: underline;
            margin-top: 10px;
            /* Reduced margin-top */
        }
    </style>
</head>

<body>

    <div class="main">

        <!-- Forgot Password form -->
        <section class="forgot-password">
            <div class="container">
                <div class="forgot-password-content">
                    <div class="forgot-password-form">
                        <h2 class="form-title">Forgot Password</h2>
                        <div id="message"></div>
                        <form method="POST" action="forgetPasswordController" class="register-form"
                            id="forgot-password-form">
                            <div class="form-group">
                                <label for="email"></label>
                                <input type="email" name="email" id="email" placeholder="Your Email" required />
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="reset_password" id="reset_password" class="form-submit"
                                    value="Submit" />
                            </div>
                        </form>
                        <a href="sign_in.jsp"  id="back-to-signin" class="form-submit">Back to Sign in</a>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- JS -->
    <script src="register/vendor/jquery/jquery.min.js"></script>
    <script src="register/js/main.js"></script>
</body>

</html>