package lite.crud.application.util.export;

import lite.crud.application.util.export.support.ExportEasyExcelHandler;
import lite.crud.application.util.export.support.ExportHandler;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
public class ExportUtil {

    private static final ExportHandler EXPORT_HANDLER = new ExportEasyExcelHandler();

    public static <T> void doExport(final OutputStream outputStream, final Class<T> clazz, final List<T> dataList) {
        EXPORT_HANDLER.doExport(outputStream, clazz, dataList);
    }

    public static <T> void doBatchExport(final OutputStream outputStream, final Consumer<Consumer<Collection<T>>> sharding, final Class<T> clazz) {
        EXPORT_HANDLER.doBatchExport(outputStream, sharding, clazz);
    }
}
