package com.klu;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        // 1️⃣ INSERT Multiple Products
        Product p1 = new Product("Laptop", "Gaming Laptop", 75000, 10);
        Product p2 = new Product("Mouse", "Wireless Mouse", 800, 50);
        Product p3 = new Product("Keyboard", "Mechanical Keyboard", 2500, 30);
        session.save(p1);
        session.save(p2);
        session.save(p3);
        tx.commit();
        session.close();
        System.out.println("Products Inserted");
        // 2️⃣ RETRIEVE Product by ID
        session = HibernateUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, p1.getId());
        System.out.println("Retrieved: " + product.getName());
        session.close();
        // 3️⃣ UPDATE Product
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        product.setPrice(72000);
        product.setQuantity(8);
        session.update(product);
        tx.commit();
        session.close();
        System.out.println("Product Updated");
        // 4️⃣ DELETE Product by ID
        /*session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.delete(product);
        tx.commit();
        session.close();
        System.out.println("Product Deleted");*/
    }
}
