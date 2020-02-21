/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.navercorp.pinpoint.thrift.dto.flink;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2019-08-02")
public class TFTransaction implements org.apache.thrift.TBase<TFTransaction, TFTransaction._Fields>, java.io.Serializable, Cloneable, Comparable<TFTransaction> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TFTransaction");

  private static final org.apache.thrift.protocol.TField SAMPLED_NEW_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("sampledNewCount", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField SAMPLED_CONTINUATION_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("sampledContinuationCount", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField UNSAMPLED_NEW_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("unsampledNewCount", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField UNSAMPLED_CONTINUATION_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("unsampledContinuationCount", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField SKIPPED_NEW_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("skippedNewCount", org.apache.thrift.protocol.TType.I64, (short)6);
  private static final org.apache.thrift.protocol.TField SKIPPED_CONTINUATION_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("skippedContinuationCount", org.apache.thrift.protocol.TType.I64, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TFTransactionStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TFTransactionTupleSchemeFactory();

  private long sampledNewCount; // optional
  private long sampledContinuationCount; // optional
  private long unsampledNewCount; // optional
  private long unsampledContinuationCount; // optional
  private long skippedNewCount; // optional
  private long skippedContinuationCount; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SAMPLED_NEW_COUNT((short)2, "sampledNewCount"),
    SAMPLED_CONTINUATION_COUNT((short)3, "sampledContinuationCount"),
    UNSAMPLED_NEW_COUNT((short)4, "unsampledNewCount"),
    UNSAMPLED_CONTINUATION_COUNT((short)5, "unsampledContinuationCount"),
    SKIPPED_NEW_COUNT((short)6, "skippedNewCount"),
    SKIPPED_CONTINUATION_COUNT((short)7, "skippedContinuationCount");

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
        case 2: // SAMPLED_NEW_COUNT
          return SAMPLED_NEW_COUNT;
        case 3: // SAMPLED_CONTINUATION_COUNT
          return SAMPLED_CONTINUATION_COUNT;
        case 4: // UNSAMPLED_NEW_COUNT
          return UNSAMPLED_NEW_COUNT;
        case 5: // UNSAMPLED_CONTINUATION_COUNT
          return UNSAMPLED_CONTINUATION_COUNT;
        case 6: // SKIPPED_NEW_COUNT
          return SKIPPED_NEW_COUNT;
        case 7: // SKIPPED_CONTINUATION_COUNT
          return SKIPPED_CONTINUATION_COUNT;
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
  private static final int __SAMPLEDNEWCOUNT_ISSET_ID = 0;
  private static final int __SAMPLEDCONTINUATIONCOUNT_ISSET_ID = 1;
  private static final int __UNSAMPLEDNEWCOUNT_ISSET_ID = 2;
  private static final int __UNSAMPLEDCONTINUATIONCOUNT_ISSET_ID = 3;
  private static final int __SKIPPEDNEWCOUNT_ISSET_ID = 4;
  private static final int __SKIPPEDCONTINUATIONCOUNT_ISSET_ID = 5;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.SAMPLED_NEW_COUNT,_Fields.SAMPLED_CONTINUATION_COUNT,_Fields.UNSAMPLED_NEW_COUNT,_Fields.UNSAMPLED_CONTINUATION_COUNT,_Fields.SKIPPED_NEW_COUNT,_Fields.SKIPPED_CONTINUATION_COUNT};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SAMPLED_NEW_COUNT, new org.apache.thrift.meta_data.FieldMetaData("sampledNewCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SAMPLED_CONTINUATION_COUNT, new org.apache.thrift.meta_data.FieldMetaData("sampledContinuationCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.UNSAMPLED_NEW_COUNT, new org.apache.thrift.meta_data.FieldMetaData("unsampledNewCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.UNSAMPLED_CONTINUATION_COUNT, new org.apache.thrift.meta_data.FieldMetaData("unsampledContinuationCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SKIPPED_NEW_COUNT, new org.apache.thrift.meta_data.FieldMetaData("skippedNewCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SKIPPED_CONTINUATION_COUNT, new org.apache.thrift.meta_data.FieldMetaData("skippedContinuationCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TFTransaction.class, metaDataMap);
  }

  public TFTransaction() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TFTransaction(TFTransaction other) {
    __isset_bitfield = other.__isset_bitfield;
    this.sampledNewCount = other.sampledNewCount;
    this.sampledContinuationCount = other.sampledContinuationCount;
    this.unsampledNewCount = other.unsampledNewCount;
    this.unsampledContinuationCount = other.unsampledContinuationCount;
    this.skippedNewCount = other.skippedNewCount;
    this.skippedContinuationCount = other.skippedContinuationCount;
  }

  public TFTransaction deepCopy() {
    return new TFTransaction(this);
  }

  @Override
  public void clear() {
    setSampledNewCountIsSet(false);
    this.sampledNewCount = 0;
    setSampledContinuationCountIsSet(false);
    this.sampledContinuationCount = 0;
    setUnsampledNewCountIsSet(false);
    this.unsampledNewCount = 0;
    setUnsampledContinuationCountIsSet(false);
    this.unsampledContinuationCount = 0;
    setSkippedNewCountIsSet(false);
    this.skippedNewCount = 0;
    setSkippedContinuationCountIsSet(false);
    this.skippedContinuationCount = 0;
  }

  public long getSampledNewCount() {
    return this.sampledNewCount;
  }

  public void setSampledNewCount(long sampledNewCount) {
    this.sampledNewCount = sampledNewCount;
    setSampledNewCountIsSet(true);
  }

  public void unsetSampledNewCount() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SAMPLEDNEWCOUNT_ISSET_ID);
  }

  /** Returns true if field sampledNewCount is set (has been assigned a value) and false otherwise */
  public boolean isSetSampledNewCount() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SAMPLEDNEWCOUNT_ISSET_ID);
  }

  public void setSampledNewCountIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SAMPLEDNEWCOUNT_ISSET_ID, value);
  }

  public long getSampledContinuationCount() {
    return this.sampledContinuationCount;
  }

  public void setSampledContinuationCount(long sampledContinuationCount) {
    this.sampledContinuationCount = sampledContinuationCount;
    setSampledContinuationCountIsSet(true);
  }

  public void unsetSampledContinuationCount() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SAMPLEDCONTINUATIONCOUNT_ISSET_ID);
  }

  /** Returns true if field sampledContinuationCount is set (has been assigned a value) and false otherwise */
  public boolean isSetSampledContinuationCount() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SAMPLEDCONTINUATIONCOUNT_ISSET_ID);
  }

  public void setSampledContinuationCountIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SAMPLEDCONTINUATIONCOUNT_ISSET_ID, value);
  }

  public long getUnsampledNewCount() {
    return this.unsampledNewCount;
  }

  public void setUnsampledNewCount(long unsampledNewCount) {
    this.unsampledNewCount = unsampledNewCount;
    setUnsampledNewCountIsSet(true);
  }

  public void unsetUnsampledNewCount() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __UNSAMPLEDNEWCOUNT_ISSET_ID);
  }

  /** Returns true if field unsampledNewCount is set (has been assigned a value) and false otherwise */
  public boolean isSetUnsampledNewCount() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __UNSAMPLEDNEWCOUNT_ISSET_ID);
  }

  public void setUnsampledNewCountIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __UNSAMPLEDNEWCOUNT_ISSET_ID, value);
  }

  public long getUnsampledContinuationCount() {
    return this.unsampledContinuationCount;
  }

  public void setUnsampledContinuationCount(long unsampledContinuationCount) {
    this.unsampledContinuationCount = unsampledContinuationCount;
    setUnsampledContinuationCountIsSet(true);
  }

  public void unsetUnsampledContinuationCount() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __UNSAMPLEDCONTINUATIONCOUNT_ISSET_ID);
  }

  /** Returns true if field unsampledContinuationCount is set (has been assigned a value) and false otherwise */
  public boolean isSetUnsampledContinuationCount() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __UNSAMPLEDCONTINUATIONCOUNT_ISSET_ID);
  }

  public void setUnsampledContinuationCountIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __UNSAMPLEDCONTINUATIONCOUNT_ISSET_ID, value);
  }

  public long getSkippedNewCount() {
    return this.skippedNewCount;
  }

  public void setSkippedNewCount(long skippedNewCount) {
    this.skippedNewCount = skippedNewCount;
    setSkippedNewCountIsSet(true);
  }

  public void unsetSkippedNewCount() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SKIPPEDNEWCOUNT_ISSET_ID);
  }

  /** Returns true if field skippedNewCount is set (has been assigned a value) and false otherwise */
  public boolean isSetSkippedNewCount() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SKIPPEDNEWCOUNT_ISSET_ID);
  }

  public void setSkippedNewCountIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SKIPPEDNEWCOUNT_ISSET_ID, value);
  }

  public long getSkippedContinuationCount() {
    return this.skippedContinuationCount;
  }

  public void setSkippedContinuationCount(long skippedContinuationCount) {
    this.skippedContinuationCount = skippedContinuationCount;
    setSkippedContinuationCountIsSet(true);
  }

  public void unsetSkippedContinuationCount() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SKIPPEDCONTINUATIONCOUNT_ISSET_ID);
  }

  /** Returns true if field skippedContinuationCount is set (has been assigned a value) and false otherwise */
  public boolean isSetSkippedContinuationCount() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SKIPPEDCONTINUATIONCOUNT_ISSET_ID);
  }

  public void setSkippedContinuationCountIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SKIPPEDCONTINUATIONCOUNT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case SAMPLED_NEW_COUNT:
      if (value == null) {
        unsetSampledNewCount();
      } else {
        setSampledNewCount((java.lang.Long)value);
      }
      break;

    case SAMPLED_CONTINUATION_COUNT:
      if (value == null) {
        unsetSampledContinuationCount();
      } else {
        setSampledContinuationCount((java.lang.Long)value);
      }
      break;

    case UNSAMPLED_NEW_COUNT:
      if (value == null) {
        unsetUnsampledNewCount();
      } else {
        setUnsampledNewCount((java.lang.Long)value);
      }
      break;

    case UNSAMPLED_CONTINUATION_COUNT:
      if (value == null) {
        unsetUnsampledContinuationCount();
      } else {
        setUnsampledContinuationCount((java.lang.Long)value);
      }
      break;

    case SKIPPED_NEW_COUNT:
      if (value == null) {
        unsetSkippedNewCount();
      } else {
        setSkippedNewCount((java.lang.Long)value);
      }
      break;

    case SKIPPED_CONTINUATION_COUNT:
      if (value == null) {
        unsetSkippedContinuationCount();
      } else {
        setSkippedContinuationCount((java.lang.Long)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case SAMPLED_NEW_COUNT:
      return getSampledNewCount();

    case SAMPLED_CONTINUATION_COUNT:
      return getSampledContinuationCount();

    case UNSAMPLED_NEW_COUNT:
      return getUnsampledNewCount();

    case UNSAMPLED_CONTINUATION_COUNT:
      return getUnsampledContinuationCount();

    case SKIPPED_NEW_COUNT:
      return getSkippedNewCount();

    case SKIPPED_CONTINUATION_COUNT:
      return getSkippedContinuationCount();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case SAMPLED_NEW_COUNT:
      return isSetSampledNewCount();
    case SAMPLED_CONTINUATION_COUNT:
      return isSetSampledContinuationCount();
    case UNSAMPLED_NEW_COUNT:
      return isSetUnsampledNewCount();
    case UNSAMPLED_CONTINUATION_COUNT:
      return isSetUnsampledContinuationCount();
    case SKIPPED_NEW_COUNT:
      return isSetSkippedNewCount();
    case SKIPPED_CONTINUATION_COUNT:
      return isSetSkippedContinuationCount();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TFTransaction)
      return this.equals((TFTransaction)that);
    return false;
  }

  public boolean equals(TFTransaction that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_sampledNewCount = true && this.isSetSampledNewCount();
    boolean that_present_sampledNewCount = true && that.isSetSampledNewCount();
    if (this_present_sampledNewCount || that_present_sampledNewCount) {
      if (!(this_present_sampledNewCount && that_present_sampledNewCount))
        return false;
      if (this.sampledNewCount != that.sampledNewCount)
        return false;
    }

    boolean this_present_sampledContinuationCount = true && this.isSetSampledContinuationCount();
    boolean that_present_sampledContinuationCount = true && that.isSetSampledContinuationCount();
    if (this_present_sampledContinuationCount || that_present_sampledContinuationCount) {
      if (!(this_present_sampledContinuationCount && that_present_sampledContinuationCount))
        return false;
      if (this.sampledContinuationCount != that.sampledContinuationCount)
        return false;
    }

    boolean this_present_unsampledNewCount = true && this.isSetUnsampledNewCount();
    boolean that_present_unsampledNewCount = true && that.isSetUnsampledNewCount();
    if (this_present_unsampledNewCount || that_present_unsampledNewCount) {
      if (!(this_present_unsampledNewCount && that_present_unsampledNewCount))
        return false;
      if (this.unsampledNewCount != that.unsampledNewCount)
        return false;
    }

    boolean this_present_unsampledContinuationCount = true && this.isSetUnsampledContinuationCount();
    boolean that_present_unsampledContinuationCount = true && that.isSetUnsampledContinuationCount();
    if (this_present_unsampledContinuationCount || that_present_unsampledContinuationCount) {
      if (!(this_present_unsampledContinuationCount && that_present_unsampledContinuationCount))
        return false;
      if (this.unsampledContinuationCount != that.unsampledContinuationCount)
        return false;
    }

    boolean this_present_skippedNewCount = true && this.isSetSkippedNewCount();
    boolean that_present_skippedNewCount = true && that.isSetSkippedNewCount();
    if (this_present_skippedNewCount || that_present_skippedNewCount) {
      if (!(this_present_skippedNewCount && that_present_skippedNewCount))
        return false;
      if (this.skippedNewCount != that.skippedNewCount)
        return false;
    }

    boolean this_present_skippedContinuationCount = true && this.isSetSkippedContinuationCount();
    boolean that_present_skippedContinuationCount = true && that.isSetSkippedContinuationCount();
    if (this_present_skippedContinuationCount || that_present_skippedContinuationCount) {
      if (!(this_present_skippedContinuationCount && that_present_skippedContinuationCount))
        return false;
      if (this.skippedContinuationCount != that.skippedContinuationCount)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetSampledNewCount()) ? 131071 : 524287);
    if (isSetSampledNewCount())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(sampledNewCount);

    hashCode = hashCode * 8191 + ((isSetSampledContinuationCount()) ? 131071 : 524287);
    if (isSetSampledContinuationCount())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(sampledContinuationCount);

    hashCode = hashCode * 8191 + ((isSetUnsampledNewCount()) ? 131071 : 524287);
    if (isSetUnsampledNewCount())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(unsampledNewCount);

    hashCode = hashCode * 8191 + ((isSetUnsampledContinuationCount()) ? 131071 : 524287);
    if (isSetUnsampledContinuationCount())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(unsampledContinuationCount);

    hashCode = hashCode * 8191 + ((isSetSkippedNewCount()) ? 131071 : 524287);
    if (isSetSkippedNewCount())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(skippedNewCount);

    hashCode = hashCode * 8191 + ((isSetSkippedContinuationCount()) ? 131071 : 524287);
    if (isSetSkippedContinuationCount())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(skippedContinuationCount);

    return hashCode;
  }

  @Override
  public int compareTo(TFTransaction other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetSampledNewCount()).compareTo(other.isSetSampledNewCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSampledNewCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sampledNewCount, other.sampledNewCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSampledContinuationCount()).compareTo(other.isSetSampledContinuationCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSampledContinuationCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sampledContinuationCount, other.sampledContinuationCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUnsampledNewCount()).compareTo(other.isSetUnsampledNewCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUnsampledNewCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.unsampledNewCount, other.unsampledNewCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUnsampledContinuationCount()).compareTo(other.isSetUnsampledContinuationCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUnsampledContinuationCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.unsampledContinuationCount, other.unsampledContinuationCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSkippedNewCount()).compareTo(other.isSetSkippedNewCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSkippedNewCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.skippedNewCount, other.skippedNewCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSkippedContinuationCount()).compareTo(other.isSetSkippedContinuationCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSkippedContinuationCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.skippedContinuationCount, other.skippedContinuationCount);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TFTransaction(");
    boolean first = true;

    if (isSetSampledNewCount()) {
      sb.append("sampledNewCount:");
      sb.append(this.sampledNewCount);
      first = false;
    }
    if (isSetSampledContinuationCount()) {
      if (!first) sb.append(", ");
      sb.append("sampledContinuationCount:");
      sb.append(this.sampledContinuationCount);
      first = false;
    }
    if (isSetUnsampledNewCount()) {
      if (!first) sb.append(", ");
      sb.append("unsampledNewCount:");
      sb.append(this.unsampledNewCount);
      first = false;
    }
    if (isSetUnsampledContinuationCount()) {
      if (!first) sb.append(", ");
      sb.append("unsampledContinuationCount:");
      sb.append(this.unsampledContinuationCount);
      first = false;
    }
    if (isSetSkippedNewCount()) {
      if (!first) sb.append(", ");
      sb.append("skippedNewCount:");
      sb.append(this.skippedNewCount);
      first = false;
    }
    if (isSetSkippedContinuationCount()) {
      if (!first) sb.append(", ");
      sb.append("skippedContinuationCount:");
      sb.append(this.skippedContinuationCount);
      first = false;
    }
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

  private static class TFTransactionStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TFTransactionStandardScheme getScheme() {
      return new TFTransactionStandardScheme();
    }
  }

  private static class TFTransactionStandardScheme extends org.apache.thrift.scheme.StandardScheme<TFTransaction> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TFTransaction struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 2: // SAMPLED_NEW_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.sampledNewCount = iprot.readI64();
              struct.setSampledNewCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SAMPLED_CONTINUATION_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.sampledContinuationCount = iprot.readI64();
              struct.setSampledContinuationCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // UNSAMPLED_NEW_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.unsampledNewCount = iprot.readI64();
              struct.setUnsampledNewCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // UNSAMPLED_CONTINUATION_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.unsampledContinuationCount = iprot.readI64();
              struct.setUnsampledContinuationCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // SKIPPED_NEW_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.skippedNewCount = iprot.readI64();
              struct.setSkippedNewCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // SKIPPED_CONTINUATION_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.skippedContinuationCount = iprot.readI64();
              struct.setSkippedContinuationCountIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TFTransaction struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetSampledNewCount()) {
        oprot.writeFieldBegin(SAMPLED_NEW_COUNT_FIELD_DESC);
        oprot.writeI64(struct.sampledNewCount);
        oprot.writeFieldEnd();
      }
      if (struct.isSetSampledContinuationCount()) {
        oprot.writeFieldBegin(SAMPLED_CONTINUATION_COUNT_FIELD_DESC);
        oprot.writeI64(struct.sampledContinuationCount);
        oprot.writeFieldEnd();
      }
      if (struct.isSetUnsampledNewCount()) {
        oprot.writeFieldBegin(UNSAMPLED_NEW_COUNT_FIELD_DESC);
        oprot.writeI64(struct.unsampledNewCount);
        oprot.writeFieldEnd();
      }
      if (struct.isSetUnsampledContinuationCount()) {
        oprot.writeFieldBegin(UNSAMPLED_CONTINUATION_COUNT_FIELD_DESC);
        oprot.writeI64(struct.unsampledContinuationCount);
        oprot.writeFieldEnd();
      }
      if (struct.isSetSkippedNewCount()) {
        oprot.writeFieldBegin(SKIPPED_NEW_COUNT_FIELD_DESC);
        oprot.writeI64(struct.skippedNewCount);
        oprot.writeFieldEnd();
      }
      if (struct.isSetSkippedContinuationCount()) {
        oprot.writeFieldBegin(SKIPPED_CONTINUATION_COUNT_FIELD_DESC);
        oprot.writeI64(struct.skippedContinuationCount);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TFTransactionTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TFTransactionTupleScheme getScheme() {
      return new TFTransactionTupleScheme();
    }
  }

  private static class TFTransactionTupleScheme extends org.apache.thrift.scheme.TupleScheme<TFTransaction> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TFTransaction struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetSampledNewCount()) {
        optionals.set(0);
      }
      if (struct.isSetSampledContinuationCount()) {
        optionals.set(1);
      }
      if (struct.isSetUnsampledNewCount()) {
        optionals.set(2);
      }
      if (struct.isSetUnsampledContinuationCount()) {
        optionals.set(3);
      }
      if (struct.isSetSkippedNewCount()) {
        optionals.set(4);
      }
      if (struct.isSetSkippedContinuationCount()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetSampledNewCount()) {
        oprot.writeI64(struct.sampledNewCount);
      }
      if (struct.isSetSampledContinuationCount()) {
        oprot.writeI64(struct.sampledContinuationCount);
      }
      if (struct.isSetUnsampledNewCount()) {
        oprot.writeI64(struct.unsampledNewCount);
      }
      if (struct.isSetUnsampledContinuationCount()) {
        oprot.writeI64(struct.unsampledContinuationCount);
      }
      if (struct.isSetSkippedNewCount()) {
        oprot.writeI64(struct.skippedNewCount);
      }
      if (struct.isSetSkippedContinuationCount()) {
        oprot.writeI64(struct.skippedContinuationCount);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TFTransaction struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.sampledNewCount = iprot.readI64();
        struct.setSampledNewCountIsSet(true);
      }
      if (incoming.get(1)) {
        struct.sampledContinuationCount = iprot.readI64();
        struct.setSampledContinuationCountIsSet(true);
      }
      if (incoming.get(2)) {
        struct.unsampledNewCount = iprot.readI64();
        struct.setUnsampledNewCountIsSet(true);
      }
      if (incoming.get(3)) {
        struct.unsampledContinuationCount = iprot.readI64();
        struct.setUnsampledContinuationCountIsSet(true);
      }
      if (incoming.get(4)) {
        struct.skippedNewCount = iprot.readI64();
        struct.setSkippedNewCountIsSet(true);
      }
      if (incoming.get(5)) {
        struct.skippedContinuationCount = iprot.readI64();
        struct.setSkippedContinuationCountIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

