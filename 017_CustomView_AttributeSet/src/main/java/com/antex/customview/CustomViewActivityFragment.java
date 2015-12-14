package com.antex.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class CustomViewActivityFragment extends Fragment {
    private int Themes[] = {R.style
            .AppTheme_WithCustomizeStyle_Activity_WithValues_withCustomizeStyle, R.style
            .AppTheme_WithCustomizeStyle_Activity_WithValues, R.style
            .AppTheme_WithCustomizeStyle_Activity_NoValues, R.style
            .AppTheme_NoCustomizeStyle_Activity_NoValues};

    public CustomViewActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        Intent intent = getActivity().getIntent();
        int checkedID = intent.getIntExtra("checkedID", 0);
        getActivity().setTheme(Themes[checkedID]);
        View view = inflater.inflate(R.layout.fragment_custom_view, container, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);

        CustomTextView customTextView1 = new CustomTextView(getActivity());

        customTextView1.setText("New CustomTextView");

        linearLayout.addView(customTextView1, 0, new ViewGroup.LayoutParams(ViewGroup
                .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radiaGroup);
        ((RadioButton) radioGroup.getChildAt(checkedID)).setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = 0;
                switch (checkedId) {
                    case R.id.rb1:
                        id = 0;
                        break;
                    case R.id.rb2:
                        id = 1;
                        break;
                    case R.id.rb3:
                        id = 2;
                        break;
                    case R.id.rb4:
                        id = 3;
                        break;
                    default:
                        break;

                }

                startActivity(new Intent(getActivity(), CustomViewActivity.class).putExtra
                        ("checkedID", id));
                getActivity().finish();
            }
        });

        return view;
    }

}
