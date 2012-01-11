package rtifinal.OSC;

public abstract class ControlX extends Control {
  
  public ControlX(String prefix, String suffix) {
    super(prefix, suffix);
    initValues();
  }
  
  public ControlX(String prefix, int id) {
    super(prefix, id);
    initValues();
  } 
  
  private void initValues() {
    values = new Object[] {(float)0};
    valuesClass = new Class[] {float.class};
  }
  
  public float getValue() {
    return (Float) values[0];
  }

}
