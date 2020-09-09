package com.example.questionpaper.Screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import butterknife.ButterKnife;
//import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
   // private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=createRootView(inflater,container);
        //unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }

    private View createRootView(LayoutInflater inflater , ViewGroup container){
        return inflater.inflate(getLayout(),container,false);
    }

    protected abstract int getLayout();
    protected abstract void initialiseView(View view);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            initialiseView(view);
        }catch (Exception e){

        }
    }
}
