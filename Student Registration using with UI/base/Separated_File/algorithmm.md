Ernest Ramazani 
IUPUI // CSCI 24000
Spring 2022 
Final Project: Student Registration 

Overview // Goals
We are making a Java application that help a school to create a registration system and help professors to grade and see the average score of students. 
The register should be able to create a major, create some classes for this major. Each created major has 4 level, which are the 4 college years. Student cannot choose their own preferred classes, instead each major has its own predefined classes depending on its level. He should then create students and assigning them to a certain class. Then, the student can be graded depending on which class is taking. After all students are graded, we can look for the statistics to see how the class is doing in general. 
 
Software
To make this application, we will be using an external library that create panels and frames called swing. The program will be coded and run on Java. 

Algorithm // Design 

This application will have 10 menu and a menu bar. Starting with the menu bar, it will contain 4 options that are file, edit, view and help. The file menu will contain the option quit and save. The menu edit will have the tool for copy cut and past. Theses tool will be turned on and off depending on which panels or frame is opened. The view menu will open a new panel that will help the user to change the font and theme of the application. The help menu will display the About us option. 
The 10 menus in the main frame are divided in 4 separates blocks. The menu New, Delete, Update and Notes from the first block called Students. The second block, Majors, contains the menu Major and Classes. The third block, Statistics, has the menu Stats and Avg. And lastly, the menu Save and Quit are in the last block.
All menus open new frames that contain new option and the menu bar at the top. 
The menu New open the frame for adding a new student. When the frame is opened, A student Id is automatically generated in an ascending order. There are fields for adding first and last name, birth date, sex, major and major level. The are two options, which are add and back. The option add adds the student into the database and back close the frame and get the user back to the main frame. The menu Update and delete open the same exact frame. This frame asks first for the student id. If the student does not exist, a new frame of error is opened. If the student id exists, new panels appear. There would be labels for first and last name, birthdate, major with the old value and the same panels next to them, so the user can enter new values. There are 2 options, Update and delete. The option search and back in this frame what they mean. Search for searching student by Id and back to get back to the main frame. 
The next menu is Notes. It opens the scores frames. Here the professor can search a student by Id, get his class, and add scores for the homework. The Major menu opens a new frame for adding majors. There are two options, back and add. The next menu is the classes menu. When a student adds a major, it automatically creates 4 different levels of this majors. Furthermore, when the frame class is opened, the user is asks to choose the major, then the major level. After completing that, he can now as many classes he wants giving each the number of credit hours and clicking on a radio button to specify whether the added class has a homework or not. The user can delete a class from the option delete in this frame, and finally get back to the menu using the back option. The next menu is the Stats menu. It opens a frame that create a table that shows statistics such as the number of students taking a certain major at certain level, the Max Avg, the min Avg, and the percentage of students that are passing the major. The only option is the back menu. The last two menus are for saving all the modifications or quitting the program. 

Algorithm // Reasoning and codes 

This project will be using 17 classes. The imported library for the design will be swing. There would be two databases in a csv format. One that will deal with majors and classes and the other for student information. To access all data, we will be reading from the files and then save everything in a form of list in vectors. Every new frame will extends the main class which has the bar option. 

