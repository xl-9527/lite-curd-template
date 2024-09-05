package lite.crud.application.util.eimport.support;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
public interface ImportHandler {

    <T> List<T> doImport(final InputStream inputStream, final Class<T> clazz);

    <T> void doMultipleImport(final InputStream[] inputStreams, final Class<T> clazz, final Consumer<Collection<T>> dataListConsumer, final Integer batchSize);
}
