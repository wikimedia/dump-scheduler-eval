#!/bin/bash

sqoop import --username root --connect 'jdbc:mysql://localhost/elwikt' --as-avrodatafile \
  --target-dir 'hdfs://localhost:9001/user/ariel/avro_page_full' \
 --query "select                                    \
    page_id,                                        \
    page_namespace,                                 \
    convert(page_title using utf8) page_title       \
      from page where page_id > 223000 and page_id < 224000 and \$CONDITIONS" \
  --num-mappers 1 \
  -P
