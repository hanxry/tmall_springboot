package com.hanxry.tmall.service;

import com.hanxry.tmall.dao.PropertyValueDAO;
import com.hanxry.tmall.pojo.Product;
import com.hanxry.tmall.pojo.Property;
import com.hanxry.tmall.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueService  {

	@Autowired
	PropertyValueDAO propertyValueDAO;
	@Autowired PropertyService propertyService;

	public void update(PropertyValue bean) {
		propertyValueDAO.save(bean);
	}

	/**
	 * 这个方法的作用是初始化PropertyValue。 为什么要初始化呢？
	 * 因为对于PropertyValue的管理，没有增加，只有修改。 所以需要通过初始化来进行自动地增加，以便于后面的修改。
	 * @param product
	 */
	public void init(Product product) {
		List<Property> properties= propertyService.listByCategory(product.getCategory());
		for (Property property: properties) {
			PropertyValue propertyValue = getByPropertyAndProduct(product, property);
			if(null==propertyValue){
				propertyValue = new PropertyValue();
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				propertyValueDAO.save(propertyValue);
			}
		}
	}

	public PropertyValue getByPropertyAndProduct(Product product, Property property) {
		return propertyValueDAO.getByPropertyAndProduct(property,product);
	}

	public List<PropertyValue> list(Product product) {
		return propertyValueDAO.findByProductOrderByIdDesc(product);
	}
	
}
