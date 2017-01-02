This was done on my local install of mw, if you don't have the appropriate
dbs and mw installs it won't work for you. And why should it?  It's just
here to prove how awful my java code is.

Goal: get something like a stubs dump out. Still a good ways off.
This is just getting most of the framework together so folks can see...
how bad my java code is. I mean uh, they can see the direction I'm
going with the refinery->stubs production.

BTW there's no nice packaging or anything. Yeah well ,first off I don't know nice
java packaging. And second of all, this is a proof of concept.  It doesn't have to be
nice or good. It just has to (eventually) work.

This currently produces a little tiny bit of XML. I'm running it for one lousy
page with one lousy revision.  And a fixed project name and db and etc.
Still, it's better than nothing :-P

<mw:mediawiki xmlns:mw="http://www.mediawiki.org/xml/export-0.10/" xmlns:ns3="http://www.w3.org/2001/XMLSchema-instance" xml:lang="el" version="0.10" ns3:schemaLocation="http://www.mediawiki.org/xml/export-0.10/ http://www.mediawiki.org/xml/export-0.10.xsd">
    <siteinfo>
        <sitename>Βικιλεξικό</sitename>
        <dbname>elwikt</dbname>
        <base>http://localhost/elwikt/index.php/%CE%92%CE%B9%CE%BA%CE%B9%CE%BB%CE%B5%CE%BE%CE%B9%CE%BA%CF%8C:%CE%9A%CF%8D%CF%81%CE%B9%CE%B1_%CE%A3%CE%B5%CE%BB%CE%AF%CE%B4%CE%B1</base>
        <generator>MediaWiki 1.27.0-wmf.17</generator>
        <case>case-sensitive</case>
        <namespaces>
            <namespace key="1" case="case-sensitive">Συζήτηση</namespace>
            <namespace key="2" case="first-letter">Χρήστης</namespace>
            <namespace key="4" case="case-sensitive">Βικιλεξικό</namespace>
        </namespaces>
    </siteinfo>
    <page>
        <title>ἑταῖρος</title>
        <ns>0</ns>
        <id>223276</id>
        <revision>
            <id>2700027</id>
            <timestamp>20120731182554</timestamp>
            <contributor>
                <id>448</id>
                <username>Flubot</username>
            </contributor>
            <comment>ενημέρωση των interwikis, προσθήκη mg</comment>
            <minor/>
            <model>some_default_FIXME</model>
            <format>some_default_FIXME</format>
            <text id="2688156" bytes="503"/>
            <sha1>cwuoiseuswpgbds348sa35o5or3ujjz</sha1>
        </revision>
    </page>
</mw:mediawiki>

How I run it:

import table by:
  sqoop_revision.sh
  beeline -f create_revision_table.hql -u 'jdbc:hive2://localhost:10001/wmf' -n ariel
  sqoop_page.sh
  beeline -f create_page_table.hql -u 'jdbc:hive2://localhost:10001/wmf' -n ariel

build by:
  build.sh
run by:
  run.sh

