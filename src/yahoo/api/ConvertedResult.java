package yahoo.api;

import java.math.BigDecimal;

public class ConvertedResult
{
    private String id;

    private BigDecimal val;

    private String Name;

    private String Time;

    private String Date;

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

    public String getRate ()
    {
        return Rate;
    }

    public void setRate (String Rate)
    {
        this.Rate = Rate;
    }

	public BigDecimal getVal() {
		return val;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "ConvertedResult [id=" + id + ", val=" + val + ", Name=" + Name
				+ ", Time=" + Time + ", Date=" + Date + ", Rate=" + Rate + "]";
	}

    
}