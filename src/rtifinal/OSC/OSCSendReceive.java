package rtifinal.OSC;

import oscP5.*;
import java.io.*;
import java.net.*;
import java.util.*;
import netP5.NetAddress;
import rtifinal.Main;

public class OSCSendReceive {

  OscP5 oscP5;
  float xacc;
  float yacc;
  float zacc;
  OscMessage theOscMessage;
  NetAddress myRemoteLocation;

  public OSCSendReceive() {
    myRemoteLocation = new NetAddress("127.0.0.1", 12000);
    oscP5 = new OscP5(this, 9000);
  }

  public void oscSend() {

    OscMessage myMessage1 = new OscMessage("/test1");
    OscMessage myMessage2 = new OscMessage("/test2");
    OscMessage myMessage3 = new OscMessage("/test3");

    myMessage1.add(Main.applet.mouseX);
    myMessage2.add(Main.applet.mouseY);
    myMessage3.add(3);

    //System.out.println("TestOSCsend " + (int)xacc);
    //System.out.println("TestOSCx " + (int)yacc);

    oscP5.send(myMessage1, myRemoteLocation);
    oscP5.send(myMessage2, myRemoteLocation);
    oscP5.send(myMessage3, myRemoteLocation);
  }

  public void oscEvent(OscMessage theOscMessage) {

    if (theOscMessage.checkAddrPattern("/accxyz") == true) {
      if (theOscMessage.checkTypetag("fff")) {
        xacc = theOscMessage.get(0).floatValue();
        yacc = theOscMessage.get(1).floatValue();
        zacc = theOscMessage.get(2).floatValue();
//        System.out.println("TestOSCReceiveX " + xacc);
//        System.out.println("TestOSCReceiveY " + yacc);
//        System.out.println("TestOSCReceiveZ " + zacc);  
      }
    }


  }
}
