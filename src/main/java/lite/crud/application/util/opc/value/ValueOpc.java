package lite.crud.application.util.opc.value;

/**
 * @author xl-9527
 * @since 2024/8/24
 */
public interface ValueOpc<T, FORMAT, FORMAT_RESULT> {

	FORMAT_RESULT format(T val, FORMAT format);
}
