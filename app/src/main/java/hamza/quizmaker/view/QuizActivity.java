package hamza.quizmaker.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlipinski.flippablestackview.FlippableStackView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hamza.quizmaker.R;
import hamza.quizmaker.models.Question;
import hamza.quizmaker.utils.Dialogs;
import hamza.quizmaker.utils.ScrollViewPager;
import hamza.quizmaker.view.adapters.QuizQuestionsAdapter;

public class QuizActivity extends AppCompatActivity implements ScrollViewPager {

    @BindView(R.id.stack_view)
    FlippableStackView stackView;

    @BindView(R.id.score_textView)
    TextView mTextView;

    private int currentItem = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        stackView.initStack(4);
        stackView.setOnTouchListener((view, motionEvent) -> true);
        //noinspection unchecked
        stackView.setAdapter(new QuizQuestionsAdapter((ArrayList<Question>) getIntent().getSerializableExtra("questions"), this, this));
    }

    @Override
    public void scrollToNextItem(int score) {
        mTextView.setText(score + " Points");
        if (currentItem == 0) {
            Dialogs.getScoreDialog(score, this, view -> finish()).show();
            return;
        }
        currentItem--;
        stackView.setCurrentItem(currentItem, true);
    }
}
