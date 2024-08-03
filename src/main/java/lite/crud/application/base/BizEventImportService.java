package lite.crud.application.base;

import lite.crud.application.BizEventService;

import java.io.InputStream;
import java.util.List;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
public interface BizEventImportService<T> extends BizEventService {

    List<T> doImport(final InputStream inputStream, final Class<T> clazz);

    void doImports(final InputStream[] inputStream, final Class<T> clazz);
}
