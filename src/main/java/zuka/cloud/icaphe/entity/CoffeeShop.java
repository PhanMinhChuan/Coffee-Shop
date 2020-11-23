/**
 * FileName : CoffeeShopEntity
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.entity;

import zuka.cloud.icaphe.CommonUtil.Status;

import javax.persistence.*;

/**
 * @author Nguyen Viet Long
 */
@Entity(name = "coffee_shops")
public class CoffeeShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "address")
    private String address;

    @Column(name = "pass_wifi")
    private String passWifi;

    @Enumerated(EnumType.STRING)
    @Column(name = "delete_flag")
    private Status.STATUS_DELETE_FLAG deleteFlag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassWifi() {
        return passWifi;
    }

    public void setPassWifi(String passWifi) {
        this.passWifi = passWifi;
    }

    public Status.STATUS_DELETE_FLAG getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Status.STATUS_DELETE_FLAG deleteFlg) {
        this.deleteFlag = deleteFlg;
    }
}