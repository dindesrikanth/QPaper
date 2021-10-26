package com.example.questionpaper.Screens.mytest.review;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.questionpaper.Common.CustomAdapter;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.R;
import com.example.questionpaper.Requests.MyTests.review.ObjectionsData;

import java.util.ArrayList;
import java.util.List;

public class ObjectionScreenDialogFragment extends DialogFragment {

    EditText edtDescription;
    Button btnSubmit;

    Spinner spnIssueWith,spnObjection;
    int selectedObjectionId = -1,selectedIssueId = -1;
    private ProgressDialog pDialog;

    String[] issuesList;
    String[] objectionsList;
    String questionLocalId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.objection_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pDialog=Utility.getProgressDialog(getActivity());
        spnIssueWith= view.findViewById(R.id.spnIssueWith);
        spnObjection= view.findViewById(R.id.spnObjection);
        edtDescription=view.findViewById(R.id.edtDescription);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        questionLocalId= getArguments().getString("questionLocalId");

        getIssuesList();
        getObjectionsList();

        setIssues();
        setObjections();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getActivity(),selectedIssueId+"\n"+selectedObjectionId,Toast.LENGTH_LONG).show();
                 getUserInfoData();
            }
        });
    }

    private void setIssues() {
        CustomAdapter customAdapter=new CustomAdapter(getContext(), issuesList);
        spnIssueWith.setAdapter(customAdapter);

        spnIssueWith.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                getIssuesId(issuesList[position]+"");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // nothing
            }
        });
    }

    private void setObjections(){
        CustomAdapter customAdapter=new CustomAdapter(getContext(),objectionsList);
        spnObjection.setAdapter(customAdapter);
        spnObjection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                getObjectionsId(objectionsList[position]+"");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // nothing
            }
        });
    }

    private void getIssuesId(String issue){
        for (IssueWithModel model:getIssuesList()){
            if(model.getIssueWith().equalsIgnoreCase(issue)){
                selectedIssueId = model.getId();
            }
        }
    }

    private void getObjectionsId(String objection){
        for (ObjectionsModel model:getObjectionsList()){
            if(model.getObjection().equalsIgnoreCase(objection)){
                selectedObjectionId = model.getObjectionId();
            }
        }
    }

    private void getUserInfoData(){
       // pDialog.show();
        String userId = Utility.getUserIdFromSharedPref(getContext());
       // long questionLocalId,long testId,String userId
        for (ObjectionsData data : Utility.objectionsList){
            if(data.getQuestionLocalId() == Long.parseLong(questionLocalId)){
                Utility.objectionsList.remove(data);
                break;
            }
        }

        ObjectionsData reviewRequest=new ObjectionsData(selectedIssueId,edtDescription.getEditableText().toString(),selectedObjectionId, Long.parseLong(questionLocalId),2L,userId);
        Utility.objectionsList.add(reviewRequest);
        this.dismiss();
    }

    private List<ObjectionsModel> getObjectionsList(){

        List<ObjectionsModel> objectionsModels=new ArrayList<>();
        objectionsModels.clear();

        ObjectionsModel objModel1= new ObjectionsModel();
        objModel1.setObjectionId(11);
        objModel1.setObjection("Question is incorrect");
        objectionsModels.add(objModel1);

        ObjectionsModel objModel2= new ObjectionsModel();
        objModel2.setObjectionId(12);
        objModel2.setObjection("Question is incomplete");
        objectionsModels.add(objModel2);

        ObjectionsModel objModel3= new ObjectionsModel();
        objModel3.setObjectionId(21);
        objModel3.setObjection("Answer is incorrect");
        objectionsModels.add(objModel3);

        ObjectionsModel objModel4= new ObjectionsModel();
        objModel4.setObjectionId(22);
        objModel4.setObjection("Answer not available in Options");
        objectionsModels.add(objModel4);

        ObjectionsModel objModel5= new ObjectionsModel();
        objModel5.setObjectionId(31);
        objModel5.setObjection("Explanation is incorrect");
        objectionsModels.add(objModel5);

        ObjectionsModel objModel6= new ObjectionsModel();
        objModel6.setObjectionId(99);
        objModel6.setObjection("Others");
        objectionsModels.add(objModel6);

        objectionsList = new String[]{"Question is incorrect","Question is incomplete","Answer is incorrect","Answer not available in Options","Explanation is incorrect","Others"};

        return objectionsModels;
    }
    private List<IssueWithModel> getIssuesList(){

        List<IssueWithModel> listData = new ArrayList<>();
        listData.clear();
        IssueWithModel model1 = new IssueWithModel();
        model1.setId(1);
        model1.setIssueWith("Question");
        listData.add(model1);

        IssueWithModel model2 = new IssueWithModel();
        model2.setId(2);
        model2.setIssueWith("Answer");
        listData.add(model2);

        IssueWithModel model3 = new IssueWithModel();
        model3.setId(3);
        model3.setIssueWith("Explanation");
        listData.add(model3);

        issuesList = new String[]{"Question","Answer","Explanation"};
        return listData;
    }

}
