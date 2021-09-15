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
		String dataOutput = "";

		try {
		InputStream jsonFileObject = ResourceUtils.class.getResourceAsStream(resourceName);

		String json = StreamUtils.copyToString(jsonFileObject, Charset.forName("UTF-8"));

		jsonDataObject = new JSONObject(json);
	
		assert jsonDataObject != null;
	
		jsonDataObject = (JSONObject) jsonDataObject.get(dataGroup);
		dataOutput = jsonDataObject.toString();

		return dataOutput;

		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}

		return dataOutput;
	}
}