package ru.alphalab.testtask.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ilya Avkhimenya
 */
public class Test {

    record Patient(String name, String uuid) implements NamedObject {
    }

    public static void main(String[] args) {
        Patient patient1 = new Patient("Генадий", UUID.randomUUID().toString());
        Patient patient2 = new Patient("Генадий", UUID.randomUUID().toString());
        Patient patient3 = new Patient("Аркадий", UUID.randomUUID().toString());
        Patient patient4 = new Patient("Генадий", UUID.randomUUID().toString());

        List<NamedObject> namedObjects = new ArrayList<>();
        namedObjects.add(patient1);
        namedObjects.add(patient2);
        namedObjects.add(patient3);
        namedObjects.add(patient4);

        Map<String, List<NamedObject>> groupByName = Grouper.groupByName(namedObjects);
        System.out.println(groupByName);
    }
}
