package main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dai_li_fan.testnumbergame.MainActivity;
import com.example.dai_li_fan.testnumbergame.R;

/**
 * Created by dai_li_fan on 2015/12/8.
 */
public class LandingActivity extends Activity{
    private EditText editName;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_layout);
        findView();
        setView();
        setListener();
    }

    private void findView() {
        editName = (EditText) findViewById(R.id.editName);
        login = (Button) findViewById(R.id.login);

    }

    private void setView() {
        editName.setText("213213213");
        editName.setSelection(editName.getText().length());

    }

    private void setListener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LandingActivity.this, MainActivity.class);
                intent.putExtra("name",editName.getText().toString());
                startActivity(intent);
            }
        });
        
    }
}
