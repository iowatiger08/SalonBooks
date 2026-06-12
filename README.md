# SalonBooks
-----------

SalonBooks is meant for a person to run salon appointments, orders and item 
inventory. This is a work in progress.

Installation
------------
Create mysql db schema using the sql file. 
Use Eclipse\Spring Tool Suite to export the project into a war or use Maven.
Deploy to your servlet container. 
Open browser to http://localhost:8080/salonbooks/home

Associated projects Recommendations
-----------------------------------
Maven 2.3
Tomcat 7.0+
MySQL 5.5+  

Known Issues:
----------
1. Validation is not completely implemented
2. Workflow starting with appointments can result in bad url.
3. Successful saves are not reported nor are users promted to wait for loading. 
4. Friendly errors do not get reported. tomcat logs/console have to be consulted

Use at own risk. No guarantees implied. 

Copyright
---------
Initial Developer: Tony E Dillon-Hansen

Source released under license
------------------------------
Common Development and Distribution License (CDDL-1.0)
at http://opensource.org/licenses/CDDL-1.0
