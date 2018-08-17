package hamza.quizmaker.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hamza.quizmaker.R;

public class Dialogs {

    public static Dialog getLoadingDialog(@NonNull Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.progree_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    public static Dialog getScoreDialog(int score, @NonNull Context context, View.OnClickListener onClickListener) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.score_dialog);
        TextView scoreTextView = dialog.findViewById(R.id.dialog_score_textView);
        Button submitButton = dialog.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(onClickListener);
        String message = "";
        if (score <= 2) message = "Pathetic!";
        else if (score < 4) message = "Not bad!";
        else if (score == 4) message = "Amazing!";
        scoreTextView.setText(message + " You scored " + score + "/4");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        return dialog;
    }

}
