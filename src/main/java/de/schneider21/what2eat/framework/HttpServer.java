package de.schneider21.what2eat.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fi.iki.elonen.NanoHTTPD;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class HttpServer extends NanoHTTPD {

    public HttpServer(int port) {
        super(port);
    }

    private final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    private final List<RestController> restControllers = new ArrayList<>();

    public void registerRestController(RestController restController) {
        restControllers.add(restController);
    }

    @Override
    public Response serve(IHTTPSession session) {

        final String path = session.getUri();
        System.out.printf("HttpServer: Incoming %s request for %s with params %s\n", session.getMethod(), path,
                session.getParms());

        if (session.getMethod() == Method.GET) {
            for (RestController restController : restControllers) {
                for (Map.Entry<String, Function<RestController.IRequestParameters, Object>> httpGetMapping :
                        restController.httpGetMappings().entrySet()) {
                    final String pathExpression = httpGetMapping.getKey();
                    if (path.matches(pathExpression)) {
                        try {
                            final Object returnObject =
                                    httpGetMapping.getValue().apply(new RestController.IRequestParameters() {
                                        @Override
                                        public String getQueryParameter(String parameterName) {
                                            return session.getParms().get(parameterName);
                                        }

                                        @Override
                                        public String getPath() {
                                            return path;
                                        }
                                    });

                            return newFixedLengthResponse(Response.Status.OK, "application/json",
                                    objectWriter.writeValueAsString(returnObject));
                        } catch (Exception e) {
                            return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, NanoHTTPD.MIME_PLAINTEXT,
                                    "ERROR: " + e.getMessage());
                        }
                    }
                }
            }
        }

        // ... could be extended for other methods, e.g. POST, PUT, DELETE

        return newFixedLengthResponse(Response.Status.NOT_FOUND, NanoHTTPD.MIME_PLAINTEXT, "Path/Method not supported");

    }
}
