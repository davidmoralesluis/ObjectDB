package tutorial;

import javax.persistence.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory(
                        "$objectdb/db/p2.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Store 1000 Point objects in the database:
//        for (int i = 0; i <= 10; i++) {
//            Point p = new Point(i,i, i);
//            em.persist(p);
//        }



        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query =
                em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
        }

        //amosar punto 10
        Query punto10 = em.createQuery("select p from Point p where p.id =10");
        System.out.println("Punto 10: "+punto10.getSingleResult());

        try {
            Query cambiarPunto= em.createQuery("update Point set y = y+2 where id = 10");
            int cambioInt = cambiarPunto.executeUpdate();
        }catch (TransactionRequiredException e){
            System.out.println(e);
        }


        //amosar novo punto 10
        Query newPunto10 = em.createQuery("select p from Point p where p.id =10");
        System.out.println("Punto 10: "+newPunto10.getSingleResult());

        // Close the database connection:
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}