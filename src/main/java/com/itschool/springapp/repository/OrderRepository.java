package com.itschool.springapp.repository;

import com.itschool.springapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> { // 'Order' entity has the primary key of type 'String' (UUID)

    @Query("SELECT o FROM Order o WHERE o.user.id = :id") // JPQL query - uses the entity name as table and its field names. ':id' is the dynamic parameter binding to the method parameter (long id)
    List<Order> findByUserId(long id); // same as findByUserIdWithNativeSQL and findOrdersByUser_Id, but specifying an explicit query with JPQL (method can have any name)

    @Query(value = "SELECT * FROM orders o INNER JOIN users u ON o.user_id = u.id WHERE o.user_id = :id", nativeQuery = true) // native SQL query - Also here ':id' is the dynamic parameter binding to the method parameter (long id)
    List<Order> findByUserIdWithNativeSQL(long id); // same as findByUserId and findOrdersByUser_Id, but specifying an explicit query with native SQL (method can have any name)

    // recommended for simple scenarios - Spring Data JPA will generate the right needed query under the hood (can be easily done by pressing Ctrl+Space in the IDE)
    List<Order> findOrdersByUser_Id(long id); // same as findByUserId and findByUserIdWithNativeSQL, but using Spring Data JPA method name query - uses the naming convention to create a query under the hood

    List<Order> findAllByUser_Address_Street(String street);

    List<Order> findOrdersByUser_Address_City(String city); // unused, but shows how to use the nested property in the method name query (the 'user' field is a reference to the 'User' entity, and the 'address' field is a reference to the 'Address' entity, which has a 'city' field)

    List<Order> findOrdersByDescription(String description); // Spring Data JPA method name query
}
