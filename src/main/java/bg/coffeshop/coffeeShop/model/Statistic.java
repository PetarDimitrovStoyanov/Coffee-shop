package bg.coffeshop.coffeeShop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "statistics")
public class Statistic extends BaseEntity {

    private String type;
    private BigDecimal turnover;
    private Integer dealsCount;
    private Integer peopleAndOrder;
    private Integer visitorsCount;

    public Statistic() {
    }

    @Column(name = "type", unique = true, nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column
    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    @Column(name = "deals_count")
    public Integer getDealsCount() {
        return dealsCount;
    }

    public void setDealsCount(Integer dealsCount) {
        this.dealsCount = dealsCount;
    }

    @Column(name = "people_and_orders")
    public Integer getPeopleAndOrder() {
        return peopleAndOrder;
    }

    public void setPeopleAndOrder(Integer peopleAndOrder) {
        this.peopleAndOrder = peopleAndOrder;
    }

    @Column(name = "visitors_count")
    public Integer getVisitorsCount() {
        return visitorsCount;
    }

    public void setVisitorsCount(Integer visitorsCount) {
        this.visitorsCount = visitorsCount;
    }
}
