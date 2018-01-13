# Application Documentation

## Libraries
espresso

play-services-maps

retrofit
Retrofit is a REST client for Android .Restrofit Api is used to make http call with backened server.

converter-gson
A converter which uses  Gson for serialization to and from JSon.The Backend server sends data in
Json format and this library is used to convert it into Java Object.

okhttp


## File Structure
activities
Activities folder contains the map and settings UI screen code.

broadcast_recievers
This folder contains broadcast receiver for receiving WIFI scan result and sending them to server.

models
This folder contain model classes for example Building, Floor, Position etc..

network

services
This folder contains services to fetch data from server using Rest APIs.


## App Overview
This Application uses WIFI signal strength measurment for determining indoor position of user.
The Wifi signal strength data for particular indoor location is captured at different points
and is saved in a database on a server. The indoor navigation application measures the signal strength
from different wifi access points and sends them to server.The server implements indoor positioning
algorithm and returns user location. The application displays user position of user on the map.


In this section, different components of the app will be discussed.

### Map view

The map view displays a Google map with a floor map of the selected HFT building floor map displaying.

#### Navigation

The navigation bar enables the user to navigate to the settings page (right cog icon) as well as opening the side menu (hamburger menu icon on the left).

![Navigation bar](images/navigation_bar.png)

### Settings page

In the settings page the user is able to select the project context and the floor he/she is on. Both the project list and the floor list are fetched dynamically using the backend's REST API.

![Settings page](images/settings.png)

### Side Menu



![Side menu](images/side_menu.png)
