package hamza.quizmaker.view.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hamza.quizmaker.R;
import hamza.quizmaker.api.MyRequests;
import hamza.quizmaker.models.Category;
import hamza.quizmaker.models.Question;
import hamza.quizmaker.parsers.QuizParser;
import hamza.quizmaker.utils.Dialogs;
import hamza.quizmaker.view.QuizActivity;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private ArrayList<Category> categories;
    private Activity mActivity;
    private Dialog mLoadingDialog;

    public HomeAdapter(Activity mActivity) {
        this.mActivity = mActivity;
        categories = new ArrayList<>();
        categories.add(new Category("1", "Sport", R.mipmap.football));
        categories.add(new Category("2", "Technology", R.mipmap.desktop));
        categories.add(new Category("3", "Maths", R.mipmap.calculation));
        categories.add(new Category("4", "Music", R.mipmap.acoustic_guitar));
        mLoadingDialog = Dialogs.getLoadingDialog(mActivity);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        int screenWidth = getScreenWidth(mActivity);
        itemView.getLayoutParams().width = screenWidth / 2;
        itemView.getLayoutParams().height = screenWidth / 2;
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.categoryTitleTextView.setText(categories.get(position).getCategoryName());
        holder.categoryImageView.setImageResource(categories.get(position).getCategoryImageResource());
        holder.cardView.setOnClickListener(view -> {
            mLoadingDialog.show();
            MyRequests.getQuizzes(String.valueOf(position + 1), mActivity, response -> {
                mLoadingDialog.dismiss();
                ArrayList<Question> questions = QuizParser.ParseQuiz(response);
                Intent intent = new Intent(mActivity, QuizActivity.class);
                intent.putExtra("questions", questions);
                mActivity.startActivity(intent);
            }, error -> {
                mLoadingDialog.dismiss();
                Toast.makeText(mActivity, "Error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            });
        });


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImageView;
        TextView categoryTitleTextView;
        CardView cardView;
        View itemView;

        MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            categoryImageView = itemView.findViewById(R.id.category_imageView);
            categoryTitleTextView = itemView.findViewById(R.id.category_title_textView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    private int getScreenWidth(Activity mActivity) {
        WindowManager windowManager = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        Display mDisplay = windowManager.getDefaultDisplay();
        Point size = new Point();
        mDisplay.getSize(size);
        return size.x;
    }
}
