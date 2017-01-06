import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetterPage extends Getter {
    GetterPage() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
        super();
    }

    public ResultSet getPage(int id) throws SQLException {
        PreparedStatement query = conn.prepareStatement(
                "select * from page where page_id = " + Integer.toString(id));
        ResultSet result = query.executeQuery();
        return result;
    }

    public ResultSet getPages(int startId, int endId) throws SQLException {
        PreparedStatement query = conn.prepareStatement(
                "select * from page where page_id > " + Integer.toString(startId)
                + " and page_id < " + Integer.toString(endId));
        ResultSet result = query.executeQuery();
        return result;
    }
}
