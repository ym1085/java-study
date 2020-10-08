package com.java.study.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		// [1] Map<k, v>
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("a", "A");
		map.put("b", "B");
		map.put("c", "C");
		
		System.out.println("a : " + map.get("a"));
		System.out.println("b : " + map.get("b"));
		System.out.println("c : " + map.get("c"));
		System.out.println();
		
		map.remove("a");
		
		System.out.println("a : " + map.get("a"));
		System.out.println();
		
		// [2] HashMap<k, v>
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("a", "Brown");
		paramMap.put("b", "Jane");
		paramMap.put("c", "Aplil");
		paramMap.put("d", "Kane");
		paramMap.put("e", "Mike");
		paramMap.put("f", "Michel");
		
		
		Set<String> paramKey = paramMap.keySet();
		for(String key : paramKey) {
			System.out.print(key + "\t");
		}
		
		System.out.println();
		
		for(String key : paramKey) {
			System.out.print(paramMap.get(key) + "\t");
		}
		
	}
}
