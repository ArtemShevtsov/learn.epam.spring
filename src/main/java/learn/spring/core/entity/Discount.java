package learn.spring.core.entity;

import learn.spring.core.strategy.DiscountStrategy;

public class Discount {
    int percent;
    Class<? extends DiscountStrategy> discountClass;

    public Discount(int percent, Class<? extends DiscountStrategy> discountClass) {
        this.percent = percent;
        this.discountClass = discountClass;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public Class<? extends DiscountStrategy> getDiscountClass() {
        return discountClass;
    }

    public void setDiscountClass(Class<? extends DiscountStrategy> discountClass) {
        this.discountClass = discountClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (percent != discount.percent) return false;
        return !(discountClass != null ? !discountClass.equals(discount.discountClass) : discount.discountClass != null);

    }

    @Override
    public int hashCode() {
        int result = percent;
        result = 31 * result + (discountClass != null ? discountClass.hashCode() : 0);
        return result;
    }
}
