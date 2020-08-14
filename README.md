# bierlijst

This is a simple Android app that allows us to keep track of our beer statistics. Our system is kind of complex, a single beer has a lot of possibilities in terms of who pays for it and who drinks it. This is because we have cleaning tasks, and missing the deadline of a cleaning task means that everyone is allowed to drink one beer on your cost. We want to keep track of who cleans badly, so we can't reduce that amount. So if person A owes one beer to person B and vice versa, we can't reduce those values to 0. 

Here are several beers counters the app keeps track of per person:
 - Schoonmaakbier: How much beer person A owes to person B for missing cleaning tasks
 - Gestreeptbier: If a person does not have any schoonmaakbier he can drink for free, this is incremented
 - Gedronkenbier: How much a person has drinked
 
 Furthermore, everytime a person opens a beer, he throws the cap and if he hits a bucket he can give a beer to another person. The app keeps track of the amount of hits and the amount of throws per person. This is done twofold: all-time hits and throws and between every HV(2~3 months). 
