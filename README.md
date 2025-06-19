Siphesihle Jali ST10479775 

Link- https://github.com/Siphesihle-J/practicum.git 

Screenshots and Explanations 

Main Screen 

Show Image Explanation: The main screen displays the app title and three primary buttons: 

Picture 

 

Add Song to Playlist: Opens a dialog to input song details 

View Playlist Details: Navigates to the detailed view screen 

Exit Application: Closes the app with confirmation dialog 

Add Song Dialog 

Show Image Explanation: When "Add to Playlist" is clicked, a dialog appears with input fields for: 

 

Song title (required) 

Artist name (required) 

Rating (1-5, required) 

Comments (optional) The app validates all inputs and provides error messages for invalid data. 

Detailed View Screen - Song List 

Show Image Explanation: The detailed view screen shows: 

Picture 

A list of all songs with complete details using loops 

Each song displays title, artist, rating, and comments 

Clean formatting with separators between songs 

 

Detailed View Screen - Average Rating 

Show Image Explanation: When "Average Rating" is clicked, the app: 

Picture 

Calculates the average rating using a loop 

Displays total songs, valid ratings, and the calculated average 

Shows a breakdown of individual song ratings 

Provides the result in a formatted, easy-to-read layout 

Key Features Implemented 

✅ Arrays and Loops 

Parallel Arrays: Four arrays store song titles, artist names, ratings, and comments 

Loops Used:  

for loops to display song lists 

for loops to calculate average ratings 

Array iteration for data validation 

✅ Screen Navigation 

MainActivity to DetailedViewActivity navigation with data passing 

Back navigation from detailed view to main screen 

Intent-based data transfer between activities 

✅ Error Handling 

Input validation for empty fields 

Rating range validation (1-5) 

Playlist capacity limits (4 songs maximum) 

Exception handling with user-friendly error messages 

Toast notifications for feedback 

✅ User Interface 

Modern design with gradients and elevated buttons 

Intuitive layout with clear visual hierarchy 

Responsive buttons with appropriate icons 

Scrollable content area for song details 

Color-coded buttons for different actions 

✅ Code Quality 

Clear comments explaining each method and functionality 

Meaningful variable names and method names 

Modular code structure with separate methods for different functions 

Exception handling throughout the application 

Input validation and sanitization 

 
