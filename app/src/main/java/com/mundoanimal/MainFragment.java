package com.mundoanimal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class MainFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureButton();
    }

    private void configureButton() {
        Button btnAfrica = getView().findViewById(R.id.btnAfrica);
        btnAfrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("continente","africa");
                Navigation.findNavController(v).navigate(R.id.action_MainFragment_to_AnimalFragment, bundle);
            }
        });

        Button btnAsia = getView().findViewById(R.id.btnAsia);
        btnAsia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("continente","asia");
                Navigation.findNavController(v).navigate(R.id.action_MainFragment_to_AnimalFragment, bundle);
            }
        });

        Button btnAmerica = getView().findViewById(R.id.btnAmerica);
        btnAmerica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("continente","america");
                Navigation.findNavController(v).navigate(R.id.action_MainFragment_to_AnimalFragment, bundle);
            }
        });
    }
}
