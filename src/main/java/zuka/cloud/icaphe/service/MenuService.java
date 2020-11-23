package zuka.cloud.icaphe.service;

import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.entity.Menu;

import java.util.List;
import java.util.Optional;

@Service
public interface MenuService {

    List<Menu> findAllMenuByIdShop(long idShop, Integer page, Integer size);

    Optional<Menu> findOneMenu(long idShop, long id);

    boolean checkLoop(Menu menu, long idShop, long id);

    void checkStatus(long idShop, long id);

    void updateMenu(long idShop, long id, Menu menu);

    void add(Menu menu);

    void deletedMenu(long idShop, long id);

}
