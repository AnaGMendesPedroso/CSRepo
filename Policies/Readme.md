
###Naming branch policies
	A branch shall be created for each issue.
	A branch shall be created only when someone starts the issue's development.
	The branch shall be named with the name of the developer and the number of the issue, divided with a slash. 
	Ex: jose/#55

###Configuring branch policies

	When starting to work on a new issue, use the following command to create and move to the new branch:
		git checkout -b firstName/featureNumber
		Ex: git checkout -b jose/#55

	During the development of the issue, the code should be committed and pushed constantly, everytime you have a code that works. (The code shall be committed to YOUR branch).
	The code for commit and push your branch is:
		git add .
		git commit -m "Message"
		git push origin firstName/featureNumber


	When the issue is finished, use the commands bellow to merge your branch with master and delete your branch:
		git checkout master
		git merge --no-ff firstName/featureNumber
		git branch -b firstName/featureNumber
		git push origin master
