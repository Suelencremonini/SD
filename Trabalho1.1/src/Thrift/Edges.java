/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package Thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
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
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2017-06-27")
public class Edges implements org.apache.thrift.TBase<Edges, Edges._Fields>, java.io.Serializable, Cloneable, Comparable<Edges> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Edges");

  private static final org.apache.thrift.protocol.TField V1ID_FIELD_DESC = new org.apache.thrift.protocol.TField("V1id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField V2ID_FIELD_DESC = new org.apache.thrift.protocol.TField("V2id", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField WEIGHT_FIELD_DESC = new org.apache.thrift.protocol.TField("Weight", org.apache.thrift.protocol.TType.DOUBLE, (short)3);
  private static final org.apache.thrift.protocol.TField FLAG_FIELD_DESC = new org.apache.thrift.protocol.TField("flag", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField DESCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("description", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new EdgesStandardSchemeFactory());
    schemes.put(TupleScheme.class, new EdgesTupleSchemeFactory());
  }

  public long V1id; // required
  public long V2id; // required
  public double Weight; // required
  public int flag; // required
  public String description; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    V1ID((short)1, "V1id"),
    V2ID((short)2, "V2id"),
    WEIGHT((short)3, "Weight"),
    FLAG((short)4, "flag"),
    DESCRIPTION((short)5, "description");

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
        case 1: // V1ID
          return V1ID;
        case 2: // V2ID
          return V2ID;
        case 3: // WEIGHT
          return WEIGHT;
        case 4: // FLAG
          return FLAG;
        case 5: // DESCRIPTION
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
  private static final int __V1ID_ISSET_ID = 0;
  private static final int __V2ID_ISSET_ID = 1;
  private static final int __WEIGHT_ISSET_ID = 2;
  private static final int __FLAG_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.V1ID, new org.apache.thrift.meta_data.FieldMetaData("V1id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.V2ID, new org.apache.thrift.meta_data.FieldMetaData("V2id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.WEIGHT, new org.apache.thrift.meta_data.FieldMetaData("Weight", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.FLAG, new org.apache.thrift.meta_data.FieldMetaData("flag", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.DESCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("description", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Edges.class, metaDataMap);
  }

  public Edges() {
  }

  public Edges(
    long V1id,
    long V2id,
    double Weight,
    int flag,
    String description)
  {
    this();
    this.V1id = V1id;
    setV1idIsSet(true);
    this.V2id = V2id;
    setV2idIsSet(true);
    this.Weight = Weight;
    setWeightIsSet(true);
    this.flag = flag;
    setFlagIsSet(true);
    this.description = description;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Edges(Edges other) {
    __isset_bitfield = other.__isset_bitfield;
    this.V1id = other.V1id;
    this.V2id = other.V2id;
    this.Weight = other.Weight;
    this.flag = other.flag;
    if (other.isSetDescription()) {
      this.description = other.description;
    }
  }

  public Edges deepCopy() {
    return new Edges(this);
  }

  @Override
  public void clear() {
    setV1idIsSet(false);
    this.V1id = 0;
    setV2idIsSet(false);
    this.V2id = 0;
    setWeightIsSet(false);
    this.Weight = 0.0;
    setFlagIsSet(false);
    this.flag = 0;
    this.description = null;
  }

  public long getV1id() {
    return this.V1id;
  }

  public Edges setV1id(long V1id) {
    this.V1id = V1id;
    setV1idIsSet(true);
    return this;
  }

  public void unsetV1id() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __V1ID_ISSET_ID);
  }

  /** Returns true if field V1id is set (has been assigned a value) and false otherwise */
  public boolean isSetV1id() {
    return EncodingUtils.testBit(__isset_bitfield, __V1ID_ISSET_ID);
  }

  public void setV1idIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __V1ID_ISSET_ID, value);
  }

  public long getV2id() {
    return this.V2id;
  }

  public Edges setV2id(long V2id) {
    this.V2id = V2id;
    setV2idIsSet(true);
    return this;
  }

  public void unsetV2id() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __V2ID_ISSET_ID);
  }

  /** Returns true if field V2id is set (has been assigned a value) and false otherwise */
  public boolean isSetV2id() {
    return EncodingUtils.testBit(__isset_bitfield, __V2ID_ISSET_ID);
  }

  public void setV2idIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __V2ID_ISSET_ID, value);
  }

  public double getWeight() {
    return this.Weight;
  }

  public Edges setWeight(double Weight) {
    this.Weight = Weight;
    setWeightIsSet(true);
    return this;
  }

  public void unsetWeight() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __WEIGHT_ISSET_ID);
  }

  /** Returns true if field Weight is set (has been assigned a value) and false otherwise */
  public boolean isSetWeight() {
    return EncodingUtils.testBit(__isset_bitfield, __WEIGHT_ISSET_ID);
  }

  public void setWeightIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __WEIGHT_ISSET_ID, value);
  }

  public int getFlag() {
    return this.flag;
  }

  public Edges setFlag(int flag) {
    this.flag = flag;
    setFlagIsSet(true);
    return this;
  }

  public void unsetFlag() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __FLAG_ISSET_ID);
  }

  /** Returns true if field flag is set (has been assigned a value) and false otherwise */
  public boolean isSetFlag() {
    return EncodingUtils.testBit(__isset_bitfield, __FLAG_ISSET_ID);
  }

  public void setFlagIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __FLAG_ISSET_ID, value);
  }

  public String getDescription() {
    return this.description;
  }

  public Edges setDescription(String description) {
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
    case V1ID:
      if (value == null) {
        unsetV1id();
      } else {
        setV1id((Long)value);
      }
      break;

    case V2ID:
      if (value == null) {
        unsetV2id();
      } else {
        setV2id((Long)value);
      }
      break;

    case WEIGHT:
      if (value == null) {
        unsetWeight();
      } else {
        setWeight((Double)value);
      }
      break;

    case FLAG:
      if (value == null) {
        unsetFlag();
      } else {
        setFlag((Integer)value);
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
    case V1ID:
      return getV1id();

    case V2ID:
      return getV2id();

    case WEIGHT:
      return getWeight();

    case FLAG:
      return getFlag();

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
    case V1ID:
      return isSetV1id();
    case V2ID:
      return isSetV2id();
    case WEIGHT:
      return isSetWeight();
    case FLAG:
      return isSetFlag();
    case DESCRIPTION:
      return isSetDescription();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Edges)
      return this.equals((Edges)that);
    return false;
  }

  public boolean equals(Edges that) {
    if (that == null)
      return false;

    boolean this_present_V1id = true;
    boolean that_present_V1id = true;
    if (this_present_V1id || that_present_V1id) {
      if (!(this_present_V1id && that_present_V1id))
        return false;
      if (this.V1id != that.V1id)
        return false;
    }

    boolean this_present_V2id = true;
    boolean that_present_V2id = true;
    if (this_present_V2id || that_present_V2id) {
      if (!(this_present_V2id && that_present_V2id))
        return false;
      if (this.V2id != that.V2id)
        return false;
    }

    boolean this_present_Weight = true;
    boolean that_present_Weight = true;
    if (this_present_Weight || that_present_Weight) {
      if (!(this_present_Weight && that_present_Weight))
        return false;
      if (this.Weight != that.Weight)
        return false;
    }

    boolean this_present_flag = true;
    boolean that_present_flag = true;
    if (this_present_flag || that_present_flag) {
      if (!(this_present_flag && that_present_flag))
        return false;
      if (this.flag != that.flag)
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
    List<Object> list = new ArrayList<Object>();

    boolean present_V1id = true;
    list.add(present_V1id);
    if (present_V1id)
      list.add(V1id);

    boolean present_V2id = true;
    list.add(present_V2id);
    if (present_V2id)
      list.add(V2id);

    boolean present_Weight = true;
    list.add(present_Weight);
    if (present_Weight)
      list.add(Weight);

    boolean present_flag = true;
    list.add(present_flag);
    if (present_flag)
      list.add(flag);

    boolean present_description = true && (isSetDescription());
    list.add(present_description);
    if (present_description)
      list.add(description);

    return list.hashCode();
  }

  @Override
  public int compareTo(Edges other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetV1id()).compareTo(other.isSetV1id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetV1id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.V1id, other.V1id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetV2id()).compareTo(other.isSetV2id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetV2id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.V2id, other.V2id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWeight()).compareTo(other.isSetWeight());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWeight()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Weight, other.Weight);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFlag()).compareTo(other.isSetFlag());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFlag()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.flag, other.flag);
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
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Edges(");
    boolean first = true;

    sb.append("V1id:");
    sb.append(this.V1id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("V2id:");
    sb.append(this.V2id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("Weight:");
    sb.append(this.Weight);
    first = false;
    if (!first) sb.append(", ");
    sb.append("flag:");
    sb.append(this.flag);
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

  private static class EdgesStandardSchemeFactory implements SchemeFactory {
    public EdgesStandardScheme getScheme() {
      return new EdgesStandardScheme();
    }
  }

  private static class EdgesStandardScheme extends StandardScheme<Edges> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Edges struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // V1ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.V1id = iprot.readI64();
              struct.setV1idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // V2ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.V2id = iprot.readI64();
              struct.setV2idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // WEIGHT
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.Weight = iprot.readDouble();
              struct.setWeightIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // FLAG
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.flag = iprot.readI32();
              struct.setFlagIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // DESCRIPTION
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Edges struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(V1ID_FIELD_DESC);
      oprot.writeI64(struct.V1id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(V2ID_FIELD_DESC);
      oprot.writeI64(struct.V2id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(WEIGHT_FIELD_DESC);
      oprot.writeDouble(struct.Weight);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(FLAG_FIELD_DESC);
      oprot.writeI32(struct.flag);
      oprot.writeFieldEnd();
      if (struct.description != null) {
        oprot.writeFieldBegin(DESCRIPTION_FIELD_DESC);
        oprot.writeString(struct.description);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class EdgesTupleSchemeFactory implements SchemeFactory {
    public EdgesTupleScheme getScheme() {
      return new EdgesTupleScheme();
    }
  }

  private static class EdgesTupleScheme extends TupleScheme<Edges> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Edges struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetV1id()) {
        optionals.set(0);
      }
      if (struct.isSetV2id()) {
        optionals.set(1);
      }
      if (struct.isSetWeight()) {
        optionals.set(2);
      }
      if (struct.isSetFlag()) {
        optionals.set(3);
      }
      if (struct.isSetDescription()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetV1id()) {
        oprot.writeI64(struct.V1id);
      }
      if (struct.isSetV2id()) {
        oprot.writeI64(struct.V2id);
      }
      if (struct.isSetWeight()) {
        oprot.writeDouble(struct.Weight);
      }
      if (struct.isSetFlag()) {
        oprot.writeI32(struct.flag);
      }
      if (struct.isSetDescription()) {
        oprot.writeString(struct.description);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Edges struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.V1id = iprot.readI64();
        struct.setV1idIsSet(true);
      }
      if (incoming.get(1)) {
        struct.V2id = iprot.readI64();
        struct.setV2idIsSet(true);
      }
      if (incoming.get(2)) {
        struct.Weight = iprot.readDouble();
        struct.setWeightIsSet(true);
      }
      if (incoming.get(3)) {
        struct.flag = iprot.readI32();
        struct.setFlagIsSet(true);
      }
      if (incoming.get(4)) {
        struct.description = iprot.readString();
        struct.setDescriptionIsSet(true);
      }
    }
  }

}

