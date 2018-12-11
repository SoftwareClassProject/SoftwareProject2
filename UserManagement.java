import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManagement {
	//this method writes to the file
	public static void Write(ArrayList<ArrayList<String>> users) throws IOException{
		BufferedWriter buff=new BufferedWriter(new FileWriter("/Users/RossMonroe/Documents/Userfile.txt"));
	 	
	 	//for loop that writes the text to the file in the specific format
        for(int i=0; i<users.size(); i++){
        	buff.write(users.get(i).get(0) + " " + users.get(i).get(1) + " " + users.get(i).get(2));
            buff.newLine();
        }
     
	     //Sending final output
	     buff.flush();
	}
	
	//This method displays the choices the user has after the choose option 1 on the previous menu. 
	public static ArrayList<ArrayList<String>> UserManagementChoices(ArrayList<ArrayList<String>> users) throws IOException, NoSuchAlgorithmException {
		int exitnum=0;
		//This switch case presents the choices available to the user once they choose option 1 from the other page.
		loop: while(exitnum!=5){
			System.out.println("Please enter one of the following numbers to execute it's prompt:");
			System.out.println("1. Display All Users");
			System.out.println("2. Add New User");
			System.out.println("3. Update Existing User");
			System.out.println("4. Delete Existing User");
			System.out.println("5. Return to Main Menu");
			Scanner menuanswer= new Scanner (System.in);
			int mAnswer=menuanswer.nextInt();
			switch(mAnswer){
				//This case displays all users once the user selects 1
				case(1):
					for(int i=0; i<users.size(); i++){
						for(int j=0; j<users.get(i).size(); j++){
							System.out.print(users.get(i).get(j) + "\t");
						}
						System.out.print("\n");
					}
					//Adds the activity to the user log report
					String activity="Displayed all users";
					Main.TrackActivity(activity);
			    	break;
			    
			    //This case allows the user to add a new user	
			    case(2):
			    	ArrayList<String> newUser=new ArrayList<String>();
		        	System.out.println("Please enter new username.");
			        Scanner nameScan= new Scanner (System.in);
					String aName=nameScan.nextLine();
					System.out.println("Please enter new password.");
			        Scanner pwScan= new Scanner (System.in);
					String aPassword=pwScan.nextLine();
					int randomNum = (int)(Math.random() * 99999 + 10000);
			    	String EID=Integer.toString(randomNum);
			    	newUser.add(EID);
		        	newUser.add(aName);
		        	newUser.add(Main.Encryption(aPassword));
		        	users.add(newUser);
		        	//Adds the activity to the user log report
		        	activity="Added new user: " + aName + ", with EID: " + EID;
		        	Main.TrackActivity(activity);
		        	System.out.println(aName + " has been added to the system with employee ID: " + EID);
		        	break;
		        	
		        //Case 3 allows the user to update the users and asks which they would like to update.
		        //It splits it into another case where they can update the username or the password. 
			    case(3):
			    	System.out.println("What would you like to update?");
				    System.out.println("1. User Name");
					System.out.println("2. Password");
					mAnswer=menuanswer.nextInt();
					switch(mAnswer){
					case(1):
						System.out.println("Please enter the employee ID of the employee's name you'd like to update");
						Scanner unamescan=new Scanner (System.in);
						String username=unamescan.nextLine();
						for(int l=0; l<users.size(); l++){
							if(users.get(l).get(0).equals(username)){
								System.out.println("What would you like to update the user's name to?");
								String newUserName=unamescan.nextLine();
								users.get(l).remove(1);
								users.get(l).add(1, newUserName);
							}
						}
						//Adds the activity to the user log report
						activity="Updated existing username";
						Main.TrackActivity(activity);
						break;
						
					case(2):
						System.out.println("Please enter the employee ID of the employee's password you'd like to update");
						Scanner pwScan1=new Scanner(System.in);
						String pw=pwScan1.nextLine();
						for(int l=0; l<users.size(); l++){
							if(users.get(l).get(0).equals(pw)){
								System.out.println("What would you like to update the password to?");
								String newPw=pwScan1.nextLine();
								users.get(l).remove(2);
								users.get(l).add(2, Main.Encryption(newPw));
							}
						}	
						//Adds the activity to the user log report
						activity="Updated existing user password";
						Main.TrackActivity(activity);
						break;
					}
			    	break;
			    	
			    //This allows the user to delete a user. 
			    //The only way that they can update the user is if they know the password as well. 
			    case(4):
			    	System.out.println("Please enter the Employee ID of the user to delete.");
			        Scanner nScan= new Scanner (System.in);
					String eid=nScan.nextLine();
					System.out.println("Please enter the password of the user to delete.");
			        Scanner pScan= new Scanner (System.in);
					String password=pScan.nextLine();
					for(int i=0; i<users.size(); i++){
						if(users.get(i).get(0).equals(eid) && users.get(i).get(2).equals(Main.Encryption(password))){
							users.remove(i);
						}
					}
					//Adds the activity to the user log report
					activity="Deleted employee: " + eid;
					Main.TrackActivity(activity);
			    	break;
			    
			    //Case 5 logs out of the menu 
			    case(5):
			    	break loop;
			    
			    default:
		        	System.out.println("Invalid number. Please try again.");
		    	
			}
			 Write(users);
		}
		return users;
	}
}
