# Application Documentation

## Libraries

[Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson):
A converter which uses  Gson for serialization to and from JSon.The Backend server sends data in
Json format and this library is used to convert it into Java Object.

[Retrofit](http://square.github.io/retrofit/):
Retrofit is a REST client for Android. Restrofit Api is used to make http calls to the backened server.

[OkHttp](http://square.github.io/okhttp/):
okHttp is Http client used for communication with server.


## File Structure
The file structure of the application is based on the [App Components](https://developer.android.com/guide/components/fundamentals.html#Components) provided by the Android Framework. 

activities - Activities are classes managing user interactions and providing the GUI.
The folder contains activities for the map view and the settings screen.

broadcast_recievers - Broadcast receivers allow the system to deliver events. 
The most important event in this application is the receiving of WIFI scan result and delivering them to the services in order to send them to the backend server.

models - The basic data structures of the application are called models. 
In this application, model classes are for example Building, Floor, Position etc.

services - Services are dedicated for long-running operations or remote processes. 
The directory contains classes to fetch data from the backend server using Rest APIs.

network - The network folder is not an app component of the Android Framework, but it contains classes for connection pooling and is therefore an extra element of the file hierarchy.

## App Overview
This Application uses WIFI signal strength measurment for determining indoor position of user.
The Wifi signal strength data for particular indoor location is captured at different points
and is saved in a database on a server. The indoor navigation application measures the signal strength
from different wifi access points and sends them to server.The server implements indoor positioning
algorithm and returns user location. The application displays user position of user on the map.


In this section, different components of the app will be discussed.

### Map view

The map view displays a Google map with a floor map of the selected HFT building floor map displaying.
Floor map also reflects user's walking track.

<img src="images/map_view_1.png" width="350px">

#### Navigation

The navigation bar enables the user to navigate to the settings page (right cog icon) as well as opening the side menu (hamburger menu icon on the left).

<img src="images/navigation_bar.png" width="350px">

### Settings page

In the settings page the user is able to select the project context and the floor he/she is on. Both the project list and the floor list are fetched dynamically using the backend's REST API.


<img src="images/settings.png" width="350px">

### Project info
This screen will show list of projects available on server and will allow user to select the project.
Project selection is important in order to calculate the position correctly(project contain some
parameter settings in backend)

<img src="images/settings_project_list.png" width="350px">

### Floor info
This screen will show list of floors available on server. Selecting a floor triggers that the floor
map in the map view is changed.

<img src="images/settings_floor_list.png" width="350px">


### Side Menu
The two buttons(Github) redirect the user to Github docs and Web frontend.

<img src="images/side_menu.png" width="350px">
