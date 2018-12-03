package com.magamine.qcm.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.magamine.qcm.Classes.QuestionModel;
import com.magamine.qcm.LoginActivity;
import com.magamine.qcm.MainActivity;
import com.magamine.qcm.MyAppSingleton;
import com.magamine.qcm.R;
import com.magamine.qcm.ResultActivity;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Question.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question extends AbstractFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_QUESTION = "QUESTION";
    private static final String ARG_IMAGE = "IMAGE";
    private static final String ARG_ANSWERS = "ANSWERS";
    private static final String ARG_CORRECT = "CORRECT";

    // TODO: Rename and change types of parameters
    private QuestionModel mQuestion;

    private ImageView imgQuest;
    private TextView txtQuest;

    private RadioGroup radioGroup;
    private RadioButton checkedVal;
    private RadioButton rb1,rb2,rb3,rb4;

    //private OnFragmentInteractionListener mListener;

    public Question() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question.
     */
    // TODO: Rename and change types and number of parameters
    public static Question newInstance(String question, int img, String[] answers,int correctId) {
        Question fragment = new Question();
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION, question);
        args.putInt(ARG_IMAGE,img);
        args.putStringArray(ARG_ANSWERS,answers);
        args.putInt(ARG_CORRECT, correctId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuestion = new QuestionModel();
            mQuestion.question = getArguments().getString(ARG_QUESTION);
            mQuestion.imgResId = getArguments().getInt(ARG_IMAGE);
            mQuestion.answers = getArguments().getStringArray(ARG_ANSWERS);
            mQuestion.correctId = getArguments().getInt(ARG_CORRECT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_question, container, false);
        imgQuest = v.findViewById(R.id.imgQuestion);
        imgQuest.setImageDrawable(getResources().getDrawable(mQuestion.imgResId));
        txtQuest = v.findViewById(R.id.txtQuestion);
        txtQuest.setText(mQuestion.question);
        rb1 = v.findViewById(R.id.rbRep1);
        rb2 = v.findViewById(R.id.rbRep2);
        rb3 = v.findViewById(R.id.rbRep3);
        rb4 = v.findViewById(R.id.rbRep4);
        rb1.setText(mQuestion.answers[0]);
        rb2.setText(mQuestion.answers[1]);
        rb3.setText(mQuestion.answers[2]);
        rb4.setText(mQuestion.answers[3]);
        radioGroup = v.findViewById(R.id.rgReponses);
        return v;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if(radioGroup.getCheckedRadioButtonId() == -1)
            return new VerificationError("Selectionnez une reponse.");
        else{
            checkedVal = Question.this.getView().findViewById(radioGroup.getCheckedRadioButtonId());
            return null;
        }
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {

        if(checkedVal.getText().toString().equals(mQuestion.answers[mQuestion.correctId]))
            MyAppSingleton.playerScore++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //you can do anythings you want
                callback.goToNextStep();

            }
        }, 3L);// delay open another fragment,

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        if(checkedVal.getText().toString().equals(mQuestion.answers[mQuestion.correctId]))
            MyAppSingleton.playerScore++;
        showResults();// delay open another fragment,
    }
    private void showResults(){
        /*AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setTitle("Vos resultats")
                .setMessage("Votre score est de "+MyAppSingleton.playerScore+".")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();*/
        Intent intent = new Intent(getActivity(), ResultActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        MyAppSingleton.playerScore--;
        callback.goToPrevStep();
    }
}
