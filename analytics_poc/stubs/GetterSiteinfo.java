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
import java.net.MalformedURLException;
import java.io.IOException;

public class GetterSiteinfo {

    Siteinfo siteinfo;

    GetterSiteinfo() throws MalformedURLException, IOException {
	GetterNamespaces getterNs = new GetterNamespaces();
	List<JSONObject> namespacesJson = getterNs.getNamespaces();
	Namespaces namespaces = new Namespaces();
	for (JSONObject nspaceJson: namespacesJson) {
	    Namespace nspace = new Namespace();
	    nspace.setKey(nspaceJson.getInt("key"));
	    nspace.setValue(nspaceJson.getString("value"));
	    nspace.setCase(Case.fromValue(nspaceJson.getString("case")));
	    namespaces.getNamespace().add(nspace);
	}
	List<Namespace> toput = namespaces.getNamespace();

	// do we want to do call once for both siteinfo and namespaces? bleah
	GetterUrl getterUrl = new GetterUrl();
	String contents = getterUrl.getUrl("http://localhost/elwikt/api.php?action=query&meta=siteinfo&siprop=general&format=json");
	JSONObject generalSiteInfo = JSONObject.fromObject(contents);
	JSONObject siteinfojson = generalSiteInfo.getJSONObject("query");
	JSONObject generaljunkjson = siteinfojson.getJSONObject("general");

	siteinfo = new Siteinfo();
	siteinfo.setSitename(generaljunkjson.getString("sitename"));
	siteinfo.setDbname(generaljunkjson.getString("wikiid"));
	siteinfo.setBase(generaljunkjson.getString("base"));
	siteinfo.setGenerator(generaljunkjson.getString("generator"));
	siteinfo.setCase(Case.fromValue(generaljunkjson.getString("case")));
	siteinfo.setNamespaces(namespaces);
    }

    public Siteinfo getSiteinfo() {
	return siteinfo;
    }
}
