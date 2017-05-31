package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller
{
    @FXML
    public Button start_btn;
    @FXML
    private ImageView currentFrame;
    private ScheduledExecutorService timer;
    private VideoCapture capture = new VideoCapture(0);
    private boolean cameraActive = false;

    @FXML
    protected void startCamera()
    {
        if (!this.cameraActive)
        {
            this.capture.open(0);
            if (this.capture.isOpened())
            {
                this.cameraActive = true;
                Runnable frameGrabber = () -> {
                    Mat frame = grabFrame();
                    Mat MatOut= new Mat();
                    FeatureDetector blobDetector;
                    blobDetector = FeatureDetector.create(FeatureDetector.SIMPLEBLOB);
                    blobDetector.write("blob.html");
                    blobDetector.read("blob.html");
                    MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
                    blobDetector.detect(frame,keypoints1);
                    org.opencv.core.Scalar cores = new org.opencv.core.Scalar(0,0,255);
                    org.opencv.features2d.Features2d.drawKeypoints(frame,keypoints1,MatOut,cores,2);
                    Image imageToShow = Utils.mat2Image(MatOut);
                    updateImageView(currentFrame, imageToShow);
                };
                this.timer = Executors.newSingleThreadScheduledExecutor();
                this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
            }
            else
            {
                System.err.println("Impossible to open the camera connection...");
            }
        }
        else
        {
            this.cameraActive = false;
            this.stopAcquisition();
        }
    }

    private Mat grabFrame()
    {
        Mat frame = new Mat();
        if (this.capture.isOpened())
        {
            try
            {
                this.capture.read(frame);
                if (!frame.empty())
                {
                    Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGRA2BGR);
                }
            }
            catch (Exception e)
            {
                System.err.println("Exception during the image elaboration: " + e);
            }
        }
        return frame;
    }

    private void stopAcquisition()
    {
        if (this.timer!=null && !this.timer.isShutdown())
        {
            try
            {
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e)
            {
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }
        }
        if (this.capture.isOpened())
        {
            this.capture.release();
        }
    }

    private void updateImageView(ImageView view, Image image)
    {
        Utils.onFXThread(view.imageProperty(), image);
    }

}