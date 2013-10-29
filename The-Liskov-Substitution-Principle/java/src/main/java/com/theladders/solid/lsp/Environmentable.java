package com.theladders.solid.lsp;

public interface Environmentable
{
  String get(String name);
  void put(String key, String value);
}
