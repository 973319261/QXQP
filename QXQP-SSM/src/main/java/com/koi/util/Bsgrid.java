package com.koi.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Bsgrid {
	public static JSONObject getJson(JSONArray json,Long curPage,Long totalRows){
		JSONObject jsons=new JSONObject();
		jsons.put("data", json);
		jsons.put("curPage", curPage);
		jsons.put("totalRows", totalRows);
		jsons.put("success", true);
		return jsons;
	}
	public static <T> Map getList(List<T> list,Long curPage,Long totalRows){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("curPage", curPage);
		map.put("totalRows", totalRows);
		map.put("success", true);
		return map;
	}
}
