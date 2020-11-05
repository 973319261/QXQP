package com.gx.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Bsgrid {
	public static JSONObject getJson(JSONArray json,int curPage,int totalRows){
		JSONObject jsons=new JSONObject();
		jsons.put("data", json);
		jsons.put("curPage", curPage);
		jsons.put("totalRows", totalRows);
		jsons.put("success", true);
		return jsons;
	}
	public static <T> JSONObject getList(List<T> list,int curPage,int totalRows){
		JSONObject jsons=new JSONObject();
		jsons.put("data", list);
		jsons.put("curPage", curPage);
		jsons.put("totalRows", totalRows);
		jsons.put("success", true);
		return jsons;
	}
}
