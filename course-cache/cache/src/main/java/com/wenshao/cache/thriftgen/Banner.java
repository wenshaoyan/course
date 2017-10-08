/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.wenshao.cache.thriftgen;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-10-08")
public class Banner implements org.apache.thrift.TBase<Banner, Banner._Fields>, java.io.Serializable, Cloneable, Comparable<Banner> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Banner");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField CREATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("create_time", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField UPDATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("update_time", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField USER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("user_id", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField IMAGE_URL_FIELD_DESC = new org.apache.thrift.protocol.TField("image_url", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField REDIRECT_URL_FIELD_DESC = new org.apache.thrift.protocol.TField("redirect_url", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField SHOW_CLIENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("show_client_id", org.apache.thrift.protocol.TType.I32, (short)7);
  private static final org.apache.thrift.protocol.TField LOCATION_FIELD_DESC = new org.apache.thrift.protocol.TField("location", org.apache.thrift.protocol.TType.I32, (short)8);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new BannerStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new BannerTupleSchemeFactory();

  public int id; // required
  public String create_time; // required
  public String update_time; // required
  public int user_id; // required
  public String image_url; // required
  public String redirect_url; // required
  public int show_client_id; // required
  public int location; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    CREATE_TIME((short)2, "create_time"),
    UPDATE_TIME((short)3, "update_time"),
    USER_ID((short)4, "user_id"),
    IMAGE_URL((short)5, "image_url"),
    REDIRECT_URL((short)6, "redirect_url"),
    SHOW_CLIENT_ID((short)7, "show_client_id"),
    LOCATION((short)8, "location");

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
        case 4: // USER_ID
          return USER_ID;
        case 5: // IMAGE_URL
          return IMAGE_URL;
        case 6: // REDIRECT_URL
          return REDIRECT_URL;
        case 7: // SHOW_CLIENT_ID
          return SHOW_CLIENT_ID;
        case 8: // LOCATION
          return LOCATION;
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
  private static final int __USER_ID_ISSET_ID = 1;
  private static final int __SHOW_CLIENT_ID_ISSET_ID = 2;
  private static final int __LOCATION_ISSET_ID = 3;
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
    tmpMap.put(_Fields.USER_ID, new org.apache.thrift.meta_data.FieldMetaData("user_id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.IMAGE_URL, new org.apache.thrift.meta_data.FieldMetaData("image_url", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.REDIRECT_URL, new org.apache.thrift.meta_data.FieldMetaData("redirect_url", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SHOW_CLIENT_ID, new org.apache.thrift.meta_data.FieldMetaData("show_client_id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.LOCATION, new org.apache.thrift.meta_data.FieldMetaData("location", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Banner.class, metaDataMap);
  }

  public Banner() {
  }

  public Banner(
    int id,
    String create_time,
    String update_time,
    int user_id,
    String image_url,
    String redirect_url,
    int show_client_id,
    int location)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.create_time = create_time;
    this.update_time = update_time;
    this.user_id = user_id;
    setUser_idIsSet(true);
    this.image_url = image_url;
    this.redirect_url = redirect_url;
    this.show_client_id = show_client_id;
    setShow_client_idIsSet(true);
    this.location = location;
    setLocationIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Banner(Banner other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetCreate_time()) {
      this.create_time = other.create_time;
    }
    if (other.isSetUpdate_time()) {
      this.update_time = other.update_time;
    }
    this.user_id = other.user_id;
    if (other.isSetImage_url()) {
      this.image_url = other.image_url;
    }
    if (other.isSetRedirect_url()) {
      this.redirect_url = other.redirect_url;
    }
    this.show_client_id = other.show_client_id;
    this.location = other.location;
  }

  public Banner deepCopy() {
    return new Banner(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.create_time = null;
    this.update_time = null;
    setUser_idIsSet(false);
    this.user_id = 0;
    this.image_url = null;
    this.redirect_url = null;
    setShow_client_idIsSet(false);
    this.show_client_id = 0;
    setLocationIsSet(false);
    this.location = 0;
  }

  public int getId() {
    return this.id;
  }

  public Banner setId(int id) {
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

  public Banner setCreate_time(String create_time) {
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

  public Banner setUpdate_time(String update_time) {
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

  public int getUser_id() {
    return this.user_id;
  }

  public Banner setUser_id(int user_id) {
    this.user_id = user_id;
    setUser_idIsSet(true);
    return this;
  }

  public void unsetUser_id() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __USER_ID_ISSET_ID);
  }

  /** Returns true if field user_id is set (has been assigned a value) and false otherwise */
  public boolean isSetUser_id() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __USER_ID_ISSET_ID);
  }

  public void setUser_idIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __USER_ID_ISSET_ID, value);
  }

  public String getImage_url() {
    return this.image_url;
  }

  public Banner setImage_url(String image_url) {
    this.image_url = image_url;
    return this;
  }

  public void unsetImage_url() {
    this.image_url = null;
  }

  /** Returns true if field image_url is set (has been assigned a value) and false otherwise */
  public boolean isSetImage_url() {
    return this.image_url != null;
  }

  public void setImage_urlIsSet(boolean value) {
    if (!value) {
      this.image_url = null;
    }
  }

  public String getRedirect_url() {
    return this.redirect_url;
  }

  public Banner setRedirect_url(String redirect_url) {
    this.redirect_url = redirect_url;
    return this;
  }

  public void unsetRedirect_url() {
    this.redirect_url = null;
  }

  /** Returns true if field redirect_url is set (has been assigned a value) and false otherwise */
  public boolean isSetRedirect_url() {
    return this.redirect_url != null;
  }

  public void setRedirect_urlIsSet(boolean value) {
    if (!value) {
      this.redirect_url = null;
    }
  }

  public int getShow_client_id() {
    return this.show_client_id;
  }

  public Banner setShow_client_id(int show_client_id) {
    this.show_client_id = show_client_id;
    setShow_client_idIsSet(true);
    return this;
  }

  public void unsetShow_client_id() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SHOW_CLIENT_ID_ISSET_ID);
  }

  /** Returns true if field show_client_id is set (has been assigned a value) and false otherwise */
  public boolean isSetShow_client_id() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SHOW_CLIENT_ID_ISSET_ID);
  }

  public void setShow_client_idIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SHOW_CLIENT_ID_ISSET_ID, value);
  }

  public int getLocation() {
    return this.location;
  }

  public Banner setLocation(int location) {
    this.location = location;
    setLocationIsSet(true);
    return this;
  }

  public void unsetLocation() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LOCATION_ISSET_ID);
  }

  /** Returns true if field location is set (has been assigned a value) and false otherwise */
  public boolean isSetLocation() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LOCATION_ISSET_ID);
  }

  public void setLocationIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LOCATION_ISSET_ID, value);
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

    case USER_ID:
      if (value == null) {
        unsetUser_id();
      } else {
        setUser_id((Integer)value);
      }
      break;

    case IMAGE_URL:
      if (value == null) {
        unsetImage_url();
      } else {
        setImage_url((String)value);
      }
      break;

    case REDIRECT_URL:
      if (value == null) {
        unsetRedirect_url();
      } else {
        setRedirect_url((String)value);
      }
      break;

    case SHOW_CLIENT_ID:
      if (value == null) {
        unsetShow_client_id();
      } else {
        setShow_client_id((Integer)value);
      }
      break;

    case LOCATION:
      if (value == null) {
        unsetLocation();
      } else {
        setLocation((Integer)value);
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

    case USER_ID:
      return getUser_id();

    case IMAGE_URL:
      return getImage_url();

    case REDIRECT_URL:
      return getRedirect_url();

    case SHOW_CLIENT_ID:
      return getShow_client_id();

    case LOCATION:
      return getLocation();

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
    case USER_ID:
      return isSetUser_id();
    case IMAGE_URL:
      return isSetImage_url();
    case REDIRECT_URL:
      return isSetRedirect_url();
    case SHOW_CLIENT_ID:
      return isSetShow_client_id();
    case LOCATION:
      return isSetLocation();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Banner)
      return this.equals((Banner)that);
    return false;
  }

  public boolean equals(Banner that) {
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

    boolean this_present_user_id = true;
    boolean that_present_user_id = true;
    if (this_present_user_id || that_present_user_id) {
      if (!(this_present_user_id && that_present_user_id))
        return false;
      if (this.user_id != that.user_id)
        return false;
    }

    boolean this_present_image_url = true && this.isSetImage_url();
    boolean that_present_image_url = true && that.isSetImage_url();
    if (this_present_image_url || that_present_image_url) {
      if (!(this_present_image_url && that_present_image_url))
        return false;
      if (!this.image_url.equals(that.image_url))
        return false;
    }

    boolean this_present_redirect_url = true && this.isSetRedirect_url();
    boolean that_present_redirect_url = true && that.isSetRedirect_url();
    if (this_present_redirect_url || that_present_redirect_url) {
      if (!(this_present_redirect_url && that_present_redirect_url))
        return false;
      if (!this.redirect_url.equals(that.redirect_url))
        return false;
    }

    boolean this_present_show_client_id = true;
    boolean that_present_show_client_id = true;
    if (this_present_show_client_id || that_present_show_client_id) {
      if (!(this_present_show_client_id && that_present_show_client_id))
        return false;
      if (this.show_client_id != that.show_client_id)
        return false;
    }

    boolean this_present_location = true;
    boolean that_present_location = true;
    if (this_present_location || that_present_location) {
      if (!(this_present_location && that_present_location))
        return false;
      if (this.location != that.location)
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

    hashCode = hashCode * 8191 + user_id;

    hashCode = hashCode * 8191 + ((isSetImage_url()) ? 131071 : 524287);
    if (isSetImage_url())
      hashCode = hashCode * 8191 + image_url.hashCode();

    hashCode = hashCode * 8191 + ((isSetRedirect_url()) ? 131071 : 524287);
    if (isSetRedirect_url())
      hashCode = hashCode * 8191 + redirect_url.hashCode();

    hashCode = hashCode * 8191 + show_client_id;

    hashCode = hashCode * 8191 + location;

    return hashCode;
  }

  @Override
  public int compareTo(Banner other) {
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
    lastComparison = Boolean.valueOf(isSetUser_id()).compareTo(other.isSetUser_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUser_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.user_id, other.user_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetImage_url()).compareTo(other.isSetImage_url());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetImage_url()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.image_url, other.image_url);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRedirect_url()).compareTo(other.isSetRedirect_url());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRedirect_url()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.redirect_url, other.redirect_url);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetShow_client_id()).compareTo(other.isSetShow_client_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetShow_client_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.show_client_id, other.show_client_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLocation()).compareTo(other.isSetLocation());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLocation()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.location, other.location);
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
    StringBuilder sb = new StringBuilder("Banner(");
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
    sb.append("user_id:");
    sb.append(this.user_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("image_url:");
    if (this.image_url == null) {
      sb.append("null");
    } else {
      sb.append(this.image_url);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("redirect_url:");
    if (this.redirect_url == null) {
      sb.append("null");
    } else {
      sb.append(this.redirect_url);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("show_client_id:");
    sb.append(this.show_client_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("location:");
    sb.append(this.location);
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

  private static class BannerStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public BannerStandardScheme getScheme() {
      return new BannerStandardScheme();
    }
  }

  private static class BannerStandardScheme extends org.apache.thrift.scheme.StandardScheme<Banner> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Banner struct) throws org.apache.thrift.TException {
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
          case 4: // USER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.user_id = iprot.readI32();
              struct.setUser_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // IMAGE_URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.image_url = iprot.readString();
              struct.setImage_urlIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // REDIRECT_URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.redirect_url = iprot.readString();
              struct.setRedirect_urlIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // SHOW_CLIENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.show_client_id = iprot.readI32();
              struct.setShow_client_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // LOCATION
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.location = iprot.readI32();
              struct.setLocationIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Banner struct) throws org.apache.thrift.TException {
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
      oprot.writeFieldBegin(USER_ID_FIELD_DESC);
      oprot.writeI32(struct.user_id);
      oprot.writeFieldEnd();
      if (struct.image_url != null) {
        oprot.writeFieldBegin(IMAGE_URL_FIELD_DESC);
        oprot.writeString(struct.image_url);
        oprot.writeFieldEnd();
      }
      if (struct.redirect_url != null) {
        oprot.writeFieldBegin(REDIRECT_URL_FIELD_DESC);
        oprot.writeString(struct.redirect_url);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(SHOW_CLIENT_ID_FIELD_DESC);
      oprot.writeI32(struct.show_client_id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LOCATION_FIELD_DESC);
      oprot.writeI32(struct.location);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class BannerTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public BannerTupleScheme getScheme() {
      return new BannerTupleScheme();
    }
  }

  private static class BannerTupleScheme extends org.apache.thrift.scheme.TupleScheme<Banner> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Banner struct) throws org.apache.thrift.TException {
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
      if (struct.isSetUser_id()) {
        optionals.set(3);
      }
      if (struct.isSetImage_url()) {
        optionals.set(4);
      }
      if (struct.isSetRedirect_url()) {
        optionals.set(5);
      }
      if (struct.isSetShow_client_id()) {
        optionals.set(6);
      }
      if (struct.isSetLocation()) {
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
      if (struct.isSetUser_id()) {
        oprot.writeI32(struct.user_id);
      }
      if (struct.isSetImage_url()) {
        oprot.writeString(struct.image_url);
      }
      if (struct.isSetRedirect_url()) {
        oprot.writeString(struct.redirect_url);
      }
      if (struct.isSetShow_client_id()) {
        oprot.writeI32(struct.show_client_id);
      }
      if (struct.isSetLocation()) {
        oprot.writeI32(struct.location);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Banner struct) throws org.apache.thrift.TException {
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
        struct.user_id = iprot.readI32();
        struct.setUser_idIsSet(true);
      }
      if (incoming.get(4)) {
        struct.image_url = iprot.readString();
        struct.setImage_urlIsSet(true);
      }
      if (incoming.get(5)) {
        struct.redirect_url = iprot.readString();
        struct.setRedirect_urlIsSet(true);
      }
      if (incoming.get(6)) {
        struct.show_client_id = iprot.readI32();
        struct.setShow_client_idIsSet(true);
      }
      if (incoming.get(7)) {
        struct.location = iprot.readI32();
        struct.setLocationIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

