package is.teamgoomba.TicTacToe;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldTest {

  @Test
	public void testSomeLibraryMethod() {
    HelloWorld test = new HelloWorld();
    assertEquals(test.hello(), "Hello, World");
  }

  @Test
  public void testMainMethod() {
    HelloWorld test = new HelloWorld();
    try {
      String[] args = new String[0];
      test.main(args);
    } catch(Exception e) {
      fail("Exception caught");
    }
  }
  
  @Test
  public void testGetPort() {
    HelloWorld test = new HelloWorld();
	assertEquals(test.getPort(null), null);
	assertEquals(test.getPort("8080"), "8080");
  }
}