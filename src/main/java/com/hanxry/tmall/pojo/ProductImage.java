package com.hanxry.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "productimage")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})
public class ProductImage {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    private int id;
	
    @ManyToOne
    @JoinColumn(name="pid")
    @JsonBackReference // 这个注解是为了避免双向引用
	private Product product;
	
    private String type;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
