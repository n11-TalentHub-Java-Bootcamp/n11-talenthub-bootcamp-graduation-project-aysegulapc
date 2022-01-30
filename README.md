# N11-talenthub-bootcamp-graduation-project

## I - Introduction

### 1- Backend Side

```Maven``` was used as the Java management tool in this project. So to inject the dependencies, 
first reload the project in Maven, then clean and install it.

```PostgreSql``` was used as database. The ```Hibernate (JPA)``` ORM library was used to transform Java classes 
into database tables.

Backend side works on ```8080``` port.

**Note**: In order for the application to work, the necessary db configuration settings must be made from the ```application.properties``` file.

#### API Doc

```Swagger``` was used for API documentation. The user's get, delete, post and put operations, as well as querying the credit 
service by TC number and date of birth, displaying users' credit details and returning a user's credit score can be tested.

![Screen Shot 2022-01-30 at 21 08 30](https://user-images.githubusercontent.com/23243392/151711778-be5ee035-91ae-4eee-ae6a-ffe22d750706.png)


### 2- Frontend Side

A simple interface was made with ```React```. Bootstrap and basic level css were used for the design. 
Run this command to download the required dependencies:

> npm i

Run the following command to start the project:

> npm start

Frontend side works on ```3000``` port.

## II - Project Details

### 1- Add User And Calculate Credit Result

Users can be registered in the database according to the project requirements. 
As a result of the user information entered, the credit limit and credit result 
are saved in the database. The following video shows the credit limit and credit 
result of a registered user:

https://user-images.githubusercontent.com/23243392/151710455-a840db99-1b39-47ba-8ea5-13c407e50ee1.mov


The credit score was simply calculated according to the tc number. According to the 
first scenario, users whose last 3 digits of the Turkish Republic are less than 500 
were assigned a credit limit of 0 TL and a ```RED``` response was returned. Since the last 
3 digits of the first user's TC were greater than 500, the credit score was also returned 
as greater than 500 and was assigned as ```ONAY```.

### 2- Update User And Calculate Credit Result

Registered users can be updated from the table. According to the updated information, 
the user's credit result is recalculated and this result is updated. In the video below, 
it is seen that the credit result of a user whose information has been updated is recalculated.


https://user-images.githubusercontent.com/23243392/151710511-16c11b5f-3940-4c4d-a3ee-3f634f8c5994.mov

### 3- Delete User And Credit Result

When a user is deleted from the table, the credit result of that user is also deleted from the table.
The video below shows the deletion process.


https://user-images.githubusercontent.com/23243392/151710982-086f2261-9137-49ef-a9c6-1d0d19390086.mov


### 4- Search User


User can be searched according to TC number and birthday. If the date of birth and the tc number do not match, 
it gives a "User not found" error. The video below shows a search process from the table.


https://user-images.githubusercontent.com/23243392/151711011-44ccf4e9-f999-4cdd-bdc0-154fc19881c6.mov

### 5- Send SMS

After the user is registered, an SMS is sent to the relevant phone number. The transaction is carried out with 
a Twilio trial account.

The following message is for the first registered user:

![whatsapp-image-2022-01-30-at-2](https://user-images.githubusercontent.com/23243392/151711284-0c360230-14f7-4209-89e5-48673bb01410.jpg)

The following message is for the second registered user:

![Webp net-resizeimage (2)](https://user-images.githubusercontent.com/23243392/151711409-9e86e4fc-f965-4e4d-9e65-8e415b1f1bd1.jpg)


