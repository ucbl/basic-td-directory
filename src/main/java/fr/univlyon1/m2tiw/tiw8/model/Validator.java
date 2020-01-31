package fr.univlyon1.m2tiw.tiw8.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Validator {
    public static void validate (String source) throws IOException, ValidationException {
        validateJson(source);
        validateSchema(source);
    }

    private static void validateJson (String source) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(source, Map.class);
    }

    private static void validateSchema (String source) throws ValidationException {
        InputStream tdSchemaStream = Validator.class.getResourceAsStream("/td-schema.json");
        JSONObject tdSchema = new JSONObject(new JSONTokener(tdSchemaStream));
        Schema schema = SchemaLoader.load(tdSchema);
        schema.validate(new JSONObject(source)); // throws a ValidationException if this object is invalid
    }
}