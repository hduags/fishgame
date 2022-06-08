package FishGame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class DataImg {
    public static URL start_path = DataImg.class.getResource("../Img/begin_picture.jpg");
    public static URL fish08_path = DataImg.class.getResource("../Img/fish08.png");
    public static URL fish12_path = DataImg.class.getResource("../Img/fish12.png");
    public static URL fish03_path = DataImg.class.getResource("../Img/fish03.png");
    public static URL fish17_path = DataImg.class.getResource("../Img/fish17.png");
    public static URL background_path = DataImg.class.getResource("../Img/background2.jpeg");
    public static URL fish06_path = DataImg.class.getResource("../Img/fish06.png");
    public static URL lantern_path = DataImg.class.getResource("../Img/fish.png");

    public static Image start = new ImageIcon(start_path).getImage();
    public static Image fish08 = new ImageIcon(fish08_path).getImage();
    public static Image fish12 = new ImageIcon(fish12_path).getImage();
    public static Image fish03 = new ImageIcon(fish03_path).getImage();
    public static Image fish17 = new ImageIcon(fish17_path).getImage();
    public static Image background = new ImageIcon(background_path).getImage();
    public static Image fish06 = new ImageIcon(fish06_path).getImage();
    public static Image lantern = new ImageIcon(lantern_path).getImage();
}
