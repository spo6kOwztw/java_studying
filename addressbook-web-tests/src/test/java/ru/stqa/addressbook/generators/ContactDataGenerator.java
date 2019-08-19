package ru.stqa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact Count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;\n", contact.getFirstName(), contact.getLastName(), contact.getMiddleName(), contact.getMobilePhone(), contact.getHomePhone(), contact.getWorkPhone(), contact.getEmail1(), contact.getAddress()));        }
        writer.close();


    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstName(String.format("Bill %s", i))
                    .withLastName(String.format("Burroughs %s", i))
                    .withMiddleName(String.format("Seward %s", i))
                    .withMobilePhone(String.format("999 %s", i))
                    .withHomePhone(String.format("343333 %s", i))
                    .withWorkPhone(String.format("342222 %s", i))
                    .withAddress(String.format("in the middle of nowhere %s",i))
                    .withGroup("[none]"));
        }
        return contacts;
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

}
