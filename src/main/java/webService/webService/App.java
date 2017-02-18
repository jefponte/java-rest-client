package webService.webService;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import sun.misc.BASE64Encoder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String url = URL+"/lista_usuario.php";
        String authString = USUARIO + ":" + SENHA;
		String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
        Client restClient = Client.create();
        WebResource webResource = restClient.resource(url);
        ClientResponse resp = webResource.accept("application/json")
                                         .header("Authorization", "Basic " + authStringEnc)
                                         .get(ClientResponse.class);
        if(resp.getStatus() != 200){
        	
            System.err.println("Unable to connect to the server");
            return;
        }
        
        String output = resp.getEntity(String.class);     
        JSONArray projectArray;
		try {
			
			projectArray = new JSONArray(output.substring(12));
			for (int i = 0; i < projectArray.length(); i++) {
	            JSONObject proj = projectArray.getJSONObject(i);
	            System.out.println(proj.getString("nome"));
	        }
			
		} catch (JSONException e) {
			e.printStackTrace();
			return;
		}
		
    }
    
    public static final String URL = "http://localhost/service";
	public static final String USUARIO = "usuario";
	public static final String SENHA = "Senha";
	
}
