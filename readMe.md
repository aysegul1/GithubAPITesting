Coding part wasn't too bad. I think I was on time and thinking I was done with it but I troubleshooting took way too long only to find the issue is a very simple one line change.
I have been reading about auths and did all the steps suggested on the internet with no successful results. Their status codes are not very clear. My issue wasn't all about
authentication but I kept getting 401s. My branch head name had an issue. I got it to work last minute which left me no time to figure out the new issue of Pull Request Number is not
getting passed to my value for get calls. I will continue troubleshooting but submitting the project as is.

#**What is GitHub pull request API testing and what does it test?**

GitHub pull request api automation validates GitHub's api via test user and test repository. The tests that are currently
included verify access to certain repo, creation of pull request on a repo, updating a pull request, and deleting a pull
request as well as merging a pull request and verifying that it is merged.
#Reasons
1. The reasons why I choose to go with these tests were to limit testing to basic create, read, update, and delete testing.
2. Timeboxing the project to roughly 2 hours
3. I was facing authentication issues at the begining
4. The basic CRUD testing is a good way to verify the most important scope of verifying Github's pull request api

#**What does it NOT test?**
1. There are many other scopes to the api such as listing pull request, commits, users, assignees and such.
2. It was a very open project, I thought testing basic api functionalities would be sufficient for the given time.

**HOW TO:**

1. Navigate to the repository link
2. Copy the repository link from the 'Clone or Download button'
3. Open Git bash (or any other command prompt of choice)
4. Navigate to the folder where you would like to save the project to
5. Type in the following command: 'git clone {Repository URL}'
6. Once the project is cloned to your local machine, open the project in InteliJ
7. You can do so by clicking File > Open Project

The tests will need to be run individually since the number value doesn't get passed. Please run test for deleting the pull request before running another create test.
I also have merge pull request test. Those can also be ran in order to get a successful create status.
