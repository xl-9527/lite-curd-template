package lite.crud.application.util.export.support;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
public class ExportEasyExcelHandler implements ExportHandler {

    private final Logger log = LoggerFactory.getLogger(ExportEasyExcelHandler.class);

    @Override
    public <T> void doExport(final OutputStream outputStream, final Class<T> clazz, final List<T> dataList) {
        this.doBatchExport(outputStream, (childrenConsumer) -> childrenConsumer.accept(dataList), clazz);
    }

    @Override
    public <T> void doBatchExport(final OutputStream outputStream, final Consumer<Consumer<Collection<T>>> sharding, final Class<T> clazz) {
        try (final ExcelWriter writer = EasyExcel.write(outputStream, clazz).build()) {
            final WriteSheet sheet = EasyExcel.writerSheet("sheet").build();
            sharding.accept((dataList) -> writer.write(dataList, sheet));
        } catch (Exception e) {
            log.error("导入数据异常 -> ", e);
        }
    }
}
