package com.task.bigxyt.repository;

import com.task.bigxyt.model.StockExchangeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<StockExchangeOrder, Integer> {
    // Finding max quantity for specified order ID and order type - subtracting sum of remove orders from sum of add orders
    @Query(value = "SELECT COALESCE(t1.quantity, 0) - COALESCE(t2.quantity, 0) FROM " +
            "(SELECT COALESCE(SUM(quantity), 0) AS quantity FROM stock_exchange_order WHERE order_id = :orderId AND order_type = :orderType AND operation_type = 'Add') AS t1, " +
            "(SELECT COALESCE(SUM(quantity), 0) AS quantity FROM stock_exchange_order WHERE order_id = :orderId AND order_type = :orderType AND operation_type = 'Remove') AS t2",
            nativeQuery = true)
    Integer findMaxQuantityByOrderIdAndOrderType(final String orderId, final String orderType);

    // Finding best priced orders - should be "group by", but not working for some reason :(
    @Query("SELECT s FROM StockExchangeOrder s WHERE s.orderType = 'Buy' AND s.operationType = 'Add' ORDER BY s.price ASC LIMIT 1 " +
            "UNION " +
            "SELECT s FROM StockExchangeOrder s WHERE s.orderType = 'Sell' AND s.operationType = 'Add' ORDER BY s.price DESC LIMIT 1")
    List<StockExchangeOrder> findBestPricedOrders();
}
