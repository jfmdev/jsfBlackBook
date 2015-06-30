jsfBlackBook
============

**jsfBlackBook** is a sample web application (for learn _Java EE_ and _Java Server Faces_) that allows user to manage a list of contacts.

Features
--------

Standard's user can:

 * Login into the application.
 * Display his list of contacts.
 * Create a new contact.
 * Edit or delete an existing contact.
 * Change his username or password.
     
Administrator's users can (additionally) manage the application's users (display the list of users, create a new user or edit an existing user).

Usage
-----

The application should be able to run in any valid _Java EE_ container, such as Tomcat, WildFly, WebLogic, Jetty, GlassFish, etc.

Since the application uses a self-contained database, no further configuration is need after deploying the application in the server.

After deployment, two default accounts can be used to login: "admin" and "test" (whose passwords are respectively "admin" and "test").

Optionally, you can configure the path in which the log files are going to be created. In order to do it, you must edit the file `tinylog.properties` located in the folder `web\WEB-INF`.

Stack
-----

The application was developed using:

 * _Java_ as programming language.
 * _JSF_ as web application framework.
 * _SQLite_ as database (along with the libraries _JDBC_ and _Commons DBUtils_).
 * _Tinylog_ as logger library.
 * _Bootstrap_ as front-end framework.

License
-------

This application is free software; you can redistribute it and/or
modify it under the terms of the GNU Affero General Public
License as published by the Free Software Foundation; either
version 3 of the License, or (at your option) any later version.

This application is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public
License along with this application; If not, see <http://www.gnu.org/licenses/>.