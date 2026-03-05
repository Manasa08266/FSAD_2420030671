package com.klu;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
public class App {
public static void main(String[] args) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	session.save(new Product("Laptop", "Electronics", 75000, 10));
	session.save(new Product("Mouse", "Electronics", 800, 50));
	session.save(new Product("Keyboard", "Electronics", 2500, 30));
	session.save(new Product("Chair", "Furniture", 4500, 20));
	session.save(new Product("Table", "Furniture", 9000, 5));
	session.save(new Product("Pen", "Stationery", 20, 200));
	session.save(new Product("Notebook", "Stationery", 80, 150));
	tx.commit();
	session.close();
	System.out.println("Products Inserted Successfully");
	session = HibernateUtil.getSessionFactory().openSession();
	// a) Ascending Order
	List<Product> priceAsc = session.createQuery("FROM Product ORDER BY price ASC", Product.class) .list();
	System.out.println("\nProducts Sorted by Price (ASC):");
	priceAsc.forEach(p ->System.out.println(p.getName() + " - " + p.getPrice()));
	// b) Descending Order
	List<Product> priceDesc = session.createQuery("FROM Product ORDER BY price DESC", Product.class).list();
	System.out.println("\nProducts Sorted by Price (DESC):");
	priceDesc.forEach(p ->System.out.println(p.getName() + " - " + p.getPrice()));
	List<Product> qtyDesc =session.createQuery("FROM Product ORDER BY quantity DESC", Product.class) .list();
	System.out.println("\nProducts Sorted by Quantity (High → Low):");
	qtyDesc.forEach(p ->System.out.println(p.getName() + " - " + p.getQuantity()));
	Query<Product> page1 =session.createQuery("FROM Product", Product.class);
	page1.setFirstResult(0);
	page1.setMaxResults(3);
	System.out.println("\nFirst 3 Products:");
	page1.list().forEach(p -> System.out.println(p.getName()));
	Query<Product> page2 = session.createQuery("FROM Product", Product.class);
	page2.setFirstResult(3);
	page2.setMaxResults(3);
	System.out.println("\nNext 3 Products:");
	page2.list().forEach(p -> System.out.println(p.getName()));
	// a) Total number of products
	Long totalCount = session.createQuery("SELECT COUNT(p) FROM Product p",        Long.class).uniqueResult();
	System.out.println("\nTotal Products: " + totalCount);
	// b) Count products where quantity > 0
	Long availableCount = session.createQuery( "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0", Long.class) .uniqueResult();
	System.out.println("Products with Quantity > 0: " + availableCount);
	// c) Count products grouped by description
	List<Object[]> countByDesc =session.createQuery( "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description", Object[].class) .list();
	System.out.println("\nCount of Products by Description:");
	for (Object[] row : countByDesc) {System.out.println(row[0] + " : " + row[1]);
	}
	// d) Minimum and Maximum price
	Object[] minMax =session.createQuery( "SELECT MIN(p.price), MAX(p.price) FROM Product p", Object[].class) .uniqueResult();
	System.out.println("\nMinimum Price: " + minMax[0]);
	System.out.println("Maximum Price: " + minMax[1]);
	List<Object[]> grouped = session.createQuery( "SELECT p.description, SUM(p.quantity) FROM Product p GROUP BY p.description", Object[].class) .list();
	System.out.println("\nGrouped by Description (Total Quantity):");
	for (Object[] row : grouped) {
	System.out.println(row[0] + " → " + row[1]);
	}
	List<Product> priceRange =session.createQuery( "FROM Product WHERE price BETWEEN :min AND :max",Product.class) .setParameter("min", 1000.0) .setParameter("max", 10000.0) .list();
	System.out.println("\nProducts in Price Range 1000–10000:");
	priceRange.forEach(p ->
	System.out.println(p.getName() + " - " + p.getPrice())
	);
	// a) Names starting with 'L'
	System.out.println("\nNames Starting with 'L':");
	session.createQuery( "FROM Product WHERE name LIKE 'L%'", Product.class) .list().forEach(p -> System.out.println(p.getName()));
	// b) Names ending with 'r'
	System.out.println("\nNames Ending with 'r':");
	session.createQuery("FROM Product WHERE name LIKE '%r'", Product.class) .list() .forEach(p -> System.out.println(p.getName()));
	// c) Names containing 'top'
	System.out.println("\nNames Containing 'top':");
	session.createQuery( "FROM Product WHERE name LIKE '%top%'", Product.class).list() .forEach(p -> System.out.println(p.getName()));
	// d) Names with exact length = 5
	System.out.println("\nNames with Exactly 5 Characters:");
	session.createQuery("FROM Product WHERE LENGTH(name) = 5", Product.class).list() .forEach(p -> System.out.println(p.getName()));
	session.close();
	}
	}
