import java.util.Scanner;
import java.io.*;
class Users
{
	String username, password;
	public int createUser()
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
		
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Username (must not contain spaces, '.', or '/'): ");
			username = sc.next();
			FileWriter writer = new FileWriter(username+".txt");
			System.out.print("Create a password: ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Enter Name of Groom: ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Enter Name of Bride: ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Enter your budget: Rs. ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Enter the number of attendees: ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			System.out.print("Please mention any special arrangements needed (if any): ");
			writer.write(sc.next()+sc.nextLine()+"\n");
			writer.close();
		}
		catch(IOException e)
		{
			System.out.println("Error saving details!");
		}
		return 0;
	}
}
public class Main
{
	public static void main(String[] args) throws IOException
	{
		Users user = new Users();
		int id = user.createUser();
		System.out.println("User created with id = " + id);
	}
}