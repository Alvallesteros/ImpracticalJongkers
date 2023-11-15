package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByCustomer_Id(Long customerId);

	Order findByOrderCode(String orderCode);
	
//	@Query("SELECT SUM(oi.price) "
//			+ "FROM Order o JOIN OrderItem oi"
//			+ "WHERE o.id = ?1")
//	double findPrice(long orderId);
	
}
