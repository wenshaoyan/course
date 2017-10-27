/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.wenshao.dal.thriftgen;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-10-25")
public class Version implements org.apache.thrift.TBase<Version, Version._Fields>, java.io.Serializable, Cloneable, Comparable<Version> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Version");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField CREATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("create_time", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField UPDATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("update_time", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField VERSION_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("version_name", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField VERSION_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("version_number", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField CLIENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("client_id", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField DOWNLOAD_URL_FIELD_DESC = new org.apache.thrift.protocol.TField("download_url", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField DESCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("description", org.apache.thrift.protocol.TType.STRING, (short)8);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new VersionStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new VersionTupleSchemeFactory();

  public int id; // required
  public String create_time; // required
  public String update_time; // required
  public String version_name; // required
  public String version_number; // required
  public int client_id; // required
  public String download_url; // required
  public String description; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    CREATE_TIME((short)2, "create_time"),
    UPDATE_TIME((short)3, "update_time"),
    VERSION_NAME((short)4, "version_name"),
    VERSION_NUMBER((short)5, "version_number"),
    CLIENT_ID((short)6, "client_id"),
    DOWNLOAD_URL((short)7, "download_url"),
    DESCRIPTION((short)8, "description");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

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
        case 1: // ID
          return ID;
        case 2: // CREATE_TIME
          return CREATE_TIME;
        case 3: // UPDATE_TIME
          return UPDATE_TIME;
        case 4: // VERSION_NAME
          return VERSION_NAME;
        case 5: // VERSION_NUMBER
          return VERSION_NUMBER;
        case 6: // CLIENT_ID
          return CLIENT_ID;
        case 7: // DOWNLOAD_URL
          return DOWNLOAD_URL;
        case 8: // DESCRIPTION
          return DESCRIPTION;
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
  private static final int __ID_ISSET_ID = 0;
  private static final int __CLIENT_ID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CREATE_TIME, new org.apache.thrift.meta_data.FieldMetaData("create_time", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.UPDATE_TIME, new org.apache.thrift.meta_data.FieldMetaData("update_time", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VERSION_NAME, new org.apache.thrift.meta_data.FieldMetaData("version_name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VERSION_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("version_number", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CLIENT_ID, new org.apache.thrift.meta_data.FieldMetaData("client_id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.DOWNLOAD_URL, new org.apache.thrift.meta_data.FieldMetaData("download_url", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DESCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("description", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Version.class, metaDataMap);
  }

  public Version() {
  }

  public Version(
    int id,
    String create_time,
    String update_time,
    String version_name,
    String version_number,
    int client_id,
    String download_url,
    String description)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.create_time = create_time;
    this.update_time = update_time;
    this.version_name = version_name;
    this.version_number = version_number;
    this.client_id = client_id;
    setClient_idIsSet(true);
    this.download_url = download_url;
    this.description = description;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Version(Version other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetCreate_time()) {
      this.create_time = other.create_time;
    }
    if (other.isSetUpdate_time()) {
      this.update_time = other.update_time;
    }
    if (other.isSetVersion_name()) {
      this.version_name = other.version_name;
    }
    if (other.isSetVersion_number()) {
      this.version_number = other.version_number;
    }
    this.client_id = other.client_id;
    if (other.isSetDownload_url()) {
      this.download_url = other.download_url;
    }
    if (other.isSetDescription()) {
      this.description = other.description;
    }
  }

  public Version deepCopy() {
    return new Version(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.create_time = null;
    this.update_time = null;
    this.version_name = null;
    this.version_number = null;
    setClient_idIsSet(false);
    this.client_id = 0;
    this.download_url = null;
    this.description = null;
  }

  public int getId() {
    return this.id;
  }

  public Version setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public String getCreate_time() {
    return this.create_time;
  }

  public Version setCreate_time(String create_time) {
    this.create_time = create_time;
    return this;
  }

  public void unsetCreate_time() {
    this.create_time = null;
  }

  /** Returns true if field create_time is set (has been assigned a value) and false otherwise */
  public boolean isSetCreate_time() {
    return this.create_time != null;
  }

  public void setCreate_timeIsSet(boolean value) {
    if (!value) {
      this.create_time = null;
    }
  }

  public String getUpdate_time() {
    return this.update_time;
  }

  public Version setUpdate_time(String update_time) {
    this.update_time = update_time;
    return this;
  }

  public void unsetUpdate_time() {
    this.update_time = null;
  }

  /** Returns true if field update_time is set (has been assigned a value) and false otherwise */
  public boolean isSetUpdate_time() {
    return this.update_time != null;
  }

  public void setUpdate_timeIsSet(boolean value) {
    if (!value) {
      this.update_time = null;
    }
  }

  public String getVersion_name() {
    return this.version_name;
  }

  public Version setVersion_name(String version_name) {
    this.version_name = version_name;
    return this;
  }

  public void unsetVersion_name() {
    this.version_name = null;
  }

  /** Returns true if field version_name is set (has been assigned a value) and false otherwise */
  public boolean isSetVersion_name() {
    return this.version_name != null;
  }

  public void setVersion_nameIsSet(boolean value) {
    if (!value) {
      this.version_name = null;
    }
  }

  public String getVersion_number() {
    return this.version_number;
  }

  public Version setVersion_number(String version_number) {
    this.version_number = version_number;
    return this;
  }

  public void unsetVersion_number() {
    this.version_number = null;
  }

  /** Returns true if field version_number is set (has been assigned a value) and false otherwise */
  public boolean isSetVersion_number() {
    return this.version_number != null;
  }

  public void setVersion_numberIsSet(boolean value) {
    if (!value) {
      this.version_number = null;
    }
  }

  public int getClient_id() {
    return this.client_id;
  }

  public Version setClient_id(int client_id) {
    this.client_id = client_id;
    setClient_idIsSet(true);
    return this;
  }

  public void unsetClient_id() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CLIENT_ID_ISSET_ID);
  }

  /** Returns true if field client_id is set (has been assigned a value) and false otherwise */
  public boolean isSetClient_id() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CLIENT_ID_ISSET_ID);
  }

  public void setClient_idIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CLIENT_ID_ISSET_ID, value);
  }

  public String getDownload_url() {
    return this.download_url;
  }

  public Version setDownload_url(String download_url) {
    this.download_url = download_url;
    return this;
  }

  public void unsetDownload_url() {
    this.download_url = null;
  }

  /** Returns true if field download_url is set (has been assigned a value) and false otherwise */
  public boolean isSetDownload_url() {
    return this.download_url != null;
  }

  public void setDownload_urlIsSet(boolean value) {
    if (!value) {
      this.download_url = null;
    }
  }

  public String getDescription() {
    return this.description;
  }

  public Version setDescription(String description) {
    this.description = description;
    return this;
  }

  public void unsetDescription() {
    this.description = null;
  }

  /** Returns true if field description is set (has been assigned a value) and false otherwise */
  public boolean isSetDescription() {
    return this.description != null;
  }

  public void setDescriptionIsSet(boolean value) {
    if (!value) {
      this.description = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    case CREATE_TIME:
      if (value == null) {
        unsetCreate_time();
      } else {
        setCreate_time((String)value);
      }
      break;

    case UPDATE_TIME:
      if (value == null) {
        unsetUpdate_time();
      } else {
        setUpdate_time((String)value);
      }
      break;

    case VERSION_NAME:
      if (value == null) {
        unsetVersion_name();
      } else {
        setVersion_name((String)value);
      }
      break;

    case VERSION_NUMBER:
      if (value == null) {
        unsetVersion_number();
      } else {
        setVersion_number((String)value);
      }
      break;

    case CLIENT_ID:
      if (value == null) {
        unsetClient_id();
      } else {
        setClient_id((Integer)value);
      }
      break;

    case DOWNLOAD_URL:
      if (value == null) {
        unsetDownload_url();
      } else {
        setDownload_url((String)value);
      }
      break;

    case DESCRIPTION:
      if (value == null) {
        unsetDescription();
      } else {
        setDescription((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case CREATE_TIME:
      return getCreate_time();

    case UPDATE_TIME:
      return getUpdate_time();

    case VERSION_NAME:
      return getVersion_name();

    case VERSION_NUMBER:
      return getVersion_number();

    case CLIENT_ID:
      return getClient_id();

    case DOWNLOAD_URL:
      return getDownload_url();

    case DESCRIPTION:
      return getDescription();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case CREATE_TIME:
      return isSetCreate_time();
    case UPDATE_TIME:
      return isSetUpdate_time();
    case VERSION_NAME:
      return isSetVersion_name();
    case VERSION_NUMBER:
      return isSetVersion_number();
    case CLIENT_ID:
      return isSetClient_id();
    case DOWNLOAD_URL:
      return isSetDownload_url();
    case DESCRIPTION:
      return isSetDescription();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Version)
      return this.equals((Version)that);
    return false;
  }

  public boolean equals(Version that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_create_time = true && this.isSetCreate_time();
    boolean that_present_create_time = true && that.isSetCreate_time();
    if (this_present_create_time || that_present_create_time) {
      if (!(this_present_create_time && that_present_create_time))
        return false;
      if (!this.create_time.equals(that.create_time))
        return false;
    }

    boolean this_present_update_time = true && this.isSetUpdate_time();
    boolean that_present_update_time = true && that.isSetUpdate_time();
    if (this_present_update_time || that_present_update_time) {
      if (!(this_present_update_time && that_present_update_time))
        return false;
      if (!this.update_time.equals(that.update_time))
        return false;
    }

    boolean this_present_version_name = true && this.isSetVersion_name();
    boolean that_present_version_name = true && that.isSetVersion_name();
    if (this_present_version_name || that_present_version_name) {
      if (!(this_present_version_name && that_present_version_name))
        return false;
      if (!this.version_name.equals(that.version_name))
        return false;
    }

    boolean this_present_version_number = true && this.isSetVersion_number();
    boolean that_present_version_number = true && that.isSetVersion_number();
    if (this_present_version_number || that_present_version_number) {
      if (!(this_present_version_number && that_present_version_number))
        return false;
      if (!this.version_number.equals(that.version_number))
        return false;
    }

    boolean this_present_client_id = true;
    boolean that_present_client_id = true;
    if (this_present_client_id || that_present_client_id) {
      if (!(this_present_client_id && that_present_client_id))
        return false;
      if (this.client_id != that.client_id)
        return false;
    }

    boolean this_present_download_url = true && this.isSetDownload_url();
    boolean that_present_download_url = true && that.isSetDownload_url();
    if (this_present_download_url || that_present_download_url) {
      if (!(this_present_download_url && that_present_download_url))
        return false;
      if (!this.download_url.equals(that.download_url))
        return false;
    }

    boolean this_present_description = true && this.isSetDescription();
    boolean that_present_description = true && that.isSetDescription();
    if (this_present_description || that_present_description) {
      if (!(this_present_description && that_present_description))
        return false;
      if (!this.description.equals(that.description))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + id;

    hashCode = hashCode * 8191 + ((isSetCreate_time()) ? 131071 : 524287);
    if (isSetCreate_time())
      hashCode = hashCode * 8191 + create_time.hashCode();

    hashCode = hashCode * 8191 + ((isSetUpdate_time()) ? 131071 : 524287);
    if (isSetUpdate_time())
      hashCode = hashCode * 8191 + update_time.hashCode();

    hashCode = hashCode * 8191 + ((isSetVersion_name()) ? 131071 : 524287);
    if (isSetVersion_name())
      hashCode = hashCode * 8191 + version_name.hashCode();

    hashCode = hashCode * 8191 + ((isSetVersion_number()) ? 131071 : 524287);
    if (isSetVersion_number())
      hashCode = hashCode * 8191 + version_number.hashCode();

    hashCode = hashCode * 8191 + client_id;

    hashCode = hashCode * 8191 + ((isSetDownload_url()) ? 131071 : 524287);
    if (isSetDownload_url())
      hashCode = hashCode * 8191 + download_url.hashCode();

    hashCode = hashCode * 8191 + ((isSetDescription()) ? 131071 : 524287);
    if (isSetDescription())
      hashCode = hashCode * 8191 + description.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Version other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCreate_time()).compareTo(other.isSetCreate_time());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCreate_time()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.create_time, other.create_time);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUpdate_time()).compareTo(other.isSetUpdate_time());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUpdate_time()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.update_time, other.update_time);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVersion_name()).compareTo(other.isSetVersion_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersion_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.version_name, other.version_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVersion_number()).compareTo(other.isSetVersion_number());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersion_number()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.version_number, other.version_number);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetClient_id()).compareTo(other.isSetClient_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClient_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.client_id, other.client_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDownload_url()).compareTo(other.isSetDownload_url());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDownload_url()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.download_url, other.download_url);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDescription()).compareTo(other.isSetDescription());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDescription()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.description, other.description);
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
  public String toString() {
    StringBuilder sb = new StringBuilder("Version(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("create_time:");
    if (this.create_time == null) {
      sb.append("null");
    } else {
      sb.append(this.create_time);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("update_time:");
    if (this.update_time == null) {
      sb.append("null");
    } else {
      sb.append(this.update_time);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("version_name:");
    if (this.version_name == null) {
      sb.append("null");
    } else {
      sb.append(this.version_name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("version_number:");
    if (this.version_number == null) {
      sb.append("null");
    } else {
      sb.append(this.version_number);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("client_id:");
    sb.append(this.client_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("download_url:");
    if (this.download_url == null) {
      sb.append("null");
    } else {
      sb.append(this.download_url);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("description:");
    if (this.description == null) {
      sb.append("null");
    } else {
      sb.append(this.description);
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

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class VersionStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public VersionStandardScheme getScheme() {
      return new VersionStandardScheme();
    }
  }

  private static class VersionStandardScheme extends org.apache.thrift.scheme.StandardScheme<Version> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Version struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CREATE_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.create_time = iprot.readString();
              struct.setCreate_timeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // UPDATE_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.update_time = iprot.readString();
              struct.setUpdate_timeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // VERSION_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.version_name = iprot.readString();
              struct.setVersion_nameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // VERSION_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.version_number = iprot.readString();
              struct.setVersion_numberIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // CLIENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.client_id = iprot.readI32();
              struct.setClient_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // DOWNLOAD_URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.download_url = iprot.readString();
              struct.setDownload_urlIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // DESCRIPTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.description = iprot.readString();
              struct.setDescriptionIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Version struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      if (struct.create_time != null) {
        oprot.writeFieldBegin(CREATE_TIME_FIELD_DESC);
        oprot.writeString(struct.create_time);
        oprot.writeFieldEnd();
      }
      if (struct.update_time != null) {
        oprot.writeFieldBegin(UPDATE_TIME_FIELD_DESC);
        oprot.writeString(struct.update_time);
        oprot.writeFieldEnd();
      }
      if (struct.version_name != null) {
        oprot.writeFieldBegin(VERSION_NAME_FIELD_DESC);
        oprot.writeString(struct.version_name);
        oprot.writeFieldEnd();
      }
      if (struct.version_number != null) {
        oprot.writeFieldBegin(VERSION_NUMBER_FIELD_DESC);
        oprot.writeString(struct.version_number);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(CLIENT_ID_FIELD_DESC);
      oprot.writeI32(struct.client_id);
      oprot.writeFieldEnd();
      if (struct.download_url != null) {
        oprot.writeFieldBegin(DOWNLOAD_URL_FIELD_DESC);
        oprot.writeString(struct.download_url);
        oprot.writeFieldEnd();
      }
      if (struct.description != null) {
        oprot.writeFieldBegin(DESCRIPTION_FIELD_DESC);
        oprot.writeString(struct.description);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class VersionTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public VersionTupleScheme getScheme() {
      return new VersionTupleScheme();
    }
  }

  private static class VersionTupleScheme extends org.apache.thrift.scheme.TupleScheme<Version> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Version struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetCreate_time()) {
        optionals.set(1);
      }
      if (struct.isSetUpdate_time()) {
        optionals.set(2);
      }
      if (struct.isSetVersion_name()) {
        optionals.set(3);
      }
      if (struct.isSetVersion_number()) {
        optionals.set(4);
      }
      if (struct.isSetClient_id()) {
        optionals.set(5);
      }
      if (struct.isSetDownload_url()) {
        optionals.set(6);
      }
      if (struct.isSetDescription()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetCreate_time()) {
        oprot.writeString(struct.create_time);
      }
      if (struct.isSetUpdate_time()) {
        oprot.writeString(struct.update_time);
      }
      if (struct.isSetVersion_name()) {
        oprot.writeString(struct.version_name);
      }
      if (struct.isSetVersion_number()) {
        oprot.writeString(struct.version_number);
      }
      if (struct.isSetClient_id()) {
        oprot.writeI32(struct.client_id);
      }
      if (struct.isSetDownload_url()) {
        oprot.writeString(struct.download_url);
      }
      if (struct.isSetDescription()) {
        oprot.writeString(struct.description);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Version struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.create_time = iprot.readString();
        struct.setCreate_timeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.update_time = iprot.readString();
        struct.setUpdate_timeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.version_name = iprot.readString();
        struct.setVersion_nameIsSet(true);
      }
      if (incoming.get(4)) {
        struct.version_number = iprot.readString();
        struct.setVersion_numberIsSet(true);
      }
      if (incoming.get(5)) {
        struct.client_id = iprot.readI32();
        struct.setClient_idIsSet(true);
      }
      if (incoming.get(6)) {
        struct.download_url = iprot.readString();
        struct.setDownload_urlIsSet(true);
      }
      if (incoming.get(7)) {
        struct.description = iprot.readString();
        struct.setDescriptionIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
