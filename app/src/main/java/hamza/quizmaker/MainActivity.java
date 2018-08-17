package hamza.quizmaker;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import hamza.quizmaker.api.MyRequests;
import hamza.quizmaker.utils.Dialogs;
import hamza.quizmaker.view.HomeActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.username_edittext)
    TextInputEditText usernameEditText;

    @BindView(R.id.password_edittext)
    TextInputEditText passwordEditText;

    @BindView(R.id.login_button)
    Button loginButton;

    @BindView(R.id.signup_button)
    Button signUpButton;

    private Dialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLoadingDialog = Dialogs.getLoadingDialog(this);
        assignActions();
    }

    private void assignActions() {
        loginButton.setOnClickListener(view -> login());
        signUpButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomeActivity.class)));
    }

    private void login() {
        if (!checkFields()) return;
        mLoadingDialog.show();
        MyRequests.loginUser(usernameEditText.getText().toString(), passwordEditText.getText().toString(),
                this,
                response -> {
                    mLoadingDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    Log.e("Login", response.toString());
                }, error -> {
                    mLoadingDialog.dismiss();
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                });
    }

    private boolean checkFields() {
        boolean isFieldsFilled = true;
        if (usernameEditText.getText().toString().equals("")) {
            usernameEditText.setError(getString(R.string.enter_username));
            isFieldsFilled = false;
        }
        if (passwordEditText.getText().toString().equals("")) {
            passwordEditText.setError(getString(R.string.enter_password));
            isFieldsFilled = false;
        }
        return isFieldsFilled;
    }
}
