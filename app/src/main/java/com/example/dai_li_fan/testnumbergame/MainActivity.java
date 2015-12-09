package com.example.dai_li_fan.testnumbergame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import db.ThreeDBController;
import model.ModelSuccessInfo;

/**
 * Created by dai_li_fan on 2015/4/7.
 */
public class MainActivity extends Activity{
    private TextView answer_ab_view, answer_number_view, check_view, userName;
    private EditText inpute_text;
    private Button btn_3, btn_4, btn_5, btn_check, btn_clear;
    private int ans_number = 0, range = 0;
    private LinearLayout layout_display;
    private int count = 0;
    private ScrollView check_text_scrollview;
    private long startTime, endTime;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        findView();
        setView();
        setListener();
    }

    private void findView() {
        answer_number_view = (TextView) findViewById(R.id.answer_number_view);
        answer_ab_view = (TextView) findViewById(R.id.answer_ab_view);
        inpute_text = (EditText) findViewById(R.id.input_view);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_check = (Button) findViewById(R.id.btn_check);
        layout_display = (LinearLayout) findViewById(R.id.result_layout);
        check_view = (TextView) findViewById(R.id.check_view);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        check_text_scrollview = (ScrollView) findViewById(R.id.check_text_scrollview);
        userName = (TextView) findViewById(R.id.userName);
    }

    private void setView() {
        check_view.setText("檢查 " + count + " 次");
        answer_ab_view.setText("請選擇要猜的數字");
        userName.setText(getIntent().getSerializableExtra("name").toString());
    }

    private void setListener() {
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(range!=3){
                    count = 0;
//                };
                ans_number = 0;
                range = 3;
                inpute_text.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
                setNumber(999);
                Toast.makeText(MainActivity.this, "3位數開始", Toast.LENGTH_SHORT).show();
                layout_display.removeAllViews();
                check_view.setText("檢查 " + count + " 次");
                answer_ab_view.setText("猜 3 位數");
                startTime = Calendar.getInstance().getTimeInMillis();
                Log.w("ww",String.valueOf(startTime));

            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(range!=4){
                    count = 0;
//                };
                ans_number = 0;
                inpute_text.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                range = 4;
                setNumber(9999);
                Toast.makeText(MainActivity.this, "4位數開始", Toast.LENGTH_SHORT).show();
                layout_display.removeAllViews();
                check_view.setText("檢查 " + count + " 次");
                answer_ab_view.setText("猜 4 位數");
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(range!=5){
                    count = 0;
//                };
                ans_number = 0;
                inpute_text.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
                range = 5;
                setNumber(99999);
                Toast.makeText(MainActivity.this, "5位數開始", Toast.LENGTH_SHORT).show();
                layout_display.removeAllViews();
                check_view.setText("檢查 " + count + " 次");
                answer_ab_view.setText("猜 5 位數");

            }
        });

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_ans();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_display.removeAllViews();
            }
        });
    }

    private void setNumber(int number_length){
//        answer_number_view.setText(String.valueOf( (int) (Math.random() * number_length) ));

//        int random = (int) (Math.random() * number_length);
        int min_number = 0;

        if(number_length  == 999){
            min_number = 100;
        }else if(number_length == 9999){
            min_number = 1000;
        }else if (number_length == 99999){
            min_number = 10000;
        }

        if(min_number == 100){
            while (ans_number <= min_number){
                ans_number = (int) (Math.random() * number_length);
            }
        }else if(min_number == 1000){
            while (ans_number <= min_number){
                ans_number = (int) (Math.random() * number_length);
            }
        }else if(min_number == 10000){
            while (ans_number <= min_number){
                ans_number = (int) (Math.random() * number_length);
            }
        }
//        answer_number_view.setText(String.valueOf(ans_number));



    }

    private void check_ans(){
        if(ans_number == 0){
            Toast.makeText(MainActivity.this, "請選擇挑戰的數字", Toast.LENGTH_SHORT).show();
            return;
        }
        if (inpute_text.getText().toString().length() < range ){
            Toast.makeText(MainActivity.this, "請輸入符合數字的數量", Toast.LENGTH_SHORT).show();
            return;
        }
        String input_t_ans = inpute_text.getText().toString();
        String ans_t_ans = String.valueOf(ans_number);
        count ++;
        int isA = 0;
        int isB = 0;
        check_view.setText("檢查 " + count + " 次");
        if(range == 3){

            String input_a = input_t_ans.substring(0,1);
            String input_b = input_t_ans.substring(1,2);
            String input_c = input_t_ans.substring(2,3);
            String ans_a = ans_t_ans.substring(0,1);
            String ans_b = ans_t_ans.substring(1,2);
            String ans_c = ans_t_ans.substring(2,3);


            if(input_a.equals(ans_a)){
               ans_a = "x";
               input_a = "y";
               isA++;
            }
            if(input_b.equals(ans_b)){
                ans_b = "x";
                input_b = "y";
                isA++;
            }
            if(input_c.equals(ans_c)){
                ans_c = "x";
                input_c = "y";
                isA++;
            }

            if(ans_a.equals(input_b)){
                ans_a = "x";
                input_b = "y";
                isB++;
            }

            if(ans_a.equals(input_c)){
                ans_a = "x";
                input_c = "y";
                isB++;
            }
            if(ans_b.equals(input_a)){
                ans_b = "x";
                input_a = "y";
                isB++;
            }
            if(ans_b.equals(input_c)){
                ans_b = "x";
                input_c = "y";
                isB++;
            }
            if(ans_c.equals(input_a)){
                ans_c = "x";
                input_a = "y";
                isB++;
            }
            if(ans_c.equals(input_b)){
                ans_c = "x";
                input_b = "y";
                isB++;
            }

        }else if(range == 4){

            String input_a = input_t_ans.substring(0,1);
            String input_b = input_t_ans.substring(1,2);
            String input_c = input_t_ans.substring(2,3);
            String input_d = input_t_ans.substring(3,4);
            String ans_a = ans_t_ans.substring(0,1);
            String ans_b = ans_t_ans.substring(1,2);
            String ans_c = ans_t_ans.substring(2,3);
            String ans_d = ans_t_ans.substring(3,4);


            if(input_a.equals(ans_a)){
                ans_a = "x";
                input_a = "y";
                isA++;
            }
            if(input_b.equals(ans_b)){
                ans_b = "x";
                input_b = "y";
                isA++;
            }
            if(input_c.equals(ans_c)){
                ans_c = "x";
                input_c = "y";
                isA++;
            }

            if(input_d.equals(ans_d)){
                ans_d = "x";
                input_d = "y";
                isA++;
            }



            if(!ans_a.equals("x")){
                if(ans_a.equals(input_b)){
                    ans_a = "x";
                    input_b = "y";
                    isB++;
                }

                if(ans_a.equals(input_c)){
                    ans_a = "x";
                    input_c = "y";
                    isB++;
                }

                if(ans_a.equals(input_d)){
                    ans_a = "x";
                    input_d = "y";
                    isB++;
                }
            }

            if(!ans_b.equals("x")){
                if(ans_b.equals(input_a)){
                    ans_b = "x";
                    input_a = "y";
                    isB++;
                }
                if(ans_b.equals(input_c)){
                    ans_b = "x";
                    input_c = "y";
                    isB++;
                }

                if(ans_b.equals(input_d)){
                    ans_b = "x";
                    input_d = "y";
                    isB++;
                }
            }

            if(!ans_c.equals("x")){
                if(ans_c.equals(input_a)){
                    ans_c = "x";
                    input_a = "y";
                    isB++;
                }

                if(ans_c.equals(input_b)){
                    ans_c = "x";
                    input_b = "y";
                    isB++;
                }

                if(ans_c.equals(input_d)){
                    ans_c = "x";
                    input_d = "y";
                    isB++;
                }
            }

            if(!ans_d.equals("x")){
                if(ans_d.equals(input_a)){
                    ans_d = "x";
                    input_a = "y";
                    isB++;
                }

                if(ans_d.equals(input_b)){
                    ans_d = "x";
                    input_b = "y";
                    isB++;
                }

                if(ans_d.equals(input_c)){
                    ans_d = "x";
                    input_c = "y";
                    isB++;
                }
            }

        }else if(range == 5) {

            String input_a = input_t_ans.substring(0, 1);
            String input_b = input_t_ans.substring(1, 2);
            String input_c = input_t_ans.substring(2, 3);
            String input_d = input_t_ans.substring(3, 4);
            String input_e = input_t_ans.substring(4, 5);
            String ans_a = ans_t_ans.substring(0, 1);
            String ans_b = ans_t_ans.substring(1, 2);
            String ans_c = ans_t_ans.substring(2, 3);
            String ans_d = ans_t_ans.substring(3, 4);
            String ans_e = ans_t_ans.substring(4, 5);


            if (input_a.equals(ans_a)) {
                ans_a = "x";
                input_a = "y";
                isA++;
            }
            if (input_b.equals(ans_b)) {
                ans_b = "x";
                input_b = "y";
                isA++;
            }
            if (input_c.equals(ans_c)) {
                ans_c = "x";
                input_c = "y";
                isA++;
            }

            if (input_d.equals(ans_d)) {
                ans_d = "x";
                input_d = "y";
                isA++;
            }

            if (input_e.equals(ans_e)) {
                ans_e = "x";
                input_e = "y";
                isA++;
            }


            if (!ans_a.equals("x")) {
                if (ans_a.equals(input_b)) {
                    ans_a = "x";
                    input_b = "y";
                    isB++;
                }

                if (ans_a.equals(input_c)) {
                    ans_a = "x";
                    input_c = "y";
                    isB++;
                }

                if (ans_a.equals(input_d)) {
                    ans_a = "x";
                    input_d = "y";
                    isB++;
                }

                if (ans_a.equals(input_e)) {
                    ans_a = "x";
                    input_e = "y";
                    isB++;
                }

            }

            if (!ans_b.equals("x")) {
                if (ans_b.equals(input_a)) {
                    ans_b = "x";
                    input_a = "y";
                    isB++;
                }

                if (ans_b.equals(input_c)) {
                    ans_b = "x";
                    input_c = "y";
                    isB++;
                }

                if (ans_b.equals(input_d)) {
                    ans_b = "x";
                    input_d = "y";
                    isB++;
                }

                if (ans_b.equals(input_e)) {
                    ans_b = "x";
                    input_e = "y";
                    isB++;
                }

            }

            if (!ans_c.equals("x")) {
                if (ans_c.equals(input_a)) {
                    ans_c = "x";
                    input_a = "y";
                    isB++;
                }

                if (ans_c.equals(input_b)) {
                    ans_c = "x";
                    input_b = "y";
                    isB++;
                }

                if (ans_c.equals(input_d)) {
                    ans_c = "x";
                    input_d = "y";
                    isB++;
                }

                if (ans_c.equals(input_e)) {
                    ans_c = "x";
                    input_e = "y";
                    isB++;
                }
            }

            if (!ans_d.equals("x")) {
                if (ans_d.equals(input_a)) {
                    ans_d = "x";
                    input_a = "y";
                    isB++;
                }

                if (ans_d.equals(input_b)) {
                    ans_d = "x";
                    input_b = "y";
                    isB++;
                }

                if (ans_d.equals(input_c)) {
                    ans_d = "x";
                    input_c = "y";
                    isB++;
                }

                if (ans_d.equals(input_e)) {
                    ans_d = "x";
                    input_e = "y";
                    isB++;
                }
            }

            if (!ans_e.equals("x")) {
                if (ans_e.equals(input_a)) {
                    ans_e = "x";
                    input_a = "y";
                    isB++;
                }

                if (ans_e.equals(input_b)) {
                    ans_e = "x";
                    input_b = "y";
                    isB++;
                }

                if (ans_e.equals(input_c)) {
                    ans_e = "x";
                    input_c = "y";
                    isB++;
                }

                if (ans_e.equals(input_d)) {
                    ans_e = "x";
                    input_d = "y";
                    isB++;
                }
            }
        }




//        set_how_A_B(isA, isB);
        String result = input_t_ans + " " + isA + "A" + isB + "B";
        addResultTextView(result);

        if (isA == range) {
            endTime = Calendar.getInstance().getTimeInMillis();
            long ansTime =  (endTime - startTime) / 1000;
            Log.w("ww", String.valueOf(ansTime));
            ModelSuccessInfo model = new ModelSuccessInfo();
            model.name = String.valueOf(userName);
            model.sec = String.valueOf(ansTime);
            ThreeDBController.insertData(model);

			String data = "";

			if (count == 1) {
				data = "你是作弊喔!!";
				count = 0;
			} else if (count < 4) {
				data = "太神啦!!";
				count = 0;
			} else if (count <= 9) {
				data = "不錯呦!!";
				count = 0;
			} else if (count > 9) {
				data = "加油~!!";
				count = 0;
			}

			AlertDialog dialog = new AlertDialog.Builder(this)
			.setTitle("猜數字")
			.setMessage(data)
			.setPositiveButton("重新",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							layout_display.removeAllViews();
                            ans_number = 0;
                            answer_ab_view.setText("請選擇要猜的數字");
                            ThreeDBController.queryUserAccount();
						}
					})
			.create();
			dialog.show();
		}




    }


    private void set_how_A_B(int isA, int isB){
        answer_ab_view.setText(isA + "A" + isB + "B");

    }

    private void addResultTextView(String result) {
        TextView check_result = new TextView(this);
        check_result.setText(result);
        // LinearLayout

		check_result.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT));

		layout_display.addView(check_result);
//        check_text_scrollview.scrollTo(0, layout_display.getHeight() + 50);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                check_text_scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 100);

//        check_text_scrollview.fullScroll(ScrollView.FOCUS_DOWN);
	}

	public void clear_input(View view) {
		layout_display.removeAllViews();
	}


}
