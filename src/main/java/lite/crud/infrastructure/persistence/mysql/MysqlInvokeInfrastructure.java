package lite.crud.infrastructure.persistence.mysql;

import lite.crud.application.base.BizEventCrudService;
import lite.crud.infrastructure.InvokeInfrastructure;

/**
 * @author xl-9527
 * @since 2024/8/27
 */
public class MysqlInvokeInfrastructure<T extends BizEventCrudService<?, ?, ?>> implements InvokeInfrastructure<T> {

    private final T t;

    public MysqlInvokeInfrastructure(final T t) {
        this.t = t;
    }

    @Override
    public T invoke() {
        return t;
    }
}
