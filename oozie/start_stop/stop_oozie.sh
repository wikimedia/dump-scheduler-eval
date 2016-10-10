#!/bin/bash
service oozie stop
service hadoop-yarn-resourcemanager stop
service hadoop-yarn-nodemanager stop
service hadoop-mapreduce-historyserver stop
service hadoop-hdfs-journalnode stop
service hadoop-hdfs-datanode stop
service hadoop-hdfs-namenode stop
