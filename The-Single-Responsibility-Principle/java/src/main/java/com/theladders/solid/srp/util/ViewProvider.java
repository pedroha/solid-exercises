package com.theladders.solid.srp.util;


public interface ViewProvider
{
  Result getViewResult();

  void putData(String key,
               Object data);
}
