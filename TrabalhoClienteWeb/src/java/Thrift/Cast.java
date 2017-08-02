/**
 * Autogenerated by Thrift Compiler (0.9.0)
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

public class Cast implements org.apache.thrift.TBase<Cast, Cast._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Cast");

  private static final org.apache.thrift.protocol.TField DIRECTOR_FIELD_DESC = new org.apache.thrift.protocol.TField("director", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField PRODUCTOR_FIELD_DESC = new org.apache.thrift.protocol.TField("productor", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField MAIN_CHARACTER_FIELD_DESC = new org.apache.thrift.protocol.TField("main_character", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CastStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CastTupleSchemeFactory());
  }

  public String director; // required
  public String productor; // required
  public String main_character; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DIRECTOR((short)1, "director"),
    PRODUCTOR((short)2, "productor"),
    MAIN_CHARACTER((short)3, "main_character");

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
        case 1: // DIRECTOR
          return DIRECTOR;
        case 2: // PRODUCTOR
          return PRODUCTOR;
        case 3: // MAIN_CHARACTER
          return MAIN_CHARACTER;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DIRECTOR, new org.apache.thrift.meta_data.FieldMetaData("director", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PRODUCTOR, new org.apache.thrift.meta_data.FieldMetaData("productor", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.MAIN_CHARACTER, new org.apache.thrift.meta_data.FieldMetaData("main_character", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Cast.class, metaDataMap);
  }

  public Cast() {
  }

  public Cast(
    String director,
    String productor,
    String main_character)
  {
    this();
    this.director = director;
    this.productor = productor;
    this.main_character = main_character;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Cast(Cast other) {
    if (other.isSetDirector()) {
      this.director = other.director;
    }
    if (other.isSetProductor()) {
      this.productor = other.productor;
    }
    if (other.isSetMain_character()) {
      this.main_character = other.main_character;
    }
  }

  public Cast deepCopy() {
    return new Cast(this);
  }

  @Override
  public void clear() {
    this.director = null;
    this.productor = null;
    this.main_character = null;
  }

  public String getDirector() {
    return this.director;
  }

  public Cast setDirector(String director) {
    this.director = director;
    return this;
  }

  public void unsetDirector() {
    this.director = null;
  }

  /** Returns true if field director is set (has been assigned a value) and false otherwise */
  public boolean isSetDirector() {
    return this.director != null;
  }

  public void setDirectorIsSet(boolean value) {
    if (!value) {
      this.director = null;
    }
  }

  public String getProductor() {
    return this.productor;
  }

  public Cast setProductor(String productor) {
    this.productor = productor;
    return this;
  }

  public void unsetProductor() {
    this.productor = null;
  }

  /** Returns true if field productor is set (has been assigned a value) and false otherwise */
  public boolean isSetProductor() {
    return this.productor != null;
  }

  public void setProductorIsSet(boolean value) {
    if (!value) {
      this.productor = null;
    }
  }

  public String getMain_character() {
    return this.main_character;
  }

  public Cast setMain_character(String main_character) {
    this.main_character = main_character;
    return this;
  }

  public void unsetMain_character() {
    this.main_character = null;
  }

  /** Returns true if field main_character is set (has been assigned a value) and false otherwise */
  public boolean isSetMain_character() {
    return this.main_character != null;
  }

  public void setMain_characterIsSet(boolean value) {
    if (!value) {
      this.main_character = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DIRECTOR:
      if (value == null) {
        unsetDirector();
      } else {
        setDirector((String)value);
      }
      break;

    case PRODUCTOR:
      if (value == null) {
        unsetProductor();
      } else {
        setProductor((String)value);
      }
      break;

    case MAIN_CHARACTER:
      if (value == null) {
        unsetMain_character();
      } else {
        setMain_character((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DIRECTOR:
      return getDirector();

    case PRODUCTOR:
      return getProductor();

    case MAIN_CHARACTER:
      return getMain_character();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DIRECTOR:
      return isSetDirector();
    case PRODUCTOR:
      return isSetProductor();
    case MAIN_CHARACTER:
      return isSetMain_character();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Cast)
      return this.equals((Cast)that);
    return false;
  }

  public boolean equals(Cast that) {
    if (that == null)
      return false;

    boolean this_present_director = true && this.isSetDirector();
    boolean that_present_director = true && that.isSetDirector();
    if (this_present_director || that_present_director) {
      if (!(this_present_director && that_present_director))
        return false;
      if (!this.director.equals(that.director))
        return false;
    }

    boolean this_present_productor = true && this.isSetProductor();
    boolean that_present_productor = true && that.isSetProductor();
    if (this_present_productor || that_present_productor) {
      if (!(this_present_productor && that_present_productor))
        return false;
      if (!this.productor.equals(that.productor))
        return false;
    }

    boolean this_present_main_character = true && this.isSetMain_character();
    boolean that_present_main_character = true && that.isSetMain_character();
    if (this_present_main_character || that_present_main_character) {
      if (!(this_present_main_character && that_present_main_character))
        return false;
      if (!this.main_character.equals(that.main_character))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(Cast other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    Cast typedOther = (Cast)other;

    lastComparison = Boolean.valueOf(isSetDirector()).compareTo(typedOther.isSetDirector());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDirector()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.director, typedOther.director);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetProductor()).compareTo(typedOther.isSetProductor());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProductor()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.productor, typedOther.productor);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMain_character()).compareTo(typedOther.isSetMain_character());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMain_character()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.main_character, typedOther.main_character);
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
    StringBuilder sb = new StringBuilder("Cast(");
    boolean first = true;

    sb.append("director:");
    if (this.director == null) {
      sb.append("null");
    } else {
      sb.append(this.director);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("productor:");
    if (this.productor == null) {
      sb.append("null");
    } else {
      sb.append(this.productor);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("main_character:");
    if (this.main_character == null) {
      sb.append("null");
    } else {
      sb.append(this.main_character);
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class CastStandardSchemeFactory implements SchemeFactory {
    public CastStandardScheme getScheme() {
      return new CastStandardScheme();
    }
  }

  private static class CastStandardScheme extends StandardScheme<Cast> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Cast struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DIRECTOR
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.director = iprot.readString();
              struct.setDirectorIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PRODUCTOR
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.productor = iprot.readString();
              struct.setProductorIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MAIN_CHARACTER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.main_character = iprot.readString();
              struct.setMain_characterIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Cast struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.director != null) {
        oprot.writeFieldBegin(DIRECTOR_FIELD_DESC);
        oprot.writeString(struct.director);
        oprot.writeFieldEnd();
      }
      if (struct.productor != null) {
        oprot.writeFieldBegin(PRODUCTOR_FIELD_DESC);
        oprot.writeString(struct.productor);
        oprot.writeFieldEnd();
      }
      if (struct.main_character != null) {
        oprot.writeFieldBegin(MAIN_CHARACTER_FIELD_DESC);
        oprot.writeString(struct.main_character);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CastTupleSchemeFactory implements SchemeFactory {
    public CastTupleScheme getScheme() {
      return new CastTupleScheme();
    }
  }

  private static class CastTupleScheme extends TupleScheme<Cast> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Cast struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetDirector()) {
        optionals.set(0);
      }
      if (struct.isSetProductor()) {
        optionals.set(1);
      }
      if (struct.isSetMain_character()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetDirector()) {
        oprot.writeString(struct.director);
      }
      if (struct.isSetProductor()) {
        oprot.writeString(struct.productor);
      }
      if (struct.isSetMain_character()) {
        oprot.writeString(struct.main_character);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Cast struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.director = iprot.readString();
        struct.setDirectorIsSet(true);
      }
      if (incoming.get(1)) {
        struct.productor = iprot.readString();
        struct.setProductorIsSet(true);
      }
      if (incoming.get(2)) {
        struct.main_character = iprot.readString();
        struct.setMain_characterIsSet(true);
      }
    }
  }

}

