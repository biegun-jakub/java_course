package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public GroupData getGroup(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData result = (GroupData) session.createQuery("from GroupData where id=" + id).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public ContactData getContact(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ContactData result = (ContactData) session.createQuery("from ContactData where id=" + id).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public Groups getGroupsFromContact(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from ContactData where group_id=" + id).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public GroupData getGroupsFromContactWithMaxId() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData result = (GroupData) session.createQuery
                ("from GroupData where id=(select max(group1.id) from GroupData group1)").getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public GroupData chosenGroup(Groups groups, ContactData contact) {
        Groups chosenGroups = contact.getGroups();
        for (GroupData group : groups) {
            if (chosenGroups.contains(group)) {
                continue;
            } else {
                return group;
            }
        }
        return null;
    }

    public GroupData chosenGroupForRemovingContact() {
        Groups groups = groups();
        for (GroupData group : groups) {
            if (group.getContacts().size() > 0) {
                return group;
            }
        }
        return null;
    }
}
