package com.xiemiao.rxnetdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xiemiao.rxnet.exception.ExceptionHandle;
import com.xiemiao.rxnet.observer.CommonObserver;
import com.xiemiao.rxnet.observer.ObservablePacking;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

public class MainActivity extends RxAppCompatActivity {

    private EditText etPhone;
    private Button btnSubmit;
    private TextView tvPhone;
    private TextView tvProvince;
    private TextView tvCity;
    private TextView tvOperator;
    private Retrofit mRetrofit;
    private RLoadingDialog mRLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRetrofit = BaseApplication.getInstance().getRetrofit();


        initView();
        initListener();
    }

    private void initView() {
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvProvince = (TextView) findViewById(R.id.tvProvince);
        tvCity = (TextView) findViewById(R.id.tvCity);
        tvOperator = (TextView) findViewById(R.id.tvOperator);

        mRLoadingDialog = new RLoadingDialog(this, true);

    }

    private void initListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etPhone.getText().toString();
                requestQueryPhone(phone);
            }
        });
    }

    private void requestQueryPhone(final String phone) {
        Map<String, String> params = new HashMap<>();
        params.put("key", "1889b37351288");//申请的key,验证接口有效用
        params.put("phone", phone);

        ObservablePacking.getObservable(mRetrofit.create(CommonService.class).phoneQuery(params), this)
                .subscribe(new CommonObserver<QueryResult>() {
                    @Override
                    protected void onStart(Disposable d) {
                        mRLoadingDialog.show();
                    }

                    @Override
                    protected void onSuccess(QueryResult response) {
                        mRLoadingDialog.dismiss();
                        if (response.getResultt() != null) {
                            tvPhone.setText(phone);
                            tvProvince.setText(response.getResultt().getProvince());
                            tvCity.setText(response.getResultt().getCity());
                            tvOperator.setText(response.getResultt().getOperator());
                        }
                    }

                    @Override
                    protected void onError(ExceptionHandle.ResponeThrowable e) {
                        mRLoadingDialog.dismiss();
                        Toast.makeText(MainActivity.this, e.message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
