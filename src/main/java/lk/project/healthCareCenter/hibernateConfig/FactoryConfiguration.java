package lk.project.healthCareCenter.hibernateConfig;

import lk.project.healthCareCenter.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Patient.class);
        config.addAnnotatedClass(ProgramDetails.class);
        config.addAnnotatedClass(Payment.class);
        config.addAnnotatedClass(Therapist.class);
        config.addAnnotatedClass(TherapyProgram.class);
        config.addAnnotatedClass(TherapySession.class);
        config.addAnnotatedClass(User.class);
        sessionFactory = config.buildSessionFactory();
//        Annotated Classes Here
    }

    public static FactoryConfiguration getFactoryConfiguration() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
