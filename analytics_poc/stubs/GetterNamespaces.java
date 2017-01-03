import java.io.IOException;
import org.apache.hive.jdbc.HiveDriver;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.text.Normalizer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.List;
import java.util.ArrayList;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import java.net.MalformedURLException;
import java.io.IOException;
import java.util.Iterator;

public class GetterNamespaces {
    List<JSONObject> namespaces;

    GetterNamespaces() throws MalformedURLException, IOException {
	namespaces = new ArrayList<JSONObject>();

	GetterUrl getterUrl = new GetterUrl();
	String contents = getterUrl.getUrl("https://el.wikipedia.org/w/api.php?action=query&meta=siteinfo&siprop=namespaces&format=json");
	// who knows the right thing to call here
	// convert to json object
	JSONObject namespaceInfo = JSONObject.fromObject(contents);

	// for each entry in query:namespaces get the id and the case and the '*' entry
	JSONObject nspacesjson = namespaceInfo.getJSONObject("query");
	JSONObject nspacearrayjson = nspacesjson.getJSONObject("namespaces");
	for (Iterator nsIter = nspacearrayjson.keys(); nsIter.hasNext();) {
	    JSONObject namestuff = nspacearrayjson.getJSONObject((String)nsIter.next());
	    JSONObject nspace = new JSONObject();
	    nspace.put("value", namestuff.getString("*"));
	    nspace.put("key", namestuff.getInt("id"));
	    nspace.put("case", namestuff.getString("case"));
	    namespaces.add(nspace);
	}
    }

    public List<JSONObject> getNamespaces() {
	return namespaces;
    }
}
