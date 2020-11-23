/**
 * FileName : CoffeeShopController
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zuka.cloud.icaphe.entity.CoffeeShop;
import zuka.cloud.icaphe.service.Impl.CoffeeShopServiceImpl;

import java.util.List;

@RestController
@RequestMapping("coffee-shops")
public class CoffeeShopController {

    @Autowired
    CoffeeShopServiceImpl coffeeShopService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<CoffeeShop> getListCoffeeShop() {
        return coffeeShopService.getAllCoffeShop();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CoffeeShop getCoffeeShopById(@PathVariable(name = "id") long id) {
        return coffeeShopService.getCoffeeShopById(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CoffeeShop> saveCoffeeShop(@RequestBody CoffeeShop coffeeShop) {
        coffeeShopService.saveCaffeShop(coffeeShop);
        return ResponseEntity.ok(coffeeShop);
    }

    /*
    + thiếu 1 api xóa shop.
    + thiêu 1 api update shop: địa chỉ, tên shop, passwifi.
    */
}
