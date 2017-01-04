import java.io.InputStream;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.net.MalformedURLException;

public class GetterUrl {

    public String getUrl(String url) throws MalformedURLException, IOException {

	InputStream in = new URL(url).openStream();
	try {
	    return(IOUtils.toString(in));
	} finally {
	    IOUtils.closeQuietly(in);
	}
    }
}
