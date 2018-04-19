package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibUtils {

    public static SessionFactory factory = new Configuration().configure("model/hibernate.cfg.xml")
            .addAnnotatedClass(ProductsEntity.class)
            .addAnnotatedClass(UsersEntity.class)
            .buildSessionFactory();

    public static List<ProductsEntity> getAllProducts(){
        Session hsession = factory.openSession();
        hsession.beginTransaction();

        List<ProductsEntity> products = (List<ProductsEntity>) hsession.createQuery("from ProductsEntity ").list();

        hsession.close();

        return products;
    }

    public static List<UsersEntity> getAllUsers(){
        Session hsession = factory.openSession();
        hsession.beginTransaction();

        List<UsersEntity> users = (List<UsersEntity>) hsession.createQuery("from UsersEntity ").list();

        hsession.close();

        return users;
    }

    public static boolean addUser(UsersEntity user){

        try {
            Session hsession = factory.openSession();
            hsession.beginTransaction();

            hsession.save(user);

            hsession.getTransaction().commit();

            hsession.close();

            return true;
        }catch (Exception e){
            return false;
        }


    }

    public static ProductsEntity getProduct(int productID) {
        try {
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();

            ProductsEntity entity = session.get(ProductsEntity.class, productID);

            tx.commit();
            session.close();

            return entity;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<ProductsEntity> searchProduct(String pattern){
        try {
            Session hsession = factory.openSession();
            hsession.beginTransaction();

            List<ProductsEntity> products = (List<ProductsEntity>) hsession.createQuery("from ProductsEntity as p where upper(p.name) LIKE '%"+pattern.toUpperCase()+"%'").list();

            hsession.close();

            return products;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
