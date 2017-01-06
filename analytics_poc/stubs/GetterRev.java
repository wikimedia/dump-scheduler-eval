import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetterRev extends Getter {
    GetterRev() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
        super();
    }

    public ResultSet getRevision(int id) throws SQLException {
        PreparedStatement query = conn.prepareStatement(
                "select * from revision where rev_id = " + Integer.toString(id));
        ResultSet result = query.executeQuery();
        return result;
    }

    public ResultSet getRevisionsForPage(int id) throws SQLException {
        // fixme why is this so much slower, even for queries with 0 results,
        // than without the explicit order?
        PreparedStatement query = conn.prepareStatement(
                "select * from revision where rev_page = " + Integer.toString(id)
                + " order by rev_id asc");
        ResultSet result = query.executeQuery();
        return result;
    }
}
