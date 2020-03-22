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
 
 ##How to use it
 1) Run VocabAppGuiFX
 2) Enter your name.
 3) Decide whether you'd like to proceed with a sample database (saves you from having to add entries before testing) or 
    without.
 4) Your are now in MAIN. Enter an entry with description, meaning, comment, and example sentence (comment and example sentence not required)
  and click 'add'.
 5) Go to MORE --> DATABASE and notice that your entry as well as the example entries are in the table (depending on whether
 you also loaded the example database)
 6) Add and/or delete entries in DATABASE (you must add at least description and meaning if you want to add)
 7) Go to MORE --> TEST and click 'Start!'. Hit 'Enter' to submit and get a new question. Click 'Return to main' or any of 
  the menu items to go to a different page.
 8) Go to MORE --> SEARCH to search your entries. 
 9) Go to MORE --> QUIT or close the window to save and exit.
 10) Run the application again.
 11) Enter your name. Notice that this time it does not ask you if you want to load an example database, because you
 are not a new user.
 12) Go to PROFILE and notice that the success rate of your previous session has been recorded (it currently records
 whenever you quit the program, so in the previous session it will have shown 0%).
 
 Easter Eggs you may find: The App is not entirely robust yet. I need to add guards to make sure that you must at least
 enter description and meaning for the words/phrases. It's also not complete yet. I want to implement the Delete Profile
 button as well as some other features and make it look nicer. I also want to improve the success rate and the line chart:
 to avoid dividing by 0, all attempts start at 1. That works, but distorts the statistics. Also, the line chart currently
 only represents the changes over all sessions, instead of individual sessions (ie it does not drop down to 0 if you
 miss every shot at a single session). But since those are likely bigger changes I want to save that for Phase 4.
 
 
 
    
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