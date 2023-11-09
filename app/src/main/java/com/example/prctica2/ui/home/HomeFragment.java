package com.example.prctica2.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prctica2.R;
import com.example.prctica2.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private ListView lista_Consola;

    private EditText txt_Nombre, txt_Descripcion;

    private ArrayList<String> listaNombres = new ArrayList<>();
    private ArrayList<String> listaDescripciones = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        txt_Nombre = binding.txtNombre;
        txt_Descripcion = binding.txtDescripcion;

        binding.btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregar();
            }
        });

        return root;
    }

    public void agregar() {
        lista_Consola = binding.listaConsolas;

        if (txt_Nombre.getText().toString().equals("") || txt_Descripcion.getText().toString().equals("")){
            String mensaje = "Todos los campos son obligatorios.";
            Toast.makeText(this.getContext(), mensaje, Toast.LENGTH_LONG).show();
            return;
        }

        listaNombres.add(txt_Nombre.getText().toString());
        listaDescripciones.add(txt_Descripcion.getText().toString());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.item_list, listaNombres);

        lista_Consola.setAdapter(adapter);

        String mensaje = "Consola agregada.";
        Toast.makeText(this.getContext(), mensaje, Toast.LENGTH_LONG).show();

        final Context context = this.getContext();
        lista_Consola.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String mensaje = "Nombre: " + lista_Consola.getItemAtPosition(i) + ". Descripción: "
                        + listaDescripciones.get(i);
                Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}