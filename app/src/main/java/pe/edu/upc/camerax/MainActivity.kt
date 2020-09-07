package pe.edu.upc.camerax

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Permission
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    var imageCapture: ImageCapture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Verificar permisos
        if (allPermissonsGranted()) {
            //Inicializar la cámara
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISONS,
                REQUEST_CODE_PERMISSION
            )
        }

        btCapture.setOnClickListener {
            takePhoto()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (allPermissonsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    this,
                    "Permisos no otorgados por el usuario",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
    }

    private fun takePhoto() {

    }

    private fun startCamera() {

    }

    //Método que verifica si se otorgaron los permisos
    private fun allPermissonsGranted(): Boolean = REQUIRED_PERMISONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        val REQUIRED_PERMISONS = arrayOf(android.Manifest.permission.CAMERA)
        val REQUEST_CODE_PERMISSION = 10
    }
}