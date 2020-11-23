package zuka.cloud.icaphe.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.CommonUtil.Status;
import zuka.cloud.icaphe.entity.Menu;
import zuka.cloud.icaphe.repository.MenuRepository;
import zuka.cloud.icaphe.service.MenuService;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findAllMenuByIdShop(long idShop, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return menuRepository.findByIdShop(idShop, pageable);
    }

    @Override
    public Optional<Menu> findOneMenu(long idShop, long id) {
        return (Optional<Menu>) menuRepository.findOne(idShop, id);
    }

    @Override
    public boolean checkLoop(Menu menu, long idShop, long id) {
        Optional<Menu> menuEx = findOneMenu(idShop, id);
        if (menuEx.isEmpty()) {
            System.out.println("CHECK LOOP CREATE OK!");
            return true;
        } else if (menuEx.get().getName().equals(menu.getName()) && menuEx.get().getAmount() == menu.getAmount()
                && menuEx.get().getPrice() == menu.getPrice()) {
            System.out.println("CHECK LOOP UPDATE OK!");
            return false;
        }
        System.out.println("TRUE!");
        return true;
    }

    @Override
    public void checkStatus(long idShop, long id) {
        if (findOneMenu(idShop, id).get().getAmount() > 0
                && findOneMenu(idShop, id).get().getStatus() == Status.STATUS_MENU.HET_HANG) {
            System.out.println("Error Status: Hệ thống sẽ tự update status = CO_HANG!");
            findOneMenu(idShop, id).get().setStatus(Status.STATUS_MENU.CO_HANG);
        } else if (findOneMenu(idShop, id).get().getAmount() == 0
                && findOneMenu(idShop, id).get().getStatus() == Status.STATUS_MENU.CO_HANG) {
            System.out.println("Error Status: Hệ thống sẽ tự update status = HET_HANG!");
            findOneMenu(idShop, id).get().setStatus(Status.STATUS_MENU.HET_HANG);
        }
        menuRepository.save(findOneMenu(idShop, id).get());
    }

    @Override
    public void updateMenu(long id, long idShop, Menu menu) {
        if (checkLoop(menu, idShop, id)) {
            findOneMenu(idShop, id).get().setAmount(menu.getAmount());
            findOneMenu(idShop, id).get().setName(menu.getName());
            findOneMenu(idShop, id).get().setPrice(menu.getPrice());
            menuRepository.save(findOneMenu(idShop, id).get());
            checkStatus(idShop, id);
            System.out.println("WORKING UPDATE!");
        }
    }

    @Override
    public void deletedMenu(long idShop, long id) {
        menuRepository.deleteMenu(idShop, id);
    }

    @Override
    public void add(Menu menu) {
        if (checkLoop(menu, menu.getIdShop(), menu.getId())) {
            menuRepository.save(menu);
            checkStatus(menu.getId(), menu.getIdShop());
            System.out.println("WORKING CREATE!");
        }
    }

}
