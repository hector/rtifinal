package rtifinal.OSC;

import oscP5.OscMessage;

public class Push extends ControlX {
  
  public Push(String prefix, int id) {
    super(prefix, id);
  }
  
  @Override
  protected String oscStr() {
    return "/push";
  }    
  
  @Override
  public OscMessage oscMessage() {
    return null;
  }  

}
