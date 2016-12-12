package yahoo.api;

public class Rate
{
    private String id;

    private String Name;

    private String Time;

    private String Date;

    private String Bid;

    private String Ask;

    private String Rate;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getTime ()
    {
        return Time;
    }

    public void setTime (String Time)
    {
        this.Time = Time;
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    public String getBid ()
    {
        return Bid;
    }

    public void setBid (String Bid)
    {
        this.Bid = Bid;
    }

    public String getAsk ()
    {
        return Ask;
    }

    public void setAsk (String Ask)
    {
        this.Ask = Ask;
    }

    public String getRate ()
    {
        return Rate;
    }

    public void setRate (String Rate)
    {
        this.Rate = Rate;
    }

	@Override
	public String toString() {
		return "Rate [id=" + id + ", Name=" + Name + ", Time=" + Time
				+ ", Date=" + Date + ", Bid=" + Bid + ", Ask=" + Ask
				+ ", Rate=" + Rate + "]";
	}
    
}