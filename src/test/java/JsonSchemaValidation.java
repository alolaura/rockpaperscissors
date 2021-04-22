import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JsonSchemaValidation {
    @Test
    public void validate() throws FileNotFoundException {
        String cwd = System.getProperty("user.dir");

        File schemaFile = new File(cwd + "\\" + "json-schema.json");
        JSONTokener schemaData = new JSONTokener(new FileInputStream(schemaFile));
        JSONObject jsonSchema = new JSONObject(schemaData);

        File jsonData = new File(cwd + "\\" + "result.json");
        JSONTokener jsonDataFile = new JSONTokener(new FileInputStream(jsonData));
        //JSONObject jsonObject = new JSONObject(jsonDataFile);
        JSONArray jsonDataArray = new JSONArray(jsonDataFile);

        Schema schemaValidator = SchemaLoader.load(jsonSchema);
        schemaValidator.validate(jsonDataArray);
    }
}
