package com.example.projetodesensores.fragments

import android.content.Context
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
import com.example.projetodesensores.databinding.FragmentGiroscopioBinding


class giroscopioFragment : Fragment(), SensorEventListener {
    lateinit var binding: FragmentGiroscopioBinding
    lateinit var SM: SensorManager



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_giroscopio,container,false)
        setHasOptionsMenu(true)
        ativarSensor()


        return binding.root
    }
    private fun ativarSensor(){
        SM = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE)?.also { accelerometer ->
            SM.registerListener(
                    this,
                    accelerometer,
                    SensorManager.SENSOR_DELAY_FASTEST,
                    SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_GYROSCOPE){
            var valueZ = event.values[2]
            binding.giroscopioValor.text = "Valor: ${valueZ}"
    }


        }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }



}






