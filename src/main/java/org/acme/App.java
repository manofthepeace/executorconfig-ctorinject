package org.acme;

import java.util.stream.IntStream;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.jboss.logging.Logger;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.context.api.ManagedExecutorConfig;
import jakarta.enterprise.event.Observes;

public class App {

    private static final Logger LOGGER = Logger.getLogger("App");

    // @Inject
    // @ManagedExecutorConfig(maxAsync = 5)
    ManagedExecutor executor;

    App(@ManagedExecutorConfig(maxAsync = 5) ManagedExecutor executor) {
        this.executor = executor;
    }

    void onStart(@Observes StartupEvent ev) {
        IntStream.range(0, 100).forEach(i -> executor.runAsync(() -> LOGGER.info(i)));
    }

}
