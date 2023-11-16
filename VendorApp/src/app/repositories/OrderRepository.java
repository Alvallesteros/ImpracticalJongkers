package app.repositories;

import app.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//	@Query("SELECT SUM(oi.price) "
//			+ "FROM Order o JOIN OrderItem oi"
//			+ "WHERE o.id = ?1")
//	double findPrice(long orderId);
    Order findByOrderCode(String orderCode);
}
