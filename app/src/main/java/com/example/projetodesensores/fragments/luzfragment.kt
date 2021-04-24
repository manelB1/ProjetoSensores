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
import com.example.projetodesensores.databinding.FragmentLuzfragmentBinding



class luzfragment : Fragment(), SensorEventListener {
    lateinit var binding: FragmentLuzfragmentBinding
    lateinit var SM: SensorManager
    var luz: Sensor? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_luzfragment,container,false)
        setHasOptionsMenu(true)
        ativarSensor()



        return binding.root
    }
    private fun ativarSensor(){
        SM = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        luz = SM.getDefaultSensor(Sensor.TYPE_LIGHT)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type==Sensor.TYPE_LIGHT){
            val luzes = event.values[0]
            binding.luzTexto.text = "Sensor: $luzes\n${(luzes)}"
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()

        SM.registerListener(this, luz, SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onPause() {
        super.onPause()
        SM.unregisterListener(this)
    }


}