import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class InventoryManagement {
	
	//Method that has for loop that prints and formats each element of the ArrayList 
	public static void Print(ArrayList<ArrayList<String>> inventory){
		for(int i=0; i<inventory.size(); i++){
			System.out.printf("%-11s%-56s%-14s%-14s\n", inventory.get(i).get(0),inventory.get(i).get(1),inventory.get(i).get(2),inventory.get(i).get(3));
		}
	}
	
	//Method that reads from the inventory file.
	public static ArrayList<ArrayList<String>> Read() throws FileNotFoundException{
		//creating two dimensional array which is an ArrayList<ArrayList<String>>
		ArrayList<ArrayList<String>> items=new ArrayList<ArrayList<String>>();
		
		//Scanner that reads the data from the specified file
		Scanner itemsFileScan=new Scanner(new File("/Users/RossMonroe/Documents/Inventoryfile.txt"));
		String[] itemInfo;
		int i=0;
		while(itemsFileScan.hasNextLine()){
			itemInfo=itemsFileScan.nextLine().split("\t");
			ArrayList<String> anItem=new ArrayList<String>();
				for(int j=0; j<itemInfo.length; j++){
					anItem.add(itemInfo[j]);
				}
			items.add(anItem);
			i++;
		}
		return items;
	}
	
	//Method that writes to the file with a loop 
	public static void Write(ArrayList<ArrayList<String>> items) throws IOException{
		BufferedWriter buff=new BufferedWriter(new FileWriter("/Users/RossMonroe/Documents/Inventoryfile.txt"));
	 	
	 	//This is a for loop that writes the text to the file in the specific format
		 for(int i=0; i<items.size(); i++){
	            buff.write(items.get(i).get(0) + "\t" +	items.get(i).get(1) + "\t" + items.get(i).get(2) + "\t" + items.get(i).get(3));
	            buff.newLine();
	     }
	     //Sending final output
	     buff.flush();
	}
	
	//This method updates the data from the users choice from the switch case. 
	public static ArrayList<ArrayList<String>> updateData(ArrayList<ArrayList<String>> inventory){
		int exitnum=0;
		loop: while(exitnum!=4){
			System.out.println("Please enter one of the following numbers to execute it's prompt:");
			System.out.println("1. Update quantity");
			System.out.println("2. Update product name");
			System.out.println("3. Update unit price");
			System.out.println("4. Return to Inventory Management Menu");
			Scanner menuanswer= new Scanner (System.in);
			int manswer=menuanswer.nextInt();
			
			//Switch statement that gets user's menu choice to execute it's prompt
			switch(manswer){
		        	
		        //Case for updating the quantity of a product. Uses for loop to find matching SKU number(entered by user),
		        //then removes the original quantity and adds the new quantity
		        case(1):
		        	Print(inventory);
			        System.out.println("Please enter the SKU number of the product you want to update the quantity on.");
			        Scanner Scan= new Scanner (System.in);
					String skuNum=Scan.nextLine();
					System.out.println("Please enter the new quantity.");
					String quantity=Scan.nextLine();
					for(int i=0; i<inventory.size(); i++){
						if(inventory.get(i).get(0).equals(skuNum)){
							inventory.get(i).remove(3);
							inventory.get(i).add(quantity);
						}
					}
					//Adds the activity to the user log report
					String activity="Updated quantity of SKU number item " + skuNum + " to " + quantity;
					Main.TrackActivity(activity);
		        	break;
		        	
		        //Case for updating the name of a product. Uses for loop to find matching SKU number(entered by user),
			    //then removes the original name and adds the new name
		        case(2):
		        	Print(inventory);
			        System.out.println("Please enter the SKU number of the product you want to change the name of.");
			        Scanner ScanName= new Scanner (System.in);
					skuNum= ScanName.nextLine();
					System.out.println("Please enter the new name.");
					String name=ScanName.nextLine();
					for(int i=0; i<inventory.size(); i++){
						if(inventory.get(i).get(0).equals(skuNum)){
							inventory.get(i).remove(1);
							inventory.get(i).add(1, name);
						}
					}
					//Adds the activity to the user log report
					activity="Updated product name of SKU number item " + skuNum + " to " + name;
					Main.TrackActivity(activity);
		        	break;
		        	
		        //Case for updating the price of a product. Uses for loop to find matching SKU number(entered by user),
			    //then removes the original price and adds the new price
		        case(3):
		        	Print(inventory);
			        System.out.println("Please enter the SKU number of the product you want to update the unit price on.");
			        Scanner ScanPrice= new Scanner (System.in);
					skuNum=ScanPrice.nextLine();
					System.out.println("Please enter the new price.");
					String price=ScanPrice.nextLine();
					for(int i=0; i<inventory.size(); i++){
						if(inventory.get(i).get(0).equals(skuNum)){
							inventory.get(i).remove(2);
							inventory.get(i).add(2, price);
						}
					}
					//Adds the activity to the user log report
					activity="Updated unit price of SKU number item " + skuNum + " to " + price;
					Main.TrackActivity(activity);
					break;
				
					
				//Case 4 breaks from the loop and out of the menu.
		        case(4):
		        	break loop;
		        
		        default:
		        	System.out.println("Invalid number. Please try again.");
			}
		}
		return inventory;
	}

	
	//This method displays the choices for the inventory management through a switch case. 
	public static ArrayList<ArrayList<String>> InventoryManagementChoices() throws IOException{
		//creating two dimensional array which is an ArrayList<ArrayList<String>>
		ArrayList<ArrayList<String>> inventory=Read();
		int exitnum=0;
		loop: while(exitnum!=5){
			System.out.println("Please enter one of the following numbers to execute it's prompt:");
			System.out.println("1. Display All Inventory");
			System.out.println("2. Add New Inventory");
			System.out.println("3. Update Existing Inventory");
			System.out.println("4. Delete Existing Inventory");
			System.out.println("5. Return to Main Menu");
			Scanner menuanswer= new Scanner (System.in);
			int mAnswer=menuanswer.nextInt();
			switch(mAnswer){
			
			//Display All Inventory
			case(1):
				Print(inventory);
				//Adds the activity to the user log report
				String activity="Displayed all inventory";
				Main.TrackActivity(activity);
	        	break;
	        	
	        //Add new inventory by scanning the user input for the sku, name, price, and quantity. 
	        case(2):
	        	ArrayList<String> newInventory=new ArrayList<String>();
		    	System.out.println("Please enter the product name.");
		        Scanner scan= new Scanner (System.in);
				String aName=scan.nextLine();
				System.out.println("Please enter the unit price.");
				String aPrice=scan.nextLine();
				System.out.println("Please enter the quantity.");
				String aQuantity=scan.nextLine();
				int randomNum = (int)(Math.random() * 99999 + 10000);
		    	String aSkuNum=Integer.toString(randomNum);
		    	newInventory.add(aSkuNum);
		    	newInventory.add(aName);
		    	newInventory.add(aPrice);
		    	newInventory.add(aQuantity);
		    	inventory.add(newInventory);
		    	//Adds the activity to the user log report
		    	activity="Added new inventory: " + aName;
		    	Main.TrackActivity(activity);
		    	break;
	        	
	        //Update Existing inventory by calling the other method that has another set of switch cases.
	        case(3):
	        	InventoryManagement.updateData(inventory);
	        	break;
	        	
	        //Delete Existing inventory and checks for matching skus through the loop.
	        case(4):
	        	Print(inventory);
	        	System.out.println("Please enter the SKU number of the item you want to delete.");
		        Scanner skuNew= new Scanner (System.in);
				String aSku=skuNew.nextLine();
				for(int i=0; i<inventory.size(); i++){
					if(inventory.get(i).get(0).equals(aSku)){
						inventory.remove(i);
					}
				}
				//Adds the activity to the user log report
				activity="Deleted inventory of SKU number item " + aSku;
				Main.TrackActivity(activity);
	        	break;
	        
	        //Return to main menu
	        case(5):
	        	break loop;
	        
	        default: 
	        	System.out.println("Invalid number. Please try again.");
			}
			Write(inventory);
		}
		return inventory;
	}
}
