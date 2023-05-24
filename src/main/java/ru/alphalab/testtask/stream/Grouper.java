package ru.alphalab.testtask.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ilya Avkhimenya
 */
public class Grouper {

    public static Map<String, List<NamedObject>> groupByName(List<NamedObject> objects) {
        return objects.stream()
                .collect(Collectors.groupingBy(NamedObject::name));
    }
}
