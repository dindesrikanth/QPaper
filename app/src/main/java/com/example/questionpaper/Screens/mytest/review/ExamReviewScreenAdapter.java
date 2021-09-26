package com.example.questionpaper.Screens.mytest.review;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.Review.ExamReviews;

import java.util.List;
public class ExamReviewScreenAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    List<ExamReviews> examReviewsList;
    ObjectionInterface objectionInterface;

    public ExamReviewScreenAdapter(Context context, List<ExamReviews> examReviewsList,
                                   ObjectionInterface objectionInterface) {
        this.context = context;
        this.examReviewsList = examReviewsList;
        this.objectionInterface = objectionInterface;
    }

    @Override
    public int getCount() {
        return examReviewsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.exam_review_screen_fragment_adapter, null);

        TextView tvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
        TextView tvOptionA = (TextView) view.findViewById(R.id.tvOptionA);
        TextView tvOptionB = (TextView) view.findViewById(R.id.tvOptionB);
        TextView tvOptionC = (TextView) view.findViewById(R.id.tvOptionC);
        TextView tvOptionD = (TextView) view.findViewById(R.id.tvOptionD);
        TextView tvOptionE = (TextView) view.findViewById(R.id.tvOptionE);
        TextView tvExplanation = (TextView) view.findViewById(R.id.tvExplanation);
        ImageView imgObjection=(ImageView)view.findViewById(R.id.imgObjection);

        tvQuestion.setText(examReviewsList.get(position).getQuestionLocalId()+". "+examReviewsList.get(position).getQuestionDetails());

        tvOptionA.setText("A: "+examReviewsList.get(position).getOptionA());
        tvOptionB.setText("B: "+examReviewsList.get(position).getOptionB());
        tvOptionC.setText("C: "+examReviewsList.get(position).getOptionC());
        tvOptionD.setText("D: "+examReviewsList.get(position).getOptionD());
        tvOptionE.setText("E: "+examReviewsList.get(position).getOptionE());
        tvExplanation.setText(examReviewsList.get(position).getQuestionExplanation());

        if(!TextUtils.isEmpty(examReviewsList.get(position).getRightAns()) && !TextUtils.isEmpty(examReviewsList.get(position).getUserAns())){
            String rightAnswer = examReviewsList.get(position).getRightAns().trim();
            String userAnswer = examReviewsList.get(position).getUserAns().trim();

            if(userAnswer.equalsIgnoreCase(rightAnswer)){
                if(userAnswer.equalsIgnoreCase("A")){
                    setRightBackground(tvOptionA);
                }else if(userAnswer.equalsIgnoreCase("B")){
                    setRightBackground(tvOptionB);
                }else if(userAnswer.equalsIgnoreCase("C")){
                    setRightBackground(tvOptionC);
                }else if(userAnswer.equalsIgnoreCase("D")){
                    setRightBackground(tvOptionD);
                }else if(userAnswer.equalsIgnoreCase("E")){
                    setRightBackground(tvOptionE);
                }
            }else{
                if(rightAnswer.equalsIgnoreCase("A")){
                    setRightBackground(tvOptionA);
                }else if(rightAnswer.equalsIgnoreCase("B")){
                    setRightBackground(tvOptionB);
                }else if(rightAnswer.equalsIgnoreCase("C")){
                    setRightBackground(tvOptionC);
                }else if(rightAnswer.equalsIgnoreCase("D")){
                    setRightBackground(tvOptionD);
                }else if(rightAnswer.equalsIgnoreCase("E")){
                    setRightBackground(tvOptionE);
                }

                if(userAnswer.equalsIgnoreCase("A")){
                    setWrongBackground(tvOptionA);
                }else if(userAnswer.equalsIgnoreCase("B")){
                    setWrongBackground(tvOptionB);
                }else if(userAnswer.equalsIgnoreCase("C")){
                    setWrongBackground(tvOptionC);
                }else if(userAnswer.equalsIgnoreCase("D")){
                    setWrongBackground(tvOptionD);
                }else if(userAnswer.equalsIgnoreCase("E")){
                    setWrongBackground(tvOptionE);
                }
            }
        }

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);


        imgObjection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectionInterface.onObjectionItemClicked(position);
            }
        });


        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    private void setRightBackground(TextView tvOptions){
        tvOptions.setBackgroundResource(R.drawable.right_ans_border);
        tvOptions.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_correct, 0);
    }
    private void setWrongBackground(TextView tvOptions){
        tvOptions.setBackgroundResource(R.drawable.wrong_ans_border);
        tvOptions.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close, 0);
    }


    interface ObjectionInterface{
        void onObjectionItemClicked(int position);
    }
}