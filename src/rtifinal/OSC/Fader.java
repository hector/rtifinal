package rtifinal.OSC;

public class Fader extends ControlX {
  
  public Fader(String prefix, int id) {
    super(prefix, id);
  }
  
  @Override
  protected String oscStr() {
    return "/fader";
  }   

}
