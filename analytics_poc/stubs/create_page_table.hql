-- Creates table statement for page table.
--
-- Parameters:
--     <none>
--
-- Usage
--     hive -f create_page_table.hql --database wmf
--

DROP TABLE IF EXISTS `page`;
CREATE EXTERNAL TABLE IF NOT EXISTS `page`(
  `page_id`           bigint      COMMENT 'Primary key for each page',
  `page_namespace`    bigint      COMMENT 'number of namespace for this page',
  `page_title`        string      COMMENT 'title of this page'
)
ROW FORMAT SERDE
  'org.apache.hadoop.hive.serde2.avro.AvroSerDe'
STORED AS INPUTFORMAT
  'org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat'
OUTPUTFORMAT
  'org.apache.hadoop.hive.ql.io.avro.AvroContainerOutputFormat'
LOCATION
  'hdfs://localhost:9001/user/ariel/avro_page_full'
;
