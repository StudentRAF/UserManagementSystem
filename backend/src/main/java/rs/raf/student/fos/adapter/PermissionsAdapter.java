package rs.raf.student.fos.adapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rs.raf.student.fos.type.Permissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PermissionsAdapter {

    private static final JavaType type = TypeFactory.defaultInstance().constructType(Permissions.class);

    public static class Serializer extends StdSerializer<Permissions> {

        protected Serializer() {
            super(type);
        }

        @Override
        public void serialize(Permissions value, JsonGenerator generator, SerializerProvider provider) throws IOException {
            String[] permissions = value.names().toArray(String[]::new);

            generator.writeArray(permissions, 0, permissions.length);
        }

    }

    public static class Deserializer extends StdDeserializer<Permissions> {

        protected Deserializer() {
            super(type);
        }

        @Override
        public Permissions deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            JsonNode rootNode = parser.getCodec().readTree(parser);

            List<String> permissions = new ArrayList<>();

            rootNode.forEach(node -> permissions.add(node.asText()));

            return Permissions.find(permissions.toArray(String[]::new));
        }

    }

}
