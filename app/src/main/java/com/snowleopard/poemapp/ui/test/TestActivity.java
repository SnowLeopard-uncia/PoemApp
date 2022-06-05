package com.snowleopard.poemapp.ui.test;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.snowleopard.poemapp.BaseActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ActivityTestBinding;
import com.snowleopard.poemapp.logic.model.Question;

import java.math.BigDecimal;
import java.util.List;

public class TestActivity extends BaseActivity {

    ActivityTestBinding activityTestBinding;

    TestViewModel testViewModel;

    RadioButton radioButton;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTestBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        getQuestionList();


        activityTestBinding.btnTestNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!testViewModel.isGetRight()) {
                    if (testViewModel.getAnswer().equals(testViewModel.getQuestion().getRightChoice())) {
                        testViewModel.getCorrectMistakes().setCorrectNum(testViewModel.getCorrectMistakes().getCorrectNum() + 1);

                        Toast.makeText(TestActivity.this, "回答正确", Toast.LENGTH_SHORT).show();
                        testViewModel.setI(testViewModel.getI() + 1);
                        testViewModel.mutableLiveData.postValue(testViewModel.getI());
                        activityTestBinding.tvTestRight.setVisibility(View.GONE);
                    } else {
                        testViewModel.getCorrectMistakes().setMistakesNum(testViewModel.getCorrectMistakes().getMistakesNum() + 1);
                        Toast.makeText(TestActivity.this, "回答错误", Toast.LENGTH_SHORT).show();
                        disableRadioGroup(activityTestBinding.rgTestChooseAnswer); //禁用点击按钮

                        radioButton.setBackground(getResources().getDrawable(R.drawable.bg_btn_incorrect));
                        activityTestBinding.tvTestRight.setVisibility(View.VISIBLE);
                        activityTestBinding.btnTestNext.setText("下一题");
                        testViewModel.setGetRight(true);

                    }
                } else {
                    testViewModel.setI(testViewModel.getI() + 1);
                    testViewModel.mutableLiveData.postValue(testViewModel.getI());
                    activityTestBinding.tvTestRight.setVisibility(View.GONE);
                    activityTestBinding.btnTestNext.setText("确定");
                    testViewModel.setGetRight(false);
                    enableRadioGroup(activityTestBinding.rgTestChooseAnswer);
                }


            }
        });

        activityTestBinding.rgTestChooseAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = findViewById(checkedId);
                testViewModel.setAnswer((String) radioButton.getText());
            }
        });

        testViewModel.mutableLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Message message = Message.obtain();
                message.what = 1;
                handler.sendMessage(message);
            }
        });


    }

    private void getQuestionList() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String level = bundle.getString("level");
        String type = bundle.getString("type");
        testViewModel.getQuestions(level, type).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                testViewModel.setQuestionList(questions);
                Message msg = Message.obtain();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        });

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                //testViewModel得到的是一个问题list，这里先初始化第一条
                if (testViewModel.getI() < testViewModel.getQuestionList().size()) {
                    activityTestBinding.rgTestChooseAnswer.removeAllViews(); //因为复用，所以先删除前面创建的控件，不然会重复

                    Question question = testViewModel.getQuestionList().get(testViewModel.getI());
                    testViewModel.setQuestion(question);
                    activityTestBinding.setQuestion(question);
                    //选项动态生成View
                    String[] choices = question.getChoice().split(";");
                    for (String choice : choices) {
                        RadioButton radioButton = new RadioButton(TestActivity.this);

                        radioButton.setText(choice);
                        radioButton.setTextSize(15); //尝试中 10 too samll 30 too big 20 too big
                        radioButton.setButtonDrawable(null);
                        radioButton.setTextColor(getResources().getColor(R.color.black));
                        radioButton.setGravity(Gravity.CENTER);
                        radioButton.setBackground(getResources().getDrawable(R.drawable.btn_choose_selector));
                        radioButton.setPadding(17, 25, 17, 25);

                        RadioGroup.LayoutParams layoutParams = new RadioGroup
                                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                        //RasioButton 的边距
                        layoutParams.setMargins(5, 15, 5, 20);
//它的父布局应该是RadioGroup不是constraintLayout
                        activityTestBinding.rgTestChooseAnswer.addView(radioButton, layoutParams);
//                        activityTestBinding.constraintLayout.addView(radioButton, layoutParams);
                    }

                    msg.what = 0;
                } else {

                    BigDecimal bigDecimal = BigDecimal.valueOf(testViewModel.getCorrectMistakes().getCorrectNum() / testViewModel.getQuestionList().size());
//                    对double类型四舍五入
                    double rate = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    testViewModel.getCorrectMistakes().setRate(rate);
                    Intent intent = new Intent(TestActivity.this, FinishTestActivity.class);
                    intent.putExtra("rate_data", testViewModel.getCorrectMistakes());
                    startActivity(intent);
                    finish();
                }

            }
        }
    };


    //禁用按钮
    public void disableRadioGroup(RadioGroup testRadioGroup) {
        for (int i = 0; i < testRadioGroup.getChildCount(); i++) {
            testRadioGroup.getChildAt(i).setEnabled(false);
        }
    }

    //启用按钮
    public void enableRadioGroup(RadioGroup testRadioGroup) {
        for (int i = 0; i < testRadioGroup.getChildCount(); i++) {
            testRadioGroup.getChildAt(i).setEnabled(true);
        }
    }

}