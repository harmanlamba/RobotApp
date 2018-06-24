package robot.forms;

import robot.CarrierRobot;
import robot.CustomRobot;
import robot.RobotModel;
import robot.forms.util.Form;
import robot.forms.util.FormHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CustomRobotFormHandler implements FormHandler {
    private RobotModel _robotModel;
    private CarrierRobot _parentOfNewRobot;
    private File _imageFile;
    private int _width;
    private int _deltaX;
    private int _deltaY;


    public CustomRobotFormHandler(RobotModel robotModel, CarrierRobot parentOfRobot) {
        _robotModel = robotModel;
        _parentOfNewRobot = parentOfRobot;
    }

    private class RobotWorker extends SwingWorker<BufferedImage, Void> {
        @Override
        protected BufferedImage doInBackground() throws Exception {
            BufferedImage fullImage = null;
            try {
                fullImage = ImageIO.read(_imageFile);
            } catch (IOException e) {
                System.out.println("Error loading image.");
            }

            int fullImageWidth = fullImage.getWidth();
            int fullImageHeight = fullImage.getHeight();

            BufferedImage scaledImage = fullImage;

            // Scale the image if necessary.
            if (fullImageWidth > _width) {
                double scaleFactor = (double) _width / (double) fullImageWidth;
                int height = (int) ((double) fullImageHeight * scaleFactor);

                scaledImage = new BufferedImage(_width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = scaledImage.createGraphics();

                // Method drawImage() scales an already loaded image. The
                // ImageObserver argument is null because we don't need to monitor
                // the scaling operation.
                g.drawImage(fullImage, 0, 0, _width, height, null);


            }
            return scaledImage;
        }

        @Override
        protected void done() {
            // Create the new Robot and add it to the model.
            CustomRobot imageRobot = null;
            try {
                imageRobot = new CustomRobot(_deltaX, _deltaY, this.get());
            } catch (InterruptedException|ExecutionException e) {
                e.printStackTrace();
            }
            _robotModel.add(imageRobot, _parentOfNewRobot);
        }

    }


    @Override
    public void processForm(Form form) {
        // Read field values from the form.
        File imageFile = (File) form.getFieldValue(File.class, ImageFormElement.IMAGE);
        int width = form.getFieldValue(Integer.class, RobotFormElement.WIDTH);
        int deltaX = form.getFieldValue(Integer.class, RobotFormElement.DELTA_X);
        int deltaY = form.getFieldValue(Integer.class, RobotFormElement.DELTA_Y);
        _width = width;
        _deltaX = deltaX;
        _deltaY = deltaY;
        _imageFile = imageFile;
        RobotWorker _worker= new RobotWorker();
        _worker.execute();
    }


}
