package de.schneider21.what2eat.framework;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class RestController {

    public interface IRequestParameters {
        String getQueryParameter(String parameterName);

        String getPath();
    }

    private final Map<String, Function<IRequestParameters, Object>> getMappings = new HashMap<>();

    /**
     * Registers a path pattern to be handled by one of the controllers functions
     *
     * @param pathPattern is matched as regular expression
     * @param handler     the function to be called. The returned object is serialized as JSON
     */
    protected void addHttpGetMapping(String pathPattern, Function<IRequestParameters, Object> handler) {
        getMappings.put(pathPattern, handler);
    }

    public Map<String, Function<IRequestParameters, Object>> httpGetMappings() {
        return getMappings;
    }
}
