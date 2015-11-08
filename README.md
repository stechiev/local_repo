# vote system task

Short json api made by using JPA + Hibernate + Spring + JAX-RS + Apache Tomcat 8

The task is:

  Build a voting system for deciding where to have lunch.
  
    - two types of users: admin and regular users
    - Admin can input a restorant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
    - Users can vote on which restaurant they want to have lunch at
    - Only one vote counted per user.
    
  If user votes again the same day:
  
      If it is before 11:00 we asume that he changed his mind.
      If it is after 11:00 then it is too late, vote can't be changed

Each restorant provides new menu each day.


As a result system have two Controllers:

Restaurant Controller, that allows admin to add restaurants and dishes and to get List of restaurants with their daily menu

Vote Controller, that allows users to vote on where they want to have a lunch and to see the voting statistics

All POST-requests must have MediaType = "application/json"

##RestaurantController:


####1. Add restaurant:

    http-post by URL http://{somebaseurl}/testspringweb/api/restaurant/add
    with JSON body like:
    { "userId": "1", "restaraunt":{
      	             "address":"some new address", 
      	             "name":"funkyJava", 
      	             "dishes": [
      	                        { "name": "steak", "price": "5.55" }, 
      	                        { "name": "ratatois", "price": "1.45" }]
      	  
      	            } 
	  }
	  response:
	  400 BAD_REQUEST - if userId or restaraunt is null
	  400 BAD_REQUEST - restaurant fields 'name', 'address' cannot be null 
    403 FORBIDDEN - if user with userId has no admin rights 
    200 OK success - if request was successfully processed


####2. Add lunch dishes:

    http-post by URL http://{somebaseurl}/testspringweb/api/restaurant/newlunch
    with JSON body like:
    { "userId": "1", "restarauntId":"15", 
      "dishes"https://github.com/stechiev/local_repo/new/master?readme=1#: [ { "name": "steak", "price": "5.55" }, 
                  {"name": "ratatois", "price": "1.45" } 
                ] 
    }
    response:
    400 BAD_REQUEST - if userId or restarauntId is null or if user or restaraunt with such ids not exists or if user with userId has no admin rights 
    200 OK success - if request was successfully processed

    

####3. List restaurants to have lunch at:

    http-post by URL http://{somebaseurl}/testspringweb/api/restaurant/list
    without parameters
    
    response:
    200 OK with JSON BODY LIKE:
      [
      	{ "id": 17, "address":"another stupid address", "phone": "911", "name":"test json cafe", 
      	"dishes": [ { "id": 9, "name": "burger","price": 2 }, 
      				{ "id": 11, "name": "pancake","price": 4 } ] },
      				
      	{ "id": 19, "address":"another stupid address", "phone": "911", "name":"test json cafe", 
      	"dishes": [ { "id": 21, "name": "steak","price": 5.55 }, 
      				{ "id": 23, "name": "ratatois","price": 1.45 }
      				] 
      	} 
      ]
    
##VoteController


####1. Vote for restaurant:

    http-post by URL http://{somebaseurl}/testspringweb/api/voting/vote
    with JSON:  { "userId":"intValue", "restarauntId":"intValue"}
    
    response:
    400 BAD_REQUEST - if userId or restarauntId is null or if user or restaraunt with such ids not exists 
    403 FORBIDDEN - if vote was sended after 11:00
    200 OK "accepted" if vote was successfully processed
####2. See voting statistics for current day:

    http-get by URL http://{somebaseurl}/testspringweb/api/voting/votestat
    
    response:
    200 ok wit JSON body like :[ { "restarauntId": intvalue, "votesCount": intvalue }, 
                                 { "restarauntId": intvalue1, "votesCount": intvalue } ] 
    200 empty - if statistics is empty

## Database creation:
  create some schema; i called mine 'test'<br/>
  use schema.sql placed in src/main/resources to build inside created schema database tables for this application<br/>
  use schema.sql inserts to insert some basic values into the tables


## Tomcat configuration:

####1. Add Resource to server.xml:
        <Resource auth="Container" 
	    driverClassName="com.mysql.jdbc.Driver" 
	    maxActive="40" maxIdle="4" 
	    name="jdbc/tutorialDS" type="javax.sql.DataSource" 
	    url="jdbc:mysql://192.168.42.22:3306/test?zeroDateTimeBehavior=convertToNull" 
	    username="user" password="password" />
	    
	    url - url to database
	    
####2. Add to context.xml

      <ResourceLink global="jdbc/tutorialDS" name="jdbc/tutorialDS"
		type="javax.sql.DataSource" />


## Testing

To test the api i've used SOAPUI opensource application tht can be download here : http://www.soapui.org/

1. Install soapui.
2. open file src/main/resources/Voting-system-soapui-project.xml from SOAPUI interface
3. execute prepared requests
