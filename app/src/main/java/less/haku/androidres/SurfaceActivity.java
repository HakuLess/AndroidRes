package less.haku.androidres;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import less.haku.androidres.common.BaseActivity;

/**
 * Created by HaKu on 15/11/30.
 */
public class SurfaceActivity extends BaseActivity implements SurfaceHolder.Callback{

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Button startButton;
    private Button pauseButton;
    private Button stopButton;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surface_activity);

        initViews();
    }

    //初始化UI控件
    private void initViews() {
        surfaceView = (SurfaceView) this.findViewById(R.id.video_surface_view);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        startButton = (Button) this.findViewById(R.id.surface_start);
        pauseButton = (Button) this.findViewById(R.id.surface_pause);
        stopButton = (Button) this.findViewById(R.id.surface_stop);

        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }});

        pauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }});

        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }});
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        //Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        //必须在surface创建后才能初始化MediaPlayer,否则不会显示图像
        mediaPlayer = MediaPlayer.create(this, R.raw.our_graduation);
//        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDisplay(surfaceHolder);
        Log.d("surfaceView", "create");
        //设置显示视频显示在SurfaceView上
//        try {
//
//            mediaPlayer.setDataSource(R.raw.我们的毕业季);
//            mediaPlayer.prepare();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}