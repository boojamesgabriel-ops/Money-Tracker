//Support Class || File handling file
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Money_tracker {
    
    private double gross_income = 0;
    private double amount = 0;
    Scanner scan = new Scanner(System.in);
    ArrayList<Transaction> transactions = new ArrayList<>(); 

    public void Menu() 
    {
        System.out.println("\n==========Money Tracker==========");
        System.out.println("1. Set Income");
        System.out.println("2. Add Expense");
        System.out.println("3. View Balance");
        System.out.println("4. View Transaction History");
        System.out.println("5. Exit");
    }

    public void income()
    {

        if(amount == 0)
        {
            while (true) 
            { 
            System.out.println("\n==========SET GROSS INCOME=========");
                System.out.print("Enter Gross Income: ");
                if(scan.hasNextDouble())
                {
                    amount = scan.nextDouble();
                    scan.nextLine();

                    if (amount > 0)
                    {
                        break;
                    }
                    else 
                    {
                        System.out.println("Income must be greater than zero");
                    }
                } 
                else
                {
                    System.out.println("INVALID INPUT. PLease input numbers(1-9)");
                    scan.nextLine();
                }
            }
            gross_income = amount;
            System.out.println("Gross Income of " + gross_income + " have been set successfully.");
        }
        else if(amount > 0)
        {
            while (true) 
            { 
            System.out.println("\n==========ADD TO GROSS INCOME=========");
                System.out.print("Enter Additional Income: ");
                if(scan.hasNextDouble())
                {
                    amount = scan.nextDouble();
                    scan.nextLine();

                    if (amount > 0)
                    {
                        break;
                    }
                    else 
                    {
                        System.out.println("Income must be greater than zero");
                    }
                } 
                else
                {
                    System.out.println("INVALID INPUT. PLease input numbers(1-9)");
                    scan.nextLine();
                }
            }
            gross_income += amount;
            System.out.println("Added Income of " + amount + " have been added to Gross Income successfully.");
        }    
    }    

    public void expense()
    {
        double amount = 0;
        String description;
        int categ;
        boolean valid = false;
        char answer;

        if(gross_income == 0)
            {
                System.out.println("\nSet your Gross Income First(Choice #1).");
                return;
            }

        System.out.println("\n==========ALLOCATE EXPENSES==========");
        do
        {
            do
            {
                    System.out.print("Enter the amount of the Expense: ");

                    if(scan.hasNextDouble())
                    {
                        amount = scan.nextDouble();
                        scan.nextLine();
                        if(amount <= 0)
                        {
                            System.out.println("The amount of the expense cannot be zero.");
                            valid = false;
                        }
                        else if (amount > gross_income)
                        {
                            System.out.println("The amount of the expense cannot be greater than the gross income.");
                            System.out.println(" ");
                            valid = false;
                        }
                        else
                        {
                            valid = true;
                        }
                    }
                    else
                    {
                        System.out.println("Invalid input! Please enter a number.");
                        scan.nextLine(); 
                        valid = false;
                    }


            }while(!valid);

            valid = false;

            Transaction.CATEGORY category = null;

            do
            {
                System.out.println("\n===Category===");
                System.out.println("1. Bills");
                System.out.println("2. Investments");
                System.out.println("3. Savings");
                System.out.println("4. Wants");

                System.out.print("Enter Category(1-4): ");

               if(!scan.hasNextInt())
                {
                    System.out.println("\nInvalid input! Please enter a number.");
                    scan.nextLine();
                    continue;
                }
            
                categ = scan.nextInt();
                scan.nextLine();
                
                if(categ == 1)
                {
                    category = Transaction.CATEGORY.BILLS;
                    valid = true;
                }
                else if(categ == 2)
                {
                    category = Transaction.CATEGORY.INVESTMENTS;
                    valid = true;
                }
                else if(categ == 3)
                {
                    category = Transaction.CATEGORY.SAVINGS;
                    valid = true;
                }
                else if(categ == 4)
                {
                    category = Transaction.CATEGORY.WANTS;
                    valid = true;
                }
                else
                {
                    System.out.println("\nInvalid input! Please enter a valid choice(1-4).");
                    valid = false;
                }
                

            }while(!valid);

            valid = false;
            
            do
            {
                System.out.print("Enter Description of the income(any desciption): ");
                description = scan.nextLine();

                if(description.matches("[a-zA-Z0-9 $?,\\.]+"))
                {
                    valid = true;
                }
                else
                {
                    System.out.println("Invalid description! Please use only letters, numbers, spaces, $, ?, ,, and .");
                    valid = false;
                }

            }while(!valid);
        
            Transaction track = new Transaction(amount, "Expense", category, description);
            transactions.add(track);
            gross_income -= amount;

            System.out.print("\nDo you want to allocate another expense?(y/n): ");
            answer = scan.next().charAt(0);
            scan.nextLine();

            if(answer == 'y' || answer == 'Y')
            {
                System.out.println(" ");
            }
        
        }while(answer == 'y' || answer == 'Y');   
    }

    public void balance()
    {
        //calculation of the balance
        System.out.println("\n==========BALANCE==========");
        System.out.println("Remaining Money Balance: " + gross_income);
    }

    public void track_sheet()
    {
        //Function for transaction tracking the allocation of expense
        if(transactions.isEmpty())
        {
            System.out.println("There is no history of Transactions yet.");
            return;
        }

         System.out.println("\n==========TRANSACTION HISTORY==========");
        System.out.printf("%-12s %-10s %-10s %-15s %-30s%n", "Date", "Type", "Amount", "Category", "Description");

        for(Transaction t : transactions) 
        {
            System.out.printf(
            "%-12s %-10s %-10.2f %-15s %-30s%n",
            t.getDate(),
            t.getType(),
            t.getAmount(),
            t.getCategory().name(),
            t.getDescription()
        );
    }
    }

    public void save_file()
    {
        try(PrintWriter writer = new PrintWriter(new FileWriter("Transactions.txt")))
        {
            for (Transaction track : transactions)
            {
                writer.println(
                    track.getDate() + " || " +
                    track.getType() + " || " +
                    track.getAmount() + " || " +
                    track.getCategory().name() + " || " +
                    track.getDescription()
                );
            }

            System.out.println("Saved to File Successfully.");

        }
        catch(Exception e)
        {
             System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void load_file()
    {
        File file = new File("Transactions.txt");

        if(!file.exists())
        {
            return;
        }

        try(Scanner read = new Scanner(file))
        {
            while(read.hasNextLine())
            {
                String line = read.nextLine().trim();

                if(!line.isEmpty())
                {
                    continue;
                }

                String[] data = line.split(" \\|\\| ");

                LocalDate date = LocalDate.parse(data[0]);
                String type = data[1];
                double amount = Double.parseDouble(data[2]);
                Transaction.CATEGORY category = Transaction.CATEGORY.valueOf(data[3]);
                String description = data[4];

                Transaction track = new Transaction(amount, type, category, description);
                transactions.add(track);
            }

            System.out.println("Loaded data successfully.");
        }

        catch(Exception e)
        {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }


    public enum CATEGORY
    {
        BILLS,
        INVESTMENTS,
        SAVINGS,
        WANTS,
    }



}
