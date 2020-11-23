/**
 * FileName : CoffeeShopServiceImpl
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.service;

import zuka.cloud.icaphe.entity.CoffeeShop;

import java.util.List;

/**
 * @author Nguyen Viet Long
 */
public interface CoffeeShopService {

    CoffeeShop getCoffeeShopById(Long id);

    List<CoffeeShop> getAllCoffeShop();

    CoffeeShop saveCaffeShop(CoffeeShop coffeeShop);
}
