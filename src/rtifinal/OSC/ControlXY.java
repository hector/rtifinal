package rtifinal.OSC;

public abstract class ControlXY extends Control {
  
  public ControlXY(String prefix, String suffix) {
    super(prefix, suffix);
    values = new Object[] {(float)0, (float)0};
    valuesClass = new Class[] {float.class, float.class};
  }
  
}
