package app;


import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import interfaces.AdminWebService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			URL url = new URL("http://127.0.0.1:9999/admin?wsdl");
			QName qname = new QName("http://webservice/","AdminWebServerService");
			Service ws = Service.create(url, qname);
			AdminWebService admin = ws.getPort(AdminWebService.class);
			
			admin.RemoveQueue("queue10");
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
