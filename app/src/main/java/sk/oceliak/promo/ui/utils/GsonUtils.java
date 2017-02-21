package sk.oceliak.promo.ui.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


/**
 * Created by Juraj Oceliak{juraj.oceliak@eman.cz} on 2/21/17.
 */

/**
 * General utils class
 */
public class GsonUtils {

    public enum IntObjectAdapterFactory implements TypeAdapterFactory {
        INSTANCE; // Josh Bloch's Enum singleton pattern

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!IntObject.class.isAssignableFrom(type.getRawType())) {
                return null;
            }

            // Note: You have access to the `gson` object here; you can access other deserializers using gson.getAdapter and pass them into your constructor
            return (TypeAdapter<T>) new IntObjectAdapter();
        }

        private static class IntObjectAdapter extends TypeAdapter<IntObject> {
            public IntObject read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                }
                String input = reader.nextString();
                int value = Integer.parseInt(input);
                return new IntObject(value);
            }

            public void write(JsonWriter writer, IntObject value) throws IOException {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                String stringValue = String.valueOf(value.getValue());
                writer.value(stringValue);
            }
        }
    }

    public enum StringObjectAdapterFactory implements TypeAdapterFactory {
        INSTANCE; // Josh Bloch's Enum singleton pattern

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!StringObject.class.isAssignableFrom(type.getRawType())) {
                return null;
            }

            // Note: You have access to the `gson` object here; you can access other deserializers using gson.getAdapter and pass them into your constructor
            return (TypeAdapter<T>) new StringObjectAdapter();
        }

        private static class StringObjectAdapter extends TypeAdapter<StringObject> {
            public StringObject read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                }
                String input = reader.nextString();
                return new StringObject(input);
            }

            public void write(JsonWriter writer, StringObject value) throws IOException {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                String stringValue = String.valueOf(value.getValue());
                writer.value(stringValue);
            }
        }
    }
}
