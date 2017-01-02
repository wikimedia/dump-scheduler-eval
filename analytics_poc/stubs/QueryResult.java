// ORM class for table 'null'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Sat Dec 31 14:51:04 EET 2016
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class QueryResult extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private Long rev_id;
  public Long get_rev_id() {
    return rev_id;
  }
  public void set_rev_id(Long rev_id) {
    this.rev_id = rev_id;
  }
  public QueryResult with_rev_id(Long rev_id) {
    this.rev_id = rev_id;
    return this;
  }
  private Long rev_page;
  public Long get_rev_page() {
    return rev_page;
  }
  public void set_rev_page(Long rev_page) {
    this.rev_page = rev_page;
  }
  public QueryResult with_rev_page(Long rev_page) {
    this.rev_page = rev_page;
    return this;
  }
  private Long rev_text_id;
  public Long get_rev_text_id() {
    return rev_text_id;
  }
  public void set_rev_text_id(Long rev_text_id) {
    this.rev_text_id = rev_text_id;
  }
  public QueryResult with_rev_text_id(Long rev_text_id) {
    this.rev_text_id = rev_text_id;
    return this;
  }
  private String rev_comment;
  public String get_rev_comment() {
    return rev_comment;
  }
  public void set_rev_comment(String rev_comment) {
    this.rev_comment = rev_comment;
  }
  public QueryResult with_rev_comment(String rev_comment) {
    this.rev_comment = rev_comment;
    return this;
  }
  private Long rev_user;
  public Long get_rev_user() {
    return rev_user;
  }
  public void set_rev_user(Long rev_user) {
    this.rev_user = rev_user;
  }
  public QueryResult with_rev_user(Long rev_user) {
    this.rev_user = rev_user;
    return this;
  }
  private String rev_user_text;
  public String get_rev_user_text() {
    return rev_user_text;
  }
  public void set_rev_user_text(String rev_user_text) {
    this.rev_user_text = rev_user_text;
  }
  public QueryResult with_rev_user_text(String rev_user_text) {
    this.rev_user_text = rev_user_text;
    return this;
  }
  private String rev_timestamp;
  public String get_rev_timestamp() {
    return rev_timestamp;
  }
  public void set_rev_timestamp(String rev_timestamp) {
    this.rev_timestamp = rev_timestamp;
  }
  public QueryResult with_rev_timestamp(String rev_timestamp) {
    this.rev_timestamp = rev_timestamp;
    return this;
  }
  private Integer rev_minor_edit;
  public Integer get_rev_minor_edit() {
    return rev_minor_edit;
  }
  public void set_rev_minor_edit(Integer rev_minor_edit) {
    this.rev_minor_edit = rev_minor_edit;
  }
  public QueryResult with_rev_minor_edit(Integer rev_minor_edit) {
    this.rev_minor_edit = rev_minor_edit;
    return this;
  }
  private Integer rev_deleted;
  public Integer get_rev_deleted() {
    return rev_deleted;
  }
  public void set_rev_deleted(Integer rev_deleted) {
    this.rev_deleted = rev_deleted;
  }
  public QueryResult with_rev_deleted(Integer rev_deleted) {
    this.rev_deleted = rev_deleted;
    return this;
  }
  private Long rev_len;
  public Long get_rev_len() {
    return rev_len;
  }
  public void set_rev_len(Long rev_len) {
    this.rev_len = rev_len;
  }
  public QueryResult with_rev_len(Long rev_len) {
    this.rev_len = rev_len;
    return this;
  }
  private Long rev_parent_id;
  public Long get_rev_parent_id() {
    return rev_parent_id;
  }
  public void set_rev_parent_id(Long rev_parent_id) {
    this.rev_parent_id = rev_parent_id;
  }
  public QueryResult with_rev_parent_id(Long rev_parent_id) {
    this.rev_parent_id = rev_parent_id;
    return this;
  }
  private String rev_sha1;
  public String get_rev_sha1() {
    return rev_sha1;
  }
  public void set_rev_sha1(String rev_sha1) {
    this.rev_sha1 = rev_sha1;
  }
  public QueryResult with_rev_sha1(String rev_sha1) {
    this.rev_sha1 = rev_sha1;
    return this;
  }
  private String rev_content_format;
  public String get_rev_content_format() {
    return rev_content_format;
  }
  public void set_rev_content_format(String rev_content_format) {
    this.rev_content_format = rev_content_format;
  }
  public QueryResult with_rev_content_format(String rev_content_format) {
    this.rev_content_format = rev_content_format;
    return this;
  }
  private String rev_content_model;
  public String get_rev_content_model() {
    return rev_content_model;
  }
  public void set_rev_content_model(String rev_content_model) {
    this.rev_content_model = rev_content_model;
  }
  public QueryResult with_rev_content_model(String rev_content_model) {
    this.rev_content_model = rev_content_model;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof QueryResult)) {
      return false;
    }
    QueryResult that = (QueryResult) o;
    boolean equal = true;
    equal = equal && (this.rev_id == null ? that.rev_id == null : this.rev_id.equals(that.rev_id));
    equal = equal && (this.rev_page == null ? that.rev_page == null : this.rev_page.equals(that.rev_page));
    equal = equal && (this.rev_text_id == null ? that.rev_text_id == null : this.rev_text_id.equals(that.rev_text_id));
    equal = equal && (this.rev_comment == null ? that.rev_comment == null : this.rev_comment.equals(that.rev_comment));
    equal = equal && (this.rev_user == null ? that.rev_user == null : this.rev_user.equals(that.rev_user));
    equal = equal && (this.rev_user_text == null ? that.rev_user_text == null : this.rev_user_text.equals(that.rev_user_text));
    equal = equal && (this.rev_timestamp == null ? that.rev_timestamp == null : this.rev_timestamp.equals(that.rev_timestamp));
    equal = equal && (this.rev_minor_edit == null ? that.rev_minor_edit == null : this.rev_minor_edit.equals(that.rev_minor_edit));
    equal = equal && (this.rev_deleted == null ? that.rev_deleted == null : this.rev_deleted.equals(that.rev_deleted));
    equal = equal && (this.rev_len == null ? that.rev_len == null : this.rev_len.equals(that.rev_len));
    equal = equal && (this.rev_parent_id == null ? that.rev_parent_id == null : this.rev_parent_id.equals(that.rev_parent_id));
    equal = equal && (this.rev_sha1 == null ? that.rev_sha1 == null : this.rev_sha1.equals(that.rev_sha1));
    equal = equal && (this.rev_content_format == null ? that.rev_content_format == null : this.rev_content_format.equals(that.rev_content_format));
    equal = equal && (this.rev_content_model == null ? that.rev_content_model == null : this.rev_content_model.equals(that.rev_content_model));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof QueryResult)) {
      return false;
    }
    QueryResult that = (QueryResult) o;
    boolean equal = true;
    equal = equal && (this.rev_id == null ? that.rev_id == null : this.rev_id.equals(that.rev_id));
    equal = equal && (this.rev_page == null ? that.rev_page == null : this.rev_page.equals(that.rev_page));
    equal = equal && (this.rev_text_id == null ? that.rev_text_id == null : this.rev_text_id.equals(that.rev_text_id));
    equal = equal && (this.rev_comment == null ? that.rev_comment == null : this.rev_comment.equals(that.rev_comment));
    equal = equal && (this.rev_user == null ? that.rev_user == null : this.rev_user.equals(that.rev_user));
    equal = equal && (this.rev_user_text == null ? that.rev_user_text == null : this.rev_user_text.equals(that.rev_user_text));
    equal = equal && (this.rev_timestamp == null ? that.rev_timestamp == null : this.rev_timestamp.equals(that.rev_timestamp));
    equal = equal && (this.rev_minor_edit == null ? that.rev_minor_edit == null : this.rev_minor_edit.equals(that.rev_minor_edit));
    equal = equal && (this.rev_deleted == null ? that.rev_deleted == null : this.rev_deleted.equals(that.rev_deleted));
    equal = equal && (this.rev_len == null ? that.rev_len == null : this.rev_len.equals(that.rev_len));
    equal = equal && (this.rev_parent_id == null ? that.rev_parent_id == null : this.rev_parent_id.equals(that.rev_parent_id));
    equal = equal && (this.rev_sha1 == null ? that.rev_sha1 == null : this.rev_sha1.equals(that.rev_sha1));
    equal = equal && (this.rev_content_format == null ? that.rev_content_format == null : this.rev_content_format.equals(that.rev_content_format));
    equal = equal && (this.rev_content_model == null ? that.rev_content_model == null : this.rev_content_model.equals(that.rev_content_model));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.rev_id = JdbcWritableBridge.readLong(1, __dbResults);
    this.rev_page = JdbcWritableBridge.readLong(2, __dbResults);
    this.rev_text_id = JdbcWritableBridge.readLong(3, __dbResults);
    this.rev_comment = JdbcWritableBridge.readString(4, __dbResults);
    this.rev_user = JdbcWritableBridge.readLong(5, __dbResults);
    this.rev_user_text = JdbcWritableBridge.readString(6, __dbResults);
    this.rev_timestamp = JdbcWritableBridge.readString(7, __dbResults);
    this.rev_minor_edit = JdbcWritableBridge.readInteger(8, __dbResults);
    this.rev_deleted = JdbcWritableBridge.readInteger(9, __dbResults);
    this.rev_len = JdbcWritableBridge.readLong(10, __dbResults);
    this.rev_parent_id = JdbcWritableBridge.readLong(11, __dbResults);
    this.rev_sha1 = JdbcWritableBridge.readString(12, __dbResults);
    this.rev_content_format = JdbcWritableBridge.readString(13, __dbResults);
    this.rev_content_model = JdbcWritableBridge.readString(14, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.rev_id = JdbcWritableBridge.readLong(1, __dbResults);
    this.rev_page = JdbcWritableBridge.readLong(2, __dbResults);
    this.rev_text_id = JdbcWritableBridge.readLong(3, __dbResults);
    this.rev_comment = JdbcWritableBridge.readString(4, __dbResults);
    this.rev_user = JdbcWritableBridge.readLong(5, __dbResults);
    this.rev_user_text = JdbcWritableBridge.readString(6, __dbResults);
    this.rev_timestamp = JdbcWritableBridge.readString(7, __dbResults);
    this.rev_minor_edit = JdbcWritableBridge.readInteger(8, __dbResults);
    this.rev_deleted = JdbcWritableBridge.readInteger(9, __dbResults);
    this.rev_len = JdbcWritableBridge.readLong(10, __dbResults);
    this.rev_parent_id = JdbcWritableBridge.readLong(11, __dbResults);
    this.rev_sha1 = JdbcWritableBridge.readString(12, __dbResults);
    this.rev_content_format = JdbcWritableBridge.readString(13, __dbResults);
    this.rev_content_model = JdbcWritableBridge.readString(14, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(rev_id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(rev_page, 2 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(rev_text_id, 3 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(rev_comment, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(rev_user, 5 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(rev_user_text, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(rev_timestamp, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(rev_minor_edit, 8 + __off, -6, __dbStmt);
    JdbcWritableBridge.writeInteger(rev_deleted, 9 + __off, -6, __dbStmt);
    JdbcWritableBridge.writeLong(rev_len, 10 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(rev_parent_id, 11 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(rev_sha1, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(rev_content_format, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(rev_content_model, 14 + __off, 12, __dbStmt);
    return 14;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(rev_id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(rev_page, 2 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(rev_text_id, 3 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(rev_comment, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(rev_user, 5 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(rev_user_text, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(rev_timestamp, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(rev_minor_edit, 8 + __off, -6, __dbStmt);
    JdbcWritableBridge.writeInteger(rev_deleted, 9 + __off, -6, __dbStmt);
    JdbcWritableBridge.writeLong(rev_len, 10 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(rev_parent_id, 11 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(rev_sha1, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(rev_content_format, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(rev_content_model, 14 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.rev_id = null;
    } else {
    this.rev_id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.rev_page = null;
    } else {
    this.rev_page = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.rev_text_id = null;
    } else {
    this.rev_text_id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.rev_comment = null;
    } else {
    this.rev_comment = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.rev_user = null;
    } else {
    this.rev_user = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.rev_user_text = null;
    } else {
    this.rev_user_text = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.rev_timestamp = null;
    } else {
    this.rev_timestamp = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.rev_minor_edit = null;
    } else {
    this.rev_minor_edit = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.rev_deleted = null;
    } else {
    this.rev_deleted = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.rev_len = null;
    } else {
    this.rev_len = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.rev_parent_id = null;
    } else {
    this.rev_parent_id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.rev_sha1 = null;
    } else {
    this.rev_sha1 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.rev_content_format = null;
    } else {
    this.rev_content_format = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.rev_content_model = null;
    } else {
    this.rev_content_model = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.rev_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_id);
    }
    if (null == this.rev_page) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_page);
    }
    if (null == this.rev_text_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_text_id);
    }
    if (null == this.rev_comment) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_comment);
    }
    if (null == this.rev_user) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_user);
    }
    if (null == this.rev_user_text) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_user_text);
    }
    if (null == this.rev_timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_timestamp);
    }
    if (null == this.rev_minor_edit) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.rev_minor_edit);
    }
    if (null == this.rev_deleted) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.rev_deleted);
    }
    if (null == this.rev_len) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_len);
    }
    if (null == this.rev_parent_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_parent_id);
    }
    if (null == this.rev_sha1) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_sha1);
    }
    if (null == this.rev_content_format) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_content_format);
    }
    if (null == this.rev_content_model) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_content_model);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.rev_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_id);
    }
    if (null == this.rev_page) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_page);
    }
    if (null == this.rev_text_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_text_id);
    }
    if (null == this.rev_comment) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_comment);
    }
    if (null == this.rev_user) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_user);
    }
    if (null == this.rev_user_text) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_user_text);
    }
    if (null == this.rev_timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_timestamp);
    }
    if (null == this.rev_minor_edit) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.rev_minor_edit);
    }
    if (null == this.rev_deleted) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.rev_deleted);
    }
    if (null == this.rev_len) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_len);
    }
    if (null == this.rev_parent_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.rev_parent_id);
    }
    if (null == this.rev_sha1) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_sha1);
    }
    if (null == this.rev_content_format) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_content_format);
    }
    if (null == this.rev_content_model) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, rev_content_model);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(rev_id==null?"null":"" + rev_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_page==null?"null":"" + rev_page, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_text_id==null?"null":"" + rev_text_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_comment==null?"null":rev_comment, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_user==null?"null":"" + rev_user, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_user_text==null?"null":rev_user_text, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_timestamp==null?"null":rev_timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_minor_edit==null?"null":"" + rev_minor_edit, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_deleted==null?"null":"" + rev_deleted, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_len==null?"null":"" + rev_len, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_parent_id==null?"null":"" + rev_parent_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_sha1==null?"null":rev_sha1, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_content_format==null?"null":rev_content_format, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_content_model==null?"null":rev_content_model, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(rev_id==null?"null":"" + rev_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_page==null?"null":"" + rev_page, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_text_id==null?"null":"" + rev_text_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_comment==null?"null":rev_comment, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_user==null?"null":"" + rev_user, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_user_text==null?"null":rev_user_text, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_timestamp==null?"null":rev_timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_minor_edit==null?"null":"" + rev_minor_edit, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_deleted==null?"null":"" + rev_deleted, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_len==null?"null":"" + rev_len, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_parent_id==null?"null":"" + rev_parent_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_sha1==null?"null":rev_sha1, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_content_format==null?"null":rev_content_format, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rev_content_model==null?"null":rev_content_model, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_id = null; } else {
      this.rev_id = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_page = null; } else {
      this.rev_page = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_text_id = null; } else {
      this.rev_text_id = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_comment = null; } else {
      this.rev_comment = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_user = null; } else {
      this.rev_user = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_user_text = null; } else {
      this.rev_user_text = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_timestamp = null; } else {
      this.rev_timestamp = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_minor_edit = null; } else {
      this.rev_minor_edit = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_deleted = null; } else {
      this.rev_deleted = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_len = null; } else {
      this.rev_len = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_parent_id = null; } else {
      this.rev_parent_id = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_sha1 = null; } else {
      this.rev_sha1 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_content_format = null; } else {
      this.rev_content_format = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_content_model = null; } else {
      this.rev_content_model = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_id = null; } else {
      this.rev_id = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_page = null; } else {
      this.rev_page = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_text_id = null; } else {
      this.rev_text_id = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_comment = null; } else {
      this.rev_comment = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_user = null; } else {
      this.rev_user = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_user_text = null; } else {
      this.rev_user_text = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_timestamp = null; } else {
      this.rev_timestamp = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_minor_edit = null; } else {
      this.rev_minor_edit = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_deleted = null; } else {
      this.rev_deleted = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_len = null; } else {
      this.rev_len = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rev_parent_id = null; } else {
      this.rev_parent_id = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_sha1 = null; } else {
      this.rev_sha1 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_content_format = null; } else {
      this.rev_content_format = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.rev_content_model = null; } else {
      this.rev_content_model = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    QueryResult o = (QueryResult) super.clone();
    return o;
  }

  public void clone0(QueryResult o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("rev_id", this.rev_id);
    __sqoop$field_map.put("rev_page", this.rev_page);
    __sqoop$field_map.put("rev_text_id", this.rev_text_id);
    __sqoop$field_map.put("rev_comment", this.rev_comment);
    __sqoop$field_map.put("rev_user", this.rev_user);
    __sqoop$field_map.put("rev_user_text", this.rev_user_text);
    __sqoop$field_map.put("rev_timestamp", this.rev_timestamp);
    __sqoop$field_map.put("rev_minor_edit", this.rev_minor_edit);
    __sqoop$field_map.put("rev_deleted", this.rev_deleted);
    __sqoop$field_map.put("rev_len", this.rev_len);
    __sqoop$field_map.put("rev_parent_id", this.rev_parent_id);
    __sqoop$field_map.put("rev_sha1", this.rev_sha1);
    __sqoop$field_map.put("rev_content_format", this.rev_content_format);
    __sqoop$field_map.put("rev_content_model", this.rev_content_model);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("rev_id", this.rev_id);
    __sqoop$field_map.put("rev_page", this.rev_page);
    __sqoop$field_map.put("rev_text_id", this.rev_text_id);
    __sqoop$field_map.put("rev_comment", this.rev_comment);
    __sqoop$field_map.put("rev_user", this.rev_user);
    __sqoop$field_map.put("rev_user_text", this.rev_user_text);
    __sqoop$field_map.put("rev_timestamp", this.rev_timestamp);
    __sqoop$field_map.put("rev_minor_edit", this.rev_minor_edit);
    __sqoop$field_map.put("rev_deleted", this.rev_deleted);
    __sqoop$field_map.put("rev_len", this.rev_len);
    __sqoop$field_map.put("rev_parent_id", this.rev_parent_id);
    __sqoop$field_map.put("rev_sha1", this.rev_sha1);
    __sqoop$field_map.put("rev_content_format", this.rev_content_format);
    __sqoop$field_map.put("rev_content_model", this.rev_content_model);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("rev_id".equals(__fieldName)) {
      this.rev_id = (Long) __fieldVal;
    }
    else    if ("rev_page".equals(__fieldName)) {
      this.rev_page = (Long) __fieldVal;
    }
    else    if ("rev_text_id".equals(__fieldName)) {
      this.rev_text_id = (Long) __fieldVal;
    }
    else    if ("rev_comment".equals(__fieldName)) {
      this.rev_comment = (String) __fieldVal;
    }
    else    if ("rev_user".equals(__fieldName)) {
      this.rev_user = (Long) __fieldVal;
    }
    else    if ("rev_user_text".equals(__fieldName)) {
      this.rev_user_text = (String) __fieldVal;
    }
    else    if ("rev_timestamp".equals(__fieldName)) {
      this.rev_timestamp = (String) __fieldVal;
    }
    else    if ("rev_minor_edit".equals(__fieldName)) {
      this.rev_minor_edit = (Integer) __fieldVal;
    }
    else    if ("rev_deleted".equals(__fieldName)) {
      this.rev_deleted = (Integer) __fieldVal;
    }
    else    if ("rev_len".equals(__fieldName)) {
      this.rev_len = (Long) __fieldVal;
    }
    else    if ("rev_parent_id".equals(__fieldName)) {
      this.rev_parent_id = (Long) __fieldVal;
    }
    else    if ("rev_sha1".equals(__fieldName)) {
      this.rev_sha1 = (String) __fieldVal;
    }
    else    if ("rev_content_format".equals(__fieldName)) {
      this.rev_content_format = (String) __fieldVal;
    }
    else    if ("rev_content_model".equals(__fieldName)) {
      this.rev_content_model = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
  public boolean setField0(String __fieldName, Object __fieldVal) {
    if ("rev_id".equals(__fieldName)) {
      this.rev_id = (Long) __fieldVal;
      return true;
    }
    else    if ("rev_page".equals(__fieldName)) {
      this.rev_page = (Long) __fieldVal;
      return true;
    }
    else    if ("rev_text_id".equals(__fieldName)) {
      this.rev_text_id = (Long) __fieldVal;
      return true;
    }
    else    if ("rev_comment".equals(__fieldName)) {
      this.rev_comment = (String) __fieldVal;
      return true;
    }
    else    if ("rev_user".equals(__fieldName)) {
      this.rev_user = (Long) __fieldVal;
      return true;
    }
    else    if ("rev_user_text".equals(__fieldName)) {
      this.rev_user_text = (String) __fieldVal;
      return true;
    }
    else    if ("rev_timestamp".equals(__fieldName)) {
      this.rev_timestamp = (String) __fieldVal;
      return true;
    }
    else    if ("rev_minor_edit".equals(__fieldName)) {
      this.rev_minor_edit = (Integer) __fieldVal;
      return true;
    }
    else    if ("rev_deleted".equals(__fieldName)) {
      this.rev_deleted = (Integer) __fieldVal;
      return true;
    }
    else    if ("rev_len".equals(__fieldName)) {
      this.rev_len = (Long) __fieldVal;
      return true;
    }
    else    if ("rev_parent_id".equals(__fieldName)) {
      this.rev_parent_id = (Long) __fieldVal;
      return true;
    }
    else    if ("rev_sha1".equals(__fieldName)) {
      this.rev_sha1 = (String) __fieldVal;
      return true;
    }
    else    if ("rev_content_format".equals(__fieldName)) {
      this.rev_content_format = (String) __fieldVal;
      return true;
    }
    else    if ("rev_content_model".equals(__fieldName)) {
      this.rev_content_model = (String) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}
