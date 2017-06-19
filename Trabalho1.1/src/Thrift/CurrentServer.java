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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2017-06-18")
public class CurrentServer implements org.apache.thrift.TBase<CurrentServer, CurrentServer._Fields>, java.io.Serializable, Cloneable, Comparable<CurrentServer> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CurrentServer");

  private static final org.apache.thrift.protocol.TField CURRENT_IP_FIELD_DESC = new org.apache.thrift.protocol.TField("currentIp", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CURRENT_PORT_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("currentPortNumber", org.apache.thrift.protocol.TType.I32, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CurrentServerStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CurrentServerTupleSchemeFactory());
  }

  public String currentIp; // required
  public int currentPortNumber; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CURRENT_IP((short)1, "currentIp"),
    CURRENT_PORT_NUMBER((short)2, "currentPortNumber");

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
        case 1: // CURRENT_IP
          return CURRENT_IP;
        case 2: // CURRENT_PORT_NUMBER
          return CURRENT_PORT_NUMBER;
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
  private static final int __CURRENTPORTNUMBER_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CURRENT_IP, new org.apache.thrift.meta_data.FieldMetaData("currentIp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CURRENT_PORT_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("currentPortNumber", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CurrentServer.class, metaDataMap);
  }

  public CurrentServer() {
  }

  public CurrentServer(
    String currentIp,
    int currentPortNumber)
  {
    this();
    this.currentIp = currentIp;
    this.currentPortNumber = currentPortNumber;
    setCurrentPortNumberIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CurrentServer(CurrentServer other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetCurrentIp()) {
      this.currentIp = other.currentIp;
    }
    this.currentPortNumber = other.currentPortNumber;
  }

  public CurrentServer deepCopy() {
    return new CurrentServer(this);
  }

  @Override
  public void clear() {
    this.currentIp = null;
    setCurrentPortNumberIsSet(false);
    this.currentPortNumber = 0;
  }

  public String getCurrentIp() {
    return this.currentIp;
  }

  public CurrentServer setCurrentIp(String currentIp) {
    this.currentIp = currentIp;
    return this;
  }

  public void unsetCurrentIp() {
    this.currentIp = null;
  }

  /** Returns true if field currentIp is set (has been assigned a value) and false otherwise */
  public boolean isSetCurrentIp() {
    return this.currentIp != null;
  }

  public void setCurrentIpIsSet(boolean value) {
    if (!value) {
      this.currentIp = null;
    }
  }

  public int getCurrentPortNumber() {
    return this.currentPortNumber;
  }

  public CurrentServer setCurrentPortNumber(int currentPortNumber) {
    this.currentPortNumber = currentPortNumber;
    setCurrentPortNumberIsSet(true);
    return this;
  }

  public void unsetCurrentPortNumber() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CURRENTPORTNUMBER_ISSET_ID);
  }

  /** Returns true if field currentPortNumber is set (has been assigned a value) and false otherwise */
  public boolean isSetCurrentPortNumber() {
    return EncodingUtils.testBit(__isset_bitfield, __CURRENTPORTNUMBER_ISSET_ID);
  }

  public void setCurrentPortNumberIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CURRENTPORTNUMBER_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CURRENT_IP:
      if (value == null) {
        unsetCurrentIp();
      } else {
        setCurrentIp((String)value);
      }
      break;

    case CURRENT_PORT_NUMBER:
      if (value == null) {
        unsetCurrentPortNumber();
      } else {
        setCurrentPortNumber((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CURRENT_IP:
      return getCurrentIp();

    case CURRENT_PORT_NUMBER:
      return getCurrentPortNumber();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CURRENT_IP:
      return isSetCurrentIp();
    case CURRENT_PORT_NUMBER:
      return isSetCurrentPortNumber();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CurrentServer)
      return this.equals((CurrentServer)that);
    return false;
  }

  public boolean equals(CurrentServer that) {
    if (that == null)
      return false;

    boolean this_present_currentIp = true && this.isSetCurrentIp();
    boolean that_present_currentIp = true && that.isSetCurrentIp();
    if (this_present_currentIp || that_present_currentIp) {
      if (!(this_present_currentIp && that_present_currentIp))
        return false;
      if (!this.currentIp.equals(that.currentIp))
        return false;
    }

    boolean this_present_currentPortNumber = true;
    boolean that_present_currentPortNumber = true;
    if (this_present_currentPortNumber || that_present_currentPortNumber) {
      if (!(this_present_currentPortNumber && that_present_currentPortNumber))
        return false;
      if (this.currentPortNumber != that.currentPortNumber)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_currentIp = true && (isSetCurrentIp());
    list.add(present_currentIp);
    if (present_currentIp)
      list.add(currentIp);

    boolean present_currentPortNumber = true;
    list.add(present_currentPortNumber);
    if (present_currentPortNumber)
      list.add(currentPortNumber);

    return list.hashCode();
  }

  @Override
  public int compareTo(CurrentServer other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCurrentIp()).compareTo(other.isSetCurrentIp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCurrentIp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.currentIp, other.currentIp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCurrentPortNumber()).compareTo(other.isSetCurrentPortNumber());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCurrentPortNumber()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.currentPortNumber, other.currentPortNumber);
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
    StringBuilder sb = new StringBuilder("CurrentServer(");
    boolean first = true;

    sb.append("currentIp:");
    if (this.currentIp == null) {
      sb.append("null");
    } else {
      sb.append(this.currentIp);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("currentPortNumber:");
    sb.append(this.currentPortNumber);
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

  private static class CurrentServerStandardSchemeFactory implements SchemeFactory {
    public CurrentServerStandardScheme getScheme() {
      return new CurrentServerStandardScheme();
    }
  }

  private static class CurrentServerStandardScheme extends StandardScheme<CurrentServer> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CurrentServer struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CURRENT_IP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.currentIp = iprot.readString();
              struct.setCurrentIpIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CURRENT_PORT_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.currentPortNumber = iprot.readI32();
              struct.setCurrentPortNumberIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CurrentServer struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.currentIp != null) {
        oprot.writeFieldBegin(CURRENT_IP_FIELD_DESC);
        oprot.writeString(struct.currentIp);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(CURRENT_PORT_NUMBER_FIELD_DESC);
      oprot.writeI32(struct.currentPortNumber);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CurrentServerTupleSchemeFactory implements SchemeFactory {
    public CurrentServerTupleScheme getScheme() {
      return new CurrentServerTupleScheme();
    }
  }

  private static class CurrentServerTupleScheme extends TupleScheme<CurrentServer> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CurrentServer struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCurrentIp()) {
        optionals.set(0);
      }
      if (struct.isSetCurrentPortNumber()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetCurrentIp()) {
        oprot.writeString(struct.currentIp);
      }
      if (struct.isSetCurrentPortNumber()) {
        oprot.writeI32(struct.currentPortNumber);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CurrentServer struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.currentIp = iprot.readString();
        struct.setCurrentIpIsSet(true);
      }
      if (incoming.get(1)) {
        struct.currentPortNumber = iprot.readI32();
        struct.setCurrentPortNumberIsSet(true);
      }
    }
  }

}

