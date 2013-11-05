package com.theladders.solid.ocp.resume;

import java.util.ArrayList;
import java.util.List;

public class PhraseListContainer
{
  private List<ConfidentialPhraseCategory> phraseList = new ArrayList<ConfidentialPhraseCategory>();

  public List<ConfidentialPhraseCategory> values()
  {
    return phraseList;
  }
  
  public int indexOf(ConfidentialPhraseCategory category)
  {
    return phraseList.indexOf(category);
  }
  
  public void add(ConfidentialPhraseCategory category)
  {
    phraseList.add(category);    
  }
}
