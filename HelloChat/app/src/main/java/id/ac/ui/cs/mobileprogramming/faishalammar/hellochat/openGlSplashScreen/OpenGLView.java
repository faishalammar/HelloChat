package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.openGlSplashScreen;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class OpenGLView extends GLSurfaceView {
    OpenGLRenderer myRender;

    public OpenGLView(Context context) {
        super(context);
        // Create an OpenGL ES 3.0 context.
        setEGLContextClientVersion(2);

        super.setEGLConfigChooser(8, 8, 8, 8, 16, 0);

        // Set the Renderer for drawing on the GLSurfaceView
        myRender = new OpenGLRenderer(context);
        setRenderer(myRender);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }


    //private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private static final float TOUCH_SCALE_FACTOR = 0.015f;
    private float mPreviousX;
    private float mPreviousY;

}
