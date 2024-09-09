package br.com.tavuencas.sergio.desafio_btg_pactual_orderms.entity;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {

    private String product;
    private Integer quantity;

    @Field(targetType = FieldType.DECIMAL128) // Sem essa anotação, o mongo salva como string
    private BigDecimal price;

    public OrderItem() {
    }

    public OrderItem(String product, Integer quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(product, orderItem.product) && Objects.equals(quantity, orderItem.quantity) && Objects.equals(price, orderItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, price);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
