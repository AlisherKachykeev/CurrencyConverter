package yahoo.api;

public class Results
{
    private Rate rate;

    public Rate getRate ()
    {
        return rate;
    }

    public void setRate (Rate rate)
    {
        this.rate = rate;
    }

	@Override
	public String toString() {
		return "Results [rate=" + rate+ "]";
	}
}