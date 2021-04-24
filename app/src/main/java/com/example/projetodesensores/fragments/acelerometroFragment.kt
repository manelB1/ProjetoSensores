package com.example.projetodesensores.fragments

import android.content.Context.SENSOR_SERVICE
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
import com.example.projetodesensores.databinding.FragmentAcelerometroBinding

class acelerometroFragment : Fragment(), SensorEventListener {
    lateinit var binding: FragmentAcelerometroBinding
    lateinit var SM: SensorManager
    var sensor: Sensor? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_acelerometro,container,false)

        ativarSensor()

        return binding.root
    }

    private fun ativarSensor(){
        SM = activity?.getSystemService(SENSOR_SERVICE) as SensorManager

        SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            SM.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val valorx = event.values[0]
            val valory = event.values[1]

            binding.acelerometroValor.text = "Valor Y: ${valory.toInt()}\nValor X: ${valorx.toInt()}"
    }


    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        SM.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    override fun onPause() {
        super.onPause()
        SM.unregisterListener(this);
    }

}