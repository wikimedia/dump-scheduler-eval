#!/bin/bash
service hadoop-hdfs-namenode start
service hadoop-hdfs-datanode start
service hadoop-hdfs-journalnode start
service hadoop-mapreduce-historyserver start
service hadoop-yarn-nodemanager start
service hadoop-yarn-resourcemanager start
service oozie start
