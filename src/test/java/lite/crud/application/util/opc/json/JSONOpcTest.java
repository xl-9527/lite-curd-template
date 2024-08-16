package lite.crud.application.util.opc.json;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/8/16
 */
@SpringBootTest
class JSONOpcTest {

	@Autowired
	private JSONOpc jsonOpc;

	private final Person person = new Person(1, "xl-9527");

	@Test
	void toJSONStr() {
		String jsonStr = jsonOpc.toJSONStr(person);
		assert jsonStr.equals("{\"age\":1,\"name\":\"xl-9527\"}");
	}

	@Test
	void parseObject() {
		Person parsed = jsonOpc.parseObject("{\"age\":2,\"name\":\"xl-9527\"}", Person.class);
		assert parsed.age() == 2;
	}

	@Test
	void parseArray() {
		List<Person> people = jsonOpc.parseArray("[{\"age\":2,\"name\":\"xl-9527\"}]", Person.class);
		assert people.get(0).age() == 2;
		assert people.size() == 1;
	}

	@Test
	void supportLib() {
		assert jsonOpc.supportLib().equals(JSONOpcEnum.JACKSON);
	}
}
