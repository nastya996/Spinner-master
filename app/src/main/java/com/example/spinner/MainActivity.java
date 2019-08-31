package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner mCountriesSpinner;
    private Spinner mCitiesSpinner;
    private Spinner mHouseNumberSpinner;
    private Button mShowAddressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mCountriesSpinner = findViewById(R.id.countriesSpinner);
        mCitiesSpinner = findViewById(R.id.citiesSpinner);
        mHouseNumberSpinner = findViewById(R.id.houseNumberSpinner);

        mShowAddressBtn = findViewById(R.id.showAddress);
        mShowAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        MainActivity.this,
                        mCountriesSpinner.getSelectedItem().toString()
                                + " "
                                + mCitiesSpinner.getSelectedItem().toString()
                                + " "
                                + mHouseNumberSpinner.getSelectedItem().toString(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

        initSpinnerCountries();
        initHouseNumbersSpinner();
    }

    private void initHouseNumbersSpinner() {
        Integer[] houseNumbers = new Integer[50];
        for (int i = 1; i <= 50; i++) {
            houseNumbers[i - 1] = i;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, houseNumbers);
        mHouseNumberSpinner.setAdapter(adapter);
    }

    private void initSpinnerCountries() {
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountriesSpinner.setAdapter(adapterCountries);

        mCountriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initSpinnerCountries(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initSpinnerCountries(int countryIdx) {
        String[] countries = getResources().getStringArray(R.array.countries);

        if (countryIdx >= 0 && countryIdx < countries.length) {
            int textArrayResId;
            String russia = getString(R.string.russia);
            String ukraine = getString(R.string.ukraine);
            String belarus = getString(R.string.belarus);
            // TODO implement using Map<String{R.string.russia}, Integer{R.array.r_cities}>

            if (countries[countryIdx].equals(russia)) {
                // RUSSIA
                textArrayResId = R.array.r_cities;
            } else if (countries[countryIdx].equals(ukraine)) {
                // UKRAINE
                textArrayResId = R.array.u_cities;
            } else if (countries[countryIdx].equals(belarus)) {
                // BELARUS
                textArrayResId = R.array.b_cities;
            } else {
                return;
            }

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this,
                    textArrayResId,
                    android.R.layout.simple_spinner_item
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mCitiesSpinner.setAdapter(adapter);
        }
    }
}
