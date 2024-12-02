# Online Learning System

A comprehensive, scalable online learning platform built with Java JSP, Servlet, and MySQL. This system supports course management, user authentication, quizzes, video lectures, and interactive learning features. It also integrates VietQR for payment processing and GPT-4 API to generate suggestions for course creation and modification.

## Features

- **User Registration & Authentication:** Secure sign-up/login system for students and instructors.
- **Course Management:** Instructors can create, edit, and manage courses, lessons, and multimedia content.
- **Interactive Quizzes:** Administer multiple choice and descriptive quizzes to test learner knowledge.
- **Progress Tracking:** Students can track their learning progress and completion status for each course.
- **Video Lectures:** Support for embedding and streaming video content.
- **Discussion Forums:** Allow students to interact with instructors and fellow students.
- **VietQR Payment Integration:** Accept payments for courses using VietQR, a QR-based payment system.
- **GPT-4 Suggestions for Course Creation/Modification:** Generate helpful suggestions for instructors to improve, update, or create new courses.

## Technologies Used

- **Frontend:** HTML, CSS, JavaScript, JSP (Java Server Pages)
- **Backend:** Java, JSP, Servlet
- **Database:** MySQL
- **Authentication:** JWT, OAuth
- **Payment Integration:** VietQR API
- **GPT-4 API:** For generating course creation and modification suggestions
- **Video Streaming:** Integration with platforms like YouTube or custom solutions

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/Online_Learning_System.git
   ```

2. **Navigate to the project folder:**

   ```bash
   cd Online_Learning_System
   ```

3. **Install MySQL** and create a new database for the project. Update the `web.xml` and database connection settings in the configuration files with your database details.

4. **Configure your server (Tomcat):** Make sure you have Apache Tomcat or any other Java servlet container installed and properly configured.

5. **Build the project:**

   - If using Maven:

     ```bash
     mvn clean install
     ```

   - Deploy the `.war` file to your server.

6. **Run the application:**

   - Start Tomcat or your servlet container.
   - Open the browser and go to `http://localhost:8080/Online_Learning_System`.

## VietQR Payment Integration

To enable payments via VietQR:

1. Register for a VietQR merchant account and obtain the API key.
2. Set up the VietQR payment gateway in the backend configuration.
3. Implement the payment UI in the frontend where users can choose to pay for a course.

For more information, refer to the [VietQR API documentation](https://vietqr.com).

## GPT-4 API for Course Suggestions

The system uses OpenAI's GPT-4 API to generate smart suggestions for instructors when adding or editing courses. This helps improve the quality, relevance, and engagement of course content.

To set this up:

1. Obtain an API key from OpenAI by signing up on [OpenAI](https://platform.openai.com/signup).
2. Add your GPT-4 API key to the system's environment variables or configuration files.
3. In the course creation/edit form, instructors can generate content suggestions from GPT-4 to enhance their courses.

## Usage

1. **Students:**
   - Browse and enroll in available courses.
   - Complete quizzes and track progress.
   - View video lectures and participate in forums.

2. **Instructors:**
   - Add, edit, or delete courses.
   - Receive GPT-4 suggestions for course improvements.
   - Monitor student progress and manage content.

3. **Payment:**
   - Students can pay for courses using VietQR. Simply scan the provided QR code and complete the payment via the VietQR mobile app.

## Contributing

Feel free to fork this repository and submit issues or pull requests for bug fixes, features, or improvements.

1. Fork the repo.
2. Create a new branch (`git checkout -b feature-xyz`).
3. Commit your changes (`git commit -m 'Add feature xyz'`).
4. Push to the branch (`git push origin feature-xyz`).
5. Create a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- OpenAI for GPT-4 API
- VietQR for payment integration
- The online learning community for inspiration
