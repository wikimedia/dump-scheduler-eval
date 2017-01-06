import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONObject;

public class GetterNamespaces {
    List<JSONObject> namespaces;

    GetterNamespaces(String apiUrl) throws MalformedURLException, IOException {
        namespaces = new ArrayList<JSONObject>();

        GetterUrl getterUrl = new GetterUrl();
        String contents = getterUrl.getUrl(
                    apiUrl + "?action=query&meta=siteinfo&siprop=namespaces&format=json");
        // convert to json object
        JSONObject namespaceInfo = JSONObject.fromObject(contents);

        // for each entry in query:namespaces get the id and the case and the '*' entry
        JSONObject nspacesjson = namespaceInfo.getJSONObject("query");
        JSONObject nspacearrayjson = nspacesjson.getJSONObject("namespaces");
        for (Iterator<?> nsIter = nspacearrayjson.keys(); nsIter.hasNext();) {
            JSONObject namestuff = nspacearrayjson.getJSONObject((String)nsIter.next());
            JSONObject nspace = new JSONObject();
            nspace.put("value", namestuff.getString("*"));
            nspace.put("key", namestuff.getInt("id"));
            nspace.put("case", namestuff.getString("case"));
            namespaces.add(nspace);
        }
    }

    public List<JSONObject> getNamespaces() {
        return namespaces;
    }
}
