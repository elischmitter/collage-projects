import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.Group;

import java.io.*;

import java.util.Scanner;

public class Program3 extends Application implements Program3Interface {
    private Group root = null;
    private WritableImage currimg = null;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Program 3 image manipulator");
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        ImageView image = new ImageView((WritableImage) null);
        Button btload = new Button("Load");
        Button btsave = new Button("Save");
        Button btflip = new Button("Flip");
        Button btinvert = new Button("Invert");
        Button btgrayscale = new Button("Grayscale");
        Button btpixelate = new Button("Pixelate");
        FileChooser fileChooser = new FileChooser();
        BorderPane borderPane = new BorderPane();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("ppm", "*.ppm")
        );

        btload.setOnAction(event -> {
                    File selectedFile = fileChooser.showOpenDialog(stage);
                    if (selectedFile != null) {
                        try {
                            currimg = loadImage(selectedFile.getPath());
                            ImageView newimg = new ImageView(currimg);
                            root = new Group(newimg);
                            borderPane.setCenter(root);
                        } catch (FileNotFoundException c) {
                            System.out.println(c.toString());
                        }
                    }
                }
        );
        btsave.setOnAction(event -> {
            fileChooser.setTitle("Save Image");
            System.out.println(image.toString());
            try {
                saveImage(fileChooser.showSaveDialog(stage).getPath(), currimg);
            } catch (NullPointerException ex) {
                System.out.println("error");
            }
        });
        btinvert.setOnAction(event -> {
                    currimg = invertImage(currimg);
                    ImageView newimg = new ImageView(currimg);
                    root = new Group(newimg);
                    borderPane.setCenter(root);
                }
        );
        btgrayscale.setOnAction(event -> {
            currimg = grayifyImage(currimg);
            ImageView newimg = new ImageView(currimg);
            root = new Group(newimg);
            borderPane.setCenter(root);
        });

        btflip.setOnAction(event -> {
                    currimg = flipImage(currimg);
                    ImageView newimg = new ImageView(currimg);
                    root = new Group(newimg);
                    borderPane.setCenter(root);


                }
        );
        btpixelate.setOnAction(event -> {
            currimg = pixelateImage(currimg);
            ImageView newimg = new ImageView(currimg);
            root = new Group(newimg);
            borderPane.setCenter(root);


        });
        hBox.getChildren().add(btload);
        hBox.getChildren().add(btsave);
        hBox.getChildren().add(btflip);
        hBox.getChildren().add(btinvert);
        hBox.getChildren().add(btgrayscale);
        hBox.getChildren().add(btpixelate);
        root = new Group(image);
        borderPane.setCenter(root);
        hBox.setPadding(new Insets(10, 10, 25, 10));
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        Scene scene = new Scene(borderPane, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * load an image
     *
     * @param filename of the image to be loaded
     * @return image that is loaded
     * @throws FileNotFoundException if the file is not found
     */
    public WritableImage loadImage(String filename) throws FileNotFoundException {
        File fileIn = new File(filename);
        Scanner in = new Scanner(fileIn);
        WritableImage image = null;
        try {
            in.nextLine();

            String temp = in.nextLine();
            int width;
            int height;
            if (temp.charAt(0) != '#') {
                Scanner temps = new Scanner(temp);
                width = temps.nextInt();
                height = temps.nextInt();
            } else {
                width = in.nextInt();
                height = in.nextInt();
            }
            int max = in.nextInt();
            int mult = 255 / max;
            image = new WritableImage(width, height);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    int pixelRed = in.nextInt() * mult;
                    int pixelGreen = in.nextInt() * mult;
                    int pixelBlue = in.nextInt() * mult;
                    Color pixelColor = Color.rgb(pixelRed, pixelGreen, pixelBlue);
                    image.getPixelWriter().setColor(x, y, pixelColor);
                }
            }

            in.close();
            currimg = image;
            return image;
        } catch (java.util.NoSuchElementException e) {
            System.out.println("not a supported file type");
        }
        return image;
    }

    /**
     * saves image
     *
     * @param filename the name for the file
     * @param image    the image to save
     */
    public void saveImage(String filename, WritableImage image) {

        try (PrintWriter out = new PrintWriter(filename)) {
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            out.printf("P3\n%d %d\n255\n", width, height);
            PixelReader pixel = image.getPixelReader();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color color = pixel.getColor(x, y);
                    double b = color.getBlue() * 255;
                    double r = color.getRed() * 255;
                    double g = color.getGreen() * 255;
                    out.printf("%d %d %d  ", (int) r, (int) g, (int) b);
                    // System.out.printf("%d %d %d  ",(int)r,(int)g,(int)b);
                }
                out.print("\n");
            }
        } catch (NullPointerException | FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * invert the colors of an image.
     *
     * @param image - the image to be inverted, do not modify!
     * @return newImage
     */

    public WritableImage invertImage(WritableImage image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixer = image.getPixelReader();
        PixelWriter pixew = image.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double g = pixer.getColor(x, y).getGreen() * 255;
                double r = pixer.getColor(x, y).getRed() * 255;
                double b = pixer.getColor(x, y).getBlue() * 255;
                Color pixelColor = Color.rgb((255 - (int) r), (255 - (int) g), (255 - (int) b));
                pixew.setColor(x, y, pixelColor);
            }
        }
        return image;
    }

    /**
     * @param image - the image to be converted to grayscale, do not modify!
     * @return newImage
     */
    public WritableImage grayifyImage(WritableImage image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixer = image.getPixelReader();
        PixelWriter pixew = image.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double g = pixer.getColor(x, y).getGreen() * 255;
                double r = pixer.getColor(x, y).getRed() * 255;
                double b = pixer.getColor(x, y).getBlue() * 255;
                Color pixelColor = Color.rgb((int) (0.2989 * r + 0.5870 * g + 0.1140 * b), (int) (0.2989 * r + 0.5870 * g + 0.1140 * b), (int) (0.2989 * r + 0.5870 * g + 0.1140 * b));
                pixew.setColor(x, y, pixelColor);
            }
        }
        return image;
    }

    /**
     * pixelate and image
     *
     * @param image - the image to be converted to grayscale, do not modify!
     * @return newImage
     */
    public WritableImage pixelateImage(WritableImage image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixer = image.getPixelReader();
        PixelWriter pixew = image.getPixelWriter();
        int mody = height % 5;
        int modx = width % 5;
        int y;
        int x;
        try {


            if (width > 5 && height > 5)
                for (y = 0; y < height - mody; y = y + 5) {
                    x = 0;
                    while (x < (width - modx)) {
                        Color pix = pixer.getColor(x + 2, y + 2);
                        for (int yc = y; yc < y + 5; yc++) {
                            for (int xc = x; xc < x + 5; xc++) {
                                pixew.setColor(xc, yc, pix);
                            }
                        }
                        x = x + 5;
                    }
                }
            if (modx == 4) {
                x = width - 1;
                for (y = 0; y < height - mody; y = y + 5) {
                    Color pix = pixer.getColor(x, y + 2);
                    for (int yc = y; yc < y + 5; yc++) {
                        for (int xc = x - 2; xc < x + 1; xc++) {
                            pixew.setColor(xc, yc, pix);
                        }
                    }
                }
            } else if (modx != 0) {
                x = width;
                for (y = 0; y < height - mody; y = y + 5) {
                    Color pix = pixer.getColor(x, y + 2);
                    for (int yc = y; yc < y + 5; yc++) {
                        for (int xc = x - 2; xc < x; xc++) {
                            pixew.setColor(xc, yc, pix);
                        }
                    }
                }
            }
            if (mody == 4) {
                x = 0;
                y = height - 1;
                while (x < (width - modx)) {
                    Color pix = pixer.getColor(x + 2, y);
                    for (int yc = y - 2; yc < y + 1; yc++) {
                        for (int xc = x; xc < x + 5; xc++) {
                            pixew.setColor(xc, yc, pix);
                        }
                    }
                    x = x + 5;
                }
            } else if (mody != 0) {
                x = 0;
                y = height;
                while (x < (width - modx)) {
                    Color pix = pixer.getColor(x + 2, y);
                    for (int yc = y - 2; yc < y; yc++) {
                        for (int xc = x; xc < x + 5; xc++) {
                            pixew.setColor(xc, yc, pix);
                        }
                    }
                    x = x + 5;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println();
        }
        return image;
    }

    /**
     * @param image - the image to be flipped, do not modify!
     * @return newImage
     */
    public WritableImage flipImage(WritableImage image) {
        int mid = (int) Math.floor(image.getHeight() / 2);
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixer = image.getPixelReader();
        PixelWriter pixew = image.getPixelWriter();
        for (int y = 0; y < mid; y++) {
            for (int x = 0; x < width; x++) {
                double g = pixer.getColor(x, y).getGreen() * 255;
                double r = pixer.getColor(x, y).getRed() * 255;
                double b = pixer.getColor(x, y).getBlue() * 255;
                double gtemp = pixer.getColor(x, height - y - 1).getGreen() * 255;
                double rtemp = pixer.getColor(x, height - y - 1).getRed() * 255;
                double btemp = pixer.getColor(x, height - y - 1).getBlue() * 255;
                Color pixelColor = Color.rgb(((int) r), ((int) g), ((int) b));
                pixew.setColor(x, height - y - 1, pixelColor);
                Color pixelColortemp = Color.rgb(((int) rtemp), ((int) gtemp), ((int) btemp));
                pixew.setColor(x, y, pixelColortemp);
            }
        }
        return image;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
