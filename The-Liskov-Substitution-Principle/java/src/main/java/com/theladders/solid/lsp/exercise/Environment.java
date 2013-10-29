package com.theladders.solid.lsp.exercise;

public interface Environment
{
  void set(String name, String value);
  String get(String name);
}
