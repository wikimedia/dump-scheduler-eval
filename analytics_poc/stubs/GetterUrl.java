import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.IOUtils;

public class GetterUrl {

    public String getUrl(String url) throws MalformedURLException, IOException {

        InputStream in = new URL(url).openStream();
        try {
            return IOUtils.toString(in);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
