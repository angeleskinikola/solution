import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Grouper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GrouperTest {

  private List<Map<Object, Object>> inputData;

  @BeforeEach
  void setUp() throws IOException {
    inputData = (List<Map<Object, Object>>) readTestData("inputData");
  }

  @Test
  public void testGroupByCurrency() throws IOException {
    List<String> keys = new ArrayList<>();
    keys.add("currency");
    Object result = Grouper.groupByKeys(inputData, keys);

    Object expected = readTestData("expectedGroupByCurrency");

    assert expected.equals(result);
  }

  @Test
  public void testGroupByMultipleKeys() throws IOException {
    List<Map<Object, Object>> inputData = (List<Map<Object, Object>>) readTestData("inputData");
    List<String> keys = new ArrayList<>();
    keys.add("currency");
    keys.add("country");
    keys.add("city");
    Object result = Grouper.groupByKeys(inputData, keys);

    Object expected = readTestData("expectedGroupByMultipleKeys");

    assert expected.equals(result);
  }

  private Object readTestData(String file) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(this.getClass().getResource(file + ".json"), Object.class);

  }
}
