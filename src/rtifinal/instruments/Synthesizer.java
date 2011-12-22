package rtifinal.instruments;

import rtifinal.effects.*;
import rtifinal.graphics.Cube;

public class Synthesizer extends Instrument {

  protected Delay delay;
  protected Distortion distortion;
  protected Reverb reverb;
  protected Cube cube;

  public Synthesizer() {
    delay = null;
    distortion = null;
    reverb = null;
    //instantiate cube
    cube = new Cube(200, 200, 200);
  }

  public void draw() {
    //p5.translate(p5.mouseX,p5.mouseY);
    //automatic rotation
    //cube.spinnyRotateX(p5.mouseX);
    //cube.spinnyRotateY(p5.mouseY);
    //cube.spinnyRotateZ(p5.frameCount * p5.PI / 5);
    //draw cube
    cube.draw();
  }
}
