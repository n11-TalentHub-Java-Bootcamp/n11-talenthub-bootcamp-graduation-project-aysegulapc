# N11-talenthub-bootcamp-graduation-project

## I - Introduction

### 1- Backend Side

```Maven``` was used as the Java management tool in this project. So to inject the dependencies, 
first reload the project in Maven, then clean and install it.

```PostgreSql``` was used as database. The ```Hibernate (JPA)``` ORM library was used to transform Java classes 
into database tables.

**Note**: In order for the application to work, the necessary db configuration settings must be made from the ```application.properties``` file.

### 2- Frontend Side

A simple interface was made with ```React```. Bootstrap and basic level css were used for the design. 
Run this command to download the required dependencies:

> npm i

Run the following command to start the project:

> npm start

## II - Project Details

### 1- Add User And Calculate Credit Result

Users can be registered in the database according to the project requirements. 
As a result of the user information entered, the credit limit and credit result 
are saved in the database. The following video shows the credit limit and credit 
result of a registered user:


The credit score was simply calculated according to the tc number. According to the 
first scenario, users whose last 3 digits of the Turkish Republic are less than 500 
were assigned a credit limit of 0 TL and a ```RED``` response was returned. Since the last 
3 digits of the first user's TC were greater than 500, the credit score was also returned 
as greater than 500 and was assigned as ```ONAY```.

### 2- Update User And Calculate Credit Result

Registered users can be updated from the table. According to the updated information, 
the user's credit result is recalculated and this result is updated. In the video below, 
it is seen that the credit result of a user whose information has been updated is recalculated.





