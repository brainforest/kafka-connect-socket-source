    package org.apache.kafka.connect.converters;


import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaAndValue;
import org.apache.kafka.connect.errors.DataException;
import org.apache.kafka.connect.storage.Converter;
import org.apache.kafka.connect.storage.ConverterConfig;
import org.apache.kafka.connect.storage.ConverterType;
import org.apache.kafka.connect.storage.HeaderConverter;

import java.util.Map;

/**
 * Pass-through converter for raw byte data.
 * <p>
 * This implementation currently does nothing with the topic names or header names.
 */
public class ByteConverter implements Converter, HeaderConverter {

  //  private static final ConfigDef CONFIG_DEF = ConverterConfig.newConfigDef();


    @Override
    public ConfigDef config() {
        return (new ConfigDef()).define("converter.type", ConfigDef.Type.STRING, ConfigDef.NO_DEFAULT_VALUE, ConfigDef.ValidString.in(new String[]{ConverterType.KEY.getName(), ConverterType.VALUE.getName(), ConverterType.HEADER.getName()}), ConfigDef.Importance.LOW, "How this converter will be used.");

//        return CONFIG_DEF;
    }

    @Override
    public void configure(Map<String, ?> configs) {
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] fromConnectData(String topic, Schema schema, Object value) {

        if (value != null && !(value instanceof byte[]))
            throw new DataException("ByteArrayConverter is not compatible with objects of type " + value.getClass());

        return (byte[]) value;
    }

    @Override
    public SchemaAndValue toConnectData(String topic, byte[] value) {
        return new SchemaAndValue(Schema.OPTIONAL_BYTES_SCHEMA, value);
    }

    @Override
    public byte[] fromConnectHeader(String topic, String headerKey, Schema schema, Object value) {
        return fromConnectData(topic, schema, value);
    }

    @Override
    public SchemaAndValue toConnectHeader(String topic, String headerKey, byte[] value) {
        return toConnectData(topic, value);
    }

    @Override
    public void close() {
        // do nothing
    }
}