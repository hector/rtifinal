package rtifinal.OSC;

public class XYPad extends ControlXY {
  
  public XYPad(String prefix, String suffix) {
    super(prefix, suffix);
  }
  
  @Override
  protected String oscStr() {
    return "/xy";
  }    
  
}
