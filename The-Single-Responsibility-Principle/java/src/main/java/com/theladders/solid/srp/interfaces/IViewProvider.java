package com.theladders.solid.srp.interfaces;

import java.util.Map;

import com.theladders.solid.srp.Result;

public interface IViewProvider
{
  Result getViewResult(Map<String, Object> model);
}
