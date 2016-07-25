package msgproject.xmpp.cuicui.systemanimationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    
    //界面控件
    private Button act_but;
    private TextView act_id;
    private TextView act_msg;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();
        setInfo(SecondActivity.this.getTaskId(), SecondActivity.this.toString());
    }

    private void initView() {
        act_but = (Button) findViewById(R.id.second_act_next);
        act_id = (TextView) findViewById(R.id.second_act_taskid);
        act_msg = (TextView) findViewById(R.id.second_act_msg);
    }

    private void but_click(Class className) {
        Intent intent = new Intent();
        intent.setClass(SecondActivity.this, className);
        startActivity(intent);
    }

    private void setInfo(int id ,String msg) {
        act_id.setText(act_id.getText() + String.valueOf(id));
        act_msg.setText(act_msg.getText() + msg);
    }
}
