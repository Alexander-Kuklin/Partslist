package ru.kuklin.partslist.model;

import javax.persistence.*;


@Entity
@Table(name = "parts")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "req")
    private Boolean req;

    public Item(){
    }

    public Item(String name, Integer qty, Boolean req) {
        this.name = name;
        this.qty = qty;
        this.req = req;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Boolean getReq() {
        return req;
    }

    public void setReq(Boolean req) {
        this.req = req;
    }



    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", requared=" + req +
                '}';
    }
}
