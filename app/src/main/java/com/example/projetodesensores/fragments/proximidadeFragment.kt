package com.example.projetodesensores.fragments

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.projetodesensores.R
import com.example.projetodesensores.databinding.FragmentProximidadeBinding


class proximidadeFragment : Fragment(), SensorEventListener {
    lateinit var binding: FragmentProximidadeBinding
    lateinit var SM: SensorManager
    var sensor: Sensor? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_proximidade,container,false)
        setHasOptionsMenu(true)
        ativarSensor()

        return binding.root
    }
    private fun ativarSensor(){
        SM = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = SM.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_PROXIMITY){
            val distance = event.values[0];
            binding.proximidadeValor.text = "Distancia = ${distance.toInt()}";

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        sensor?.also { proximity ->
            SM.registerListener(this, proximity, SensorManager.SENSOR_DELAY_FASTEST)
        }

    }

    override fun onPause() {
        super.onPause()
        SM.unregisterListener(this);
    }



}