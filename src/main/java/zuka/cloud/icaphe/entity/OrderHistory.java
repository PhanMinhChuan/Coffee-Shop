/**
 * FileName : OrderHistoryEntity
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import zuka.cloud.icaphe.CommonUtil.Status;

/**
 * @author Nguyen Viet Long
 */
@Entity(name = "order_histories")
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_shop")
    private Long idShop;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_table")
    private Long idTable;

    @Column(name = "id_item")
    private Long idItem;

    @Column(name = "time", columnDefinition = "TIMESTAMP")
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status.STATUS_ORDER status;

    @Enumerated(EnumType.STRING)
    @Column(name = "delete_flag")
    private Status.STATUS_DELETE_FLAG deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdShop() {
        return idShop;
    }

    public void setIdShop(Long idShop) {
        this.idShop = idShop;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdTable() {
        return idTable;
    }

    public void setIdTable(Long idTable) {
        this.idTable = idTable;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Status.STATUS_ORDER getStatus() {
        return status;
    }

    public void setStatus(Status.STATUS_ORDER status) {
        this.status = status;
    }

    public Status.STATUS_DELETE_FLAG getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Status.STATUS_DELETE_FLAG deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }
}
