package com.task.bigxyt.controller;

import com.task.bigxyt.model.StockExchangeOrder;
import com.task.bigxyt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    private OrderRepository orderRepository;

    // Displaying table of all objects in a database
    @GetMapping
    public String getOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());

        return "orders";
    }

    // Displaying form for add operations
    @GetMapping("/add")
    public String addOrder(Model model) {
        model.addAttribute("addOrder", new StockExchangeOrder("Add"));

        return "add";
    }

    // Displaying form for remove operations - removing previously added orders, so for every add order there is a "Remove" button
    @GetMapping("/remove/{id}")
    public String removeOrder(Model model, @PathVariable final Integer id) {
        // Finding order based on ID handed over in URL and creating new order with the same ID, order type and price
        StockExchangeOrder order = orderRepository.findById(id).orElseThrow();
        StockExchangeOrder removeOrder = new StockExchangeOrder(order.getOrderId(),
                order.getOrderType(), "Remove", order.getPrice());
        model.addAttribute("removeOrder", removeOrder);
        // Finding max quantity that can be removed from order with specific ID
        model.addAttribute("maxQuantity",
                orderRepository.findMaxQuantityByOrderIdAndOrderType(order.getOrderId(), order.getOrderType()));

        return "remove";
    }

    // Displaying table with the order that has best (lowest) buy price and the order with the highest sell price
    @GetMapping("/best")
    public String bestPriced(Model model) {
        model.addAttribute("bestPriced", orderRepository.findBestPricedOrders());

        return "bestPriced";
    }

    // Saveing created object to database, redirecting to the table with the best buy/sell orders
    @PostMapping("/save")
    public String createOrder(final StockExchangeOrder stockExchangeOrder) {
        orderRepository.save(stockExchangeOrder);
        return "redirect:/best";
    }

    @Autowired
    public void setOrderRepository(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
