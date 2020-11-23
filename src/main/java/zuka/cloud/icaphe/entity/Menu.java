package zuka.cloud.icaphe.entity;

import zuka.cloud.icaphe.CommonUtil.Status;

import javax.persistence.*;
import java.io.Serializable;

class MenuId implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Id
    @Column(name = "id_shop", nullable = false)
    private long idShop;

    public MenuId(long idShop, long idMenu) {
        this.idShop = idShop;
        this.id = idMenu;
    }

    public MenuId() {
    }
}

@Entity
//@Table(name = "menu")
@IdClass(MenuId.class)
public class Menu implements Serializable {

    @Id
    // @GeneratedValue // = AUTO_INCREMENT
    @Column(name = "id", nullable = false)
    private long id;

    @Id
    @Column(name = "id_shop", nullable = false)
    private long idShop;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private long amount;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status.STATUS_MENU status;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "deleted", columnDefinition = "TINYINT(1)", nullable = false)
    private boolean deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdShop() {
        return idShop;
    }

    public void setIdShop(long idShop) {
        this.idShop = idShop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Status.STATUS_MENU getStatus() {
        return status;
    }

    public void setStatus(Status.STATUS_MENU status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Menu(String name, long amount, Status.STATUS_MENU status, long id_shop, long id, boolean deleted, float price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.status = status;
        this.idShop = id_shop;
        this.deleted = deleted;
        this.price = price;
    }

    public Menu() {
    }

}
