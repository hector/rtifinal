package rtifinal.OSC;

public class Rotary extends ControlX {
  
  public Rotary(String prefix, int id) {
    super(prefix, id);
  }
  
  @Override
  protected String oscStr() {
    return "/rotary";
  }    
  
}
