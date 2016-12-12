package yahoo.api;


// http://blog.sodhanalibrary.com/2014/08/create-currency-converter-with-java.html#.V9HPm_krKM8

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public  class Converter {
    private final static String USER_AGENT = "Mozilla/5.0";   

  public static void main(String[] args) throws IOException, JsonSyntaxException, JSONException{
      System.out.println("************ start");
	  ConvertedResult cr = convert("100","CAD","CNY");
	  System.out.println("\n\n"+cr);
  }

//    public static void main(String[] args) {
//        
//        System.out.println(" ===========");
//        
//        
//    }

  public static ConvertedResult convert(String amout, String from, String to) throws IOException, JsonSyntaxException, JSONException {
	    String url ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22"+from+to+"%22)&env=store://datatables.org/alltableswithkeys";
            
            System.out.println("========================== " + url);
	    URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	    con.setRequestMethod("GET");
	    //add request header
	    con.setRequestProperty("User-Agent", USER_AGENT);

	    // Send request
	    int responseCode = con.getResponseCode();
	    System.out.println("\nSending 'GET' request to URL : " + url);
	    System.out.println("Response Code : " + responseCode + "\n\n");
	    BufferedReader in = new BufferedReader(
	           new InputStreamReader(con.getInputStream()));
	    String outputLine;
	    
	    // reading output from Request
	    StringBuffer response = new StringBuffer(); 
	    while ((outputLine = in.readLine()) != null) {
	       response.append(outputLine);
	    }
	    in.close();
	    
	    // Converting XML to JSON and then JSON to POJO Classes
	    GsonBuilder builder = new GsonBuilder();
	    Gson gson = builder.create();
	    QueryPojo mp = gson.fromJson(Utility.convertXMLtoJSON(response.toString()), QueryPojo.class);

	    // Print response and pojo
	    System.out.println(response);
	    System.out.println(mp);
	    
	    // Setting up result object
	    ConvertedResult cr =new ConvertedResult();
	    cr.setId(mp.getQuery().getResults().getRate().getId());
	    cr.setRate(mp.getQuery().getResults().getRate().getRate());
	    cr.setName(mp.getQuery().getResults().getRate().getName());
	    cr.setDate(mp.getQuery().getResults().getRate().getDate());
	    cr.setTime(mp.getQuery().getResults().getRate().getTime());
	    
	    // Convert Currency
	    BigDecimal amountOne = new BigDecimal(amout);
	    BigDecimal rate = new BigDecimal(cr.getRate());
	    cr.setVal(amountOne.multiply(rate));
	    
	    return cr;
  }

} 