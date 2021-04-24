package com.example.projetodesensores.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.projetodesensores.R
import com.example.projetodesensores.databinding.FragmentHomefragmentBinding


class homefragment : Fragment() {
    lateinit var binding: FragmentHomefragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_homefragment,container,false)

        binding.buttonAcelerometro.setOnClickListener(){
            Navigation.findNavController(it).navigate(homefragmentDirections.actionHomefragmentToAcelerometroFragment())
        }
        binding.buttonLuz.setOnClickListener() {
            Navigation.findNavController(it).navigate(homefragmentDirections.actionHomefragmentToLuzfragment())
        }
        binding.buttonProximidade.setOnClickListener(){
            Navigation.findNavController(it).navigate(homefragmentDirections.actionHomefragmentToProximidadeFragment())
        }

        binding.buttonGisroscopio.setOnClickListener(){
            Navigation.findNavController(it).navigate(homefragmentDirections.actionHomefragmentToGiroscopioFragment())
        }







        setHasOptionsMenu(true);

        return binding.root
    }



}