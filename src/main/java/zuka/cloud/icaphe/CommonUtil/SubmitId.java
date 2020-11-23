package zuka.cloud.icaphe.CommonUtil;

import java.util.List;

public class SubmitId {

    private long shopId;

    private long tableId;

    private List<Long> listItemId;

    private List<Long> listOrderId;

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public List<Long> getListItemId() {
        return listItemId;
    }

    public void setListItemId(List<Long> listItemId) {
        this.listItemId = listItemId;
    }

    public List<Long> getListOrderId() {
        return listOrderId;
    }

    public void setListOrderId(List<Long> listOrderId) {
        this.listOrderId = listOrderId;
    }

}
