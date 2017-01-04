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

This currently produces a little tiny bit of XML.  I have yet to test it on
a large page/revision import.


<mw:mediawiki xmlns:mw="http://www.mediawiki.org/xml/export-0.10/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.mediawiki.org/xml/export-0.10/ http://www.mediawiki.org/xml/export-0.10.xsd" version="0.10" xml:lang="el">
  <siteinfo>
    <sitename>Βικιλεξικό</sitename>
    <dbname>elwikt</dbname>
    <base>http://localhost/elwikt/index.php/%CE%92%CE%B9%CE%BA%CE%B9%CE%BB%CE%B5%CE%BE%CE%B9%CE%BA%CF%8C:%CE%9A%CF%8D%CF%81%CE%B9%CE%B1_%CE%A3%CE%B5%CE%BB%CE%AF%CE%B4%CE%B1</base>
    <generator>MediaWiki 1.27.0-wmf.17</generator>
    <case>case-sensitive</case>
    <namespaces>
      <namespace key="-2" case="case-sensitive">Μέσο</namespace>
      <namespace key="-1" case="first-letter">Ειδικό</namespace>
      <namespace key="0" case="case-sensitive"></namespace>
      <namespace key="1" case="case-sensitive">Συζήτηση</namespace>
      <namespace key="2" case="first-letter">Χρήστης</namespace>
      <namespace key="3" case="first-letter">Συζήτηση χρήστη</namespace>
      <namespace key="4" case="case-sensitive">Βικιλεξικό</namespace>
      <namespace key="5" case="case-sensitive">Συζήτηση βικιλεξικού</namespace>
      <namespace key="6" case="case-sensitive">Αρχείο</namespace>
      <namespace key="7" case="case-sensitive">Συζήτηση αρχείου</namespace>
      <namespace key="8" case="first-letter">MediaWiki</namespace>
      <namespace key="9" case="first-letter">Συζήτηση MediaWiki</namespace>
      <namespace key="10" case="case-sensitive">Πρότυπο</namespace>
      <namespace key="11" case="case-sensitive">Συζήτηση προτύπου</namespace>
      <namespace key="12" case="case-sensitive">Βοήθεια</namespace>
      <namespace key="13" case="case-sensitive">Συζήτηση βοήθειας</namespace>
      <namespace key="14" case="case-sensitive">Κατηγορία</namespace>
      <namespace key="15" case="case-sensitive">Συζήτηση κατηγορίας</namespace>
      <namespace key="100" case="case-sensitive">Παράρτημα</namespace>
      <namespace key="101" case="case-sensitive">Συζήτηση παραρτήματος</namespace>
      <namespace key="2300" case="case-sensitive">Gadget</namespace>
      <namespace key="2301" case="case-sensitive">Gadget talk</namespace>
      <namespace key="2302" case="case-sensitive">Gadget definition</namespace>
      <namespace key="2303" case="case-sensitive">Gadget definition talk</namespace>
    </namespaces>
  </siteinfo>
  <page>
    <title>неутрален</title>
    <ns>0</ns>
    <id>223261</id>
  </page>
  <page>
    <title>neutralny</title>
    <ns>0</ns>
    <id>223262</id>
  </page>
  <page>
    <title>nijaki</title>
    <ns>0</ns>
    <id>223263</id>
    <revision>
      <id>2749517</id>
      <timestamp>20120920121133</timestamp>
      <contributor>
        <id>448</id>
        <username>Flubot</username>
      </contributor>
      <comment>ενημέρωση των interwikis, προσθήκη mg</comment>
      <minor></minor>
      <model>some_default_FIXME</model>
      <format>some_default_FIXME</format>
      <text id="2737632" bytes="188"></text>
      <sha1>a8z3z1ffwmr54ljhhs30zp5hzcacuef</sha1>
    </revision>
  </page>
  <page>
    <title>нейтральный</title>
    <ns>0</ns>
    <id>223264</id>
  </page>
  <page>
    <title>средний</title>
    <ns>0</ns>
    <id>223265</id>
    <revision>
      <id>2750143</id>
      <timestamp>20120920155247</timestamp>
      <contributor>
        <id>448</id>
        <username>Flubot</username>
      </contributor>
      <comment>ενημέρωση των interwikis, προσθήκη mg</comment>
      <minor></minor>
      <model>some_default_FIXME</model>
      <format>some_default_FIXME</format>
      <text id="2738258" bytes="310"></text>
      <sha1>qko0pnchsfemfy9ubxc6iu1tt8lq2ol</sha1>
    </revision>
  </page>
  <page>
    <title>épidémiologie</title>
    <ns>0</ns>
    <id>223266</id>
  </page>
  <page>
    <title>épidémiologiste</title>
    <ns>0</ns>
    <id>223267</id>
  </page>
  <page>
    <title>incinérateur</title>
    <ns>0</ns>
    <id>223268</id>
  </page>
  <page>
    <title>incinérer</title>
    <ns>0</ns>
    <id>223269</id>
  </page>
  <page>
    <title>incinerator</title>
    <ns>0</ns>
    <id>223270</id>
  </page>
  <page>
    <title>incineratore</title>
    <ns>0</ns>
    <id>223271</id>
  </page>
  <page>
    <title>Χρήστης_pl</title>
    <ns>14</ns>
    <id>223272</id>
    <revision>
      <id>2755410</id>
      <timestamp>2012101000208
      <contributor>
        <id>6853</id>
        <username>MalafayaBot</username>
      </contributor>
      <comment>r2.7.2) (Ρομπότ: Προσθήκη: [[li:Categorie:Gebroeker pl]]</comment>
      <minor></minor>
      <model>some_default_FIXME</model>
      <format>some_default_FIXME</format>
      <text id="2743522" bytes="1030"></text>
      <sha1>eqy1qk6cxaeq774e898oe8nv20fj578</sha1>
    </revision>
  </page>
  <page>
    <title>Χρήστης_pl-2</title>
    <ns>14</ns>
    <id>223273</id>
  </page>
  <page>
    <title>Χρήστης_pl-2</title>
    <ns>10</ns>
    <id>223274</id>
  </page>
  <page>
    <title>User_pl-2</title>
    <ns>10</ns>
    <id>223275</id>
  </page>
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
      <minor></minor>
      <model>some_default_FIXME</model>
      <format>some_default_FIXME</format>
      <text id="2688156" bytes="503"></text>
      <sha1>cwuoiseuswpgbds348sa35o5or3ujjz</sha1>
    </revision>
  </page>
  <page>
    <title>blaireau</title>
    <ns>0</ns>
    <id>223277</id>
    <revision>
      <id>2750325</id>
      <timestamp>20120921094840</timestamp>
      <contributor>
        <id>448</id>
        <username>Flubot</username>
      </contributor>
      <comment>ενημέρωση των interwikis, προσθήκη gl</comment>
      <minor></minor>
      <model>some_default_FIXME</model>
      <format>some_default_FIXME</format>
      <text id="2738439" bytes="490"></text>
      <sha1>4ozayzfltzuadsi2vp37hpzd2mqbha8</sha1>
    </revision>
  </page>
  <page>
    <title>philogynie</title>
    <ns>0</ns>
    <id>223278</id>
  </page>
  <page>
    <title>misogynie</title>
    <ns>0</ns>
    <id>223279</id>
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

