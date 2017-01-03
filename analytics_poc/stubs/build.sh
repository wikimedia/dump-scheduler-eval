#!/bin/bash
CPATH="mysql-connector-java-5.1.40-bin.jar:/usr/lib/hive/lib/*:/usr/lib/hive-hcatalog/share/hcatalog/*:/usr/lib/hadoop/*:/usr/share/java/*:."
javac -classpath "$CPATH" Case.java
javac -classpath "$CPATH" Contributor.java
javac -classpath "$CPATH" Text.java
javac -classpath "$CPATH" Empty.java
javac -classpath "$CPATH" Revision.java
javac -classpath "$CPATH" Page.java
javac -classpath "$CPATH" Namespace.java
javac -classpath "$CPATH" Namespaces.java
javac -classpath "$CPATH" Siteinfo.java
javac -classpath "$CPATH" Mediawiki.java
javac -classpath "$CPATH" Getter.java
javac -classpath "$CPATH" GetterRev.java
javac -classpath "$CPATH" GetterPage.java
javac -classpath "$CPATH" GetterNamespaces.java
javac -classpath "$CPATH" GetterUrl.java
javac -classpath "$CPATH" GetterSiteinfo.java
#javac -classpath "$CPATH" -Xlint:unchecked WriterXmlRev.java
javac -classpath "$CPATH" WriterXmlRev.java
jar cvfm  WriterXmlRev.jar Manifest.txt *.class
