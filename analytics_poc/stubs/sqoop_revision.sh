#!/bin/bash

sqoop import --username root --connect 'jdbc:mysql://localhost/elwikt' --as-avrodatafile \
  --target-dir 'hdfs://localhost:9001/user/ariel/avro_revision_full' \
 --query "select                                     \
    rev_id,                                          \
    rev_page,                                        \
    rev_text_id,                                     \
    convert(rev_comment using utf8) rev_comment,     \
    rev_user,                                        \
    convert(rev_user_text using utf8) rev_user_text, \
    convert(rev_timestamp using utf8) rev_timestamp, \
    rev_minor_edit,                                  \
    rev_deleted,                                     \
    rev_len,                                         \
    rev_parent_id,                                   \
    convert(rev_sha1 using utf8) rev_sha1,            \
    convert(rev_content_format using utf8) rev_content_format, \
    convert(rev_content_model using utf8) rev_content_model    \
      from revision where rev_id > 2700000 and rev_id < 2800000 and \$CONDITIONS" \
  --num-mappers 1 \
  -P
