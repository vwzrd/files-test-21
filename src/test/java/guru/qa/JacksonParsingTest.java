package guru.qa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.CatModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class JacksonParsingTest {

    private ClassLoader cl = JacksonParsingTest.class.getClassLoader();

    @Test
    @DisplayName("Проверка объекта в JSON-файле")
    void jsonTest() throws Exception {

        try (InputStream stream = cl.getResourceAsStream("jsontest.json");
             Reader reader = new InputStreamReader(stream)) {
            ObjectMapper objectMapper = new ObjectMapper();
            CatModel catModel = objectMapper.readValue(reader, CatModel.class);

            assertEquals("Barsik", catModel.getName());
            assertEquals(5, catModel.getAge());
            assertEquals("Vasilii", catModel.getOwner());
            assertEquals("Mixed", catModel.getBreed());
            assertEquals("fish", catModel.getFood(0));
            assertEquals("whiskas", catModel.getFood(1));
            assertEquals("milk", catModel.getFood(2));
            assertTrue(catModel.isLoved());
        }
    }
}
