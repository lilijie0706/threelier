package json;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class SampleDemo01 {
	public static void demo01() {
		Map<String,String> map = new HashMap<>();
		map.put("s01", "zs");
		map.put("s02", "ls");		
		map.put("s03", "ww");		
		map.put("s04", "ml");
		JSONObject json = new JSONObject(map);
		
		System.out.println("map格式为："+map);
		System.out.println("json格式为："+json);
		
/*		map格式为：{s02=ls, s01=zs, s04=ml, s03=ww}
		json格式为：{"s02":"ls","s01":"zs","s04":"ml","s03":"ww"}*/
	
	}
	//b.JavaBean(普通对象变成map)
	public static void demo02() {
		Person per = new Person();
		per.setName("zs");
		per.setAge(23);
		
		Address address = new Address("西安","北京");
		per.setAddress(address);
		/*Person>Json：{对象的属性名1：属性值1，......}
		 *Person格式转变为json格式：
		 *{"address":{"schoolAddress":"北京","homeAddress":"西安"},
		 *	"name":"zs",
		 *	"age":23}
		 */

		JSONObject json = new JSONObject(per);
		System.out.println("Person格式转变为json格式："+json);
		
	}
	public static void demo03() {
		String str ="{\"s02\":\"ls\",\"s01\":\"zs\",\"s04\":\"ml\",\"s03\":\"ww\"}";
		JSONObject json = new JSONObject(str);
		System.out.println("String格式转变为json格式："+str);
/*		String格式转变为json格式：
		{"s02":"ls","s01":"zs","s04":"ml","s03":"ww"}*/
		
	}

	
	public static void main(String[] args) {
		demo03();
	}

}
