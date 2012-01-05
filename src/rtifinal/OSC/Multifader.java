package rtifinal.OSC;

public class Multifader extends ControlX {
  
  public Multifader(String prefix, int id) {
    super(prefix, "/"+String.valueOf(id));
  }
  
  @Override
  protected String oscStr() {
    return "/multifader";
  }    

}
