import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.BufferedWriter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient.Version;

public class Example implements HttpFunction {
  @Override
  public void service(HttpRequest request, HttpResponse response) throws Exception {
    java.net.http.HttpRequest APIrequest = java.net.http.HttpRequest.newBuilder()
		.uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol=TSLA"))
		.header("x-rapidapi-key", "65c02a4672mshf6ff63121b66145p1777adjsn6a125a20822d")
		.header("x-rapidapi-host", "alpha-vantage.p.rapidapi.com")
		.method("GET", java.net.http.HttpRequest.BodyPublishers.noBody())
		.build();
    java.net.http.HttpResponse<String> APIresponse = java.net.http.HttpClient.newHttpClient().send(APIrequest, java.net.http.HttpResponse.BodyHandlers.ofString());
    
    BufferedWriter writer = response.getWriter();
    writer.write(APIresponse.body());
  }
}
