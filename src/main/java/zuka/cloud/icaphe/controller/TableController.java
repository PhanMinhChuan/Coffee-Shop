package zuka.cloud.icaphe.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zuka.cloud.icaphe.entity.Tables;
import zuka.cloud.icaphe.service.TableService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("table")
public class TableController {
    @Autowired
    private TableService ts;

    @GetMapping(value = "/all", params = { "page", "size" })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Tables>> findAllTables(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page tables = ts.findAllTable(page, size);
        return new ResponseEntity<>(tables.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SHOP') and hasRole('STAFF')")
    public ResponseEntity<Tables> getTablesByIdShop(@PathVariable("idShop") Long idShop) {
        Optional<Tables> tb = ts.findById(idShop);

        if (tb.isPresent()) {
            return new ResponseEntity<>(tb.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    @PreAuthorize("hasRole('SHOP')")
    public ResponseEntity<Tables> createTables(@RequestBody Tables tables) {
        ts.save(tables);
        return new ResponseEntity<>(tables, HttpStatus.CREATED);
    }
}
