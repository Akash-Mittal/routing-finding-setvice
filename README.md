

###Route Finder API Overview



### How to Access
	Local
	localhost:8888/swagger-ui.html#/
	Cloud
	https://route-finder-app.herokuapp.com/swagger-ui.html#/


* Service which supports finding a route from Origin to Destination and computing
additional information (details below), 

* Only backend API .

* The user of the API will specify origin and destination location points, a time parameter
and way-points parameter (see the parameters description below). 

* The goal is, for every single specified way-point, to find a route from origin to destination through the (single) way-point.

* This means that if there are 3 way-points passed in the request (see example below), there will
be 3 alternative routes generated, each one with its own single way-point, not a single route with
3 way-points. 

* Use OSRM http://project-osrm.org/docs/v5.22.0/api/#route-service for fetching the routes, it's available online for free and it supports the retrieval of all the necessary data.

### Computations
* Once the alternative routes are generated, let's say there will be the same number of alternative
(imaginary) cars, each taking one of the routes and all departing at the same time from the
origin. 
* The API will determine which one of these alternative cars will be the closest to the
destination after X seconds (specified by the time parameter in the request) after departure.

* The term “closest” here means direct distance through the air, not via roads. This car will be
declared the “winner” and the name property of the way-point that it went through will be returned
in the winnerName field of the response (see below).

* Additionally, after finding the “winner” car, the service will compute how much more time the
other cars would need to reach a point on their route where they would have the same distance
from the destination like the “winner” car had when it won. 

* The results of these computations will be returned in the delays field of the response, this field will be an object with the car “names”(each one based on the name of its respective way-point) as keys and the delays in seconds as values. 

* Each delay will be the difference between the time of “winning” and the time of reaching
the desired distance from destination by the given car, the delay for the “winner” car will thus
normally be 0.

### Service details

The service will be built with a database connection in mind, but there is no need to connect to
any real DB. 
There will just be a simple mock function, which will return a fixed value when data
is requested. 
This fixed value will be the string “Mileus” and it will be used as the secret
password for the API (see the Request details below). 
If the request does not have the correct header with this string, the API will return the Unauthorized response.
Tests are not mandatory in the service, but it would be highly appreciated to have at least a
couple of them for some parts of the service.
Input validation is expected to be in place, so if there is any issue with the request, for example
wrong types, missing fields or anything else that would prevent the service from handling the
request, the API will respond with the Bad Request status code.

### Request Request headers:
	 x-secret - Secret password for the API
#### The request will be a POST containing a JSON payload with the following fields:
	 origin - Location object (with latitude and longitude) defining journey start
	 destination - Location object (with latitude and longitude) defining journey end
	 time - Delay in seconds after start for computing the positions of the cars
	 way-points - Array of location objects with names to be used as pass-through points for
				the alternative cars

### Example request payload:

	{
	"origin": {
	"lat": 50.023226,
	"lon": 14.439855
	},
	"destination": {
	"lat": 50.121765629793295,
	"lon": 14.489431312606477
	},
	"time": 180,
	"way-points": [
	{
	"name": "Point A",
	"lat": 50.058010,
	"lon": 14.406775
	},
	{
	"name": "Point B",
	"lat": 50.060757,
	"lon": 14.431909
	},
	{
	"name": "Point C",
	"lat": 50.078847,
	"lon": 14.538084
	}
	]
	}

### Response
	
	The response will be a JSON object containing the following fields:
	
	● winnerName - Name of the way-point that had the “winner” car going through it
	
	● delays - Object describing the delays of the cars (the values are in seconds and can also
	contain floats, not only integers)
	Example response (just an illustration, not linked to the example request):

### Sample
	{
	"winnerName": "Point Y",
	"delays": {
	"Point X": 34.57,
	"Point Y": 0,
	"Point Z": 78
	}
	}