package bencoepp.livius.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class COWUtil {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    /**
     * Retrieves all properties with a given prefix from the environment and returns them as a map.
     *
     * @param prefix the prefix to use for filtering the properties
     * @return a map containing the properties with the given prefix
     */
    public Map<String,String> getAllPropWithPrefix(String prefix) {
        BindResult<Map<String, String>> result = Binder.get(applicationContext.getEnvironment())
                .bind(prefix, Bindable.mapOf(String.class, String.class));
        if (!result.isBound() || result.get()==null) {
            return Collections.emptyMap();
        }
        return result.get().entrySet().stream().collect(Collectors.toMap(x->prefix+"."+x.getKey(), Map.Entry::getValue));
    }
}
