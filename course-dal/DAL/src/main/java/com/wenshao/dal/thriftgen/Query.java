/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.wenshao.dal.thriftgen;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2018-01-27")
public class Query implements org.apache.thrift.TBase<Query, Query._Fields>, java.io.Serializable, Cloneable, Comparable<Query> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Query");

  private static final org.apache.thrift.protocol.TField CREATE_TIME_START_FIELD_DESC = new org.apache.thrift.protocol.TField("create_time_start", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CREATE_TIME_END_FIELD_DESC = new org.apache.thrift.protocol.TField("create_time_end", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField UPDATE_TIME_START_FIELD_DESC = new org.apache.thrift.protocol.TField("update_time_start", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField UPDATE_TIME_END_FIELD_DESC = new org.apache.thrift.protocol.TField("update_time_end", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField PAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("page", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField LIMIT_FIELD_DESC = new org.apache.thrift.protocol.TField("limit", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField SORT_BY_FIELD_DESC = new org.apache.thrift.protocol.TField("sort_by", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField ORDER_FIELD_DESC = new org.apache.thrift.protocol.TField("order", org.apache.thrift.protocol.TType.STRING, (short)8);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new QueryStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new QueryTupleSchemeFactory();

  public java.lang.String create_time_start; // required
  public java.lang.String create_time_end; // required
  public java.lang.String update_time_start; // required
  public java.lang.String update_time_end; // required
  public int page; // required
  public int limit; // required
  public java.lang.String sort_by; // required
  public java.lang.String order; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CREATE_TIME_START((short)1, "create_time_start"),
    CREATE_TIME_END((short)2, "create_time_end"),
    UPDATE_TIME_START((short)3, "update_time_start"),
    UPDATE_TIME_END((short)4, "update_time_end"),
    PAGE((short)5, "page"),
    LIMIT((short)6, "limit"),
    SORT_BY((short)7, "sort_by"),
    ORDER((short)8, "order");

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
        case 1: // CREATE_TIME_START
          return CREATE_TIME_START;
        case 2: // CREATE_TIME_END
          return CREATE_TIME_END;
        case 3: // UPDATE_TIME_START
          return UPDATE_TIME_START;
        case 4: // UPDATE_TIME_END
          return UPDATE_TIME_END;
        case 5: // PAGE
          return PAGE;
        case 6: // LIMIT
          return LIMIT;
        case 7: // SORT_BY
          return SORT_BY;
        case 8: // ORDER
          return ORDER;
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
  private static final int __PAGE_ISSET_ID = 0;
  private static final int __LIMIT_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CREATE_TIME_START, new org.apache.thrift.meta_data.FieldMetaData("create_time_start", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CREATE_TIME_END, new org.apache.thrift.meta_data.FieldMetaData("create_time_end", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.UPDATE_TIME_START, new org.apache.thrift.meta_data.FieldMetaData("update_time_start", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.UPDATE_TIME_END, new org.apache.thrift.meta_data.FieldMetaData("update_time_end", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PAGE, new org.apache.thrift.meta_data.FieldMetaData("page", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.LIMIT, new org.apache.thrift.meta_data.FieldMetaData("limit", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SORT_BY, new org.apache.thrift.meta_data.FieldMetaData("sort_by", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ORDER, new org.apache.thrift.meta_data.FieldMetaData("order", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Query.class, metaDataMap);
  }

  public Query() {
  }

  public Query(
    java.lang.String create_time_start,
    java.lang.String create_time_end,
    java.lang.String update_time_start,
    java.lang.String update_time_end,
    int page,
    int limit,
    java.lang.String sort_by,
    java.lang.String order)
  {
    this();
    this.create_time_start = create_time_start;
    this.create_time_end = create_time_end;
    this.update_time_start = update_time_start;
    this.update_time_end = update_time_end;
    this.page = page;
    setPageIsSet(true);
    this.limit = limit;
    setLimitIsSet(true);
    this.sort_by = sort_by;
    this.order = order;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Query(Query other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetCreate_time_start()) {
      this.create_time_start = other.create_time_start;
    }
    if (other.isSetCreate_time_end()) {
      this.create_time_end = other.create_time_end;
    }
    if (other.isSetUpdate_time_start()) {
      this.update_time_start = other.update_time_start;
    }
    if (other.isSetUpdate_time_end()) {
      this.update_time_end = other.update_time_end;
    }
    this.page = other.page;
    this.limit = other.limit;
    if (other.isSetSort_by()) {
      this.sort_by = other.sort_by;
    }
    if (other.isSetOrder()) {
      this.order = other.order;
    }
  }

  public Query deepCopy() {
    return new Query(this);
  }

  @Override
  public void clear() {
    this.create_time_start = null;
    this.create_time_end = null;
    this.update_time_start = null;
    this.update_time_end = null;
    setPageIsSet(false);
    this.page = 0;
    setLimitIsSet(false);
    this.limit = 0;
    this.sort_by = null;
    this.order = null;
  }

  public java.lang.String getCreate_time_start() {
    return this.create_time_start;
  }

  public Query setCreate_time_start(java.lang.String create_time_start) {
    this.create_time_start = create_time_start;
    return this;
  }

  public void unsetCreate_time_start() {
    this.create_time_start = null;
  }

  /** Returns true if field create_time_start is set (has been assigned a value) and false otherwise */
  public boolean isSetCreate_time_start() {
    return this.create_time_start != null;
  }

  public void setCreate_time_startIsSet(boolean value) {
    if (!value) {
      this.create_time_start = null;
    }
  }

  public java.lang.String getCreate_time_end() {
    return this.create_time_end;
  }

  public Query setCreate_time_end(java.lang.String create_time_end) {
    this.create_time_end = create_time_end;
    return this;
  }

  public void unsetCreate_time_end() {
    this.create_time_end = null;
  }

  /** Returns true if field create_time_end is set (has been assigned a value) and false otherwise */
  public boolean isSetCreate_time_end() {
    return this.create_time_end != null;
  }

  public void setCreate_time_endIsSet(boolean value) {
    if (!value) {
      this.create_time_end = null;
    }
  }

  public java.lang.String getUpdate_time_start() {
    return this.update_time_start;
  }

  public Query setUpdate_time_start(java.lang.String update_time_start) {
    this.update_time_start = update_time_start;
    return this;
  }

  public void unsetUpdate_time_start() {
    this.update_time_start = null;
  }

  /** Returns true if field update_time_start is set (has been assigned a value) and false otherwise */
  public boolean isSetUpdate_time_start() {
    return this.update_time_start != null;
  }

  public void setUpdate_time_startIsSet(boolean value) {
    if (!value) {
      this.update_time_start = null;
    }
  }

  public java.lang.String getUpdate_time_end() {
    return this.update_time_end;
  }

  public Query setUpdate_time_end(java.lang.String update_time_end) {
    this.update_time_end = update_time_end;
    return this;
  }

  public void unsetUpdate_time_end() {
    this.update_time_end = null;
  }

  /** Returns true if field update_time_end is set (has been assigned a value) and false otherwise */
  public boolean isSetUpdate_time_end() {
    return this.update_time_end != null;
  }

  public void setUpdate_time_endIsSet(boolean value) {
    if (!value) {
      this.update_time_end = null;
    }
  }

  public int getPage() {
    return this.page;
  }

  public Query setPage(int page) {
    this.page = page;
    setPageIsSet(true);
    return this;
  }

  public void unsetPage() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PAGE_ISSET_ID);
  }

  /** Returns true if field page is set (has been assigned a value) and false otherwise */
  public boolean isSetPage() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PAGE_ISSET_ID);
  }

  public void setPageIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PAGE_ISSET_ID, value);
  }

  public int getLimit() {
    return this.limit;
  }

  public Query setLimit(int limit) {
    this.limit = limit;
    setLimitIsSet(true);
    return this;
  }

  public void unsetLimit() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LIMIT_ISSET_ID);
  }

  /** Returns true if field limit is set (has been assigned a value) and false otherwise */
  public boolean isSetLimit() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LIMIT_ISSET_ID);
  }

  public void setLimitIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LIMIT_ISSET_ID, value);
  }

  public java.lang.String getSort_by() {
    return this.sort_by;
  }

  public Query setSort_by(java.lang.String sort_by) {
    this.sort_by = sort_by;
    return this;
  }

  public void unsetSort_by() {
    this.sort_by = null;
  }

  /** Returns true if field sort_by is set (has been assigned a value) and false otherwise */
  public boolean isSetSort_by() {
    return this.sort_by != null;
  }

  public void setSort_byIsSet(boolean value) {
    if (!value) {
      this.sort_by = null;
    }
  }

  public java.lang.String getOrder() {
    return this.order;
  }

  public Query setOrder(java.lang.String order) {
    this.order = order;
    return this;
  }

  public void unsetOrder() {
    this.order = null;
  }

  /** Returns true if field order is set (has been assigned a value) and false otherwise */
  public boolean isSetOrder() {
    return this.order != null;
  }

  public void setOrderIsSet(boolean value) {
    if (!value) {
      this.order = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case CREATE_TIME_START:
      if (value == null) {
        unsetCreate_time_start();
      } else {
        setCreate_time_start((java.lang.String)value);
      }
      break;

    case CREATE_TIME_END:
      if (value == null) {
        unsetCreate_time_end();
      } else {
        setCreate_time_end((java.lang.String)value);
      }
      break;

    case UPDATE_TIME_START:
      if (value == null) {
        unsetUpdate_time_start();
      } else {
        setUpdate_time_start((java.lang.String)value);
      }
      break;

    case UPDATE_TIME_END:
      if (value == null) {
        unsetUpdate_time_end();
      } else {
        setUpdate_time_end((java.lang.String)value);
      }
      break;

    case PAGE:
      if (value == null) {
        unsetPage();
      } else {
        setPage((java.lang.Integer)value);
      }
      break;

    case LIMIT:
      if (value == null) {
        unsetLimit();
      } else {
        setLimit((java.lang.Integer)value);
      }
      break;

    case SORT_BY:
      if (value == null) {
        unsetSort_by();
      } else {
        setSort_by((java.lang.String)value);
      }
      break;

    case ORDER:
      if (value == null) {
        unsetOrder();
      } else {
        setOrder((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case CREATE_TIME_START:
      return getCreate_time_start();

    case CREATE_TIME_END:
      return getCreate_time_end();

    case UPDATE_TIME_START:
      return getUpdate_time_start();

    case UPDATE_TIME_END:
      return getUpdate_time_end();

    case PAGE:
      return getPage();

    case LIMIT:
      return getLimit();

    case SORT_BY:
      return getSort_by();

    case ORDER:
      return getOrder();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case CREATE_TIME_START:
      return isSetCreate_time_start();
    case CREATE_TIME_END:
      return isSetCreate_time_end();
    case UPDATE_TIME_START:
      return isSetUpdate_time_start();
    case UPDATE_TIME_END:
      return isSetUpdate_time_end();
    case PAGE:
      return isSetPage();
    case LIMIT:
      return isSetLimit();
    case SORT_BY:
      return isSetSort_by();
    case ORDER:
      return isSetOrder();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Query)
      return this.equals((Query)that);
    return false;
  }

  public boolean equals(Query that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_create_time_start = true && this.isSetCreate_time_start();
    boolean that_present_create_time_start = true && that.isSetCreate_time_start();
    if (this_present_create_time_start || that_present_create_time_start) {
      if (!(this_present_create_time_start && that_present_create_time_start))
        return false;
      if (!this.create_time_start.equals(that.create_time_start))
        return false;
    }

    boolean this_present_create_time_end = true && this.isSetCreate_time_end();
    boolean that_present_create_time_end = true && that.isSetCreate_time_end();
    if (this_present_create_time_end || that_present_create_time_end) {
      if (!(this_present_create_time_end && that_present_create_time_end))
        return false;
      if (!this.create_time_end.equals(that.create_time_end))
        return false;
    }

    boolean this_present_update_time_start = true && this.isSetUpdate_time_start();
    boolean that_present_update_time_start = true && that.isSetUpdate_time_start();
    if (this_present_update_time_start || that_present_update_time_start) {
      if (!(this_present_update_time_start && that_present_update_time_start))
        return false;
      if (!this.update_time_start.equals(that.update_time_start))
        return false;
    }

    boolean this_present_update_time_end = true && this.isSetUpdate_time_end();
    boolean that_present_update_time_end = true && that.isSetUpdate_time_end();
    if (this_present_update_time_end || that_present_update_time_end) {
      if (!(this_present_update_time_end && that_present_update_time_end))
        return false;
      if (!this.update_time_end.equals(that.update_time_end))
        return false;
    }

    boolean this_present_page = true;
    boolean that_present_page = true;
    if (this_present_page || that_present_page) {
      if (!(this_present_page && that_present_page))
        return false;
      if (this.page != that.page)
        return false;
    }

    boolean this_present_limit = true;
    boolean that_present_limit = true;
    if (this_present_limit || that_present_limit) {
      if (!(this_present_limit && that_present_limit))
        return false;
      if (this.limit != that.limit)
        return false;
    }

    boolean this_present_sort_by = true && this.isSetSort_by();
    boolean that_present_sort_by = true && that.isSetSort_by();
    if (this_present_sort_by || that_present_sort_by) {
      if (!(this_present_sort_by && that_present_sort_by))
        return false;
      if (!this.sort_by.equals(that.sort_by))
        return false;
    }

    boolean this_present_order = true && this.isSetOrder();
    boolean that_present_order = true && that.isSetOrder();
    if (this_present_order || that_present_order) {
      if (!(this_present_order && that_present_order))
        return false;
      if (!this.order.equals(that.order))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetCreate_time_start()) ? 131071 : 524287);
    if (isSetCreate_time_start())
      hashCode = hashCode * 8191 + create_time_start.hashCode();

    hashCode = hashCode * 8191 + ((isSetCreate_time_end()) ? 131071 : 524287);
    if (isSetCreate_time_end())
      hashCode = hashCode * 8191 + create_time_end.hashCode();

    hashCode = hashCode * 8191 + ((isSetUpdate_time_start()) ? 131071 : 524287);
    if (isSetUpdate_time_start())
      hashCode = hashCode * 8191 + update_time_start.hashCode();

    hashCode = hashCode * 8191 + ((isSetUpdate_time_end()) ? 131071 : 524287);
    if (isSetUpdate_time_end())
      hashCode = hashCode * 8191 + update_time_end.hashCode();

    hashCode = hashCode * 8191 + page;

    hashCode = hashCode * 8191 + limit;

    hashCode = hashCode * 8191 + ((isSetSort_by()) ? 131071 : 524287);
    if (isSetSort_by())
      hashCode = hashCode * 8191 + sort_by.hashCode();

    hashCode = hashCode * 8191 + ((isSetOrder()) ? 131071 : 524287);
    if (isSetOrder())
      hashCode = hashCode * 8191 + order.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Query other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetCreate_time_start()).compareTo(other.isSetCreate_time_start());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCreate_time_start()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.create_time_start, other.create_time_start);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCreate_time_end()).compareTo(other.isSetCreate_time_end());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCreate_time_end()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.create_time_end, other.create_time_end);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUpdate_time_start()).compareTo(other.isSetUpdate_time_start());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUpdate_time_start()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.update_time_start, other.update_time_start);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUpdate_time_end()).compareTo(other.isSetUpdate_time_end());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUpdate_time_end()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.update_time_end, other.update_time_end);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPage()).compareTo(other.isSetPage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.page, other.page);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLimit()).compareTo(other.isSetLimit());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLimit()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.limit, other.limit);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSort_by()).compareTo(other.isSetSort_by());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSort_by()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sort_by, other.sort_by);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetOrder()).compareTo(other.isSetOrder());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOrder()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.order, other.order);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Query(");
    boolean first = true;

    sb.append("create_time_start:");
    if (this.create_time_start == null) {
      sb.append("null");
    } else {
      sb.append(this.create_time_start);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("create_time_end:");
    if (this.create_time_end == null) {
      sb.append("null");
    } else {
      sb.append(this.create_time_end);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("update_time_start:");
    if (this.update_time_start == null) {
      sb.append("null");
    } else {
      sb.append(this.update_time_start);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("update_time_end:");
    if (this.update_time_end == null) {
      sb.append("null");
    } else {
      sb.append(this.update_time_end);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("page:");
    sb.append(this.page);
    first = false;
    if (!first) sb.append(", ");
    sb.append("limit:");
    sb.append(this.limit);
    first = false;
    if (!first) sb.append(", ");
    sb.append("sort_by:");
    if (this.sort_by == null) {
      sb.append("null");
    } else {
      sb.append(this.sort_by);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("order:");
    if (this.order == null) {
      sb.append("null");
    } else {
      sb.append(this.order);
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

  private static class QueryStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public QueryStandardScheme getScheme() {
      return new QueryStandardScheme();
    }
  }

  private static class QueryStandardScheme extends org.apache.thrift.scheme.StandardScheme<Query> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Query struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CREATE_TIME_START
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.create_time_start = iprot.readString();
              struct.setCreate_time_startIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CREATE_TIME_END
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.create_time_end = iprot.readString();
              struct.setCreate_time_endIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // UPDATE_TIME_START
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.update_time_start = iprot.readString();
              struct.setUpdate_time_startIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // UPDATE_TIME_END
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.update_time_end = iprot.readString();
              struct.setUpdate_time_endIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // PAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.page = iprot.readI32();
              struct.setPageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // LIMIT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.limit = iprot.readI32();
              struct.setLimitIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // SORT_BY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.sort_by = iprot.readString();
              struct.setSort_byIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // ORDER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.order = iprot.readString();
              struct.setOrderIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Query struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.create_time_start != null) {
        oprot.writeFieldBegin(CREATE_TIME_START_FIELD_DESC);
        oprot.writeString(struct.create_time_start);
        oprot.writeFieldEnd();
      }
      if (struct.create_time_end != null) {
        oprot.writeFieldBegin(CREATE_TIME_END_FIELD_DESC);
        oprot.writeString(struct.create_time_end);
        oprot.writeFieldEnd();
      }
      if (struct.update_time_start != null) {
        oprot.writeFieldBegin(UPDATE_TIME_START_FIELD_DESC);
        oprot.writeString(struct.update_time_start);
        oprot.writeFieldEnd();
      }
      if (struct.update_time_end != null) {
        oprot.writeFieldBegin(UPDATE_TIME_END_FIELD_DESC);
        oprot.writeString(struct.update_time_end);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PAGE_FIELD_DESC);
      oprot.writeI32(struct.page);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LIMIT_FIELD_DESC);
      oprot.writeI32(struct.limit);
      oprot.writeFieldEnd();
      if (struct.sort_by != null) {
        oprot.writeFieldBegin(SORT_BY_FIELD_DESC);
        oprot.writeString(struct.sort_by);
        oprot.writeFieldEnd();
      }
      if (struct.order != null) {
        oprot.writeFieldBegin(ORDER_FIELD_DESC);
        oprot.writeString(struct.order);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class QueryTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public QueryTupleScheme getScheme() {
      return new QueryTupleScheme();
    }
  }

  private static class QueryTupleScheme extends org.apache.thrift.scheme.TupleScheme<Query> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Query struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetCreate_time_start()) {
        optionals.set(0);
      }
      if (struct.isSetCreate_time_end()) {
        optionals.set(1);
      }
      if (struct.isSetUpdate_time_start()) {
        optionals.set(2);
      }
      if (struct.isSetUpdate_time_end()) {
        optionals.set(3);
      }
      if (struct.isSetPage()) {
        optionals.set(4);
      }
      if (struct.isSetLimit()) {
        optionals.set(5);
      }
      if (struct.isSetSort_by()) {
        optionals.set(6);
      }
      if (struct.isSetOrder()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetCreate_time_start()) {
        oprot.writeString(struct.create_time_start);
      }
      if (struct.isSetCreate_time_end()) {
        oprot.writeString(struct.create_time_end);
      }
      if (struct.isSetUpdate_time_start()) {
        oprot.writeString(struct.update_time_start);
      }
      if (struct.isSetUpdate_time_end()) {
        oprot.writeString(struct.update_time_end);
      }
      if (struct.isSetPage()) {
        oprot.writeI32(struct.page);
      }
      if (struct.isSetLimit()) {
        oprot.writeI32(struct.limit);
      }
      if (struct.isSetSort_by()) {
        oprot.writeString(struct.sort_by);
      }
      if (struct.isSetOrder()) {
        oprot.writeString(struct.order);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Query struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.create_time_start = iprot.readString();
        struct.setCreate_time_startIsSet(true);
      }
      if (incoming.get(1)) {
        struct.create_time_end = iprot.readString();
        struct.setCreate_time_endIsSet(true);
      }
      if (incoming.get(2)) {
        struct.update_time_start = iprot.readString();
        struct.setUpdate_time_startIsSet(true);
      }
      if (incoming.get(3)) {
        struct.update_time_end = iprot.readString();
        struct.setUpdate_time_endIsSet(true);
      }
      if (incoming.get(4)) {
        struct.page = iprot.readI32();
        struct.setPageIsSet(true);
      }
      if (incoming.get(5)) {
        struct.limit = iprot.readI32();
        struct.setLimitIsSet(true);
      }
      if (incoming.get(6)) {
        struct.sort_by = iprot.readString();
        struct.setSort_byIsSet(true);
      }
      if (incoming.get(7)) {
        struct.order = iprot.readString();
        struct.setOrderIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

