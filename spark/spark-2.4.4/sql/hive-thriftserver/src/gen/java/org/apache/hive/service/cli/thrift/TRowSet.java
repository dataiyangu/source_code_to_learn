/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hive.service.cli.thrift;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TRowSet implements org.apache.thrift.TBase<TRowSet, TRowSet._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TRowSet");

  private static final org.apache.thrift.protocol.TField START_ROW_OFFSET_FIELD_DESC = new org.apache.thrift.protocol.TField("startRowOffset", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField ROWS_FIELD_DESC = new org.apache.thrift.protocol.TField("rows", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField COLUMNS_FIELD_DESC = new org.apache.thrift.protocol.TField("columns", org.apache.thrift.protocol.TType.LIST, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TRowSetStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TRowSetTupleSchemeFactory());
  }

  private long startRowOffset; // required
  private List<TRow> rows; // required
  private List<TColumn> columns; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    START_ROW_OFFSET((short)1, "startRowOffset"),
    ROWS((short)2, "rows"),
    COLUMNS((short)3, "columns");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // START_ROW_OFFSET
          return START_ROW_OFFSET;
        case 2: // ROWS
          return ROWS;
        case 3: // COLUMNS
          return COLUMNS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __STARTROWOFFSET_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.COLUMNS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.START_ROW_OFFSET, new org.apache.thrift.meta_data.FieldMetaData("startRowOffset", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ROWS, new org.apache.thrift.meta_data.FieldMetaData("rows", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TRow.class))));
    tmpMap.put(_Fields.COLUMNS, new org.apache.thrift.meta_data.FieldMetaData("columns", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TColumn.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TRowSet.class, metaDataMap);
  }

  public TRowSet() {
  }

  public TRowSet(
    long startRowOffset,
    List<TRow> rows)
  {
    this();
    this.startRowOffset = startRowOffset;
    setStartRowOffsetIsSet(true);
    this.rows = rows;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TRowSet(TRowSet other) {
    __isset_bitfield = other.__isset_bitfield;
    this.startRowOffset = other.startRowOffset;
    if (other.isSetRows()) {
      List<TRow> __this__rows = new ArrayList<TRow>();
      for (TRow other_element : other.rows) {
        __this__rows.add(new TRow(other_element));
      }
      this.rows = __this__rows;
    }
    if (other.isSetColumns()) {
      List<TColumn> __this__columns = new ArrayList<TColumn>();
      for (TColumn other_element : other.columns) {
        __this__columns.add(new TColumn(other_element));
      }
      this.columns = __this__columns;
    }
  }

  public TRowSet deepCopy() {
    return new TRowSet(this);
  }

  @Override
  public void clear() {
    setStartRowOffsetIsSet(false);
    this.startRowOffset = 0;
    this.rows = null;
    this.columns = null;
  }

  public long getStartRowOffset() {
    return this.startRowOffset;
  }

  public void setStartRowOffset(long startRowOffset) {
    this.startRowOffset = startRowOffset;
    setStartRowOffsetIsSet(true);
  }

  public void unsetStartRowOffset() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __STARTROWOFFSET_ISSET_ID);
  }

  /** Returns true if field startRowOffset is set (has been assigned a value) and false otherwise */
  public boolean isSetStartRowOffset() {
    return EncodingUtils.testBit(__isset_bitfield, __STARTROWOFFSET_ISSET_ID);
  }

  public void setStartRowOffsetIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __STARTROWOFFSET_ISSET_ID, value);
  }

  public int getRowsSize() {
    return (this.rows == null) ? 0 : this.rows.size();
  }

  public java.util.Iterator<TRow> getRowsIterator() {
    return (this.rows == null) ? null : this.rows.iterator();
  }

  public void addToRows(TRow elem) {
    if (this.rows == null) {
      this.rows = new ArrayList<TRow>();
    }
    this.rows.add(elem);
  }

  public List<TRow> getRows() {
    return this.rows;
  }

  public void setRows(List<TRow> rows) {
    this.rows = rows;
  }

  public void unsetRows() {
    this.rows = null;
  }

  /** Returns true if field rows is set (has been assigned a value) and false otherwise */
  public boolean isSetRows() {
    return this.rows != null;
  }

  public void setRowsIsSet(boolean value) {
    if (!value) {
      this.rows = null;
    }
  }

  public int getColumnsSize() {
    return (this.columns == null) ? 0 : this.columns.size();
  }

  public java.util.Iterator<TColumn> getColumnsIterator() {
    return (this.columns == null) ? null : this.columns.iterator();
  }

  public void addToColumns(TColumn elem) {
    if (this.columns == null) {
      this.columns = new ArrayList<TColumn>();
    }
    this.columns.add(elem);
  }

  public List<TColumn> getColumns() {
    return this.columns;
  }

  public void setColumns(List<TColumn> columns) {
    this.columns = columns;
  }

  public void unsetColumns() {
    this.columns = null;
  }

  /** Returns true if field columns is set (has been assigned a value) and false otherwise */
  public boolean isSetColumns() {
    return this.columns != null;
  }

  public void setColumnsIsSet(boolean value) {
    if (!value) {
      this.columns = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case START_ROW_OFFSET:
      if (value == null) {
        unsetStartRowOffset();
      } else {
        setStartRowOffset((Long)value);
      }
      break;

    case ROWS:
      if (value == null) {
        unsetRows();
      } else {
        setRows((List<TRow>)value);
      }
      break;

    case COLUMNS:
      if (value == null) {
        unsetColumns();
      } else {
        setColumns((List<TColumn>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case START_ROW_OFFSET:
      return Long.valueOf(getStartRowOffset());

    case ROWS:
      return getRows();

    case COLUMNS:
      return getColumns();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case START_ROW_OFFSET:
      return isSetStartRowOffset();
    case ROWS:
      return isSetRows();
    case COLUMNS:
      return isSetColumns();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TRowSet)
      return this.equals((TRowSet)that);
    return false;
  }

  public boolean equals(TRowSet that) {
    if (that == null)
      return false;

    boolean this_present_startRowOffset = true;
    boolean that_present_startRowOffset = true;
    if (this_present_startRowOffset || that_present_startRowOffset) {
      if (!(this_present_startRowOffset && that_present_startRowOffset))
        return false;
      if (this.startRowOffset != that.startRowOffset)
        return false;
    }

    boolean this_present_rows = true && this.isSetRows();
    boolean that_present_rows = true && that.isSetRows();
    if (this_present_rows || that_present_rows) {
      if (!(this_present_rows && that_present_rows))
        return false;
      if (!this.rows.equals(that.rows))
        return false;
    }

    boolean this_present_columns = true && this.isSetColumns();
    boolean that_present_columns = true && that.isSetColumns();
    if (this_present_columns || that_present_columns) {
      if (!(this_present_columns && that_present_columns))
        return false;
      if (!this.columns.equals(that.columns))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();

    boolean present_startRowOffset = true;
    builder.append(present_startRowOffset);
    if (present_startRowOffset)
      builder.append(startRowOffset);

    boolean present_rows = true && (isSetRows());
    builder.append(present_rows);
    if (present_rows)
      builder.append(rows);

    boolean present_columns = true && (isSetColumns());
    builder.append(present_columns);
    if (present_columns)
      builder.append(columns);

    return builder.toHashCode();
  }

  public int compareTo(TRowSet other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    TRowSet typedOther = (TRowSet)other;

    lastComparison = Boolean.valueOf(isSetStartRowOffset()).compareTo(typedOther.isSetStartRowOffset());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStartRowOffset()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.startRowOffset, typedOther.startRowOffset);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRows()).compareTo(typedOther.isSetRows());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRows()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rows, typedOther.rows);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetColumns()).compareTo(typedOther.isSetColumns());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetColumns()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.columns, typedOther.columns);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TRowSet(");
    boolean first = true;

    sb.append("startRowOffset:");
    sb.append(this.startRowOffset);
    first = false;
    if (!first) sb.append(", ");
    sb.append("rows:");
    if (this.rows == null) {
      sb.append("null");
    } else {
      sb.append(this.rows);
    }
    first = false;
    if (isSetColumns()) {
      if (!first) sb.append(", ");
      sb.append("columns:");
      if (this.columns == null) {
        sb.append("null");
      } else {
        sb.append(this.columns);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetStartRowOffset()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'startRowOffset' is unset! Struct:" + toString());
    }

    if (!isSetRows()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'rows' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TRowSetStandardSchemeFactory implements SchemeFactory {
    public TRowSetStandardScheme getScheme() {
      return new TRowSetStandardScheme();
    }
  }

  private static class TRowSetStandardScheme extends StandardScheme<TRowSet> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TRowSet struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // START_ROW_OFFSET
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.startRowOffset = iprot.readI64();
              struct.setStartRowOffsetIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ROWS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list118 = iprot.readListBegin();
                struct.rows = new ArrayList<TRow>(_list118.size);
                for (int _i119 = 0; _i119 < _list118.size; ++_i119)
                {
                  TRow _elem120; // optional
                  _elem120 = new TRow();
                  _elem120.read(iprot);
                  struct.rows.add(_elem120);
                }
                iprot.readListEnd();
              }
              struct.setRowsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // COLUMNS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list121 = iprot.readListBegin();
                struct.columns = new ArrayList<TColumn>(_list121.size);
                for (int _i122 = 0; _i122 < _list121.size; ++_i122)
                {
                  TColumn _elem123; // optional
                  _elem123 = new TColumn();
                  _elem123.read(iprot);
                  struct.columns.add(_elem123);
                }
                iprot.readListEnd();
              }
              struct.setColumnsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TRowSet struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(START_ROW_OFFSET_FIELD_DESC);
      oprot.writeI64(struct.startRowOffset);
      oprot.writeFieldEnd();
      if (struct.rows != null) {
        oprot.writeFieldBegin(ROWS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.rows.size()));
          for (TRow _iter124 : struct.rows)
          {
            _iter124.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.columns != null) {
        if (struct.isSetColumns()) {
          oprot.writeFieldBegin(COLUMNS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.columns.size()));
            for (TColumn _iter125 : struct.columns)
            {
              _iter125.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TRowSetTupleSchemeFactory implements SchemeFactory {
    public TRowSetTupleScheme getScheme() {
      return new TRowSetTupleScheme();
    }
  }

  private static class TRowSetTupleScheme extends TupleScheme<TRowSet> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TRowSet struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.startRowOffset);
      {
        oprot.writeI32(struct.rows.size());
        for (TRow _iter126 : struct.rows)
        {
          _iter126.write(oprot);
        }
      }
      BitSet optionals = new BitSet();
      if (struct.isSetColumns()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetColumns()) {
        {
          oprot.writeI32(struct.columns.size());
          for (TColumn _iter127 : struct.columns)
          {
            _iter127.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TRowSet struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.startRowOffset = iprot.readI64();
      struct.setStartRowOffsetIsSet(true);
      {
        org.apache.thrift.protocol.TList _list128 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.rows = new ArrayList<TRow>(_list128.size);
        for (int _i129 = 0; _i129 < _list128.size; ++_i129)
        {
          TRow _elem130; // optional
          _elem130 = new TRow();
          _elem130.read(iprot);
          struct.rows.add(_elem130);
        }
      }
      struct.setRowsIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list131 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.columns = new ArrayList<TColumn>(_list131.size);
          for (int _i132 = 0; _i132 < _list131.size; ++_i132)
          {
            TColumn _elem133; // optional
            _elem133 = new TColumn();
            _elem133.read(iprot);
            struct.columns.add(_elem133);
          }
        }
        struct.setColumnsIsSet(true);
      }
    }
  }

}

