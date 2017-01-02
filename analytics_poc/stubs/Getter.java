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


public class Getter {
    Connection conn;
    CharsetDecoder decoder;

    Getter() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
	Properties props = new Properties();
	FileInputStream input = new FileInputStream("/home/ariel/java/tests/stubs/db.properties");
	props.load(input);

	conn = DriverManager.getConnection(props.getProperty("hive2.url"),
					   props.getProperty("hive2.username"),
					   props.getProperty("hive2.password"));
	decoder = Charset.forName("UTF-8").newDecoder();
        decoder.onMalformedInput(CodingErrorAction.IGNORE);
        decoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
    }
    
    public String cleanupField(String name, ResultSet result) throws SQLException, CharacterCodingException {
	// claims this isn't supported, somewhere in the bowels of org.apache.hive.jdbc.HiveBaseResultSet.getBytes(HiveBaseResultSet.java:222)

	String value = result.getString(name);
	byte[] bytes = value.getBytes();
	ByteBuffer field = ByteBuffer.wrap(bytes);
	CharBuffer converted = decoder.decode(field);
	String normalized = Normalizer.normalize(converted, Normalizer.Form.NFC);
	return(normalized);
    }	
}
