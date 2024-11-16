# **AssignMate - Backend**

The AssignMate Backend is a Spring Boot application designed to manage assignment submissions 
and feedback tracking for students and instructors. This backend works seamlessly with the frontend
to provide robust functionality for both student and admin views.

### **Features**

#### **For Students**

Submit assignments online.     
View submission status (e.g., Pending, Reviewed, Rejected).          
Receive feedback from instructors.

#### **For Admins/Instructors**

Manage assignments and submissions.   
Provide feedback for submitted assignments.  
Track and update submission statuses.

### **Technologies Used**

Language: Java  
Framework: Spring Boot  
Database: MongoDB  
Build Tool: Maven  
Deployment: Docker / Render

### **API Endpoints**

#### **Student Operations**  

Submit Assignment: POST /api/students/submit  
View Status: GET /api/students/status/{studentId}  

#### **Admin/Instructor Operations**  

Get Submissions: GET /api/admin/submissions  
Provide Feedback: POST /api/admin/feedback  