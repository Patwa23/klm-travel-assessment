KLM TRAVEL CLIENT UI
====================

## Frontend : Angular Application ,Hosted on Firebase 
URL: https://klm-travel-assignment.firebaseapp.com/dev/dashboard

**NOTE:**
As fronend is already hosted in Firebase so there is no need to clone & run the frontend.But if you would like to run in local the below steps

**Set up Frontend in local using the following below steps**

1)Install latest **Node**

2)Install angular cli **npm install -g @angular/cli**

3)Clone project & go to the travel-client-ui

4)Run **npm install**

5)Run **ng serve**

**NOTE:**
You can also run the application using Docker.

### Run Docker file 
* docker -v
* docker build -f Dockerfile -t travel-client-ui-0.1.0 .
* docker images
* docker run -p 8081:8081 travel-client-ui-0.1.0
* docker ps -a
* docker stop <container-id>

6)click **http://localhost:4200/**


**The Application has 5 different Page**

**1) Dashboard:**

Gives the traffic details such
Total number of requests processed
Total number of requests resulted in an OK response
Total number of requests resulted in a 4xx response
Total number of requests resulted in a 5xx response
Average response time of all requests
Min response time of all requests
Max response time of all requests

**2)Login Page**

To login and access Airport & Profile Details

`Username: Klm`

`Password: Klm@123`

**3)Fare Page**

Once you are logged in Airport -> Airport Fare -> Select Source & Destination Airport -> Get Fare , source and destination details
This will gives the Fare of two destination.

**4)Airport Page**

Once you are logged in Airport -> Airport -> Search 
This will gives list of Airport

**5)Profile Page:**

Once you are logged in Profile : Gives the profile info

NOTE: Kindly run the backend the backend before using the frontend. 

