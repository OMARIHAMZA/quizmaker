package hamza.quizmaker.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import hamza.quizmaker.R;
import hamza.quizmaker.models.Question;
import hamza.quizmaker.utils.ScrollViewPager;

public class QuizQuestionsAdapter extends PagerAdapter {

    private ArrayList<Question> questions;
    private Activity mActivity;
    private ScrollViewPager scrollViewPager;
    private int score;

    public QuizQuestionsAdapter(ArrayList<Question> questions, Activity mActivity, ScrollViewPager scrollViewPager) {
        this.questions = questions;
        this.mActivity = mActivity;
        this.scrollViewPager = scrollViewPager;
        Collections.reverse(this.questions);
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TextView questionTitleTextView;
        Button choiceOneButton, choiceTwoButton, choiceThreeButton, choiceFourButton;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.question_layout, container, false);
        questionTitleTextView = itemView.findViewById(R.id.question_title_textView);
        choiceOneButton = itemView.findViewById(R.id.choice_one);
        choiceTwoButton = itemView.findViewById(R.id.choice_two);
        choiceThreeButton = itemView.findViewById(R.id.choice_three);
        choiceFourButton = itemView.findViewById(R.id.choice_four);

        questionTitleTextView.setText(questions.get(position).getQuestionTitle());
        choiceOneButton.setText(questions.get(position).getChoices().get(0));
        choiceTwoButton.setText(questions.get(position).getChoices().get(1));
        choiceThreeButton.setText(questions.get(position).getChoices().get(2));
        choiceFourButton.setText(questions.get(position).getChoices().get(3));

        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(choiceOneButton);
        buttons.add(choiceTwoButton);
        buttons.add(choiceThreeButton);
        buttons.add(choiceFourButton);
        for (int i = 0; i < buttons.size(); i++) {
            if ((i + 1) == questions.get(position).getCorrectAnswerIndex()) {
                buttons.get(i).setOnClickListener(view -> {
                    score += 1;
                    scrollViewPager.scrollToNextItem(score);
                });
            } else {
                buttons.get(i).setOnClickListener(view -> {
                    scrollViewPager.scrollToNextItem(score);
                });
            }
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // Remove viewpager_item.xml from ViewPager
        container.removeView((LinearLayout) object);

    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
