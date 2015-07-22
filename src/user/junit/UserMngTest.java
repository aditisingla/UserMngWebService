package user.junit;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class UserMngTest {

	static final URI BASE_URI = getBaseURI();

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/user").port(8080).build();
	}

	@Test
	public void testSuccessGetUserList() throws IOException {
		Client client = Client.create(new DefaultClientConfig());
		WebResource service = client.resource(getBaseURI());
		ClientResponse resp = service.path("UserWebService").path("getUserList").get(ClientResponse.class);
		String output = resp.getEntity(String.class);
		Gson gson = new Gson();
		Map responseMap = gson.fromJson(output, Map.class);
		assertEquals(null, responseMap.get("errorMessage"));
	}

}
