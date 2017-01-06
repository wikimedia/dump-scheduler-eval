import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.Properties;


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
    
    public String cleanupField(String name, ResultSet result)
        throws SQLException, CharacterCodingException {

        String value = result.getString(name);
        byte[] bytes = value.getBytes();
        ByteBuffer field = ByteBuffer.wrap(bytes);
        CharBuffer converted = decoder.decode(field);
        String normalized = Normalizer.normalize(converted, Normalizer.Form.NFC);
        return normalized;
    }
}
