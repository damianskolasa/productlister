package pl.productlister.ws;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

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
			List<Product> products = productService
					.findAvailibleProducts(prductClientInformation);

			return new LinkedList<Product>(
					CollectionUtils.predicatedCollection(products,
							new AvailibleProductsPredicate(
									prductClientInformation)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product findProductById(Long id) {
		return productService.findProductById(id);
	}

	class AvailibleProductsPredicate implements Predicate {

		private ProductClientInformation productClientInformation;

		public AvailibleProductsPredicate(
				ProductClientInformation productClientInformation) {
			super();
			this.productClientInformation = productClientInformation;
		}

		@Override
		public boolean evaluate(Object arg0) {
			Product product = (Product) arg0;
			if (productClientInformation.isDriving()
					&& product.getCode().startsWith("DR")) {
				return true;
			}
			if (productClientInformation.isMarried()
					&& product.getCode().startsWith("R")) {
				return true;
			}
			if (product.getCode().startsWith("STD")) {
				return true;
			}

			return false;
		}

	}

}
