Mainn.class: Mainn.java 
	javac -g Mainn.java
SavingAccount.class: SavingAccount.java CheckingAccount.class
	javac -g Saving.java
CheckingAccount.class: CheckingAccount.java
	javac -g CheckingAccount.java
User.class: User.java
	javac -g User.java 
run: Mainn.class
	java Mainn
clean: 
	rm*.class
runSavingAccount: SavingAccount.class
	java SavingAccount
runCheckingAccount: CheckingAccount.class
	java CheckingAccount
runUser: User.class
	java User
debug: Mainn.class
	jdb Mainn
