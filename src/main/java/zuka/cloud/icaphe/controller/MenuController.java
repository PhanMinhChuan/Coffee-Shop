package zuka.cloud.icaphe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zuka.cloud.icaphe.CommonUtil.Const;
import zuka.cloud.icaphe.entity.Menu;
import zuka.cloud.icaphe.service.MenuService;

import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("menus")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping(value = "{idShop}")
    @PreAuthorize("hasRole('ADMIN') and hasRole('SHOP') and hasRole('STAFF') and hasRole('USER')")
    public ResponseEntity<List<Menu>> findAllMenuByIdShop(@PathVariable Integer idShop,
                                                          @RequestParam(value="page", defaultValue = Const.NUMBER_PAGE_START_DEFAULT) Integer page,
                                                          @RequestParam(value="size", defaultValue = Const.NUMBER_SIZE_PAGE_DEFAULT) Integer size) {
        List<Menu> menuList = menuService.findAllMenuByIdShop(idShop, page, size);
        return new ResponseEntity<>(menuList, HttpStatus.OK);
    }

    @GetMapping(value = "{idShop}/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasRole('SHOP') and hasRole('STAFF') and hasRole('USER')")
    public ResponseEntity<Optional<Menu>> findOneMenu(@PathVariable Integer idShop, @PathVariable Integer id) {
        Optional<Menu> menu = menuService.findOneMenu(idShop, id);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @PutMapping(value = "{idShop}/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasRole('SHOP')")
    public ResponseEntity<Void> updateMenu(@PathVariable Integer id,
                                           @PathVariable Integer idShop,
                                           @RequestBody Menu menu) {
        menuService.updateMenu(id, idShop, menu);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping
    @PreAuthorize("hasRole('ADMIN') and hasRole('SHOP')")
    public ResponseEntity<Void> addMenu (@RequestBody Menu menus) {
        menuService.add(menus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{idShop}/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasRole('SHOP')")
    public ResponseEntity<Void> deletedMenu(@PathVariable Integer id, @PathVariable Integer idShop) {
        menuService.deletedMenu(id, idShop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
