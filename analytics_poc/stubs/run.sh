#!/bin/bash
CPATH="mysql-connector-java-5.1.40-bin.jar:WriterXmlRev.jar:/usr/lib/hive/lib/*:/usr/lib/hive-hcatalog/share/hcatalog/*:/usr/lib/hadoop/*:/usr/lib/hadoop-mapreduce/*:/usr/lib/hadoop-hdfs/*:/usr/share/java/*:."
java -cp "$CPATH" WriterXmlRev $*
