package util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author Filip Stiglic
 */
public class JsonUtils {

	public static String toJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
