//Blueprint File
import java.time.LocalDate;

public class Transaction 
{
    private LocalDate date; //getting the current date
    private double amount; //for the amount for expense or income
    private String type; //expense or income
    private CATEGORY category; //is it food, bills, wants
    private String description; //additional details

    public Transaction(double amount, String type, CATEGORY category, String description)
    {
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.description = description;
        this.date = LocalDate.now();
    }
    
    public double getAmount()
    {
        return amount;
    }

    public String getType()
    {
        return type;
    }

    public CATEGORY getCategory()
    {
        return category;
    }

    public String getDescription()
    {
        return description;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setAmount(double newAmount)
    {
        this.amount = newAmount;
    }

    public void setType(String newType)
    {
        this.type = newType;
    }

    public void setCategory(CATEGORY newCategory)
    {
        this.category = newCategory;
    }

    public void setDescription(String newDescription)
    {
        this.description = newDescription;
    }



 public enum CATEGORY
    {
        BILLS,
        INVESTMENTS,
        SAVINGS,
        WANTS,
    }
}
