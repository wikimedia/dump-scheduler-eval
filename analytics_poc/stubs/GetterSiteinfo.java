import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import net.sf.json.JSONObject;

public class GetterSiteinfo {

    Siteinfo siteinfo;
    // need this for the mediawiki xml header
    String lang;

    GetterSiteinfo(String apiUrl) throws MalformedURLException, IOException {
        GetterNamespaces getterNs = new GetterNamespaces(apiUrl);
        List<JSONObject> namespacesJson = getterNs.getNamespaces();
        Namespaces namespaces = new Namespaces();
        for (JSONObject nspaceJson: namespacesJson) {
            Namespace nspace = new Namespace();
            nspace.setKey(nspaceJson.getInt("key"));
            nspace.setValue(nspaceJson.getString("value"));
            nspace.setCase(Case.fromValue(nspaceJson.getString("case")));
            namespaces.getNamespace().add(nspace);
        }
        List<Namespace> toput = namespaces.getNamespace();

        GetterUrl getterUrl = new GetterUrl();
        String contents = getterUrl.getUrl(apiUrl
                + "?action=query&meta=siteinfo&siprop=general&format=json");
        JSONObject generalSiteInfo = JSONObject.fromObject(contents);
        JSONObject siteinfojson = generalSiteInfo.getJSONObject("query");
        JSONObject generaljunkjson = siteinfojson.getJSONObject("general");

        siteinfo = new Siteinfo();
        siteinfo.setSitename(generaljunkjson.getString("sitename"));
        siteinfo.setDbname(generaljunkjson.getString("wikiid"));
        siteinfo.setBase(generaljunkjson.getString("base"));
        siteinfo.setGenerator(generaljunkjson.getString("generator"));
        siteinfo.setCase(Case.fromValue(generaljunkjson.getString("case")));
        siteinfo.setNamespaces(namespaces);
        lang = generaljunkjson.getString("lang");
    }

    public Siteinfo getSiteinfo() {
        return siteinfo;
    }

    public String getLang() {
        return lang;
    }
}
