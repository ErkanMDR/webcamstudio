/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webcamstudio.streams;

import java.awt.image.BufferedImage;
import java.io.File;
import webcamstudio.ffmpeg.FFMPEGRenderer;
import webcamstudio.mixers.Frame;
import webcamstudio.mixers.MasterFrameBuilder;

/**
 *
 * @author patrick
 */
public class SourceMovie extends Stream {

    FFMPEGRenderer capture = null;

    public SourceMovie(File movie) {
        capture = new FFMPEGRenderer(this, "movie");
        file=movie;
        name = movie.getName();
    }


    @Override
    public void read() {
        MasterFrameBuilder.register(this);
        capture.read();
    }


    @Override
    public void stop() {
        MasterFrameBuilder.unregister(this);
        capture.stop();

    }

    @Override
    public boolean isPlaying() {
        return !capture.isStopped();
    }

    @Override
    public BufferedImage getPreview() {
        return capture.getPreview();
    }

}
