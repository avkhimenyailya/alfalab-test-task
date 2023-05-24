package ru.alphalab.testtask.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.alphalab.testtask.hibernate.models.Document;
import ru.alphalab.testtask.hibernate.models.Person;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ilya Avkhimenya
 */
public class Main {
    private static final Session session
            = HibernateUtil.getSessionFactory().openSession();

    public static void main(String[] args) {
        // filling tables in database
        fillDBTestValues();

        // search for documents and their grouping
        List<Document> documents = findDocumentsByDocumentNumberWith("777");
        Map<Person, List<Document>> groups = documents.stream()
                .collect(Collectors.groupingBy(Document::getPerson));

        // console output
        groups.keySet().forEach(person -> {
            List<Document> docsByPerson = groups.get(person);
            String documentNumbersString = docsByPerson
                    .stream()
                    .map(Document::getDocumentNumber)
                    .map(String::valueOf)
                    .collect(Collectors.joining(",\n"));
            System.out.println(String.format("Персона: %s %s\nНайденые документы:\n%s\n\n",
                    person.getFirstName(), person.getLastName(), documentNumbersString));
        });

        session.close();
    }

    private static void fillDBTestValues() {
        Transaction transaction = session.beginTransaction();

        Person person1 = new Person();
        person1.setLastName("Иванов");
        person1.setFirstName("Никита");
        person1.setSecondName("Сергеевич");
        person1.setDateOfBirth(createDateOfBirth(15, 9, 1998));
        session.save(person1);

        Person person2 = new Person();
        person2.setLastName("Кафка");
        person2.setFirstName("Франц");
        person2.setDateOfBirth(createDateOfBirth(3, 7, 1883));
        session.save(person2);

        // will create 1000 documents with a random serial number
        for (int i = 0; i < 1000; i++) {
            Document document = new Document();
            document.setName(String.format("Документ %s", i + 1));
            document.setDocumentNumber(createDocumentNumber());
            document.setPerson(Math.random() < 0.5 ? person1 : person2);
            document.setActive(Math.random() < 0.5);
            session.save(document);
        }

        transaction.commit();
    }

    private static List<Document> findDocumentsByDocumentNumberWith(String substring) {
        String hql = "FROM Document d WHERE d.active = true " +
                "AND CAST(d.documentNumber AS string) LIKE '%" + substring + "%'";
        @SuppressWarnings("unchecked")
        Query<Document> query = session.createQuery(hql);
        return query.list();
    }

    private static Date createDateOfBirth(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    // with a probability of 50% the number will start with 777
    private static long createDocumentNumber() {
        boolean will777 = Math.random() < 0.5;
        int randomNumber = (int) (Math.random() * 1000);
        if (will777) {
            randomNumber += 777000;
        } else {
            randomNumber += (int) (Math.random() * 900000) + 100000;
        }
        return randomNumber;
    }
}
