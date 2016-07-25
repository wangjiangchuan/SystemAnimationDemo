package msgproject.xmpp.cuicui.systemanimationdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    //界面控件
    private Button act_but;
    private TextView act_id;
    private TextView act_msg;

    private Button newDialog;
    private Button imageVisible;
    private ImageView imageView;

    private RelativeLayout layout;

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1 :
                    layout.setVisibility(View.VISIBLE);
                    Log.e("msg", "1");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initView();
        setInfo(FirstActivity.this.getTaskId(), FirstActivity.this.toString());

        act_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_click(FirstActivity.class);
            }
        });

        newDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDialog();
            }
        });



        imageVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (this) {
                            try {
                                wait(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(1);
                    }
                }).start();

                layout.getW

            }
        });
    }

    private void initView() {
        act_but = (Button) findViewById(R.id.first_act_next);
        act_id = (TextView) findViewById(R.id.first_act_taskid);
        act_msg = (TextView) findViewById(R.id.first_act_msg);

        newDialog = (Button) findViewById(R.id.new_window);

        imageView = (ImageView) findViewById(R.id.first_image);
        imageVisible = (Button) findViewById(R.id.image_visible);

        layout = (RelativeLayout) findViewById(R.id.first_layout_main);
    }

    private void act_click(Class className) {
        Intent intent = new Intent();
        intent.setClass(FirstActivity.this, className);
        startActivity(intent);
    }

    private void setInfo(int id ,String msg) {
        act_id.setText(act_id.getText() + String.valueOf(id));
        act_msg.setText(act_msg.getText() + msg);
    }

    private void newDialog() {
        AlertDialog.Builder builder =new AlertDialog.Builder(FirstActivity.this);
        builder.setMessage("This is a Dialog");
        builder.setTitle("Dialog");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
               /* Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            synchronized (this) {
                                wait(600);
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(1);
                    }
                });
                thread.start();*/
            }
        });

        Dialog dia = builder.create();
        dia.getWindow().setWindowAnimations(R.style.dialogAnimation);
        dia.show();
    }


}
