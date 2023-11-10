package com.example.prctica2.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prctica2.databinding.FragmentDashboardBinding;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private EditText txt_Rut, txt_Usuario, txt_Edad;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txt_Rut = binding.txtRut;
        txt_Usuario = binding.txtUsuario;
        txt_Edad = binding.txtEdad;

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarUsuario();
            }
        });

        binding.btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUsuario();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void guardarUsuario() {

        if (txt_Rut.getText().toString().equals("") || txt_Usuario.getText().toString().equals("") ||
                txt_Edad.getText().toString().equals("")) {
            String mensaje = "Todos los campos son obligatorios.";
            Toast.makeText(this.getContext(), mensaje, Toast.LENGTH_LONG).show();
            return;
        }
        usuarioSharedPreferences();

        String mensaje = "Datos agregados con éxito.";
        Toast.makeText(this.getContext(), mensaje, Toast.LENGTH_LONG).show();
    }

    public void buscarUsuario() {

        if (txt_Rut.getText().toString().equals("")) {
            String mensaje = "Ingrese el rut para buscar datos.";
            Toast.makeText(this.getContext(), mensaje, Toast.LENGTH_LONG).show();
            return;
        }

        SharedPreferences pref = getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);


        String usuario = "usuario_" + txt_Rut.getText().toString();
        String edad = "edad_" + txt_Rut.getText().toString();

        txt_Usuario.setText(pref.getString(usuario, ""));
        txt_Edad.setText(pref.getString(edad, ""));
    }

    public void usuarioSharedPreferences() {
        SharedPreferences pref = getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        String usuario = "usuario_" + txt_Rut.getText().toString();
        String edad = "edad_" + txt_Rut.getText().toString();
        edt.putString(usuario, txt_Usuario.getText().toString());
        edt.putString(edad, txt_Edad.getText().toString());
        edt.apply();
    }
}