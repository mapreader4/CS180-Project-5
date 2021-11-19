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
handles data when called from Server
<br>
<br>
Server-Side:
<br>
while loop checks for new Clients and creates threads when they do
<br>
in thread, while loop checks for updates from Client or Model
<br>
note: when communicating with Clients, this should send the fields (eg Strings of names, etc) of the data structures rather than the Course/Quiz/User objects themselves
<br>
<br>
Client-Side:
<br>
while loop checks for updates from Server or View
<br>
<br>
View:
<br>
listens for user updates and send them to Client
<br>
updates when server receives updates
<br>
