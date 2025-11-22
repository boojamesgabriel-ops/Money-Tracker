//Main Class
import java.util.Scanner;
public class Main 
{
    public static void main(String[] args)
    {
        Money_tracker track = new Money_tracker();

        track.load_file();

        try(Scanner scan = new Scanner(System.in))
        {
            int choice;

            do
            {
                //display of the menu
                track.Menu();
                System.out.print("Enter choice: ");
                choice = scan.nextInt();

                switch(choice)
                {
                    case 1:
                    //Setting Gross Income
                    track.income();
                    break;

                    case 2:
                    //Add expenses
                    track.expense();
                    break;

                    case 3:
                    //View Balance
                    track.balance();
                    break;

                    case 4:
                    //View Transaction History
                    track.track_sheet();
                    break;

                    case 5:
                    //Exiting and Saving to the File
                    track.save_file();
                    System.out.println("Thank you for using this system. BYE!");
                    break;

                    default:
                    System.out.println("Invalid Input. Try Again.");
                    break;
                }
            } while(choice != 5);
        }
    }
}
