# CS180-Project-5
<br>
Four sections of the project:
<br>
- Model (server-side), mainly consists of compatability updates to the existing data structures
- Network (server-side, client-side), handles network IO on both the server and client, also structures concurrency
- Controller (client-side), handles exchange of information between Network and View
- View (client-side), handles GUI
<br>
<br>
Model:
<br>
login(String username, String password) to create or login to account
<br>
<br>
Server-Side:
<br>
while loop checks for updates from Client or Model
<br>
<br>
Client-Side:
<br>
while loop checks for updates from Server or View
<br>
<br>
View:
<br>
will call methods on client-side to fetch information
<br>
