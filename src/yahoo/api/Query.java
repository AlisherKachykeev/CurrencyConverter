package yahoo.api;

public class Query
{
    
    private Results results;

    public Results getResults ()
    {
        return results;
    }

    public void setResults (Results results)
    {
        this.results = results;
    }

	@Override
	public String toString() {
		return "Query [results=" + results + "]";
	}

}