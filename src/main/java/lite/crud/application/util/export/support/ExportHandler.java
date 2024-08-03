package lite.crud.application.util.export.support;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
public interface ExportHandler {

    /**
     * 同步导出
     */
    <T> void doExport(final OutputStream outputStream, final Class<T> clazz, final List<T> dataList);

    /**
     * 异步导出大量数据
     */
    <T> void doBatchExport(final OutputStream outputStream, final Consumer<Consumer<Collection<T>>> sharding, Class<T> clazz);
}
