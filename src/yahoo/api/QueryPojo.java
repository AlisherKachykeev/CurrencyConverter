package yahoo.api;

public class QueryPojo
{
    private Query query;

    public Query getQuery ()
    {
        return query;
    }

    public void setQuery (Query query)
    {
        this.query = query;
    }

	@Override
	public String toString() {
		return "MyPojo [query=" + query + "]";
	}
    
    
}
