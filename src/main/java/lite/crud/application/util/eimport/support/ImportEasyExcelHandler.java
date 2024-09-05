package lite.crud.application.util.eimport.support;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
public class ImportEasyExcelHandler implements ImportHandler {

    @Override
    public <T> List<T> doImport(final InputStream inputStream, final Class<T> clazz) {
        final List<T> dataList = new ArrayList<>();
        this.doMultipleImport(new InputStream[]{inputStream}, clazz, dataList::addAll, 10000);
        return dataList;
    }

    @Override
    public <T> void doMultipleImport(final InputStream[] inputStreams, final Class<T> clazz, final Consumer<Collection<T>> dataListConsumer, final Integer batchSize) {
        for (final InputStream inputStream : inputStreams) {
            final Integer realBatchSize = Optional.ofNullable(batchSize).orElse(1000);
            List<T> dataList = new ArrayList<>(realBatchSize);

            // 执行核心 import 逻辑
            EasyExcel.read(inputStream, clazz, new AnalysisEventListener<T>() {
                @Override
                public void invoke(final T data, final AnalysisContext context) {
                    dataList.add(data);

                    // 批量消费
                    if (dataList.size() >= realBatchSize) {
                        try {
                            dataListConsumer.accept(dataList);
                        } finally {
                            dataList.clear();
                        }
                    }
                }

                @Override
                public void doAfterAllAnalysed(final AnalysisContext context) {
                    if (!dataList.isEmpty()) {
                        try {
                            dataListConsumer.accept(dataList);
                        } finally {
                            dataList.clear();
                        }
                    }
                }
            }).doReadAll();
        }
    }
}
