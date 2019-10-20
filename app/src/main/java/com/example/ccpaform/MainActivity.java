package com.example.ccpaform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout mFirstNameTextLayout;
    TextInputEditText mFirstNameEditText;
    TextInputLayout mLastNameTextLayout;
    TextInputEditText mLastNameEditText;
    TextInputLayout mEmailTextLayout;
    TextInputEditText mEmailEditText;
    Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstNameTextLayout = findViewById(R.id.ccpa_first_name_text_field);
        mFirstNameEditText = findViewById(R.id.ccpa_first_name_edit_text);
        mLastNameTextLayout = findViewById(R.id.ccpa_last_name_text_field);
        mLastNameEditText = findViewById(R.id.ccpa_last_name_edit_text);
        mEmailTextLayout = findViewById(R.id.ccpa_email_text_field);
        mEmailEditText = findViewById(R.id.ccpa_email_edit_text);
        mSubmitButton = findViewById(R.id.submit_form_button);

//        mFirstNameEditText.setOnKeyListener(getEditTextOnKeyListener());
        mSubmitButton.setOnClickListener(getButtonOnClickListener());

    }

    private OnKeyListener getEditTextOnKeyListener() {
        return new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                mFirstNameEditText.setFocusableInTouchMode(true);
                return false;
            }
        };
    }

    private View.OnClickListener getButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFirstName();
                validateLastName();
                validateEmail();

                InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        };
    }

    private void validateFirstName() {
        EditText firstNameEditText = mFirstNameTextLayout.getEditText();
        if (firstNameEditText == null) {
            mFirstNameTextLayout.setError("Please enter your first name");
            return;
        }

        String firstNameInput = firstNameEditText.getText().toString().trim();
        if (firstNameInput.isEmpty()) {
            mFirstNameTextLayout.setError("Please enter your first name");
            return;
        }

        mFirstNameTextLayout.setError(null);
    }

    private void validateLastName() {
        EditText lastNameEditText = mLastNameTextLayout.getEditText();
        if (lastNameEditText == null) {
            mLastNameTextLayout.setError("Please enter your last name");
            return;
        }

        String lastNameInput = lastNameEditText.getText().toString().trim();
        if (lastNameInput.isEmpty()) {
            mLastNameTextLayout.setError("Please enter your last name");
            return;
        }

        mLastNameTextLayout.setError(null);
    }

    private void validateEmail() {
        // check null email
        EditText emailEditText = mEmailTextLayout.getEditText();
        if (emailEditText == null) {
            mEmailTextLayout.setError("Please enter a valid email address");
            return;
        }

        // check empty email
        String emailInput = emailEditText.getText().toString().trim();
        if (emailInput.isEmpty()) {
            mEmailTextLayout.setError("Please enter a valid email address");
            return;
        }

        // check incorrect email format
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mEmailTextLayout.setError("Please enter a valid email address");
            return;
        }

        mEmailTextLayout.setError(null);
    }
}
