/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.lhc.commons.web.property;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import static java.lang.Double.parseDouble;

public final class JsonConversions {

    private JsonConversions() {
        /* Only static methods */
    }

    // @formatter:off
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeSpecialFloatingPointValues()
            .create();
    // @formatter:on

    public static final Gson gson() {
        return GSON;
    }

    @SuppressWarnings("unchecked")
    public static <T> T defaultDeserialization(String setValue, Class<T> clazz) {
        if (clazz == Double.class) {
            return (T) (Double) parseDouble(setValue);
        } else if (clazz == String.class) {
            return (T) setValue;
        } else {
            return gson().fromJson(setValue, clazz);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T defaultDeserialization(String setValue, Type type) {
        if (type.getTypeName().equals(Double.class.getTypeName())) {
            return (T) (Double) parseDouble(setValue);
        } else if (type.getTypeName().equals(String.class.getTypeName())) {
            return (T) setValue;
        } else {
            return gson().fromJson(setValue, type);
        }
    }

    public static String defaultSerialization(Object value) {
        if (value instanceof Double) {
            return value.toString();
        } else if (value instanceof String) {
            return (String) value;
        } else {
            return gson().toJson(value);
        }
    }
}
