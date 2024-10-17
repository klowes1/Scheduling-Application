Title: Scheduling Application

Purpose: Accessing a DataBase and having functionality to add, delete, update, and view customers and appointments.

Author: Kody Lowes
Contact: klowes@wgu.edu
Application Version: 1.0
Date: 06/23/24

IDE: Intellij Community 2023.3.3
JDK: Oracle OpenJDK 17.0.1
JavaFX: JavaFX-SDK-17.0.6
MySQL Connector: mysql-connector-j-8.4.0

Directions:

    After launching the application you will be brought to the Login Page.

    Login Page
        Username: test
        Password: test

        The login page will have a username and password input, an exit button and a login button. The exit button will close the application after the approval of the confirmation alert.
        The login button will take the input from the username and password text boxes and determine if the credentials are valid. If the credentials are valid the user will log in and an alert
        will show displaying if there are any appointments within 15 minutes of the current time. If the login credentials are invalid then an error alert will show saying so.

    Menu Page
        The menu page will have a button for the reports, customers, and appointments pages that once clicked will navigate to the corresponding page. There are two more buttons log out and exit.
        The logout button will return the user back to the login page once the confirmation alert has been approved. The exit button will close the application after approval from the confirmation alert.

    Reports Page
        The reports page will have 3 different tabs displaying the different reports from the application. For the Contact Schedule tab to populate a schedule you need to select the contact that you would like to
        populate the schedule for.

            Additional Report:
                The additional report I made displays the number of appointments for each customer.

        The refresh button will re-populate the appointments by type, month, and customer table views. The return button will take the user back to the menu page.

    Customers Page
        The customers page will display a list of all the customers. There is an add button that once clicked will display the customer menu that can be used to add a customer by selecting either the save or apply button.
        The difference between the save and apply button is that the save button will close the customer menu and the apply button will not. The cancel button will also close the customer menu but won't save any customer information.
        To use the update functionality you must first select the customer you want to update then click the update button and the customer's information will be populated in the user inputs. Once you have made the desired updates you can select
        either save or apply to make your changes. To use the delete button you must first select a customer then click delete which will delete the customer after approval of the confirmation alert. The cancel button will return the user back to the menu page.

    Appointments Page
        The appointments page will display two tabs that contain a list of all appointments. These list can be filtered depending on which tab you are in, the month tab will filter the appointments based on the month and the week tab will filter them based on
        the week. To filter them all you need to do is select a date form the date picker, and it will filter the appointments list. The user inputs work in the same way as the user inputs from the customers page, everything must be filled out with the appropriate
        information and the start/end times should be within the hours of 8am to 10pm EST.