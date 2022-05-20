package com.snowleopard.poemapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.snowleopard.poemapp.R;

/**
 * input_icon 输入框上的文字
 * input_hint 输入框提示内容
 * is_password 输入内容是否以密文形式存在
 */
public class InputView extends FrameLayout {
//    private int input_icon;
    private String input_text;
    private String input_hint;
    private boolean is_password;

    private View mView;
    private TextView textView_input;
    private EditText editText_input;

    public InputView(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }
    private void init(Context context, AttributeSet attributeSet){
        if(attributeSet ==null) return;
//        通过传入的attrs获取自定义属性
//        这个方法返回一个typearray，通过typearray来获取属性
        TypedArray typedArray=
        context.obtainStyledAttributes(attributeSet, R.styleable.inputView);

        input_text= typedArray.getString(R.styleable.inputView_input_text);
                //第二个是获取不到第一个的时候的默认值
        input_hint =typedArray.getString(R.styleable.inputView_input_hint);
        is_password=typedArray.getBoolean(R.styleable.inputView_is_password,
                false);
        typedArray.recycle();
//        使用完之后释放
//        获取编译的layout资源文件
        mView = LayoutInflater.from(context).inflate(R.layout.input_view,
                this,false);
       textView_input= mView.findViewById(R.id.input_text);
        editText_input=mView.findViewById(R.id.input_hint);

        //3.布局关联属性
        textView_input.setText(input_text);
        editText_input.setHint(input_hint);
        //通过这两个属性控制edittext展示密文
        editText_input.setInputType(is_password ?
                InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD
                : InputType.TYPE_CLASS_TEXT);
        //当为false时接受手机号的输入
        addView(mView);
//        通过这个方法把mView放入这个inputView里面
        //这个绑定了input_view
    }
    /**
     * 返回输入内容
     */
    public String getInputStr(){
        return editText_input.getText().toString().trim();
    }

    /**
     * setText 方法可行
     */
    public void setText(String string){
        editText_input.setText(string);
    }
}
