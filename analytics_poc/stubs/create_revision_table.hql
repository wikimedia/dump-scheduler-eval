-- Creates table statement for revision table.
--
-- Parameters:
--     <none>
--
-- Usage
--     hive -f create_revision_table.hql --database wmf
--

DROP TABLE IF EXISTS `revision`;
CREATE EXTERNAL TABLE IF NOT EXISTS `revision`(
  `rev_id`              bigint      COMMENT 'Primary key for eash revision',
  `rev_page`            bigint      COMMENT 'id of page that contains this revision',
  `rev_text_id`         bigint      COMMENT 'id of text content of revision',
  `rev_comment`         string      COMMENT 'comment made during editor save of this revision',
  `rev_user`            bigint      COMMENT 'id of user that saved this revision (sometimes 0)',
  `rev_user_text`       string      COMMENT 'username, or for anon users, IP address',
  `rev_timestamp`       string      COMMENT 'timestamp when revision saved',
  `rev_minor_edit`      int         COMMENT '1: editor marked the revision as minor, 0 otherwise',
  `rev_deleted`         int         COMMENT 'if any revision fields are hidden: 1:DELETED_TEXT, 2:DELETED_COMMENT, 4:DELETED_USER, 8:DELETED_RESTRICTED = 8',
  `rev_len`             bigint      COMMENT 'length of revision content (wikitext, unparsed, before conversion of hmtl entities)',
  `rev_parent_id`       bigint      COMMENT 'id of prevision revision, or 0 if this is the first',
  `rev_sha1`            string      COMMENT 'SHA-1 of revision content in base-36',
  `rev_content_model`   string      COMMENT 'Content model: wikitext, javascript, css, text, json',
  `rev_content_format`  string      COMMENT 'Content format: text/{x-wiki,javascript,css,plain,html}, application/{vnd.php.serialized,json,xml}'
)
ROW FORMAT SERDE
  'org.apache.hadoop.hive.serde2.avro.AvroSerDe'
STORED AS INPUTFORMAT
  'org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat'
OUTPUTFORMAT
  'org.apache.hadoop.hive.ql.io.avro.AvroContainerOutputFormat'
LOCATION
  'hdfs://localhost:9001/user/ariel/avro_revision_full'
;
