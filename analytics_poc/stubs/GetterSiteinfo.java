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


public class GetterSiteinfo {

    Siteinfo siteinfo;

    GetterSiteinfo() {
	GetterNamespaces getterNs = new GetterNamespaces();
	List<JSONObject> namespacesJson = getterNs.getNamespaces();
	Namespaces namespaces = new Namespaces();
	System.out.println("size of namespacesJson is now " + namespacesJson.size());
	for (JSONObject nspaceJson: namespacesJson) {
	    Namespace nspace = new Namespace();
	    nspace.setKey(nspaceJson.getInt("key"));
	    nspace.setValue(nspaceJson.getString("value"));
	    nspace.setCase(Case.fromValue(nspaceJson.getString("case")));
	    namespaces.getNamespace().add(nspace);
	}
	List<Namespace> toput = namespaces.getNamespace();
	System.out.println("size of namespaces is now " + toput.size());
	// ok so here we are with three namespaces, why doesn't the set work right                                                                                          
	// for retrieval?

	// fixme for now we are hardcoding but eventually, get via                                                                                                          
	// the api? or...?                                                                                                                                                  
	siteinfo = new Siteinfo();
	siteinfo.setSitename("Βικιλεξικό");
	siteinfo.setDbname("elwikt");
	siteinfo.setBase("http://localhost/elwikt/index.php/%CE%92%CE%B9%CE%BA%CE%B9%CE%BB%CE%B5%CE%BE%CE%B9%CE%BA%CF%8C:%CE%9A%CF%8D%CF%81%CE%B9%CE%B1_%CE%A3%CE%B5%CE%BB%CE%AF%CE%B4%CE%B1");
	siteinfo.setGenerator("MediaWiki 1.27.0-wmf.17");
	siteinfo.setCase(Case.CASE_SENSITIVE);
	siteinfo.setNamespaces(namespaces);
	System.out.println("size of siteinfo namespaces is now " + siteinfo.getNamespaces().getNamespace().size());
    }

    public Siteinfo getSiteinfo() {
	return siteinfo;
    }
}
