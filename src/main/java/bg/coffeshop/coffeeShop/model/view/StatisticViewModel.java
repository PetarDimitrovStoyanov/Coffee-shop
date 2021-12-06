package bg.coffeshop.coffeeShop.model.view;

import java.time.LocalDateTime;

public class StatisticViewModel {
    private LocalDateTime localDateTime;
    private String ipAddress;
    private Long id;

    public StatisticViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
