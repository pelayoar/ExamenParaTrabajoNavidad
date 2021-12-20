package com.example.alonso_pelayo_examen1tdual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import com.example.alonso_pelayo_examen1tdual.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    var jugador = 2
        set(value) {
            field = value
            binding.layout.setBackgroundResource(if (value == 1) R.color.amarilloExamen else R.color.naranjaExamen)
        }
    var nivel = 1
    var pulsacionesRestantes = 1
    var puntosJugador1 = 0
    var puntosJugador2 = 0


    var pulsaciones1 = 0
    var pulsaciones2 = 0
    var pulsaciones3 = 0
    var pulsaciones4 = 0

    var aciertos1 = 0
    var aciertos2 = 0
    var aciertos3 = 0
    var aciertos4 = 0

    var estado =
        0 //0 pantalla principal | 1 pantalla jugador que pulsa | 2 pantalla jugador que adivina


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        var texto: String

        binding.buttonNuevaRonda.setOnClickListener {
            binding.textLevel.text = "Nivel $nivel"

            if (jugador == 1) {

                binding.layout.setBackgroundResource(R.color.amarilloExamen)
            } else if (jugador == 2) {
                pulsacionesRestantes = nivel
                binding.layout.setBackgroundResource(R.color.naranjaExamen)

            }

            binding.buttonNuevaRonda.visibility = INVISIBLE
            binding.buttonOK.visibility = VISIBLE
            binding.textIntentos.visibility = VISIBLE
            binding.textIntentos.text = "Jugador $jugador, haz hasta $nivel clicks"

            binding.pulsar1.visibility = VISIBLE
            binding.pulsar2.visibility = VISIBLE
            binding.pulsar3.visibility = VISIBLE
            binding.pulsar4.visibility = VISIBLE

            estado = 1



            binding.pulsar1.setOnClickListener {
                pulsaciones1++
                if (pulsaciones1 + pulsaciones2 + pulsaciones3 + pulsaciones4 == nivel) alerta()
            }
            binding.pulsar2.setOnClickListener {
                if (pulsacionesRestantes > 1) {
                    pulsaciones2++
                    pulsacionesRestantes--
                } else bloqueoBotones()

            }
            binding.pulsar3.setOnClickListener {
                if (pulsacionesRestantes > 0) {
                    pulsaciones3++
                    pulsacionesRestantes--
                }

            }
            binding.pulsar4.setOnClickListener {
                if (pulsacionesRestantes > 0) {
                    pulsaciones4++
                    pulsacionesRestantes--
                }

            }


        }

        binding.buttonOK.setOnClickListener {
            if (estado == 1) {
                estado = 2
                cambiaJugador()

                jugador = if (jugador == 1) {
                    binding.layout.setBackgroundResource(R.color.naranjaExamen)
                    2
                } else {
                    binding.layout.setBackgroundResource(R.color.amarilloExamen)
                    1
                }
                binding.contador1.visibility = VISIBLE
                binding.contador2.visibility = VISIBLE
                binding.contador3.visibility = VISIBLE
                binding.contador4.visibility = VISIBLE

                binding.textIntentos.text = "Jugador $jugador: REPITE PULSACIONES"

                binding.pulsar1.setOnClickListener {
                    aciertos1++
                    binding.contador1.text = aciertos1.toString()
                }

                binding.pulsar2.setOnClickListener {
                    aciertos2++
                    binding.contador2.text = aciertos2.toString()
                }

                binding.pulsar3.setOnClickListener {
                    aciertos3++
                    binding.contador3.text = aciertos3.toString()
                }

                binding.pulsar4.setOnClickListener {
                    aciertos4++
                    binding.contador4.text = aciertos4.toString()
                }


            } else {
                binding.buttonNuevaRonda.visibility = VISIBLE
                binding.buttonOK.visibility = INVISIBLE
                binding.textIntentos.visibility = INVISIBLE

                binding.contador1.visibility = INVISIBLE
                binding.contador2.visibility = INVISIBLE
                binding.contador3.visibility = INVISIBLE
                binding.contador4.visibility = INVISIBLE
                binding.pulsar1.visibility = INVISIBLE
                binding.pulsar2.visibility = INVISIBLE
                binding.pulsar3.visibility = INVISIBLE
                binding.pulsar4.visibility = INVISIBLE

                if (!(pulsaciones1 == aciertos1 && pulsaciones2 == aciertos2 && pulsaciones3 == aciertos3 && pulsaciones4 == aciertos4)) {
                    Toast.makeText(this, "El jugador $jugador ha fallado", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    terminarRonda()
                }



                binding.layout.setBackgroundResource(R.color.teal_200)
                nivel++

                binding.contador1.text = "0"
                binding.contador2.text = "0"
                binding.contador3.text = "0"
                binding.contador4.text = "0"

                pulsaciones1 = 0
                pulsaciones2 = 0
                pulsaciones3 = 0
                pulsaciones4 = 0

                aciertos1 = 0
                aciertos2 = 0
                aciertos3 = 0
                aciertos4 = 0


            }
        }


    }

    private fun terminarRonda() {
        Toast.makeText(this, "El jugador $jugador ha acertado", Toast.LENGTH_SHORT).show()
        if (jugador == 1) {
            puntosJugador2 += nivel
            binding.textJugador2Puntos.text = "Jugador 2: $puntosJugador2"
        } else {
            puntosJugador1 += nivel
            binding.textJugadorPuntos.text = "Jugador $jugador: $puntosJugador1"
        }
    }


}