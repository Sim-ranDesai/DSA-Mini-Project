


package projects;
/*
 * STATEMENT : To create a phone book using BINART SEARCH TREE
 */


/* Data Structures used :
 * BST --> To store the contact details (name and number)
 * Stack --> To store the call history
 */


/*
 * CONTRIBUTERS : 
 *  --> Simran Desai
 *  --> Sanika Deshpande
 *  --> Gargee Dorle
 *  --> Mayuri Gadhave
 */


// import java package
import java.util.*;

import java.io.*;

// class to represent the binary search tree
class Node
{                      
	Node next;

	// declaring customer details
	String customer_name;
	String customer_number;
	Node rc;
	Node lc;

	Node(String customer_name,String customer_number) // constructor
	{
		this.customer_name = customer_name;
		this.customer_number = customer_number;
		lc = rc = null;
	}
}


// class to represent the operations 
class Function
{
	Scanner sc = new Scanner(System.in);
	int ch = 0;

	// root represents the root node of the
	// binary search tree structure
	Node root = null;

	// import stack from stack framework 
	// to implement call function
	Stack<String> stac = new Stack<>();

	// login into the account
	boolean login() 
	{
		System.out.println("Enter the password : ");
		String password = sc.next();
		String hardcode = "India@123";

		// compare passwords 
		// loop till correct password is not entered
		while(password.compareTo(hardcode)!=0) {
			System.out.println("Incorrect password!! \nTry Again");
			login();
			return false;
		}
		return true;
	}


	// creation of binary tree 
	// storing name and number of customers
	void create(){
		String name = "", number = "";

		// loop till user wants to add a new contact
		do {
			System.out.println("Enter customer name : ");
			name = sc.nextLine();

			// validation for the number to be a 10 digit number
			do {
				System.out.println("Enter customer numberof 10 digits :");
				number = sc.nextLine();

			}while(number.length() != 10);

			// create a new node to store 
			// customer name and number 
			Node newNode = new Node(name,number);

			// for first contact
			if(root == null)
			{
				root = newNode;
			}

			// for rest of the contacts
			else
			{
				// tempory variable to percolate the tree structure
				Node ptr = root;

				// insert contact names such that they follow BST property
				while(ptr != null)
				{
					// new contact added to the left
					if(ptr.customer_name.compareToIgnoreCase(newNode.customer_name) > 0)
					{
						if(ptr.lc == null)
						{
							ptr.lc = newNode;
							ptr = null;
						}

						else
						{
							ptr = ptr.lc;
						}
					}

					// new contact added to the right
					else if(ptr.customer_name.compareToIgnoreCase(newNode.customer_name) < 0)
					{
						if(ptr.rc == null)
						{
							ptr.rc = newNode;
							ptr = null;
						}

						else
						{
							ptr = ptr.rc;
						} 
					}
				}
			}


			System.out.println("Enter 1 to add another contact,, 0 otherwise : ");
			ch = sc.nextInt();
			sc.nextLine();
		}while(ch != 0);
	}


	// display contacts in recursive inorder fashion
	void display(Node root)   
	{
		// recursively percolate the binary search tree
		if(root != null)
		{
			display(root.lc);
			System.out.println(root.customer_name+"\t\t"+root.customer_number);
			display(root.rc);
		}

	}

	// search for a contact
	boolean Search(String name) {

		// base case : contact list is empty 
		if(root == null)
		{
			System.out.println("Phone Book is empty!!");
			return false;
		}

		int flag = 0;
		Node ptr = root;
		while(ptr != null)
		{
			// contact found
			if(ptr.customer_name.compareToIgnoreCase(name) == 0)
			{
				System.out.println("Search successful!! \n Contact found");
				System.out.println("Customer Name\t\t Customer Number");
				System.out.print(ptr.customer_name+"\t\t"+ptr.customer_number);
				// set flag to 1
				flag = 1;
				break;
			}

			// else search in the left side
			else if(ptr.customer_name.compareToIgnoreCase(name) > 0)
			{
				ptr = ptr.lc;
			}

			// else search in the right side
			else if(ptr.customer_name.compareToIgnoreCase(name) < 0)
			{
				ptr = ptr.rc;
			}
		}


		// validation for contact not found
		if(flag == 0)
		{
			System.out.println("Search unsuccessful!! \nContact not found");
			return false;
		}
		return true;
	}



