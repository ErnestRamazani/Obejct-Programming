import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Mainn {

	public static void main(String[] args) throws IOException {
		String[] fileLog; 			
		boolean b = true;
		ArrayList<User> users = new ArrayList<User>();
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("1.Admin\n2.User\nEnter your choice");
			int choice = sc.nextInt();
			
			if (choice == 1){
				String ques_acc,ques_pass;
				Scanner obj = new Scanner(System.in); 
				System.out.println("Enter the Account Number");
				ques_acc=obj.nextLine(); 
				System.out.println("Enter password"); 
				ques_pass=obj.nextLine();
				if (ques_acc.equals("00000")&& ques_pass.equals("12345")){
				
				while (b) {
					System.out.println("1.add a user\r\n" + "	2.delete a user (account must be empty)\r\n"
							+ "	3.list all users\r\n" + "	4.apply interest to all savings accounts\r\n"
							+ "	5.exit");
					choice = sc.nextInt();
					switch(choice) {
					
					case 1:
							System.out.println("Enter account number");
							String acc = sc.next();
							System.out.println("Enter pin");
							String Pin = sc.next();
							System.out.println("Enter checking account balance");
							double balance = sc.nextDouble();
							System.out.println("Enter saving account balance");
							double balanceSave = sc.nextDouble();
							System.out.println("Enter interest rate");
							double rate = sc.nextDouble();
							CheckingAccount account = new CheckingAccount();
							account.balance=balance;
							SavingAccount account2 = new SavingAccount();
							account2.balance=balanceSave;
							account2.interest=rate;
							User user = new User();
							user.Pin=Pin;
							user.save=account2;
							user.check=account;
							user.accNo=acc;
							users.add(user);
					break;
					case 2:
							System.out.println("Enter pin");
							String deletePin = sc.next();
							for(int i=0;i<users.size();i++) {
								if(users.get(i).Pin.equals(deletePin)) {
									users.remove(i);
									System.out.println("User Removed");
									break;
								}
							}
					break;
					
					case 3:
						for(int i=0;i<users.size();i++) {
							System.out.println("Account No:"+users.get(i).accNo+" Pin: "+users.get(i).Pin+" checking account:"+users.get(i).check.balance+" Saving account:"+users.get(i).save.balance+" Saving interest:"+users.get(i).save.interest);
						}
					break;
					case 4:
						for(int i=0;i<users.size();i++) {
							users.get(i).save.interest(6);
						}
					break;
					case 5:
							b=false;
					break;
					
					}
				}
						} else{
							System.out.println("Incorrect password or account number\nQuitting the programm"); 
								break; 
				}
				}
			else{
					
				b = true;
				while(b) {
					
					boolean use = true;
					User user = new User();
					while(use) {
						System.out.println("enter account number");
						String Pin = sc.next();
						for(int i=0;i<users.size();i++) {
							if(users.get(i).accNo.equals(Pin)) {
								user=users.get(i);
								use=false;
								break;
							}
						}
						if(use) {
							System.out.println("enter correct pin again");
						}
					}
					boolean bb=true;
					while(bb) {
					System.out.println("1.get checking balance\r\n"
							+ "	2.deposit into checking\r\n"
							+ "	3.withdraw from checking\r\n"
							+ "	4.get savings balance\r\n"
							+ "	5.deposit into savings\r\n"
							+ "	6.withdraw from savings\r\n"
							+ "	7.exit");
					
					 choice = sc.nextInt();
					
					switch (choice) {
					
					case 1:
							System.out.println("Balance:"+user.check.balance);
					break;
					case 2:
							System.out.println("Enter amount");
							double amt = sc.nextDouble();
							user.check.deposit(amt);
					break;
					case 3:
						System.out.println("Enter amount");
						double amt1 = sc.nextDouble();
						user.check.withdraw(amt1);
					break;
					case 4:
						System.out.println("Balance:"+user.save.balance);
					break;
					case 5:
						System.out.println("Enter amount");
						double amt3 = sc.nextDouble();
						user.save.deposit(amt3);
					break;
					case 6 :
						System.out.println("Enter amount");
						double amt6 = sc.nextDouble();
						user.save.withdraw(amt6);
					break;
					case 7:
						String result="";
						FileWriter fw;
						try {
							fw = new FileWriter("Output.txt");

							int size = users.size();

							for (int i = 0; i < size; i++) {
								result = result+" Account number: ";
								result = result + users.get(i).accNo+" ";
								result = result+" Pin: ";
								result = result + users.get(i).Pin+" ";
								result = result + " CheckingAccount Balance: ";
								result = result + Double.toString(users.get(i).check.balance)+" ";
								result = result + " Savings Account Balance: ";
								result = result + Double.toString(users.get(i).save.balance) + "\n";

								fw.write(result);

							}

							fw.close();
						} catch (IOException e) {

							e.printStackTrace();
						}
						System.exit(1);
					break;
					}
					
					
					}
					
				}

			}

		}

	}

}

