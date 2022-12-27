package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grouper {

  public static Object groupByKeys(List<Map<Object, Object>> inputList, List<String> keys) {
    List<String> tempKeys = new ArrayList<>(keys);

    if (tempKeys.isEmpty()) {
      return inputList;
    }

    String key = tempKeys.get(0);
    tempKeys.remove(0);
    Map<Object, Object> finalMap = new HashMap<>();

    inputList.forEach(item -> {
      if (finalMap.containsKey(item.get(key))) {
        Map<Object, Object> keyToValue = new HashMap<>();
        item.forEach((k, v) -> {
          if (!k.equals(key)) {
            keyToValue.put(k, v);
          }
        });

        ((List) finalMap.get(item.get(key))).add(keyToValue);
      } else {
        List<Map<Object, Object>> itemsList = new ArrayList<>();
        Map<Object, Object> keyToValue = new HashMap<>();
        item.forEach((k, v) -> {
          if (!k.equals(key)) {
            keyToValue.put(k, v);
          }
        });

        itemsList.add(keyToValue);
        finalMap.put(item.get(key), itemsList);
      }
    });

    if (tempKeys.isEmpty()) {
      return finalMap;
    } else {
      finalMap.keySet().forEach(k -> {
        finalMap.put(k, groupByKeys((List) finalMap.get(k), new ArrayList<>(tempKeys)));
      });
    }

    return finalMap;
  }
}
