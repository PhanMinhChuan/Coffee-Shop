/**
 * FileName : CoffeeShopServiceImpl
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.entity.CoffeeShop;
import zuka.cloud.icaphe.repository.CoffeeShopRepository;
import zuka.cloud.icaphe.service.CoffeeShopService;

import java.util.List;

/**
 * @author Nguyen Viet Long
 */
@Service
public class CoffeeShopServiceImpl implements CoffeeShopService {

    @Autowired
    CoffeeShopRepository coffeeShopRepository;

    public CoffeeShop getCoffeeShopById(Long id) {
        return coffeeShopRepository.findById(id).get();
    }

    public List<CoffeeShop> getAllCoffeShop() {
        return coffeeShopRepository.findAll();
    }

    public CoffeeShop saveCaffeShop(CoffeeShop coffeeShop) {
        return coffeeShopRepository.save(coffeeShop);
    }

}
