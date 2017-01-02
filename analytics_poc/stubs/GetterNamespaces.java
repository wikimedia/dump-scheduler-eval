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

public class GetterNamespaces {
    List<JSONObject> namespaces;

    GetterNamespaces() {
	namespaces = new ArrayList<JSONObject>();

	JSONObject nsOne = new JSONObject();
	JSONObject nsTwo = new JSONObject();
	JSONObject nsThree = new JSONObject();
	    
	// these really need to be dug out from the
	// mediawiki config files, perhaps we can do this
	// via the mediawiki api. for now here are a few hardcoded
	// entries.
	nsOne.put("value", "Συζήτηση");
	nsOne.put("key", new Integer(1));
	nsOne.put("case", "case-sensitive");

	nsTwo.put("value", "Χρήστης");
	nsTwo.put("key", new Integer(2));
	nsTwo.put("case", "first-letter");

	nsThree.put("value", "Βικιλεξικό");
	nsThree.put("key", new Integer(4));
	nsThree.put("case", "case-sensitive");

	namespaces.add(nsOne);
	namespaces.add(nsTwo);
	namespaces.add(nsThree);
    }

    public List<JSONObject> getNamespaces() {
	return namespaces;
    }
}
