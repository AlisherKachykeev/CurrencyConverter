package yahoo.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Utility {
	public static String convertXMLtoJSON(String xml) throws JSONException {
	    JSONObject xmlJSONObj = XML.toJSONObject(xml);
        String jsonPrettyPrintString = xmlJSONObj.toString(4);
        return jsonPrettyPrintString;
    }

}
