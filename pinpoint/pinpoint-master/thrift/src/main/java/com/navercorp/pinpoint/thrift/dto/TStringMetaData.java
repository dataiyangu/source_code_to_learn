/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.navercorp.pinpoint.thrift.dto;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-03-16")
public class TStringMetaData implements org.apache.thrift.TBase<TStringMetaData, TStringMetaData._Fields>, java.io.Serializable, Cloneable, Comparable<TStringMetaData> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TStringMetaData");

  private static final org.apache.thrift.protocol.TField AGENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("agentId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField AGENT_START_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("agentStartTime", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField STRING_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("stringId", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField STRING_VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("stringValue", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TStringMetaDataStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TStringMetaDataTupleSchemeFactory();

  private java.lang.String agentId; // required
  private long agentStartTime; // required
  private int stringId; // required
  private java.lang.String stringValue; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    AGENT_ID((short)1, "agentId"),
    AGENT_START_TIME((short)2, "agentStartTime"),
    STRING_ID((short)4, "stringId"),
    STRING_VALUE((short)5, "stringValue");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // AGENT_ID
          return AGENT_ID;
        case 2: // AGENT_START_TIME
          return AGENT_START_TIME;
        case 4: // STRING_ID
          return STRING_ID;
        case 5: // STRING_VALUE
          return STRING_VALUE;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __AGENTSTARTTIME_ISSET_ID = 0;
  private static final int __STRINGID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.AGENT_ID, new org.apache.thrift.meta_data.FieldMetaData("agentId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.AGENT_START_TIME, new org.apache.thrift.meta_data.FieldMetaData("agentStartTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.STRING_ID, new org.apache.thrift.meta_data.FieldMetaData("stringId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.STRING_VALUE, new org.apache.thrift.meta_data.FieldMetaData("stringValue", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TStringMetaData.class, metaDataMap);
  }

  public TStringMetaData() {
  }

  public TStringMetaData(
    java.lang.String agentId,
    long agentStartTime,
    int stringId,
    java.lang.String stringValue)
  {
    this();
    this.agentId = agentId;
    this.agentStartTime = agentStartTime;
    setAgentStartTimeIsSet(true);
    this.stringId = stringId;
    setStringIdIsSet(true);
    this.stringValue = stringValue;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TStringMetaData(TStringMetaData other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetAgentId()) {
      this.agentId = other.agentId;
    }
    this.agentStartTime = other.agentStartTime;
    this.stringId = other.stringId;
    if (other.isSetStringValue()) {
      this.stringValue = other.stringValue;
    }
  }

  public TStringMetaData deepCopy() {
    return new TStringMetaData(this);
  }

  @Override
  public void clear() {
    this.agentId = null;
    setAgentStartTimeIsSet(false);
    this.agentStartTime = 0;
    setStringIdIsSet(false);
    this.stringId = 0;
    this.stringValue = null;
  }

  public java.lang.String getAgentId() {
    return this.agentId;
  }

  public void setAgentId(java.lang.String agentId) {
    this.agentId = agentId;
  }

  public void unsetAgentId() {
    this.agentId = null;
  }

  /** Returns true if field agentId is set (has been assigned a value) and false otherwise */
  public boolean isSetAgentId() {
    return this.agentId != null;
  }

  public void setAgentIdIsSet(boolean value) {
    if (!value) {
      this.agentId = null;
    }
  }

  public long getAgentStartTime() {
    return this.agentStartTime;
  }

  public void setAgentStartTime(long agentStartTime) {
    this.agentStartTime = agentStartTime;
    setAgentStartTimeIsSet(true);
  }

  public void unsetAgentStartTime() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __AGENTSTARTTIME_ISSET_ID);
  }

  /** Returns true if field agentStartTime is set (has been assigned a value) and false otherwise */
  public boolean isSetAgentStartTime() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __AGENTSTARTTIME_ISSET_ID);
  }

  public void setAgentStartTimeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __AGENTSTARTTIME_ISSET_ID, value);
  }

  public int getStringId() {
    return this.stringId;
  }

  public void setStringId(int stringId) {
    this.stringId = stringId;
    setStringIdIsSet(true);
  }

  public void unsetStringId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __STRINGID_ISSET_ID);
  }

  /** Returns true if field stringId is set (has been assigned a value) and false otherwise */
  public boolean isSetStringId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __STRINGID_ISSET_ID);
  }

  public void setStringIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __STRINGID_ISSET_ID, value);
  }

  public java.lang.String getStringValue() {
    return this.stringValue;
  }

  public void setStringValue(java.lang.String stringValue) {
    this.stringValue = stringValue;
  }

  public void unsetStringValue() {
    this.stringValue = null;
  }

  /** Returns true if field stringValue is set (has been assigned a value) and false otherwise */
  public boolean isSetStringValue() {
    return this.stringValue != null;
  }

  public void setStringValueIsSet(boolean value) {
    if (!value) {
      this.stringValue = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case AGENT_ID:
      if (value == null) {
        unsetAgentId();
      } else {
        setAgentId((java.lang.String)value);
      }
      break;

    case AGENT_START_TIME:
      if (value == null) {
        unsetAgentStartTime();
      } else {
        setAgentStartTime((java.lang.Long)value);
      }
      break;

    case STRING_ID:
      if (value == null) {
        unsetStringId();
      } else {
        setStringId((java.lang.Integer)value);
      }
      break;

    case STRING_VALUE:
      if (value == null) {
        unsetStringValue();
      } else {
        setStringValue((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case AGENT_ID:
      return getAgentId();

    case AGENT_START_TIME:
      return getAgentStartTime();

    case STRING_ID:
      return getStringId();

    case STRING_VALUE:
      return getStringValue();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case AGENT_ID:
      return isSetAgentId();
    case AGENT_START_TIME:
      return isSetAgentStartTime();
    case STRING_ID:
      return isSetStringId();
    case STRING_VALUE:
      return isSetStringValue();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TStringMetaData)
      return this.equals((TStringMetaData)that);
    return false;
  }

  public boolean equals(TStringMetaData that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_agentId = true && this.isSetAgentId();
    boolean that_present_agentId = true && that.isSetAgentId();
    if (this_present_agentId || that_present_agentId) {
      if (!(this_present_agentId && that_present_agentId))
        return false;
      if (!this.agentId.equals(that.agentId))
        return false;
    }

    boolean this_present_agentStartTime = true;
    boolean that_present_agentStartTime = true;
    if (this_present_agentStartTime || that_present_agentStartTime) {
      if (!(this_present_agentStartTime && that_present_agentStartTime))
        return false;
      if (this.agentStartTime != that.agentStartTime)
        return false;
    }

    boolean this_present_stringId = true;
    boolean that_present_stringId = true;
    if (this_present_stringId || that_present_stringId) {
      if (!(this_present_stringId && that_present_stringId))
        return false;
      if (this.stringId != that.stringId)
        return false;
    }

    boolean this_present_stringValue = true && this.isSetStringValue();
    boolean that_present_stringValue = true && that.isSetStringValue();
    if (this_present_stringValue || that_present_stringValue) {
      if (!(this_present_stringValue && that_present_stringValue))
        return false;
      if (!this.stringValue.equals(that.stringValue))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetAgentId()) ? 131071 : 524287);
    if (isSetAgentId())
      hashCode = hashCode * 8191 + agentId.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(agentStartTime);

    hashCode = hashCode * 8191 + stringId;

    hashCode = hashCode * 8191 + ((isSetStringValue()) ? 131071 : 524287);
    if (isSetStringValue())
      hashCode = hashCode * 8191 + stringValue.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TStringMetaData other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetAgentId()).compareTo(other.isSetAgentId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAgentId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.agentId, other.agentId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetAgentStartTime()).compareTo(other.isSetAgentStartTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAgentStartTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.agentStartTime, other.agentStartTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetStringId()).compareTo(other.isSetStringId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStringId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.stringId, other.stringId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetStringValue()).compareTo(other.isSetStringValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStringValue()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.stringValue, other.stringValue);
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
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TStringMetaData(");
    boolean first = true;

    sb.append("agentId:");
    if (this.agentId == null) {
      sb.append("null");
    } else {
      sb.append(this.agentId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("agentStartTime:");
    sb.append(this.agentStartTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("stringId:");
    sb.append(this.stringId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("stringValue:");
    if (this.stringValue == null) {
      sb.append("null");
    } else {
      sb.append(this.stringValue);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TStringMetaDataStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TStringMetaDataStandardScheme getScheme() {
      return new TStringMetaDataStandardScheme();
    }
  }

  private static class TStringMetaDataStandardScheme extends org.apache.thrift.scheme.StandardScheme<TStringMetaData> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TStringMetaData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // AGENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.agentId = iprot.readString();
              struct.setAgentIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // AGENT_START_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.agentStartTime = iprot.readI64();
              struct.setAgentStartTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // STRING_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.stringId = iprot.readI32();
              struct.setStringIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // STRING_VALUE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.stringValue = iprot.readString();
              struct.setStringValueIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TStringMetaData struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.agentId != null) {
        oprot.writeFieldBegin(AGENT_ID_FIELD_DESC);
        oprot.writeString(struct.agentId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(AGENT_START_TIME_FIELD_DESC);
      oprot.writeI64(struct.agentStartTime);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(STRING_ID_FIELD_DESC);
      oprot.writeI32(struct.stringId);
      oprot.writeFieldEnd();
      if (struct.stringValue != null) {
        oprot.writeFieldBegin(STRING_VALUE_FIELD_DESC);
        oprot.writeString(struct.stringValue);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TStringMetaDataTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TStringMetaDataTupleScheme getScheme() {
      return new TStringMetaDataTupleScheme();
    }
  }

  private static class TStringMetaDataTupleScheme extends org.apache.thrift.scheme.TupleScheme<TStringMetaData> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TStringMetaData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAgentId()) {
        optionals.set(0);
      }
      if (struct.isSetAgentStartTime()) {
        optionals.set(1);
      }
      if (struct.isSetStringId()) {
        optionals.set(2);
      }
      if (struct.isSetStringValue()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetAgentId()) {
        oprot.writeString(struct.agentId);
      }
      if (struct.isSetAgentStartTime()) {
        oprot.writeI64(struct.agentStartTime);
      }
      if (struct.isSetStringId()) {
        oprot.writeI32(struct.stringId);
      }
      if (struct.isSetStringValue()) {
        oprot.writeString(struct.stringValue);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TStringMetaData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.agentId = iprot.readString();
        struct.setAgentIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.agentStartTime = iprot.readI64();
        struct.setAgentStartTimeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.stringId = iprot.readI32();
        struct.setStringIdIsSet(true);
      }
      if (incoming.get(3)) {
        struct.stringValue = iprot.readString();
        struct.setStringValueIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

