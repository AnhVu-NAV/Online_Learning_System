<%-- 
    Document   : subjectList
    Created on : Oct 21, 2024, 2:33:07 PM
    Author     : AnhVuNAV
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Subjects List</title>
        <link rel="stylesheet" href="css/subjectList.css">
        <link rel="stylesheet" href="css/aiModal.css">
    </head>
    <body>
        <div class="container">
            <!-- Sidebar Section -->
            <div class="sidebar">
                <h3>Filter by Category</h3>
                <ul>
                    <c:forEach var="category" items="${categoryList}">
                        <li>
                            <input type="checkbox" class="filter-category" value="${category.value}"> ${category.value}
                        </li>
                    </c:forEach>
                </ul>

                <h3>Filter by Status</h3>
                <select id="statusFilter" class="filter-dropdown dropdown">
                    <option value="All">All Status</option>
                    <option value="Active">Active</option>
                    <option value="Inactive">Inactive</option>
                </select>

                <div class="customization-panel">
                    <h3>Customize Columns</h3>
                    <form>
                        <label><input type="checkbox" class="toggle-column" data-column="1" checked> ID</label><br>
                        <label><input type="checkbox" class="toggle-column" data-column="2" checked> Name</label><br>
                        <label><input type="checkbox" class="toggle-column" data-column="3" checked> Subtitle</label><br>
                        <label><input type="checkbox" class="toggle-column" data-column="4" checked> Category</label><br>
                        <label><input type="checkbox" class="toggle-column" data-column="5" checked> Owner</label><br>
                        <label><input type="checkbox" class="toggle-column" data-column="6" checked> Total Duration</label><br>
                        <label><input type="checkbox" class="toggle-column" data-column="7" checked> Description</label><br>
                        <label><input type="checkbox" class="toggle-column" data-column="8" checked> Status</label><br>
                        <label><input type="checkbox" class="toggle-column" data-column="9" checked> Number of Learners</label><br>
                        <label><input type="checkbox" class="toggle-column" data-column="10" checked> Created Date</label><br>
                        <label><input type="checkbox" class="toggle-column" data-column="11" checked> Updated Date</label>
                    </form>
                </div>

                <!-- Add the Generate AI Button -->
                <div class="generate-ai-button">
                    <button id="generateAIAnalysis" class="generate-ai-btn">Generate AI Analysis</button>
                    <!--<button onclick="location.href = 'GenerateAI.jsp'" class="generate-ai-btn">Generate AI</button>-->
                </div>

            </div>

            <!-- Main Content -->
            <div class="content">
                <h1 class="page-title">Subjects List</h1>

                <!-- Search and Add New Course -->
                <div class="filter-section">
                    <input type="text" id="searchBar" placeholder="Search course by name..." class="search-bar" onkeyup="filterCourses()">
                    <button class="add-course-btn" onclick="location.href = 'CourseController?action=new';">Add New Course</button>
                </div>

                <!-- Course List Table -->
                <table class="course-table" id="courseTable">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Subtitle</th>
                            <th>Category</th>
                            <th>Owner</th>
                            <th>Total Duration</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Number of Learners</th>
                            <th>Created Date</th>
                            <th>Updated Date</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="course" items="${listCourse}">
                            <tr>
                                <td>${course.id}</td>
                                <td>${course.title}</td>
                                <td>${course.subtitle}</td>
                                <td>${course.categoryName}</td>
                                <td>${course.ownerName}</td>
                                <td>${course.totalDuration}</td>
                                <td>${course.description}</td>
                                <td>${course.status == 1 ? 'Active' : 'Inactive'}</td>
                                <td>${course.numberOfLearner}</td>
                                <td>${course.createdDate}</td>
                                <td>${course.updatedDate}</td>
                                <td>
                                    <a href="CourseController?action=edit&id=${course.id}"><button class="edit-btn">Edit</button></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Pagination and Subjects Per Page Section -->
                <div class="pagination-section">
                    <div class="subjects-per-page">
                        <label for="subjectsPerPage">Subjects per page:</label>
                        <!-- Changed to an input number field -->
                        <input type="number" id="subjectsPerPage" class="input-number" value="${subjectsPerPage}" min="1" onchange="changeSubjectsPerPage()">
                    </div>

                    <div class="pagination">
                        <c:if test="${currentPage > 1}">
                            <a href="CourseController?action=list&page=${currentPage - 1}&subjectsPerPage=${subjectsPerPage}">
                                <button>Previous</button>
                            </a>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <a href="CourseController?action=list&page=${i}&subjectsPerPage=${subjectsPerPage}">
                                <button class="${currentPage == i ? 'active' : ''}">${i}</button>
                            </a>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <a href="CourseController?action=list&page=${currentPage + 1}&subjectsPerPage=${subjectsPerPage}">
                                <button>Next</button>
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
            <!-- Modal Popup for AI Analysis -->
            <div id="aiModal" class="modal">
                <div class="modal-content">
                    <span id="closeModal" class="close">&times;</span>
                    <div id="aiResponse"></div> <!-- AI response area -->
                    <input type="text" id="chatInput" placeholder="Ask a follow-up question">
                    <button id="sendChat">Submit</button>
                </div>
            </div>

        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <script>
                            // Toggle column visibility
                            document.addEventListener('DOMContentLoaded', function () {
                                const checkboxes = document.querySelectorAll('.toggle-column');
                                checkboxes.forEach(checkbox => {
                                    checkbox.addEventListener('change', function () {
                                        const column = document.querySelectorAll('td:nth-child(' + this.dataset.column + '), th:nth-child(' + this.dataset.column + ')');
                                        column.forEach(cell => {
                                            cell.style.display = this.checked ? 'table-cell' : 'none';
                                        });
                                    });

                                    // Initialize the column visibility based on checkbox state
                                    const column = document.querySelectorAll('td:nth-child(' + checkbox.dataset.column + '), th:nth-child(' + checkbox.dataset.column + ')');
                                    column.forEach(cell => {
                                        cell.style.display = checkbox.checked ? 'table-cell' : 'none';
                                    });
                                });

                                // Filter by category functionality
                                const categoryCheckboxes = document.querySelectorAll('.filter-category');
                                categoryCheckboxes.forEach(checkbox => {
                                    checkbox.addEventListener('change', filterByCategory);
                                });

                                // Function to filter courses by category
                                function filterByCategory() {
                                    const selectedCategories = Array.from(document.querySelectorAll('.filter-category:checked')).map(cb => cb.value);
                                    const rows = document.querySelectorAll('#courseTable tbody tr');
                                    rows.forEach(row => {
                                        const category = row.querySelector('td:nth-child(4)').textContent;
                                        row.style.display = selectedCategories.length === 0 || selectedCategories.includes(category) ? '' : 'none';
                                    });
                                }
                            });

                            // Change subjects per page
                            function changeSubjectsPerPage() {
                                const subjectsPerPage = document.getElementById("subjectsPerPage").value;
                                window.location.href = 'CourseController?action=list&subjectsPerPage=' + subjectsPerPage + '&page=1';
                            }

                            // Function to filter courses by category
                            function filterByCategory() {
                                const selectedCategories = Array.from(document.querySelectorAll('.filter-category:checked')).map(cb => cb.value);
                                const rows = document.querySelectorAll('#courseTable tbody tr');

                                rows.forEach(row => {
                                    const category = row.querySelector('td:nth-child(4)').textContent;
                                    // Mark the row as hidden or visible based on category filter
                                    row.setAttribute('data-category-visible', selectedCategories.length === 0 || selectedCategories.includes(category) ? 'true' : 'false');
                                });
                            }

                            // Function to filter courses by status
                            function filterByStatus() {
                                const selectedStatus = document.getElementById('statusFilter').value;
                                const rows = document.querySelectorAll('#courseTable tbody tr');

                                rows.forEach(row => {
                                    const status = row.querySelector('td:nth-child(8)').textContent;
                                    // Mark the row as hidden or visible based on status filter
                                    row.setAttribute('data-status-visible', (selectedStatus === 'All' || status === selectedStatus) ? 'true' : 'false');
                                });
                            }

                            // Search functionality
                            function filterCourses() {
                                const searchValue = document.getElementById('searchBar').value.toLowerCase();
                                const rows = document.querySelectorAll('#courseTable tbody tr');

                                rows.forEach(row => {
                                    const title = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
                                    // Mark the row as hidden or visible based on search filter
                                    row.setAttribute('data-search-visible', title.includes(searchValue) ? 'true' : 'false');
                                });
                            }

                            // Function to combine all filters
                            function applyFilters() {
                                filterByCategory();
                                filterByStatus();
                                filterCourses();

                                // Apply combined filters to determine row visibility
                                const rows = document.querySelectorAll('#courseTable tbody tr');
                                rows.forEach(row => {
                                    const isCategoryVisible = row.getAttribute('data-category-visible') === 'true';
                                    const isStatusVisible = row.getAttribute('data-status-visible') === 'true';
                                    const isSearchVisible = row.getAttribute('data-search-visible') === 'true';

                                    // Show the row only if all filters are satisfied
                                    row.style.display = (isCategoryVisible && isStatusVisible && isSearchVisible) ? '' : 'none';
                                });
                            }

                            // Event listeners for filtering
                            document.addEventListener('DOMContentLoaded', function () {
                                // Attach event listeners for the dropdown filters
                                document.getElementById('statusFilter').addEventListener('change', applyFilters);
                                // Attach the event listener to the search bar
                                document.getElementById('searchBar').addEventListener('keyup', applyFilters);
                                // Attach event listeners for category checkboxes
                                document.querySelectorAll('.filter-category').forEach(checkbox => {
                                    checkbox.addEventListener('change', applyFilters);
                                });
                            });

                            // Function to format and display AI response
                            function formatAIResponse(responseJson) {
                                console.log("Raw Response:", responseJson); // Debug: Check the raw response

                                // Parse JSON if it's a string
                                const response = typeof responseJson === 'string' ? JSON.parse(responseJson) : responseJson;

                                // Extract the AI response content
                                const aiContent = response.choices[0].message.content;
                                console.log("Formatted Content:", aiContent); // Debug: Check formatted content

                                // Apply HTML formatting
                                const formattedContent = aiContent
                                        .replace(/Outdated:/, "<strong>Outdated:</strong>")
                                        .replace(/Need to be updated with new knowledge:/, "<strong>Need to be updated with new knowledge:</strong>")
                                        .replace(/\n\d+\./g, match => `<br/><strong>${match}</strong>`)
                                        .replace(/\n/g, "<br/>");

                                // Set formatted content in the modal
                                document.getElementById("aiResponse").innerHTML = `<div>${formattedContent}</div>`;
                            }


                            // Xử lí AI 
                            $(document).ready(function () {
                                // Show modal and send initial AI query when button is clicked
                                $("#generateAIAnalysis").click(function () {
                                    $("#aiModal").show();
                                    $("#aiResponse").html("Loading analysis...");
                                    console.log("Opening AI Analysis Modal..."); // Debug
//                                    $("#aiModal").css("display", "block"); // Force display as block for debugging
//                                    $("#aiResponse").html("Loading analysis...");

                                    // Fetch course names from the database
                                    $.ajax({
                                        url: "CourseAnalysis",
                                        type: "POST",
                                        data: {action: "initial"},
                                        success: function (response) {
//                                            $("#aiResponse").html(response); // Display initial response
//                                            $("#aiResponse").html("<p><strong>AI Analysis:</strong> " + response + "</p>");
//                                            console.log("Response from server:", response); // Debug: Log raw server response

                                            // Temporarily set static content for testing
//                                            $("#aiResponse").html("<p>This is a test content for AI response area.</p>");
                                            // Pass response to formatAIResponse for formatting and display
//                                            formatAIResponse(response);
                                            $("#aiResponse").html(response)
                                        },
                                        error: function () {
                                            $("#aiResponse").html("Failed to get response from AI.");
                                        }
                                    });
                                });

                                // Send follow-up question
                                $("#sendChat").click(function () {
                                    const followUp = $("#chatInput").val();
                                    $("#chatInput").val(""); // Clear input field
                                    $("#aiResponse").append("<p><strong>You:</strong> " + followUp + "</p>");

                                    $.ajax({
                                        url: "CourseAnalysis",
                                        type: "POST",
                                        data: {action: "followUp", message: followUp},
                                        success: function (response) {
                                            // Pass follow-up response to formatAIResponse
//                                            $("#aiResponse").append("<p><strong>AI:</strong> </p>");
                                            formatAIResponse(response);
                                        },
                                        error: function () {
                                            $("#aiResponse").append("<p>Failed to get response from AI.</p>");
                                        }
                                    });
                                });

                                // Close modal
                                $("#closeModal").click(function () {
                                    $("#aiModal").hide();
                                    $("#aiResponse").html(""); // Clear chat history
                                });
                            });

        </script>
    </body>
</html>
