package rtifinal.OSC;

public class Toggle extends ControlX {
  
  public Toggle(String prefix, int id) {
    super(prefix, id);
    valuesClass = new Class[] {boolean.class};
  }
  
  public Toggle(String prefix, String suffix) {
    super(prefix, suffix);
    valuesClass = new Class[] {boolean.class};
  }
  
  @Override
  protected String oscStr() {
    return "/toggle";
  }  

  @Override
  protected void copyValues(Object[] args) {
    for(int i=0; i < values.length; i++) args[i] = boolValue(i);
  } 
  
  public boolean boolValue() {
    return boolValue(0);
  }

}
