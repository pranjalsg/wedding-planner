import java.util.Scanner;
import java.io.*;
class Users
{
	private String username, password;
	
	public boolean checkUserExists(String usr) throws FileNotFoundException
	{
		char choice = 'a';
		boolean flag = false;
		Scanner scfile;
		File file;
		try
		{
			file = new File(usr + ".txt");
			scfile = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			return false;
		}
		Scanner sc = new Scanner(System.in);
		if(file.exists() && !file.isDirectory())
		{
			String rpwd = scfile.next()+scfile.nextLine();
			do
			{
				System.out.print("Enter Password: ");
				String pwd = sc.next() + sc.nextLine();
				if(rpwd.equals(pwd))
				{
					flag = true;
					break;
				}
				else
				{
					System.out.print("Incorrect User ID or Password! Try again (t) or enter any other character to Create new User: ");
					choice = (sc.next()).charAt(0);
				}
			}while(choice=='t');
		}
		else
			return flag;
		
		return flag;
	}
	
	public int createUser() throws FileNotFoundException
	{
		/******************
		
		creates a file in the following format (Username.txt)
		
		*password
		*Name of Groom
		*Name of Bride
		*Budget
		*No. of Attendees
		*Any special arrangements required?
		
		*******************/
		int stat = -1;
		String username = "";
		
		try
		{
			Scanner sc = new Scanner(System.in);
			
			File file;
			do
			{
				System.out.print("Create Username (must not contain spaces, '.', or '/'): ");
				username = sc.next();
				file = new File(username + ".txt");
				if(file.exists() && !file.isDirectory())
				{
					System.out.println("Username already exists! Try again!");
					continue;
				}
			}while(file.exists() && !file.isDirectory());
			
			FileWriter writer = new FileWriter(username + ".txt");
			
			System.out.print("Create a password: ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Enter Name of Groom: ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Enter Name of Bride: ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Enter proposed Date of Wedding: ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Enter proposed Place of Wedding: ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Enter your budget: Rs. ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Enter the number of attendees: ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Please mention any special arrangements needed (if any): ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			writer.close();
			stat = 0;
		}
		catch(Exception e)
		{
			System.out.println("Error saving details!");
		}
		return stat;
	}
	
	public int checkEventPlanned(String usr) throws FileNotFoundException
	{
		File file = new File(usr + "plan.txt");
		
		if(file.exists() && !file.isDirectory())
		{
			Scanner scf = new Scanner(file);
			return Integer.parseInt(scf.next());
		}
		else
			return -1;
	}
	
	public void showPlan(String usr) throws FileNotFoundException
	{
		File file = new File(usr + "plan.txt");
		
		Scanner sc = new Scanner(file);
		System.out.println("\nYou have been billed " + sc.nextLine() + " for the plan.");
		while(sc.hasNext())
		{
			System.out.println(sc.nextLine());
		}
		System.out.println();
		sc.close();
	}
}
public class Main
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		Users user = new Users();
		
		System.out.print("Enter username: ");
		String username = sc.next();
		if(user.checkUserExists(username))
		{
			int status = user.checkEventPlanned(username);
			if(status != -1)
			{
				System.out.print("Congratulations! Your event has been planned! Your bill is Rs. " + status + ". Continue (y/n)?");
				char ch = sc.next().charAt(0);
				if(ch=='y' || ch=='Y')
				{
					user.showPlan(username);
				}
			}
			else
				System.out.println("Sorry but your event hasn't been planned yet. Try again later!");
		}
		else
		{
			int stat = user.createUser();
			if(stat == -1)
			System.out.println("Error creating User!");
			else
			System.out.println("User created successfully!");
		}	
	}
}