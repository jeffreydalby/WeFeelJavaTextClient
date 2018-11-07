# A Text based version of a simple WeFeel Client
Created for Advanced Java Programming at Boston University, this is a class project designed to explore the various programming techniques taught in class.

WeFeel is a mobile app developed to help people track their emotional state throughout the week to assist with mental health care. It is written in C# using Xamarin to allow it to run on any mobile platform.

This project, based on that concept, will allow a user to create “Emotion Entries,” view previous entries, and output a “discussion” text file to be printed and given to a therapist containing entries where particular emotions are more intense to facilitate discussion. 

Since this is being used as a tool to learn java and meet the needs of the class, this version will be able to store objects natively into a “.dat” file (as well as the previous JSON implementation) and will implement a text based menu system.

To demonstrate the use of databases as well as dat files, user accounts will be stored in a derby database. A simulated background synchronization process has been added, which shows the use of concurrency (implementing a full JSON REST API is beyond the scope/timeframe available for this assignment)

# Requirements as implemented:

## Create list of emotion entries
WeFeel can create a list of emotion entries.
## Output entries to system console
WeFeel can output all Emotion Entries in a formatted text to the system console, via junit test.
## Write all emotion entries to a file in json format
WeFeel will be able to persist entries between sessions 
## Read emotion entry objects from a json file 
WeFeel will read persisted data
## Accounts creation/persistence.
WeFeel will be able to create and store account objects in JSON format.
## Journal Entry creation/persistence 
WeFeel will be able to create and store journal entries in JSON format.
## WeFeel will collect entries via a text menu
Users will be able to create a new emotion entry 
## WeFeel will display emotion entries
Users will be able to select an option to display all emotion entries
## WeFeel will output “low days” to a “discussion text” file
Users may select to output all entries with a “low” Life Outlook value to a text file for them to discuss with their therapist 
## WeFeel will display “low days”
Users will be able to view all entries with an Outlook less than 5 
## WeFeel will display “high days”
Users will be able to view all entries with an Outlook greater than 5
## WeFeel will export to JSON
Users will be able to export all entries to JSON format.
## WeFeel will create a default user account
On launch WeFeel will look for a default account and either load the account or present a page to create a new account.
## WeFeel will have a user account creation page
A user account creation page will request account info from the user and store it as a default account
## WeFeel stored Emotion Entries based on the AccountID
In order to allow for a future release to support multiple account entries will not be stored in files based on the accountID
## WeFeel will be able to delete the default account
A user can delete their information to “reset” WeFeel to a default state.
## WeFeel simulates an online sync
WeFeel will simulate an online entry synchronization running as a background task to minimize delays to the user interface.
## WeFeel logs simulated connection data
The background sync process will create a “simulated” connection log file.

  

