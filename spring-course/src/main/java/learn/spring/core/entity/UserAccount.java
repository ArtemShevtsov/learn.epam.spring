package learn.spring.core.entity;

/**
 * Created by artem_shevtsov on 1/19/17.
 */
public class UserAccount {
    private User user;
    private Double availableMoney;

    public UserAccount(User user, Double availableMoney) {
        this.user = user;
        this.availableMoney = availableMoney;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(Double availableMoney) {
        this.availableMoney = availableMoney;
    }
}
