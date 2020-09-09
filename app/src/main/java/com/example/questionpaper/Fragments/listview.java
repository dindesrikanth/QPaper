package com.example.questionpaper.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Activity.MainActivity;
import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.questionpaper.Activity.MainActivity.userList;


public class listview extends Fragment implements View.OnClickListener,RecylerViewAdapter.OnItemClickListenerbtn {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    static List<Questionesmodel> answersListt = new ArrayList<>();

    private FragmentAListener listener;
    //    RecyclerView recyclerView;
    RecylerViewAdapter recyclerViewAdapter;

    public static void Markattempt(List<Questionesmodel> answersList) {
//        answersListt=answersList;
    }

    public static void Markunattempt(ArrayList<Integer> mExampleList) {


    }


    @Override
    public void quesclick(String productname) {
        Log.d("checkid",productname);
        ((MainActivity)getActivity()).gotoQuestion(productname);
    }


    public interface FragmentAListener {
        void onInputASent(int input);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.fragment_listview, container, false);
        RecyclerView recyclerView = (RecyclerView) myview.findViewById(R.id.grid_recyclerView);
        recyclerViewAdapter = new RecylerViewAdapter(userList, getContext());
        GridLayoutManager GridLayoutManager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(GridLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
//need to
        return myview;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener) {
            listener = (FragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

//get a call back and update position to onItemclick from recylerview adapter
    void onItemClick(int pos){
        //((MainActivity)getActivity()).gotoQuestion(pos);
    }

    @Override
    public void onClick(View v) {
        //getActivity().onBackPressed();
    }

}
