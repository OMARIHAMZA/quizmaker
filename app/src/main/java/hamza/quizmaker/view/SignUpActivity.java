package hamza.quizmaker.view;

import android.app.Dialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import hamza.quizmaker.R;
import hamza.quizmaker.api.MyRequests;
import hamza.quizmaker.utils.Dialogs;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.username_edittext)
    EditText usernameEditText;

    @BindView(R.id.password_editText)
    EditText passwordEditText;

    @BindView(R.id.school_edittext)
    EditText schoolEditText;

    @BindView(R.id.age_edittext)
    EditText ageEditText;

    @BindView(R.id.signup_button)
    Button signUpButton;

    @BindView(R.id.rootView)
    ConstraintLayout rootView;

    private Dialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        mLoadingDialog = Dialogs.getLoadingDialog(this);
        assignActions();
    }

    private void assignActions() {
        signUpButton.setOnClickListener(view -> signUpUser());
    }

    private void signUpUser() {
        if (!checkFields()) {
            return;
        }
        mLoadingDialog.show();
        HashMap<String, String> params = new HashMap<>();
        params.put("username", usernameEditText.getText().toString());
        params.put("password", passwordEditText.getText().toString());
        params.put("school", schoolEditText.getText().toString());
        params.put("age", ageEditText.getText().toString());
        MyRequests.signUp(params, this, response -> {
            mLoadingDialog.dismiss();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            Log.e("Response", response.toString());
        }, error -> {
            mLoadingDialog.dismiss();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean checkFields() {
        for (int i = 0; i < rootView.getChildCount(); i++) {
            if (rootView.getChildAt(i) instanceof EditText) {
                if (((EditText) rootView.getChildAt(i)).getText().toString().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}
