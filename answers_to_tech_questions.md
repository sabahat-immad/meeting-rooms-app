# meeting-rooms-app

### Answers to technical questions:

* What would you add to your solution if you had more time?

I would enhance the UI and make it more aligned, precised and attractive. I would also use constrain layout for more flexibilty over the UI and better support
of multiple secreen sizes. I would also work or a consisten naming convention and not use short names (eg. meeting room insted or mr).

* What steps would you take to ensure the display of text, buttons etc remain consistant as the project grows and more team members join the team?

I would make use of dimens.xml file to write the dimenstions of each widget with readable standard names instead of hardcoding it and place 
it in various screen resolution folders. I would also use constraint layout which I didnt for now because for shortage of time. 

* How would you track down a performance issue in production? Have you ever had to do this?

There are various ways depending on the situation. Its a best practice to always run all the tests beforehand to filter out any issues in the initial phase.
uploading the app bundle in testing mode on Play Store also helps with QA team downloading it and using in real environment. Its also good to have 
crashlytics implemented in the app to track down real time issues.
