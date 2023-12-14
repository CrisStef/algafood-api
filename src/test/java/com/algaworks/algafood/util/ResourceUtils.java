package com.algaworks.algafood.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StreamUtils;

public class ResourceUtils {

	public static String getContentFromResource(String resourceName) {
		try {
			InputStream stream = ResourceUtils.class.getResourceAsStream(resourceName);
			return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getJsonDataObject(String resourceName, String dataGroup) {
		JSONObject jsonDataObject = null;

		try {
			jsonDataObject = new JSONObject(getContentFromResource(resourceName));
		
			assert jsonDataObject != null;
		
			return jsonDataObject.get(dataGroup).toString();

		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
}