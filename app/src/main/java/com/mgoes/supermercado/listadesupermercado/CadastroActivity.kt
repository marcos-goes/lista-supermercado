package com.mgoes.supermercado.listadesupermercado

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    val COD_IMAGE = 563

    var imageBitmap: Bitmap? = null

    //var defaultCameraBitmap: Bitmap = BitmapFactory.decodeResource(this.getResources(), android.R.drawable.ic_menu_camera)


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK){

            if(data != null){
                // Lendo URI com imagem e jogando numa Stream
                val inputStream = contentResolver.openInputStream(data.data)

                // Transformando em bitmap
                imageBitmap = BitmapFactory.decodeStream(inputStream)

                // Exibindo no aplicativo
                img_foto_produto.setImageBitmap(imageBitmap)

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Implementacao listener do Button
        btn_inserir.setOnClickListener {

            // Pegando o valor digitado pelo usuario
            val nome = txt_produto.text.toString()
            val qtd = txt_qtd.text.toString()
            val valor = txt_valor.text.toString()

            if(nome.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()){
                // Enviando o item para a lista
                //produtosAdapter.add(produto

                val produto = Produto(nome, qtd.toInt(), valor.toDouble(), imageBitmap)
                produtosGlobal.add(produto)

                // Limpando os Edits
                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()
//                img_foto_produto.setImageBitmap(defaultCameraBitmap)
                img_foto_produto.setImageBitmap(null)

            } else
                txt_produto.error = "Preencha todos os campos."
        }


        // Implementacao listener da Image
        img_foto_produto.setOnClickListener {
            abrirGaleria()
        }
    }


    fun abrirGaleria(){
        // Definindo a acao de conteudo
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        // Definindo filtro para imagens
        intent.type = "image/*"

        // Inicializando a Activity com Resultado
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), COD_IMAGE)
    }

}
