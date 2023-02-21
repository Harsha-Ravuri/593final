# 593final
** CISC 593 Final Project **
Environment setup
Requisites:
JavaSE-1.8
IDE: ECLIPSE IDE
Code: https://github.com/Harsha-Ravuri/593final
Git Bash
Github account

Setup instructions:
Install softwares specified in Requirements if not installed already.
Using git bash, clone required repo specified above with following command

git clone https://github.com/Harsha-Ravuri/593final.git

The classes pertaining the execution of the Expenditure Tracking system are in 593final/src/

Introduction
This proposal describes continuation of work on the expenditure tracking system from CISC593.
The existing features already implemented in the app cover adding, editing and displaying
expenditure expenses. This proposal adds on to the existing app with new features required for
the app that are currently not implemented. Version 1 will cover these features based on total
expense and version 2 will cover this feature based on expense by category.
The features are as follows:
Version 1 Features
1) Set Budget: Asking the user to set a weekly limit/budget for the total expenses
2) Show expenses by time period: Show total expenses of user by selected week
3) Budget Prediction: Showing the user whether they will be under budget, on budget or
over budget for the month overall. The second point also gives us a scope to provide a
predicted list of whether based on current expenditure, will the user stay under budget or
not, for eg. let's say for current month, the budget was set to be 400, but after one week
the user has spent 150, so in four week he’d prolly end up spending 150*4 = 600, which
is 200 over their set budget. When budget prediction is requested, in this example, we
display “Based on current spending, you will be over budget for this month”
4) Add category: users can add a category that they seem fit. This category will then be
reflected as a new category within the app if approved by app developers. User can view
“category addition requests” and see what they’ve submitted and the status
Version 2 Features
5) Set Budget: Asking the user to set a limit/budget per week for a specific category
6) Show expenses by time period: Show category based expenses of user by selected
week
7) Budget Prediction: Showing the user whether they will be under budget, on budget or
over budget for the category per month. For eg. If the user set budget for entertainment
as 200 and in the first week they have spent 60, they are on track to go over budget by
40. When budget prediction is requested for entertainment, in this example, we display
“Based on current spending, you will be over budget for this month”.
8) Add category: In version 2 “category addition requests” will be either “Approved” or
“denied” by app developers
