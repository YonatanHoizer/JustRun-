
import javax.swing.*;
import java.awt.*;

       public class main extends JFrame {
            private character c;
            private camera camera;
            private int width = 800;
            private int height = 600;

            public main() {

                c = new character(width,height);

                setTitle("Game Window");

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setSize(width, height);

                setLocationRelativeTo(null);

                setVisible(true);

                add(c);

                repaint();
            }


           public static void main(String[] args) {

                main m = new main();
            }
       }



