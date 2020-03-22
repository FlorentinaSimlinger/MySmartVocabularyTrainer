# My Smart Vocabulary Trainer

## A user-driven vocabulary trainer


Being a non-native English speaker living in an English speaking
country I want *My Smart Vocabulary Trainer* to help the user learn words and phrases that they use on a day-to-day basis.
  
**What can you do with** ***My Smart Vocabulary Trainer*** **?**
-  create a database of words and phrases with your own interpretation  
-  enrich it with comments and example sentences
-  test yourself on your knowledge 
-  track your learning progress 

**Who can use it?**

*My Smart Vocabulary Trainer* is for anyone who wants to level up their 
language game. The design follows the principles of direct applicability, meaning that it allows you apply what you learn, and
learning what you need, meaning that you're prompted to learn something that you actually need in your daily life.
It is therefore **user-driven** in its application, **pragmatic** in its approach, and **perfect** for any skill-level.

> Let's Go! - *My Smart Vocabulary Trainer*
 
##User Stories
- As a user I want to be able to save entries to a database [implemented]
- As a user I want to be able to delete entries [implemented]
- As a user I want to be able to search for entries [implemented]
- As a user I want to be able to test myself on a random selection from the database [implemented]
- As a user I want to have a profile where I can 
    - change my name
    - see statistics [implemented]
    - reset statistics
    - delete the entire database
    - delete my profile
 - As a user I want to be able to save my profile [implemented]
 - As a user I want to be able to reload my profile [implemented]
 
 ##Instructions for Grader
 1) Run VocabAppGuiFX
 2) Enter your name and hit enter
 3) Decide whether you'd like to load an example database or not
 4) You are now in MAIN where you can add an entry to the database ***["add X to Y"]***
 5) Go to MORE -> DATABASE and notice that your entry (and the example database if applicable) appears.
 6) Delete an entry and add a new one ***["second required event related to elements of X in Y"]***
 (it is not super robust yet, please add at least description and meaning, comment and example are not required).
 7) Go to MORE -> TEST to test yourself
 8) Go to MORE-> QUIT or hit the 'Quit' button to exit, the files will automatically 
 save ***["save state of application"]***.
 9) Rerun the app with your name. Notice that this time it does not ask about the example database because you are not
 a new user.
 10) Go to MORE -> DATABASE to see that your entries are still there ***["reload state of application"]***.
 11) Go to PROFILE to your success rate from the previous sessions ***["visual component"]*** (currently it only saves when
 you quit the application, thus the result only appears after you login again)
12) You can also search specific entries in your database

##Ideas for extended version
- option to change existing entry if new entry with duplicate description is added
- scrape websites for example sentences and synonyms
- show awards for reaching 10 entries, 50 entries, 100 entries
- show awards for 50%, 75%, 80% success rate
- allow user to set individual goals such as success rates
- testing under timed conditions
- tracking response time 
- export the database in different formats, such as pdf and excel
- be able to import data
- be able to categorize my vocabulary in different categories and display only certain categories which a checkbox
- make an online version