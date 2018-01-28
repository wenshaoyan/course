/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.wenshao.dal.thriftgen;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2018-01-27")
public class RequestException extends org.apache.thrift.TException implements org.apache.thrift.TBase<RequestException, RequestException._Fields>, java.io.Serializable, Cloneable, Comparable<RequestException> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RequestException");

  private static final org.apache.thrift.protocol.TField CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("code", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("message", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField SERVER_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("serverName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField METHOD_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("methodName", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField FULL_MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("fullMessage", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RequestExceptionStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RequestExceptionTupleSchemeFactory();

  public int code; // required
  public java.lang.String message; // required
  public java.lang.String serverName; // required
  public java.lang.String methodName; // required
  public java.lang.String fullMessage; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CODE((short)1, "code"),
    MESSAGE((short)2, "message"),
    SERVER_NAME((short)3, "serverName"),
    METHOD_NAME((short)4, "methodName"),
    FULL_MESSAGE((short)5, "fullMessage");

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
        case 1: // CODE
          return CODE;
        case 2: // MESSAGE
          return MESSAGE;
        case 3: // SERVER_NAME
          return SERVER_NAME;
        case 4: // METHOD_NAME
          return METHOD_NAME;
        case 5: // FULL_MESSAGE
          return FULL_MESSAGE;
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
  private static final int __CODE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CODE, new org.apache.thrift.meta_data.FieldMetaData("code", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("message", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SERVER_NAME, new org.apache.thrift.meta_data.FieldMetaData("serverName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.METHOD_NAME, new org.apache.thrift.meta_data.FieldMetaData("methodName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FULL_MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("fullMessage", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RequestException.class, metaDataMap);
  }

  public RequestException() {
  }

  public RequestException(
    int code,
    java.lang.String message,
    java.lang.String serverName,
    java.lang.String methodName,
    java.lang.String fullMessage)
  {
    this();
    this.code = code;
    setCodeIsSet(true);
    this.message = message;
    this.serverName = serverName;
    this.methodName = methodName;
    this.fullMessage = fullMessage;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RequestException(RequestException other) {
    __isset_bitfield = other.__isset_bitfield;
    this.code = other.code;
    if (other.isSetMessage()) {
      this.message = other.message;
    }
    if (other.isSetServerName()) {
      this.serverName = other.serverName;
    }
    if (other.isSetMethodName()) {
      this.methodName = other.methodName;
    }
    if (other.isSetFullMessage()) {
      this.fullMessage = other.fullMessage;
    }
  }

  public RequestException deepCopy() {
    return new RequestException(this);
  }

  @Override
  public void clear() {
    setCodeIsSet(false);
    this.code = 0;
    this.message = null;
    this.serverName = null;
    this.methodName = null;
    this.fullMessage = null;
  }

  public int getCode() {
    return this.code;
  }

  public RequestException setCode(int code) {
    this.code = code;
    setCodeIsSet(true);
    return this;
  }

  public void unsetCode() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CODE_ISSET_ID);
  }

  /** Returns true if field code is set (has been assigned a value) and false otherwise */
  public boolean isSetCode() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CODE_ISSET_ID);
  }

  public void setCodeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CODE_ISSET_ID, value);
  }

  public java.lang.String getMessage() {
    return this.message;
  }

  public RequestException setMessage(java.lang.String message) {
    this.message = message;
    return this;
  }

  public void unsetMessage() {
    this.message = null;
  }

  /** Returns true if field message is set (has been assigned a value) and false otherwise */
  public boolean isSetMessage() {
    return this.message != null;
  }

  public void setMessageIsSet(boolean value) {
    if (!value) {
      this.message = null;
    }
  }

  public java.lang.String getServerName() {
    return this.serverName;
  }

  public RequestException setServerName(java.lang.String serverName) {
    this.serverName = serverName;
    return this;
  }

  public void unsetServerName() {
    this.serverName = null;
  }

  /** Returns true if field serverName is set (has been assigned a value) and false otherwise */
  public boolean isSetServerName() {
    return this.serverName != null;
  }

  public void setServerNameIsSet(boolean value) {
    if (!value) {
      this.serverName = null;
    }
  }

  public java.lang.String getMethodName() {
    return this.methodName;
  }

  public RequestException setMethodName(java.lang.String methodName) {
    this.methodName = methodName;
    return this;
  }

  public void unsetMethodName() {
    this.methodName = null;
  }

  /** Returns true if field methodName is set (has been assigned a value) and false otherwise */
  public boolean isSetMethodName() {
    return this.methodName != null;
  }

  public void setMethodNameIsSet(boolean value) {
    if (!value) {
      this.methodName = null;
    }
  }

  public java.lang.String getFullMessage() {
    return this.fullMessage;
  }

  public RequestException setFullMessage(java.lang.String fullMessage) {
    this.fullMessage = fullMessage;
    return this;
  }

  public void unsetFullMessage() {
    this.fullMessage = null;
  }

  /** Returns true if field fullMessage is set (has been assigned a value) and false otherwise */
  public boolean isSetFullMessage() {
    return this.fullMessage != null;
  }

  public void setFullMessageIsSet(boolean value) {
    if (!value) {
      this.fullMessage = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case CODE:
      if (value == null) {
        unsetCode();
      } else {
        setCode((java.lang.Integer)value);
      }
      break;

    case MESSAGE:
      if (value == null) {
        unsetMessage();
      } else {
        setMessage((java.lang.String)value);
      }
      break;

    case SERVER_NAME:
      if (value == null) {
        unsetServerName();
      } else {
        setServerName((java.lang.String)value);
      }
      break;

    case METHOD_NAME:
      if (value == null) {
        unsetMethodName();
      } else {
        setMethodName((java.lang.String)value);
      }
      break;

    case FULL_MESSAGE:
      if (value == null) {
        unsetFullMessage();
      } else {
        setFullMessage((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case CODE:
      return getCode();

    case MESSAGE:
      return getMessage();

    case SERVER_NAME:
      return getServerName();

    case METHOD_NAME:
      return getMethodName();

    case FULL_MESSAGE:
      return getFullMessage();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case CODE:
      return isSetCode();
    case MESSAGE:
      return isSetMessage();
    case SERVER_NAME:
      return isSetServerName();
    case METHOD_NAME:
      return isSetMethodName();
    case FULL_MESSAGE:
      return isSetFullMessage();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RequestException)
      return this.equals((RequestException)that);
    return false;
  }

  public boolean equals(RequestException that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_code = true;
    boolean that_present_code = true;
    if (this_present_code || that_present_code) {
      if (!(this_present_code && that_present_code))
        return false;
      if (this.code != that.code)
        return false;
    }

    boolean this_present_message = true && this.isSetMessage();
    boolean that_present_message = true && that.isSetMessage();
    if (this_present_message || that_present_message) {
      if (!(this_present_message && that_present_message))
        return false;
      if (!this.message.equals(that.message))
        return false;
    }

    boolean this_present_serverName = true && this.isSetServerName();
    boolean that_present_serverName = true && that.isSetServerName();
    if (this_present_serverName || that_present_serverName) {
      if (!(this_present_serverName && that_present_serverName))
        return false;
      if (!this.serverName.equals(that.serverName))
        return false;
    }

    boolean this_present_methodName = true && this.isSetMethodName();
    boolean that_present_methodName = true && that.isSetMethodName();
    if (this_present_methodName || that_present_methodName) {
      if (!(this_present_methodName && that_present_methodName))
        return false;
      if (!this.methodName.equals(that.methodName))
        return false;
    }

    boolean this_present_fullMessage = true && this.isSetFullMessage();
    boolean that_present_fullMessage = true && that.isSetFullMessage();
    if (this_present_fullMessage || that_present_fullMessage) {
      if (!(this_present_fullMessage && that_present_fullMessage))
        return false;
      if (!this.fullMessage.equals(that.fullMessage))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + code;

    hashCode = hashCode * 8191 + ((isSetMessage()) ? 131071 : 524287);
    if (isSetMessage())
      hashCode = hashCode * 8191 + message.hashCode();

    hashCode = hashCode * 8191 + ((isSetServerName()) ? 131071 : 524287);
    if (isSetServerName())
      hashCode = hashCode * 8191 + serverName.hashCode();

    hashCode = hashCode * 8191 + ((isSetMethodName()) ? 131071 : 524287);
    if (isSetMethodName())
      hashCode = hashCode * 8191 + methodName.hashCode();

    hashCode = hashCode * 8191 + ((isSetFullMessage()) ? 131071 : 524287);
    if (isSetFullMessage())
      hashCode = hashCode * 8191 + fullMessage.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RequestException other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetCode()).compareTo(other.isSetCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.code, other.code);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetMessage()).compareTo(other.isSetMessage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMessage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.message, other.message);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetServerName()).compareTo(other.isSetServerName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServerName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.serverName, other.serverName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetMethodName()).compareTo(other.isSetMethodName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMethodName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.methodName, other.methodName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetFullMessage()).compareTo(other.isSetFullMessage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFullMessage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fullMessage, other.fullMessage);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RequestException(");
    boolean first = true;

    sb.append("code:");
    sb.append(this.code);
    first = false;
    if (!first) sb.append(", ");
    sb.append("message:");
    if (this.message == null) {
      sb.append("null");
    } else {
      sb.append(this.message);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("serverName:");
    if (this.serverName == null) {
      sb.append("null");
    } else {
      sb.append(this.serverName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("methodName:");
    if (this.methodName == null) {
      sb.append("null");
    } else {
      sb.append(this.methodName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("fullMessage:");
    if (this.fullMessage == null) {
      sb.append("null");
    } else {
      sb.append(this.fullMessage);
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

  private static class RequestExceptionStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RequestExceptionStandardScheme getScheme() {
      return new RequestExceptionStandardScheme();
    }
  }

  private static class RequestExceptionStandardScheme extends org.apache.thrift.scheme.StandardScheme<RequestException> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RequestException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.code = iprot.readI32();
              struct.setCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MESSAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.message = iprot.readString();
              struct.setMessageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SERVER_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.serverName = iprot.readString();
              struct.setServerNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // METHOD_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.methodName = iprot.readString();
              struct.setMethodNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // FULL_MESSAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.fullMessage = iprot.readString();
              struct.setFullMessageIsSet(true);
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

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RequestException struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(CODE_FIELD_DESC);
      oprot.writeI32(struct.code);
      oprot.writeFieldEnd();
      if (struct.message != null) {
        oprot.writeFieldBegin(MESSAGE_FIELD_DESC);
        oprot.writeString(struct.message);
        oprot.writeFieldEnd();
      }
      if (struct.serverName != null) {
        oprot.writeFieldBegin(SERVER_NAME_FIELD_DESC);
        oprot.writeString(struct.serverName);
        oprot.writeFieldEnd();
      }
      if (struct.methodName != null) {
        oprot.writeFieldBegin(METHOD_NAME_FIELD_DESC);
        oprot.writeString(struct.methodName);
        oprot.writeFieldEnd();
      }
      if (struct.fullMessage != null) {
        oprot.writeFieldBegin(FULL_MESSAGE_FIELD_DESC);
        oprot.writeString(struct.fullMessage);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RequestExceptionTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RequestExceptionTupleScheme getScheme() {
      return new RequestExceptionTupleScheme();
    }
  }

  private static class RequestExceptionTupleScheme extends org.apache.thrift.scheme.TupleScheme<RequestException> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RequestException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetCode()) {
        optionals.set(0);
      }
      if (struct.isSetMessage()) {
        optionals.set(1);
      }
      if (struct.isSetServerName()) {
        optionals.set(2);
      }
      if (struct.isSetMethodName()) {
        optionals.set(3);
      }
      if (struct.isSetFullMessage()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetCode()) {
        oprot.writeI32(struct.code);
      }
      if (struct.isSetMessage()) {
        oprot.writeString(struct.message);
      }
      if (struct.isSetServerName()) {
        oprot.writeString(struct.serverName);
      }
      if (struct.isSetMethodName()) {
        oprot.writeString(struct.methodName);
      }
      if (struct.isSetFullMessage()) {
        oprot.writeString(struct.fullMessage);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RequestException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.code = iprot.readI32();
        struct.setCodeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.message = iprot.readString();
        struct.setMessageIsSet(true);
      }
      if (incoming.get(2)) {
        struct.serverName = iprot.readString();
        struct.setServerNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.methodName = iprot.readString();
        struct.setMethodNameIsSet(true);
      }
      if (incoming.get(4)) {
        struct.fullMessage = iprot.readString();
        struct.setFullMessageIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

