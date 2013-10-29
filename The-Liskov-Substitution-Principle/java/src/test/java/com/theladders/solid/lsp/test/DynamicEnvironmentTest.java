package com.theladders.solid.lsp.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import com.theladders.solid.lsp.DynamicEnvironment;
import com.theladders.solid.lsp.Environment;


public class DynamicEnvironmentTest extends TestCase
{
  @Test
  public void testPutAndGetInDynamicEnvironment()
  {
    // Do I get what I put onto DynamicEnvironment? Yes
    Environment env = new Environment();
    Map<String, String> keyMap = new HashMap<>();
    Environment dynEnv = new DynamicEnvironment(env, keyMap);
    
    dynEnv.put("home", "http://www.theladders.com");
    
    assertEquals(dynEnv.get("home"), "http://www.theladders.com");
  }
  
  @Test
  public void testPropertyInEnvironment()
  {
    String key = "home";
    String value = "http://www.theladders.com/home";
  
    Environment base = new Environment();
    Map<String, String> keyMap = new HashMap<>();

    base.put(key, value);
    
    Environment dynEnv = new DynamicEnvironment(base, keyMap);
   
    assertEquals(dynEnv.get(key), value);
  }

  @Test
  public void testGenericPrecedesBaseProperty()
  {
    Environment base = new Environment();
    Map<String, String> keyMap = new HashMap<>();
    
    base.put("home", "http://www.theladders.com/home");
    
    Environment dynEnv = new DynamicEnvironment(base, keyMap);
    
    dynEnv.put("home", "http://www.theladders.com/dynamic");
   
    assertEquals(dynEnv.get("home"), "http://www.theladders.com/dynamic");
  }

  @Test
  public void testMappedPropertyInBase()
  {
    Environment base = new Environment();
    Map<String, String> keyMap = new HashMap<>();
    
    keyMap.put("memberHome", "home");
    
    base.put("home", "http://www.theladders.com/home");
    
    Environment dynEnv = new DynamicEnvironment(base, keyMap);
    
    // Unmapped and mapped property should return the same
    assertEquals(dynEnv.get("memberHome"), "http://www.theladders.com/home");
    assertEquals(dynEnv.get("home"), "http://www.theladders.com/home");
  }

  @Test
  public void testMappedPropertyInBaseOverridenByGeneric()
  {
    Environment env = new Environment();
    Map<String, String> keyMap = new HashMap<>();
    
    keyMap.put("memberHome", "home");
    
    env.put("home", "http://www.theladders.com/home");
    
    Environment dynEnv = new DynamicEnvironment(env, keyMap);
    
    dynEnv.put("home", "http://www.theladders.com/generic");
    
    // Unmapped and mapped property should return the same
    assertEquals(dynEnv.get("memberHome"), "http://www.theladders.com/generic");
    assertEquals(dynEnv.get("home"), "http://www.theladders.com/generic");
  }

  @Test
  public void testKeyEntrySetCount()
  {
    Environment env = new Environment();
    Map<String, String> keyMap = new HashMap<>();
    
    keyMap.put("memberHome", "home");
    
    env.put("home", "http://www.theladders.com/home");
    
    Environment dynEnv = new DynamicEnvironment(env, keyMap);
    
    dynEnv.put("home", "http://www.theladders.com/generic");
    
    Set<Object> keys = dynEnv.keySet();
    Set<Map.Entry<Object, Object>> values = dynEnv.entrySet();

    assertEquals(keys.size(), values.size());
    assertEquals(keys.size(), 2);
  }

  @Test
  public void testValuesOnlyComingFromBase()
  {
    Environment env = new Environment();
    Map<String, String> keyMap = new HashMap<>();
    
    keyMap.put("memberHome", "home");
    
    env.put("home", "http://www.theladders.com/home");
    
    Environment dynEnv = new DynamicEnvironment(env, keyMap);
    
    dynEnv.put("home", "http://www.theladders.com/generic");
    
    Object[] values = dynEnv.values().toArray();

    assertEquals(values.length, 1);
    assertEquals(values[0], "http://www.theladders.com/home");
  }
}

