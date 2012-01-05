package rtifinal.OSC;

import java.lang.reflect.Method;
import oscP5.OscMessage;

public abstract class Control {

  String oscAddr;
  Object[] values;
  Class[] valuesClass;
  Method method;
  Object receiver;
  Object[] staticArgs;
  
  public Control(String prefix, String suffix) {
    if(prefix == null) prefix = "";
    if(suffix == null) suffix = "";
    oscAddr = prefix + oscStr() + suffix;
  }
  
  public Control(String prefix, int id) {
    this(prefix, String.valueOf(id));
  }
  
  protected abstract String oscStr();  
  
  public String getOscAddr() {
    return oscAddr;
  }
  
  public void oscEvent(OscMessage msg) throws Exception {
    for(int i=0; i < values.length; i++) values[i] = msg.get(i).floatValue();
    invokeMethod();
  }    
  
  protected void invokeMethod() throws Exception {
    if(method != null) {
      Object[] args;
      if(staticArgs != null) {
        int argsLength = values.length+staticArgs.length;
        args = new Object[argsLength];
        for(int i=values.length; i < argsLength; i++) args[i] = staticArgs[i-staticArgs.length];
      }
      else args = new Object[values.length];
      copyValues(args);
      method.invoke(receiver, args);
    }
  }
  
  protected void copyValues(Object[] args) {
    System.arraycopy(values, 0, args, 0, values.length);
  }

  public OscMessage oscMessage() {
    OscMessage msg = new OscMessage(oscAddr);
    float value;
    for(int i=0; i < values.length; i++) {
      value = (Float)values[i];
      msg.add(value);
    }
    return msg;
  }
  
  public void map(Object receiver, String method, Object... args) throws Exception {
    this.receiver = receiver;
    this.staticArgs = args;
    this.method = receiver.getClass().getMethod(method, argClasses());
  }
  
  private Class[] argClasses() {
    Class[] classes;
    if(staticArgs != null) {
      int classesLength = values.length+staticArgs.length;
      classes = new Class[classesLength];
      for(int i=values.length; i < classesLength; i++) 
        classes[i] = staticArgs[i-staticArgs.length].getClass();
    }
    else classes = new Class[values.length];
    System.arraycopy(valuesClass, 0, classes, 0, values.length);
    return classes;
  }
  
  protected boolean boolValue(int index) {
    return ((Float)values[index]) == 1;
  }
  
}
