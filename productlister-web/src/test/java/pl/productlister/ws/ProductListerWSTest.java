package pl.productlister.ws;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class ProductListerWSTest {

	public static void main(String[] args) throws Exception {
		URL url = new URL(
				"http://localhost:8081/pricecalculator/PriceCalculatorWebServiceImpl?wsdl");

		QName qname = new QName("http://ws.pricecalculator.wojcik.kolasa/",
				"PriceCalculatorWebServiceImplService");

		Service service = Service.create(url, qname);

		ProductListerWebService productLister = service
				.getPort(ProductListerWebService.class);

		//System.out.println(priceCalculator.calculatePrice("afas", new ClientInformationDTO()));
	}
}
