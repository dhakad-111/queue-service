package com.nimesa.tech.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Redis {
    public static Map<String, List<String>> subscribers = new HashMap<>();
    public static Map<String, List<String>> messages = new HashMap<>();
}
