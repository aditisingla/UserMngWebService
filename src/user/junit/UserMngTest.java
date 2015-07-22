package user.junit;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class UserMngTest {

	static final URI BASE_URI = getBaseURI();

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/userMgnt").port(8080).build();
	}

	@Test
	public void testGetUserList() throws IOException {
		Client client = Client.create(new DefaultClientConfig());
		WebResource service = client.resource(getBaseURI());
		ClientResponse resp = service.path("UserWebService").path("getUserList").get(ClientResponse.class);
		System.out.println("Got stuff: " + resp);
		assertEquals(200, resp.getStatus());
	}

}
