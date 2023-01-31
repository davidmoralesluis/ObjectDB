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
        try {
            for (int i = 0; i <= 10; i++) {
                Point p = new Point(i,i, i);
                em.persist(p);
            }
            em.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e);
        }




        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
        }
        System.out.println();

        //amosar punto 10
        Query punto10 = em.createQuery("select p from Point p where p.id =10");
        Point h = (Point)punto10.getSingleResult();
        System.out.println(h.toString());
       em.close();
        emf.close();


        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf2 =
                Persistence.createEntityManagerFactory(
                        "$objectdb/db/p2.odb");
        EntityManager em2 = emf2.createEntityManager();
        em2.getTransaction().begin();

        //cambiar coor. del punto 10
        Query cambiarPunto= em2.createQuery("update Point set y = y+2 where id = 10");
        int cambioInt = cambiarPunto.executeUpdate();
        System.out.println("Cambiamos la coor.Y del Punto 10");

        //amosar novo punto 10
        Query newPunto10 = em2.createQuery("select p from Point p where p.id =10");
        System.out.println("Punto 10(despues): "+newPunto10.getSingleResult()+"\n");

        // Close the database connection:
        em2.getTransaction().commit();
        em2.close();
        emf2.close();


        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf3 =
                Persistence.createEntityManagerFactory(
                        "$objectdb/db/p2.odb");
        EntityManager em3 = emf3.createEntityManager();
        em3.getTransaction().begin();

        //borramos el Punto 5
        Query borrarPunto= em3.createQuery("delete from Point p where p.id = 5");
        int borrarInt = borrarPunto.executeUpdate();
        System.out.println("borramos el Punto 5");

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query2 = em3.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results2 = query2.getResultList();
        for (Point p : results2) {
            System.out.println(p);
        }

        // Close the database connection:
        em3.getTransaction().commit();
        em3.close();
        emf3.close();

        //-------------------------- ejercicio 6 ----------------------------------

        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf4 =
                Persistence.createEntityManagerFactory(
                        "$objectdb/db/p2.odb");
        EntityManager em4 = emf4.createEntityManager();
        em4.getTransaction().begin();

        //cambiar coor. del punto 10
        Query cambiarPuntosA1000= em4.createQuery("update Point set y = 1000 where id < 6");
        cambiarPuntosA1000.executeUpdate();
        System.out.println("\nCambiamos la coor.Y  a 1000\n");

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query3 = em4.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results3 = query3.getResultList();
        for (Point p : results3) {
            System.out.println(p);
        }

        // Close the database connection:
        em4.getTransaction().commit();
        em4.close();
        emf4.close();

        //-------------------------- ejercicio 7 ----------------------------------

        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf5 =
                Persistence.createEntityManagerFactory(
                        "$objectdb/db/p2.odb");
        EntityManager em5 = emf5.createEntityManager();
        em5.getTransaction().begin();

        //eliminamos puntos
        Query eliminarPuntos= em5.createQuery("delete from Point p where p.x < 3");
        eliminarPuntos.executeUpdate();
        System.out.println("\neliminamos varios puntos \n");

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query5 = em5.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results5 = query5.getResultList();
        for (Point p : results5) {
            System.out.println(p);
        }

        // Close the database connection:
        em5.getTransaction().commit();
        em5.close();
        emf5.close();

    }
}