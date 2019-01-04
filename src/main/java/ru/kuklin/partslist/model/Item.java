package ru.kuklin.partslist.model;

import javax.persistence.*;


@Entity
@Table(name = "parts")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "qty")
    private int qty;

    @Column(name = "req")
    private Boolean req;

    public Item(){
    }

    public Item(String name, Integer qty, Boolean req) {
        this.name = name;
        this.qty = qty;
        this.req = req;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
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
