package pl.productlister.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import pl.productlister.model.Product;
import pl.productlister.model.ProductClientInformation;
import pl.productlister.service.ProductService;

@WebService(endpointInterface = "pl.productlister.ws.ProductListerWebService")
public class ProductListerWebServiceImpl implements ProductListerWebService {
	
	@Inject
	private ProductService productService;
	
	@Override
	public List<Product> findAvailibleProducts(
			ProductClientInformation prductClientInformation) {
		
		try {
			return productService.findAvailibleProducts(prductClientInformation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Product findProductById(Long id) {
		return productService.findProductById(id);
	}

}
