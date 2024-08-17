package lite.crud.application.util.opc.json;

import lite.crud.application.util.extend.SpringContextHolder;

import java.util.List;

/**
 * default with jackson
 *
 * @author xl-9527
 * @since 2024/8/16
 */
public class JSONOpcUtil implements JSONOpc {

	private final JSONOpc jsonOpc;

	/* JACKSON */
	public final static JSONOpcUtil DEFAULT = new JSONOpcUtil(JSONOpcEnum.JACKSON);

	public JSONOpcUtil(JSONOpcEnum jsonOpcEnum) {
		this.jsonOpc = jsonOpcEnum.getJSONOpc(SpringContextHolder.getApplicationContext());
	}

	@Override
	public String toJSONStr(Object obj) {
		return jsonOpc.toJSONStr(obj);
	}

	@Override
	public <T> T parseObject(String jsonStr, Class<T> clazz) {
		return jsonOpc.parseObject(jsonStr, clazz);
	}

	@Override
	public <T> List<T> parseArray(String jsonStr, Class<T> clazz) {
		return jsonOpc.parseArray(jsonStr, clazz);
	}

	@Override
	public JSONOpcEnum supportLib() {
		return jsonOpc.supportLib();
	}
}
