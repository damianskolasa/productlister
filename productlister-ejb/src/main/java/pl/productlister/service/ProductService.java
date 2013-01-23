package pl.productlister.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.productlister.model.Product;
import pl.productlister.model.ProductClientInformation;

@Stateless
public class ProductService {

	@Inject
	private EntityManager em;
	
	public List<Product> findAvailibleProducts (ProductClientInformation productClientInformation) throws Exception  {
		return em.createQuery("select p from Product p", Product.class).getResultList();
	}
	
	public Product findProductById(Long id) {
		return em.createQuery("select p from Product p where p.id = :id", Product.class).setParameter("id", id).getSingleResult();
	}

}
