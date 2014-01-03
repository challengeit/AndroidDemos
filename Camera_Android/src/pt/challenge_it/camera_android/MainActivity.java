package pt.challenge_it.camera_android;

import pt.challenge_it.camera_android.model.Photo;
import pt.challenge_it.camera_android.providers.PhotoManager;
import android.os.Bundle;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * This activity creates a surface view to use the device camera
 * 
 * @author Challenge.IT
 */
public class MainActivity extends Activity implements SurfaceHolder.Callback {
    
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private final int CAMERA_NUMBER = 1;
    private boolean camera_connected = false;
    private PhotoManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_title);
        manager = new PhotoManager(this);
        
        //setup button listeners
        // start button
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!camera_connected){
					try
			    	{
			    		camera = Camera.open();
			    	}
			    	catch(RuntimeException e)
			    	{
			    		Toast.makeText(MainActivity.this, R.string.camera_error, Toast.LENGTH_SHORT).show();
			    		return;
			    	}
			    	setCameraDisplayOrientation(MainActivity.this, CAMERA_NUMBER, camera);
			    	try {
			    		camera.setPreviewDisplay(surfaceHolder);
			    		camera.startPreview();
			    	} catch (Exception e) {
			    		Toast.makeText(MainActivity.this, R.string.camera_prev_error, Toast.LENGTH_SHORT).show();
			    		return;
			    	}
			    	camera_connected = true;
				}
			}
		});
        // stop button
        findViewById(R.id.btn_stop).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(camera_connected){
					camera.stopPreview();
					camera.release();
					camera_connected = false;
				}
			}
		});
     	// capture button
        findViewById(R.id.btn_take_photo).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(camera_connected){
					camera.takePicture(null, null, new PictureCallback() {
						@Override
						public void onPictureTaken(byte[] data, Camera camera) {
							//TODO save image in db
							manager.save(new Photo(Photo.byteArrayToBitmap(data),"default description"));
						}
					});
				}
			}
		});
        
        // surface settings
        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO This is called immediately after any structural changes (format or size) have been made to the surface.
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO This is called immediately after the surface is first created.
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO This is called immediately before a surface is being destroyed.
    }
    
    /**
     * Code available at: 
     * http://developer.android.com/reference/android/hardware/Camera.html#setDisplayOrientation%28int%29
     * 
     * @param activity
     * @param cameraId
     * @param camera
     */
    public static void setCameraDisplayOrientation(Activity activity,
            int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }
}
