# CS180-Project-5
Initialization and Connection Instructions:
<br>
<br>
First, run the main method in the Server class on any computer networked to the computer you intend to run Client on.
<br>
Then, run the main method in the Client class.
<br>
This will open a GUI window that will allow you to enter connection details.
<br>
Enter the domain name of the computer you're running Server on, as well 8818 for the port number.
<br>
This should allow you to access Online Quiz Navigator v2.
<br>
<br>
<br>
Project Planning:
<br>
<br>
Louis' role:
<br>
handles concurrency part which allows multiple users to use the application at the same time.
<br>
<br>
Model (Jay):
<br>
handles data when called from Server
<br>
need to update existing data structures for compatibility
<br>
<br>
Server (Nathan S):
<br>
while loop checks for new Clients and creates threads when they do
<br>
in thread, while loop checks for updates from Client or Model
<br>
with Client, establish some sort of communication protocol (maybe the first object sent should be a String naming the action, then the last object should be the String "STOP")
<br>
note: when communicating with Clients, this should send the fields (eg Strings of names, etc) of the data structures rather than the Course/Quiz/User objects themselves
<br>
<br>
Client (Aditya):
<br>
while loop checks for updates from Server or View
<br>
with Server, establish some sort of communication protocol (maybe the first object sent should be a String naming the action, then the last object should be the String "STOP")
<br>
<br>
View (Nathan R):
<br>
listens for user updates and send them to Client
<br>
updates when Client receives updates
<br>
