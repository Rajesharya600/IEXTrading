package com.example.ritz.iextrading.ViewControlllers;

import android.icu.text.UnicodeSetSpanner;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ritz.iextrading.Data.StockData;
import com.example.ritz.iextrading.R;
import com.example.ritz.iextrading.Retrofit.APIInterface;
import com.example.ritz.iextrading.Retrofit.APIClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView companyName,companyWebsite,ceo,stockValue,tv1,tv2,tv3,tv4,error;
    public EditText stockSymbol;
    public Button search;
    public ProgressBar progressBar;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        companyName = (TextView) findViewById(R.id.companyname);
        companyWebsite = (TextView) findViewById(R.id.companywebsite);
        ceo = (TextView) findViewById(R.id.ceo);
        stockValue = (TextView) findViewById(R.id.stockvalue);
        stockSymbol = (EditText) findViewById(R.id.stocksymbol);
        stockSymbol.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        search = (Button) findViewById(R.id.search);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        error = (TextView) findViewById(R.id.error);
        search.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        int vId = view.getId();
        if (vId == R.id.search) {
            if(TextUtils.isEmpty(stockSymbol.getText().toString()))
            {
                Toast.makeText(getApplicationContext(),"Please enter the stock symbol",Toast.LENGTH_SHORT).show();
            }
            else
            {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                String stocksymbolname = stockSymbol.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                getRequestData(stocksymbolname);
            }
        }
    }

    private void getRequestData(final String stocksymbolname) {

        Call<StockData> call = apiInterface.getAllData(stocksymbolname);
        call.enqueue(new Callback<StockData>() {
            @Override
            public void onResponse(Call<StockData> call, Response<StockData> response) {

                if(response!=null && response.isSuccessful()) {

                     StockData stockData = response.body();
                    Log.d("ritviz", String.valueOf(response.body()));

                    companyName.setVisibility(View.VISIBLE);
                    companyWebsite.setVisibility(View.VISIBLE);
                    ceo.setVisibility(View.VISIBLE);
                    stockValue.setVisibility(View.VISIBLE);
                    error.setText("");
                    tv1.setVisibility(View.VISIBLE);
                    tv2.setVisibility(View.VISIBLE);
                    tv3.setVisibility(View.VISIBLE);
                    tv4.setVisibility(View.VISIBLE);
                    companyName.setText(stockData.getCompany().getCompanyName());
                    companyWebsite.setText(  stockData.getCompany().getWebsite());
                    ceo.setText(stockData.getCompany().getCEO());
                    stockValue.setText(String.valueOf(stockData.getQuote().getLatestPrice()));
                    progressBar.setVisibility(View.GONE);

                }
                else
                {
                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    tv4.setVisibility(View.GONE);
                    companyName.setVisibility(View.GONE);
                    error.setText("No stock found for" +" \""+ stocksymbolname +"\"");
                    companyWebsite.setVisibility(View.GONE);
                    ceo.setVisibility(View.GONE);
                    stockValue.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<StockData> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                error.setText("");
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
