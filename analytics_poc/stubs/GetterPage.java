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


public class GetterPage extends Getter {
    GetterPage() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
	super();
    }

    public ResultSet getPage(int id) throws SQLException {
	PreparedStatement query = conn.prepareStatement("select * from page where page_id = " + Integer.toString(id));
	ResultSet result = query.executeQuery();
	return(result);
    }

    public ResultSet getPages(int startId, int endId) throws SQLException {
	PreparedStatement query = conn.prepareStatement("select * from page where page_id > " + Integer.toString(startId) + " and page_id < " + Integer.toString(endId));
	ResultSet result = query.executeQuery();
	return(result);
    }
}
