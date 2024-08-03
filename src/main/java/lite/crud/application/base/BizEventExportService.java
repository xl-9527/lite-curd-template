package lite.crud.application.base;

import lite.crud.application.BizEventService;

import java.io.OutputStream;
import java.util.List;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
public interface BizEventExportService extends BizEventService {

    <T> void doExport(final OutputStream outputStream, final List<T> dataList, final Class<T> clazz);
}
