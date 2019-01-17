package com.joshuajenster.school.controller;

import com.joshuajenster.school.domain.*;
import com.joshuajenster.school.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MessageController {

    private final MessageRepository messageRepository;
    private final OrderRepository orderRepository;
    private final ProductCatalogRepository productCatalogRepository;
    private final ProductRepository productRepository;
    private final OrderOptionRepository orderOptionRepository;
    private final BaseOrderRepository baseOrderRepository;

    // constructor dependency injection
    public MessageController(MessageRepository messageRepository,
                             OrderRepository orderRepository,
                             ProductCatalogRepository productCatalogRepository,
                             ProductRepository productRepository, OrderOptionRepository orderOptionRepository, BaseOrderRepository baseOrderRepository) {
        this.messageRepository = messageRepository;
        this.orderRepository = orderRepository;
        this.productCatalogRepository = productCatalogRepository;
        this.productRepository = productRepository;
        this.orderOptionRepository = orderOptionRepository;
        this.baseOrderRepository = baseOrderRepository;
    }


    public void createProductCatalogAndProducts() {

        // build product catalog and two products

        ProductCatalog productCatalog = new ProductCatalog();

        // right productCatalog: without id; left productCatalog: with id
        // (needed because of autoincrement)
        productCatalog = productCatalogRepository.save(productCatalog);

        Product prod1 = new Product("schroefje", 2);
        Product prod2 = new Product("moertje", 1);

        // add two products
        productCatalog.add(prod1);
        productCatalog.add(prod2);
    }

    public void createOrder() {

        // get the productCatalog
        Optional<ProductCatalog> productCatalog =
                productCatalogRepository.findById(1L);

        // "find" a product in the catalog and add it to the order
        Product prod = productCatalog.get().find(2L);

        // make a copy of the product (the copy has no id yet)
        // why a copy is made?
        Product prodCopy = new Product(prod);

        Order order = new Order();
        order = orderRepository.save(order);
        order.add(prodCopy);
    }

    public void decorateOrder() {
        Optional<Order> concreteOrder  = orderRepository.findById(4L);

        OrderOption decoratedOrder1 = new OrderOption("wrapping paper", 7, concreteOrder.get());
        baseOrderRepository.save(decoratedOrder1.getDecoratedOrder());
        orderOptionRepository.save(decoratedOrder1);


        OrderOption decoratedOrder2 = new OrderOption("nice box", 5, decoratedOrder1);

        orderOptionRepository.save(decoratedOrder2);
        OrderOption decoratedOrder3 = new OrderOption("fast delivery", 12, decoratedOrder2);

        orderOptionRepository.save(decoratedOrder3);

        System.out.println("***** content of the order: " + decoratedOrder3);
        System.out.println("***** price of the order: " + decoratedOrder3.price());


    }

    @Transactional
    @GetMapping
    public ModelAndView list() {

        createProductCatalogAndProducts();

        Iterable<Message> messages = messageRepository.findAll();
        return new ModelAndView("messages/list", "messages", messages);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Message message) {
        return new ModelAndView("messages/view", "message", message);
    }

    @Transactional
    @GetMapping(params = "form")
    public String createForm(@ModelAttribute Message message) {

        createOrder();
        decorateOrder();


        return "messages/form";
    }


    @PostMapping
    public ModelAndView create(@Valid Message message, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("messages/form", "formErrors", result.getAllErrors());
        }
        message = this.messageRepository.save(message);
        redirect.addFlashAttribute("globalMessage", "view.success");
        return new ModelAndView("redirect:/{message.id}", "message.id", message.getId());
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.messageRepository.deleteById(id);
        Iterable<Message> messages = this.messageRepository.findAll();
        return new ModelAndView("messages/list", "messages", messages);
    }

    @GetMapping("modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Message message) {
        return new ModelAndView("messages/form", "message", message);
    }

}
