package de.gammadata.microservices.addressrs.application.control;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author gfr
 */
public class JacksonZuluDateSerializerTest {

  DateFormat zuluDateFormat;

  /**
   *
   */
  @Before
  public void setUp() {
    zuluDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.GERMANY);
    zuluDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  /**
   * Test of serialize method, of class JacksonZuluDateSerializer.
   */
  @Test
  public void testSerialize_Date() {
    System.out.println("serialize");
    Date date = new Date();
    String expResult = zuluDateFormat.format(date);
    String result = JacksonZuluDateSerializer.serialize(date);
    assertEquals(expResult, result);
  }



  /**
   * Test of desializeDateString method, of class JacksonZuluDateSerializer.
   * @throws java.lang.Exception
   */
  @Test
  public void testDesializeDateString() throws Exception {
    System.out.println("desializeDateString");
    Date expResult = new Date();
    String dateString = zuluDateFormat.format(expResult);
    Date result = JacksonZuluDateSerializer.desializeDateString(dateString);
    assertEquals(expResult, result);
  }
  
    /**
   * Test of serialize method, of class JacksonZuluDateSerializer.
   * @throws java.lang.Exception
   */
  @Test
  public void testSerialize_3args() throws Exception {
    System.out.println("serialize");
    Date date = new Date();
    String expResult = zuluDateFormat.format(date);
    SerializerProvider sp = null;
    MyJsonGenerator jg = new MyJsonGenerator();
    JacksonZuluDateSerializer instance = new JacksonZuluDateSerializer();
    instance.serialize(date, jg, sp);
    assertEquals(expResult, jg.getResult());

  }
  
public static class MyJsonGenerator extends JsonGenerator{
  
  String result;

    public String getResult() {
      return result;
    }

    @Override
    public JsonGenerator setCodec(ObjectCodec oc) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObjectCodec getCodec() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Version version() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonGenerator enable(Feature ftr) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonGenerator disable(Feature ftr) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnabled(Feature ftr) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getFeatureMask() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonGenerator setFeatureMask(int i) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonGenerator useDefaultPrettyPrinter() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeStartArray() throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeEndArray() throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeStartObject() throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeEndObject() throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeFieldName(String string) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeFieldName(SerializableString ss) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeString(String string) throws IOException {
      result = string;
    }

    @Override
    public void writeString(char[] chars, int i, int i1) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeString(SerializableString ss) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeRawUTF8String(byte[] bytes, int i, int i1) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeUTF8String(byte[] bytes, int i, int i1) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeRaw(String string) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeRaw(String string, int i, int i1) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeRaw(char[] chars, int i, int i1) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeRaw(char c) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeRawValue(String string) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeRawValue(String string, int i, int i1) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeRawValue(char[] chars, int i, int i1) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeBinary(Base64Variant bv, byte[] bytes, int i, int i1) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int writeBinary(Base64Variant bv, InputStream in, int i) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeNumber(int i) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeNumber(long l) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeNumber(BigInteger bi) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeNumber(double d) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeNumber(float f) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeNumber(BigDecimal bd) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeNumber(String string) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeBoolean(boolean bln) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeNull() throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeObject(Object o) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeTree(TreeNode tn) throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonStreamContext getOutputContext() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void flush() throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isClosed() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws IOException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
}
