package rtifinal.OSC;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import oscP5.*;
import java.io.*;
import java.net.*;
import java.util.*;
import netP5.NetAddress;
import rtifinal.Main;

public class OSCSendReceive {

  OscP5 oscP5;
  public static float xpos, ypos,toggle1,toggle2,toggle3,xacc,yacc,zacc;
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

//    oscP5.send(myMessage1, myRemoteLocation);
//    oscP5.send(myMessage2, myRemoteLocation);
//    oscP5.send(myMessage3, myRemoteLocation);
  }

  public void oscEvent(OscMessage theOscMessage) {
    
    //Take Accelerometer values
    if (theOscMessage.checkAddrPattern("/accxyz") == true) {
      if (theOscMessage.checkTypetag("fff")) {
        oscP5.send(theOscMessage, myRemoteLocation);

        xacc = theOscMessage.get(0).floatValue();
        yacc = theOscMessage.get(1).floatValue();
        zacc = theOscMessage.get(2).floatValue();

      }
    }
    
    // Take XY Pad values
    if (theOscMessage.checkAddrPattern("/4/xy") == true) {
      if (theOscMessage.checkTypetag("ff")) {
        ypos = theOscMessage.get(0).floatValue();
        xpos = theOscMessage.get(1).floatValue();
        //System.out.println("xpos " +xpos);
      }
    }
    
    // Take tab4 toggle values
    if (theOscMessage.checkAddrPattern("/4/toggle1") == true) {
        toggle1 = theOscMessage.get(0).floatValue();
        //System.out.println("toggle1 " +toggle1); 
    }
    if (theOscMessage.checkAddrPattern("/4/toggle2") == true) {
        toggle2 = theOscMessage.get(0).floatValue();
        //System.out.println("toggle1 " +toggle1); 
    }
    if (theOscMessage.checkAddrPattern("/4/toggle3") == true) {
        toggle3 = theOscMessage.get(0).floatValue();
        //System.out.println("toggle1 " +toggle1); 
    }

    /**
     * *TAB1**
     */
    if (theOscMessage.checkAddrPattern("/1/fader1") == true || theOscMessage.checkAddrPattern("/1/fader2") == true || theOscMessage.checkAddrPattern("/1/toggle1") == true || theOscMessage.checkAddrPattern("/1/toggle2") == true || theOscMessage.checkAddrPattern("/1/push1") == true || theOscMessage.checkAddrPattern("/1/push2") == true || theOscMessage.checkAddrPattern("/1/push3") == true || theOscMessage.checkAddrPattern("/1/push4") == true || theOscMessage.checkAddrPattern("/1/push5") == true || theOscMessage.checkAddrPattern("/1/push6") == true || theOscMessage.checkAddrPattern("/1/push7") == true || theOscMessage.checkAddrPattern("/1/push8") == true || theOscMessage.checkAddrPattern("/1/push9") == true || theOscMessage.checkAddrPattern("/1/push10") == true || theOscMessage.checkAddrPattern("/1/push11") == true || theOscMessage.checkAddrPattern("/1/push12") == true) {

      oscP5.send(theOscMessage, myRemoteLocation);
    }

    /**
     * *TAB2**
     */
    if (theOscMessage.checkAddrPattern("/2/multifader/1") == true || theOscMessage.checkAddrPattern("/2/multifader/2") == true || theOscMessage.checkAddrPattern("/2/multifader/3") == true || theOscMessage.checkAddrPattern("/2/multifader/4") == true || theOscMessage.checkAddrPattern("/2/multifader/5") == true || theOscMessage.checkAddrPattern("/2/multifader/6") == true || theOscMessage.checkAddrPattern("/2/multifader/7") == true || theOscMessage.checkAddrPattern("/2/multifader/8") == true || theOscMessage.checkAddrPattern("/2/multifader/9") == true || theOscMessage.checkAddrPattern("/2/multifader/10") == true || theOscMessage.checkAddrPattern("/2/multifader/11") == true || theOscMessage.checkAddrPattern("/2/multifader/12") == true || theOscMessage.checkAddrPattern("/2/multifader/13") == true || theOscMessage.checkAddrPattern("/2/multifader/14") == true || theOscMessage.checkAddrPattern("/2/multifader/15") == true || theOscMessage.checkAddrPattern("/2/multifader/16") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/1") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/2") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/3") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/4") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/5") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/6") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/7") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/8") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/9") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/10") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/11") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/12") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/13") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/14") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/15") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/16") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/1") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/2") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/3") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/4") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/5") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/6") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/7") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/8") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/9") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/10") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/11") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/12") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/13") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/14") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/15") == true || theOscMessage.checkAddrPattern("/2/multitoggle/2/16") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/1") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/2") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/3") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/4") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/5") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/6") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/7") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/8") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/9") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/10") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/11") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/12") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/13") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/14") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/15") == true || theOscMessage.checkAddrPattern("/2/multitoggle/3/16") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/1") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/2") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/3") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/4") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/5") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/6") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/7") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/8") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/9") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/10") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/11") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/12") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/13") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/14") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/15") == true || theOscMessage.checkAddrPattern("/2/multitoggle/4/16") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/1") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/2") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/3") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/4") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/5") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/6") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/7") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/8") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/9") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/10") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/11") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/12") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/13") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/14") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/15") == true || theOscMessage.checkAddrPattern("/2/multitoggle/5/16") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/1") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/2") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/3") == true || theOscMessage.checkAddrPattern("/2/multitoggle/1/4") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/5") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/6") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/7") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/8") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/9") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/10") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/11") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/12") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/13") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/14") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/15") == true || theOscMessage.checkAddrPattern("/2/multitoggle/6/16") == true) {

      oscP5.send(theOscMessage, myRemoteLocation);
    }
    /**
     * *TAB3**
     */
    if (theOscMessage.checkAddrPattern("/3/rotary1") == true || theOscMessage.checkAddrPattern("/3/rotary2") == true || theOscMessage.checkAddrPattern("/3/rotary3") == true || theOscMessage.checkAddrPattern("/3/rotary4") == true || theOscMessage.checkAddrPattern("/3/rotary5") == true || theOscMessage.checkAddrPattern("/3/rotary6") == true || theOscMessage.checkAddrPattern("/3/toggle1") == true || theOscMessage.checkAddrPattern("/3/toggle2") == true || theOscMessage.checkAddrPattern("/3/toggle3") == true || theOscMessage.checkAddrPattern("/3/toggle4") == true || theOscMessage.checkAddrPattern("/3/toggle5") == true) {
      oscP5.send(theOscMessage, myRemoteLocation);
    }
    /**
     * *TAB4**
     */
    if (theOscMessage.checkAddrPattern("/4/xy") == true || theOscMessage.checkAddrPattern("/4/toggle1") == true || theOscMessage.checkAddrPattern("/4/toggle2") == true || theOscMessage.checkAddrPattern("/4/toggle3") == true || theOscMessage.checkAddrPattern("/4/toggle4") == true || theOscMessage.checkAddrPattern("/4/toggle5") == true) {
      oscP5.send(theOscMessage, myRemoteLocation);


      //toggle1 = theOscMessage.get(2).booleanValue();
      //System.out.println("toggle1 " + toggle1);
    }
  }
}