	// update name or number of the contact
	void update()
	{
		// base case : their are no contacts in the phone book
		if(root == null)
		{
			System.out.println("Phone book is empty!!");
			return;
		}


		int flag = 0;
		// switch case for updating name or number
		System.out.println("Enter name of the contact whose name or number you want to update : ");
		String name = sc.nextLine();
		System.out.println("Do you want to \n0. Exit \n1. Update name : \n2. Update number : ");
		System.out.println("Enter your choice : ");
		int choice = sc.nextInt();


		switch(choice)
		{
		// exit the feature
		case 0:
			System.exit(0);
			break;

			// update name
		case 1:
			Node temp = root;
			if(Search(name) == true)
			{
				// then ask for the update they want to make 
				System.out.println("Enter the updated name : ");
				String newName = sc.next();

				// searching for the contact
				while(temp!=null) {
					if(temp.customer_name.compareToIgnoreCase(name) == 0)
					{
						// contact found and
						// value is updated
						temp.customer_name = newName;  
						System.out.println("Update successful!");
						flag = 1;
						break;
					}

					else if(temp.customer_name.compareToIgnoreCase(name) > 0)
					{
						temp = temp.lc;
					}

					else if(temp.customer_name.compareToIgnoreCase(name) < 0)
					{
						temp = temp.rc;
					}
				}
			}

			// validation :
			// if contact not found
			if(flag == 0)
			{
				System.out.println("Contact not found!!");
				break;

			}
			break;

			// update number of a contact
		case 2 :
			// input the name to be updated 
			Node tem = root;
			int flag1 = 0;
			if(Search(name) == true)
			{    
				// then ask for the update they want to make 
				System.out.println("Enter the updated number : ");
				String newNumber = sc.next();
				while(tem != null) 
				{				
					if(tem.customer_name.compareToIgnoreCase(name) == 0)
					{
						// update the number
						tem.customer_number = newNumber;  
						System.out.println("Update successful!!");
						flag1 = 1;
						break;
					}

					else if(tem.customer_name.compareToIgnoreCase(name)>0)
					{
						tem = tem.lc;
					}

					else if(tem.customer_name.compareToIgnoreCase(name)<0)
					{
						tem = tem.rc;
					}
				}
			}

			// validation for contact not found
			if(flag1 == 0)
			{
				System.out.println("Contact not found!!");
				break;
			}
			break; 

		default:
			System.out.println("Enter valid choice : ");
			break;
		}	
	}



	// recursively call the delete function
	void delete(String del)
	{
		root = deleterec( root, del);
	}



	// delete contact from the phone book
	Node deleterec(Node root,String del) {

		// base case : their are no contacts in the phone book
		if(root == null) 
		{
			return root;
		}

		// search for the contact based on name first 
		if(root.customer_name.compareToIgnoreCase(del) > 0)
		{
			root.lc = deleterec(root.lc,del);
		}

		else if(root.customer_name.compareToIgnoreCase(del) < 0)
		{
			root.rc = deleterec(root.rc,del);
		}

		// contact found
		else
		{
			if(root.lc == null)
				return root.rc;
			else if(root.rc == null)
				return root.lc;

			root.customer_name = minValue(root.rc);

			root.rc = deleterec(root.rc, root.customer_name);
		}
		return root;
	}



	// search for the predecessor of the number to be deleted
	String minValue(Node ptr)
	{
		// predecessor os the rifght most node 
		// in the left tree
		String minv = ptr.customer_name;
		while (ptr.lc != null) 
		{
			minv = ptr.lc.customer_name;
			ptr = root.lc;
		}
		return minv;
	}



	// make a call
	void call() 
	{

		String Name;

		// base case : their are no contacts in the phone book
		if(root == null)
		{
			return;
		}

		System.out.println("Enter the name you wish to call");
		Name = sc.next();
		Search(Name);

		// validation : if number not found 
		if(!Search(Name))
		{
			System.out.println("Contact not found!!");
			System.out.println("Enter correct name :");
			Name = sc.next();

		}

		// contact found and is on call
		System.out.println("*********ON CALL***********");
		// push contact name into history stack
		stac.push(Name);
		System.out.println("You are on call with " + Name);
	}


	// call history
	void History() 
	{
		System.out.println("*********HISTORY*********");


		if(stac.isEmpty()) 
		{
			System.out.println("No calls today!! \nCall history empty");
		}

		else 
		{
			// pop from the history stack
			while(!stac.isEmpty())
			{
				System.out.println(stac.pop());
			}
		}
	}
}






// main class
public class MobileBook1 
{
	public static void main(String args[]) 
	{

		Scanner sc = new Scanner(System.in);

		// object of class to instantiate the functions
		Function fun = new Function();

		String name = "";
		int cont = 0;
		System.out.println("\t\tWELCOME TO THE PHONE BOOK");
		System.out.println("\nLOG IN");
		fun.login();

		// 
		do {
			System.out.println("\0. Exit \n1. Add contacts \n2. Display contact details \n3. Search for a contact \n4. Update contact details \n5. Delete a contact \n6. Call a contact \n7. Display history");
			System.out.println("Enter your chioce : ");
			int ch = sc.nextInt();


			switch(ch)
			{

			// Exit program
			case 0:
				System.exit(0);
				break;


				// call create function
			case 1:
				fun.create();                          
				break;


				// call display function 
			case 2:
				System.out.println("Contact Name\t\tContact Number");
				fun.display(fun.root);                   
				break;


				// call Search function
			case 3:
				System.out.println("Enter the contact name you want to search : ");   
				sc.nextLine();
				name = sc.nextLine();
				fun.Search(name);
				break;


				// call update function 
			case 4:
				fun.update();
				break;


				// delete a contact
			case 5:
				System.out.println("Enter contact name that you want to delete : ");
				sc.next();
				String del = sc.nextLine();
				fun.delete(del);
				break;


				// to call a contact
			case 6:
				fun.call();
				break;


				// to view call history
			case 7:
				fun.History();
				break;


				// display the contacts in text file
			case 8:
				File contacts = new File("Contacts.txt");

				while (sc.hasNextLine())
				{
					System.out.println(sc.nextLine());
				}


			default :
				System.out.println("Invalid choice!!");
				break;
			}

			System.out.println("\nEnter 1 to continue with the phone book, 0 to terminate : ");
			cont = sc.nextInt();
		}while(cont != 1);
		sc.close();
	}
}



