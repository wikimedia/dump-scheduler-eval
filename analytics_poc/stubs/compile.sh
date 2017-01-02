#!/bin/bash
if [ -z "$1" ]; then
    echo "usage: $0 somejavacode.java"
    exit 1
fi
CPATH="mysql-connector-java-5.1.40-bin.jar:/usr/lib/hive/lib/*:/usr/lib/hive-hcatalog/share/hcatalog/*:/usr/lib/hadoop/*:/usr/share/java/*:."
javac -classpath "$CPATH" "$1"
