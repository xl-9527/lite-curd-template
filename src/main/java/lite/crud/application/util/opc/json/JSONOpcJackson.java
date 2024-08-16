package lite.crud.application.util.opc.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/8/16
 */
@Component
public class JSONOpcJackson implements JSONOpc {

	private final ObjectMapper objectMapper;

	public JSONOpcJackson(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public String toJSONStr(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new JsonParseException(e);
		}
	}

	@Override
	public <T> T parseObject(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (JsonProcessingException e) {
			throw new JsonParseException(e);
		}
	}

	@Override
	public <T> List<T> parseArray(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
		} catch (JsonProcessingException e) {
			throw new JsonParseException(e);
		}
	}

	@Override
	public JSONOpcEnum supportLib() {
		return JSONOpcEnum.JACKSON;
	}
}
