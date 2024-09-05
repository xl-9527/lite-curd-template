package lite.crud.application.util.eimport;

import lite.crud.application.util.eimport.support.ImportEasyExcelHandler;
import lite.crud.application.util.eimport.support.ImportHandler;
import org.springframework.util.ObjectUtils;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
public class ImportUtil {

    private final static ImportHandler IMPORT_HANDLER = new ImportEasyExcelHandler();

    public static <T> List<T> doImport(final InputStream inputStream, final Class<T> clazz) {
        return IMPORT_HANDLER.doImport(inputStream, clazz);
    }

    public static <T> void doMultipleImport(final InputStream[] inputStreams, final Class<T> clazz, final Consumer<Collection<T>> dataListConsumer, final Integer batchSize) {
        if (ObjectUtils.isEmpty(dataListConsumer)) {
            throw new IllegalArgumentException("args [dataListConsumer] is null exception");
        }

        IMPORT_HANDLER.doMultipleImport(inputStreams, clazz, dataListConsumer, batchSize);
    }
}
