package rtifinal.OSC;

import oscP5.OscMessage;

public class Push extends Control {
  
  public Push(String prefix, int id) {
    super(prefix, id);
  }
  
  @Override
  protected String oscStr() {
    return "/push";
  }    
  
  @Override
  public void oscEvent(OscMessage msg) throws Exception {}
  
  @Override
  public OscMessage oscMessage() {
    return null;
  }  

}
