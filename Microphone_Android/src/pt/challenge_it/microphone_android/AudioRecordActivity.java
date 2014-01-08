package pt.challenge_it.microphone_android;

import android.os.Bundle;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.media.MediaRecorder;
import android.media.MediaPlayer;
import java.io.File;
import java.io.IOException;

/**
 * This class is based on media recorder and media player present on Android Audio Capture example.
 * 
 * @source http://developer.android.com/guide/topics/media/audio-capture.html
 * @author Challenge.IT
 * @see MediaRecorder
 * @see MediaPlayer
 */
public class AudioRecordActivity extends Activity {
	
	private Button playButton; 
	private Button recordButton;
    private String filename;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_audio_record);
        
        // define filename 
        filename = new File(getFilesDir(), getString(R.string.filename)).getAbsolutePath();
        
        // buttons listeners
        // record button
        recordButton = (Button) findViewById(R.id.btn_recording);
        recordButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                if (mediaRecorder == null) {
                	startRecording();
                	recordButton.setText(R.string.stop_recording);
                } else {
                	stopRecording();
                	recordButton.setText(R.string.start_recording);
                }
			}
		});
        
        // play button
        playButton = (Button) findViewById(R.id.btn_playing);
        playButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                if (mediaPlayer == null) {
                	startPlaying();
                	playButton.setText(R.string.stop_playing);
                } else {
                	stopPlaying();
                	playButton.setText(R.string.start_playing);
                }
			}
		});
    }

    @Override
    public void onPause() {
        super.onPause();
        //release media recorder 
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }

        //release media player
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    
    /**
     * This method is responsible for start media player
     */
    private void startPlaying() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(filename);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
        	Toast.makeText(this, R.string.error_play, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is responsible for stop media player
     */
    private void stopPlaying() {
    	mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
    
    /**
     * This method is responsible for start media recorder
     */
    private void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(filename);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            Toast.makeText(this, R.string.error_record, Toast.LENGTH_SHORT).show();
        }
        mediaRecorder.start();
    }

    /**
     * This method is responsible for stop media recorder
     */
    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }
}
