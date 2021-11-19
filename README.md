# CS180-Project-5
<br>
Louis' role TBD
<br>
Anyone who finishes their part of the project can help others.
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
